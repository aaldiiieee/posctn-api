package posctn.posctn_api.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import posctn.posctn_api.dto.request.KasbonCreateRequestDto;
import posctn.posctn_api.dto.response.TransactionResponseDto;
import posctn.posctn_api.enums.PaymentStatusEnum;
import posctn.posctn_api.enums.PaymentTypeEnum;
import posctn.posctn_api.exception.NotFoundException;
import posctn.posctn_api.mapper.TransactionMapper;
import posctn.posctn_api.model.*;
import posctn.posctn_api.repository.CustomerRepository;
import posctn.posctn_api.repository.DebtRepository;
import posctn.posctn_api.repository.MenuItemRepository;
import posctn.posctn_api.repository.TransactionRepository;
import posctn.posctn_api.security.AuthenticatedUserProvider;
import posctn.posctn_api.service.TransactionService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final CustomerRepository customerRepository;
    private final TransactionRepository transactionRepository;
    private final DebtRepository debtRepository;
    private final TransactionMapper transactionMapper;
    private final AuthenticatedUserProvider authenticatedUserProvider;
    private final MenuItemRepository menuItemRepository;

    public TransactionServiceImpl(CustomerRepository customerRepository,
                                  TransactionRepository transactionRepository,
                                  DebtRepository debtRepository,
                                  TransactionMapper transactionMapper,
                                  AuthenticatedUserProvider authenticatedUserProvider,
                                  MenuItemRepository menuItemRepository) {
        this.customerRepository = customerRepository;
        this.transactionRepository = transactionRepository;
        this.debtRepository = debtRepository;
        this.transactionMapper = transactionMapper;
        this.authenticatedUserProvider = authenticatedUserProvider;
        this.menuItemRepository = menuItemRepository;
    }

    @Override
    @Transactional
    public TransactionResponseDto createKasbon(KasbonCreateRequestDto request) {
        UserModel currentUser = authenticatedUserProvider.getCurrentUser();

        // Find customer if not any, create new customer
        CustomerModel customer = customerRepository.findByName(request.getCustomerName())
                .orElseGet(() -> {
                   CustomerModel newCustomer = new CustomerModel();
                   newCustomer.setName(request.getCustomerName());
                   return customerRepository.save(newCustomer);
                });

        List<TransactionItemModel> items = new ArrayList<>();
        BigDecimal totalAmount = BigDecimal.ZERO;

        for (KasbonCreateRequestDto.ItemRequest item : request.getItems()) {
            MenuItemModel menuItem = menuItemRepository.findById(item.getMenuItemId())
                    .orElseThrow(() -> new NotFoundException("Menu tidak ditemukan"));

            BigDecimal subtotal = menuItem.getPrice()
                    .multiply(BigDecimal.valueOf(item.getQty()));

            TransactionItemModel transactionItem = new TransactionItemModel();
            transactionItem.setMenuItem(menuItem);
            transactionItem.setQty(item.getQty());
            transactionItem.setPrice(menuItem.getPrice());
            transactionItem.setSubtotal(subtotal);

            items.add(transactionItem);
            totalAmount = totalAmount.add(subtotal);
        }

        TransactionModel transaction = new TransactionModel();
        transaction.setCustomer(customer);
        transaction.setCreatedBy(currentUser);
        transaction.setPaymentType(PaymentTypeEnum.KASBON);
        transaction.setPaymentStatus(PaymentStatusEnum.UNPAID);
        transaction.setTotalAmount(totalAmount);

        items.forEach(item -> item.setTransaction(transaction));
        transaction.setItems(items);

        TransactionModel savedTransaction = transactionRepository.save(transaction);

        DebtModel debt = new DebtModel();
        debt.setCustomer(customer);
        debt.setTransaction(savedTransaction);
        debt.setAmount(totalAmount);
        debt.setRemainingAmount(totalAmount);
        debt.setStatus(PaymentStatusEnum.UNPAID);

        debtRepository.save(debt);

        return transactionMapper.toDto(savedTransaction);
    }
}

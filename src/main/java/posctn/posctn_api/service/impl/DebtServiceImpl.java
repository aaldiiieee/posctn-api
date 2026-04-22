package posctn.posctn_api.service.impl;

import org.springframework.stereotype.Service;
import posctn.posctn_api.dto.response.DebtResponseDto;
import posctn.posctn_api.dto.response.DebtSummaryResponseDto;
import posctn.posctn_api.mapper.DebtMapper;
import posctn.posctn_api.model.DebtModel;
import posctn.posctn_api.repository.DebtRepository;
import posctn.posctn_api.service.DebtService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DebtServiceImpl implements DebtService {

    private final DebtRepository debtRepository;
    private final DebtMapper debtMapper;

    public DebtServiceImpl(DebtRepository debtRepository, DebtMapper debtMapper) {
        this.debtRepository = debtRepository;
        this.debtMapper = debtMapper;
    }

    @Override
    public List<DebtResponseDto> getAllDebts() {
        return debtRepository.findAllWithDetails().stream()
                .map(debtMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<DebtSummaryResponseDto> getAllDebtSummary() {
        List<DebtModel> debts = debtRepository.findAllWithDetails();

        // Grouping by customerId
        Map<Long, List<DebtModel>> groupedByCustomer = debts.stream()
                .collect(Collectors.groupingBy(d -> d.getCustomer().getId()));

        return groupedByCustomer.entrySet().stream()
                .map(entry -> {
                    List<DebtModel> customerDebts = entry.getValue();
                    DebtModel firstDebt = customerDebts.get(0);

                    BigDecimal totalDebt = customerDebts.stream()
                            .map(DebtModel::getAmount)
                            .reduce(BigDecimal.ZERO, BigDecimal::add);

                    BigDecimal totalRemaining = customerDebts.stream()
                            .map(DebtModel::getRemainingAmount)
                            .reduce(BigDecimal.ZERO, BigDecimal::add);

                    return DebtSummaryResponseDto.builder()
                            .customerId(firstDebt.getCustomer().getId())
                            .customerName(firstDebt.getCustomer().getName())
                            .totalDebt(totalDebt)
                            .totalRemaining(totalRemaining)
                            .totalTransaction(customerDebts.size())
                            .debts(customerDebts.stream().map(debtMapper::toDto).collect(Collectors.toList()))
                            .build();
                })
                .collect(Collectors.toList());
    }
}

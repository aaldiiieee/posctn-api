package posctn.posctn_api.mapper;

import org.springframework.stereotype.Component;
import posctn.posctn_api.dto.TransactionItemDto;
import posctn.posctn_api.dto.response.TransactionResponseDto;
import posctn.posctn_api.model.TransactionItemModel;
import posctn.posctn_api.model.TransactionModel;

import java.util.List;

@Component
public class TransactionMapper {

    private final CustomerMapper customerMapper;

    public TransactionMapper(CustomerMapper customerMapper) {
        this.customerMapper = customerMapper;
    }

    public TransactionResponseDto toDto(TransactionModel trx) {
        return TransactionResponseDto.builder()
                .id(trx.getId())
                .transactionDate(trx.getTransactionDate())
                .totalAmount(trx.getTotalAmount())
                .paymentType(trx.getPaymentType())
                .paymentStatus(trx.getPaymentStatus())
                .customer(customerMapper.toDto(trx.getCustomer()))
                .items(trx.getItems() != null
                        ? trx.getItems().stream()
                            .map(this::mapItem)
                            .toList()
                        : List.of()
                )
                .build();
    }

    private TransactionItemDto mapItem(TransactionItemModel item) {
        return TransactionItemDto.builder()
                .price(item.getPrice())
                .qty(item.getQty())
                .subtotal(item.getSubtotal())
                .menuName(item.getMenuItem() != null ? item.getMenuItem().getName() : null)
                .build();
    }
}

package posctn.posctn_api.dto.response;

import lombok.*;
import posctn.posctn_api.dto.CustomerDto;
import posctn.posctn_api.dto.TransactionItemDto;
import posctn.posctn_api.enums.PaymentStatusEnum;
import posctn.posctn_api.enums.PaymentTypeEnum;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionResponseDto {

    private Long id;
    private LocalDateTime transactionDate;
    private BigDecimal totalAmount;

    private PaymentTypeEnum paymentType;
    private PaymentStatusEnum paymentStatus;

    private CustomerDto customer;
    private List<TransactionItemDto> items;
}

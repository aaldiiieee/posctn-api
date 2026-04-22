package posctn.posctn_api.dto.response;

import lombok.*;
import posctn.posctn_api.enums.PaymentStatusEnum;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DebtResponseDto {
    private Long id;
    private String customerName;
    private Long transactionId;
    private BigDecimal amount;
    private BigDecimal remainingAmount;
    private PaymentStatusEnum status;
    private LocalDateTime createdAt;
    private List<DebtPaymentResponseDto> payments;
}
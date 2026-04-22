package posctn.posctn_api.dto.response;

import lombok.*;
import posctn.posctn_api.enums.PaymentStatusEnum;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DebtSummaryItemResponseDto {
    private Long id;
    private Long transactionId;
    private BigDecimal amount;
    private BigDecimal remainingAmount;
    private PaymentStatusEnum status;
    private LocalDateTime createdAt;
}

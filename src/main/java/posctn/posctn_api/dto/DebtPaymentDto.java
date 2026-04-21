package posctn.posctn_api.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class DebtPaymentDto {
    private BigDecimal amount;
    private LocalDateTime paymentDate;
}

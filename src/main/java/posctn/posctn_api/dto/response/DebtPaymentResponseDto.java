package posctn.posctn_api.dto.response;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DebtPaymentResponseDto {
    private Long id;
    private BigDecimal amount;
    private LocalDateTime paymentDate;
}
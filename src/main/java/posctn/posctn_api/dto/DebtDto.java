package posctn.posctn_api.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class DebtDto {
    private Long id;
    private BigDecimal amount;
    private BigDecimal remainingAmount;
    private String status;

    private List<DebtPaymentDto> payments;
}

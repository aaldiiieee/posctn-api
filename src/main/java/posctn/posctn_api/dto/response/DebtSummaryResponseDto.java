package posctn.posctn_api.dto.response;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DebtSummaryResponseDto {
    private Long customerId;
    private String customerName;
    private BigDecimal totalDebt;
    private BigDecimal totalRemaining;
    private int totalTransaction;
    private List<DebtSummaryItemResponseDto> debts;
}

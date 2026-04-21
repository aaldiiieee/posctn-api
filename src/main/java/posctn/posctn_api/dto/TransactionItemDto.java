package posctn.posctn_api.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionItemDto {
    private String menuName;
    private BigDecimal price;
    private Integer qty;
    private BigDecimal subtotal;
}

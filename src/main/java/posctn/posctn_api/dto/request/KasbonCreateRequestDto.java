package posctn.posctn_api.dto.request;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KasbonCreateRequestDto {
    private String customerName;
    private String phone;
    private List<ItemRequest> items;

    @Getter
    @Setter
    public static class ItemRequest {
        private Long menuItemId;
        private Integer qty;
    }
}

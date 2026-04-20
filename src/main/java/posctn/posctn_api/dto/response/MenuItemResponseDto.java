package posctn.posctn_api.dto.response;

import lombok.*;
import posctn.posctn_api.enums.MenuCategoryEnum;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuItemResponseDto {
    public Long id;
    public String name;
    public BigDecimal price;
    public MenuCategoryEnum category;
    public boolean active;
    public LocalDateTime createdAt;
}

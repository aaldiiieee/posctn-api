package posctn.posctn_api.dto.request;

import lombok.*;
import posctn.posctn_api.enums.MenuCategoryEnum;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MenuItemListRequestDto {
    private MenuCategoryEnum category;
    private Boolean active;
    private String search;
    private int page = 0;
    private int size = 10;
}

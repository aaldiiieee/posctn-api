package posctn.posctn_api.dto.request;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import posctn.posctn_api.enums.MenuCategoryEnum;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuItemRequestDto {

    @NotBlank(message = "Nama menu tidak boleh kosong")
    public String name;

    @NotBlank(message = "Harga menu tidak boleh kosong")
    @Digits(integer = 10, fraction = 2, message = "Format harga harus maksimal 10 digit dan 2 desimal")
    public BigDecimal price;

    public MenuCategoryEnum category;

    public boolean active;
}

package posctn.posctn_api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import posctn.posctn_api.enums.RoleEnum;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequestDto {

    @NotBlank(message = "Nama lengkap wajib diisi")
    public String fullname;

    @NotBlank(message = "Username wajib diisi")
    @Size(min = 4, max = 20, message = "Username wajib sekiranya diisi 4-20 karakter")
    @Pattern(regexp = "^\\S*$", message = "Username tidak boleh mengandung spasi")
    public String username;

    @NotBlank(message = "Password wajib diisi")
    @Size(min = 6, message = "Password minimal 6 karakter")

    private String password;

    public RoleEnum role;

    public boolean active;
}

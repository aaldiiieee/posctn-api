package posctn.posctn_api.dto.response;

import lombok.*;
import posctn.posctn_api.enums.RoleEnum;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseDto {
    public Long id;
    public String fullname;
    public String username;
    public RoleEnum role;
    public boolean active;
    public LocalDateTime createdAt;
}

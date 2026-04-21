package posctn.posctn_api.model;

import jakarta.persistence.*;
import lombok.*;
import posctn.posctn_api.enums.RoleEnum;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullname;

    private String username;

    private String password;

    @Enumerated(EnumType.STRING)
    private RoleEnum role;

    @Column(name = "is_active", nullable = false)
    private boolean isActive = true;

    private LocalDateTime createdAt;

    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}

package posctn.posctn_api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import posctn.posctn_api.enums.RoleEnum;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Data
@RequiredArgsConstructor
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

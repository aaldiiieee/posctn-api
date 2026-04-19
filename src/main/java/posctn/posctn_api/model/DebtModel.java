package posctn.posctn_api.model;

import jakarta.persistence.*;
import lombok.*;
import posctn.posctn_api.enums.DebtStatusEnum;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "debts")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DebtModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal amount;

    private BigDecimal remainingAmount;

    @Enumerated(EnumType.STRING)
    private DebtStatusEnum status;

    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerModel customer;

    @OneToOne
    @JoinColumn(name = "transaction_id")
    private TransactionModel transaction;

    @OneToMany(mappedBy = "debt", cascade = CascadeType.ALL)
    private List<DebtPaymentModel> payments;
}

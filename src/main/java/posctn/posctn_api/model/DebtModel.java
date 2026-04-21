package posctn.posctn_api.model;

import jakarta.persistence.*;
import lombok.*;
import posctn.posctn_api.enums.PaymentStatusEnum;

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
    private PaymentStatusEnum status;

    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private CustomerModel customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transaction_id", nullable = false)
    private TransactionModel transaction;

    @OneToMany(mappedBy = "debt", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<DebtPaymentModel> payments;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        if (this.remainingAmount == null) {
            this.remainingAmount = this.amount;
        }
        if (this.status == null) {
            this.status = PaymentStatusEnum.UNPAID;
        }
    }
}

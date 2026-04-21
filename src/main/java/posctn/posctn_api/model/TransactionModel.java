package posctn.posctn_api.model;

import jakarta.persistence.*;
import lombok.*;
import posctn.posctn_api.enums.PaymentStatusEnum;
import posctn.posctn_api.enums.PaymentTypeEnum;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "transactions")
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal totalAmount;

    @Enumerated(EnumType.STRING)
    private PaymentTypeEnum paymentType;

    @Enumerated(EnumType.STRING)
    private PaymentStatusEnum paymentStatus;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private UserModel createdBy;

    private LocalDateTime transactionDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private CustomerModel customer;

    @OneToMany(mappedBy = "transaction", cascade = CascadeType.ALL)
    private List<TransactionItemModel> items;

    @PrePersist
    public void onCreate() {
        this.transactionDate = LocalDateTime.now();
    }
}

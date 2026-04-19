package posctn.posctn_api.model;

import jakarta.persistence.*;
import lombok.*;
import posctn.posctn_api.enums.PaymentTypeEnum;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "transactions")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime transactionDate;

    private BigDecimal totalAmount;

    @Enumerated(EnumType.STRING)
    private PaymentTypeEnum paymentType;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private UserModel createdBy;

    @OneToMany(mappedBy = "transaction", cascade = CascadeType.ALL)
    private List<TransactionItemModel> items;
}

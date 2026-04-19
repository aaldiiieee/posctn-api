package posctn.posctn_api.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Table(name = "transaction_items")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionItemModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer qty;

    private BigDecimal price;

    private BigDecimal subtotal;

    @ManyToOne
    @JoinColumn(name = "transaction_id")
    private TransactionModel transaction;

    @ManyToOne
    @JoinColumn(name = "menu_item_id")
    private MenuItemModel menuItem;
}

package than.MK.weblaptop.backend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "detail_of_order")
public class DetailOfOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "detail_of_order_id")
    private long detailOfOrdersID;
    @Column(name = "total_amount")
    private int totalAmount;
    @ManyToOne(cascade = {
            CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH
    })
    @JoinColumn(name = "laptop_id", nullable = false)
    private Laptop laptop;
    @ManyToOne(cascade = {
            CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH
    })
    @JoinColumn(name = "orders_id", nullable = false)
    private Orders order;
}

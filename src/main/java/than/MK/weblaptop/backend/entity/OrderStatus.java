package than.MK.weblaptop.backend.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "order_status")
public class OrderStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_status_id")
    private int orderStatusID;
    @Column(name = "order_status_name")
    private String orderStatusName;

    @OneToMany(mappedBy = "orderStatus", fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH
    })
    private List<Orders> listOrder;
}

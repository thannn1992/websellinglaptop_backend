package than.MK.weblaptop.backend.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "delivery_method")
public class DeliveryMethod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "delivery_method_id")
    private int deliveryMethodID;
    @Column(name = "delivery_method_name")
    private String deliveryMethodName;
    @Column(name = "description")
    private String description;
    @Column(name = "fee_of_payment")
    private double feeOfPayment;

    @OneToMany(mappedBy = "deliveryMethod", fetch = FetchType.LAZY, cascade = {
                    CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH
    })
    private List<Orders> orders;
}

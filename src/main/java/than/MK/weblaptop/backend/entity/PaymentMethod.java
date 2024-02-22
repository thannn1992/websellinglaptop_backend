package than.MK.weblaptop.backend.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "payment_method")
public class PaymentMethod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private int paymentID;
    @Column(name = "payment_method_name")
    private String paymentMethodName;
    @Column(name = "description")
    private String description;
    @Column(name = "fee_of_payment")
    private double feeOfPayment;
    @OneToMany(mappedBy = "paymentMethod", fetch = FetchType.LAZY, cascade = {
                    CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH
    })
    private List<Orders> orders;
}

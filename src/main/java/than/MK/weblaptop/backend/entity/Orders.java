package than.MK.weblaptop.backend.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.util.List;

@Data
@Entity
@Table(name = "orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private int orderID;
    @Column(name = "order_purchase_address")
    private String orderPurchaseAddress;
    @Column(name = "receive_date")
    private Date receiveDate;
    @Column(name = "order_date")
    private Date  orderDate;
    @Column(name = "province")
    private String province;
    @Column(name = "district")
    private String district;
    @Column(name = "commune")
    private String commune;
    @Column(name = "stress_name_and_house_number")
    private String stressNameAndHouseNumber;
    @Column(name = "total_amount")
    private double totalAmount;

    @ManyToOne(cascade = {
                    CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH
    })
    @JoinColumn(name = "user_id", nullable = true)
    private User user;

    @ManyToOne(cascade = {
            CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH
    })
    @JoinColumn(name = "order_status_id", nullable = false)
    private OrderStatus orderStatus;

    @ManyToOne(cascade = {
            CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH
    })
    @JoinColumn(name = "payment_method_id", nullable = false)
    private PaymentMethod paymentMethod;

    @ManyToOne(cascade = {
            CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH
    })
    @JoinColumn(name = "delivery_method_id", nullable = false)
    private DeliveryMethod deliveryMethod;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<DetailOfOrder> listDetailOfOrders;
}

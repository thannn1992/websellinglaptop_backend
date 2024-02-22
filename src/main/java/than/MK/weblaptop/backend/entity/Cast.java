package than.MK.weblaptop.backend.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.util.List;

@Entity
@Data
@Table(name = "cast")
public class Cast {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cast_id")
    private int castID;
    @Column(name = "create_date")
    private Date createDate;
    @Column(name = "purchase-address")
    private String purchaseAddress;
    @Column(name = "delivery_address")
    private String deliveryAddress;
    @Column(name = "total_price")
    private double totalPrice;
    @Column(name = "total_amount")
    private double totalAmount;
    @OneToMany(mappedBy = "cast",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<ItemsInCast> listItemsInCast;
    @ManyToOne(cascade = {
                    CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH
            })
    @JoinColumn(name = "user_id")
    private User user;

}

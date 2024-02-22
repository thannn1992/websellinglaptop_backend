package than.MK.weblaptop.backend.entity;

import jakarta.persistence.*;
import lombok.Data;
@Entity
@Data
@Table(name = "items_in_cast")
public class ItemsInCast {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "items_in_cast_id")
    private int itemsInCastID;
    @Column(name = "quantities_items")
    private int quantitiesItems;
    @Column(name = "selling_price")
    private double sellingPrice;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cast_id")
    private Cast cast;
    @ManyToOne(cascade = {
            CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH
    })
    @JoinColumn(name = "laptop_id")
    private Laptop laptop;

}

package than.MK.weblaptop.backend.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "brand")
public class Brand {
    @Id
    //Tự động tăng
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "brand_id")
    private int brandID;
    @Column(name = "brand_name", length = 256)
    private String brandName;
    @OneToMany(mappedBy = "brand", fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH
    })
    private List<Laptop> laptops;
    @OneToMany(mappedBy = "brand", fetch = FetchType.LAZY, cascade = {
            CascadeType.ALL
    })
    private List<Model> models;
}
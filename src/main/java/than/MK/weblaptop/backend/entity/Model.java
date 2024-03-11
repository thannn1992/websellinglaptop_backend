package than.MK.weblaptop.backend.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "model")
public class Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "model_id")
    private int modelID;

    @Column(name = "model_name")
    private String modelName;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @OneToMany(mappedBy = "model", fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH
    })
    private List<Laptop> laptops;
}

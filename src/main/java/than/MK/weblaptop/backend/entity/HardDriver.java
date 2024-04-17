package than.MK.weblaptop.backend.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "hard_driver")
public class HardDriver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hard_driver_id")
    private int hardDriverID;
    @Column(name = "hard_driver_name")
    private String hardDriverName;
    @Column(name = "hard_driver_type")
    private String hardDriverType;
    @ManyToMany
    @JoinTable(
            name = "laptop_hard_driver",
            joinColumns = @JoinColumn(name = "hard_driver_id"),
            inverseJoinColumns = @JoinColumn(name = "laptop_id"))
    List<Laptop> laptops;

}

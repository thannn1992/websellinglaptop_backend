package than.MK.weblaptop.backend.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "graphics_card")
public class GraphicsCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "graphics_card_id")
    private int graphicsCardID;
    @Column(name = "graphics_card_name")
    private String graphicsCardName;
    @Column(name = "graphics_card_type")
    private String graphicsCardType;

    @ManyToMany
    @JoinTable(
            name = "laptop_graphics_card",
            joinColumns = @JoinColumn(name = "graphics_card_id"),
            inverseJoinColumns = @JoinColumn(name = "laptop_id"))
    private List<Laptop> laptops;

}

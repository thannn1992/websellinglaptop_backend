package than.MK.weblaptop.backend.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "screen_resolution")
public class ScreenResolution {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "screen_resolution_id")
    private int screenResolutionID;
    @Column(name = "screen_resolution_name")
    private String screenResolutionName;
    @Column(name = "color_accuracy")
    private String colorAccuracy;

    @OneToMany(mappedBy = "screenResolution", fetch = FetchType.LAZY, cascade = {
                    CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    private List<Laptop> laptops;

}

package than.MK.weblaptop.backend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "picture")
public class Picture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "picture_id")
    private int pictureID;
    @Column(name = "icons")
    private boolean icons;
    @Column(name = "picture_name")
    private String pictureName;
    @Column(name = "picture_link")
    private String pictureLink;
    @Column(name = "picture_data", columnDefinition = "LONGTEXT")
    private String pictureData;
    @ManyToOne //(cascade = {
            //CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.REMOVE
    //})
    @JoinColumn(name = "laptop_id", nullable = false)
    private Laptop laptop;
}

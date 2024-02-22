package than.MK.weblaptop.backend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private int reviewID;
    @Column(name = "rating")
    private float rating;
    @Column(name = "comment")
    private String comment;

    @ManyToOne(cascade = {
                    CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "laptop_id", nullable = false)
    private Laptop laptop;

    @ManyToOne(cascade = {
            CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}

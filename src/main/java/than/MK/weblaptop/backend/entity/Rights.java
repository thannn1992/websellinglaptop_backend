package than.MK.weblaptop.backend.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "rights")
public class Rights {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "right_id")
    private int rightID;
    @Column(name = "right_name")
    private String rightName;
    @Column(name = "right_description")
    private String rightDescription;

   @ManyToMany(fetch = FetchType.LAZY, cascade = {
           CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH
   })
   @JoinTable(
           name = "user_right",
           joinColumns = @JoinColumn(name = "right_id"),
           inverseJoinColumns = @JoinColumn(name = "user_id")
   )
    private List<User> user;
}

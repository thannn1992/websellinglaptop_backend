package than.MK.weblaptop.backend.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "processor")
public class Processor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "processor_id")
    private int processorID;
    @Column(name = "processor_name")
    private String processorName;
    @Column(name = "max_turbo_frequency")
    private String maxTurboFrequency;
    @Column(name = "cache")
    private String cache;

    @OneToMany(mappedBy = "processor", fetch = FetchType.LAZY, cascade = {
         CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH
    })
    private List<Laptop> laptops;

}

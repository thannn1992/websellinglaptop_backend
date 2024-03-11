package than.MK.weblaptop.backend.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "laptop")
public class Laptop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "laptop_id")
    private int laptopID;
    @Column(name = "laptop_name", length = 255)
    private String laptopName;
    @Column(name = "laptop_quantities")
    private int laptopQuantities;
    @Column(name = "describer", columnDefinition = "text")
    private String describer;
    @Column(name = "import_price")
    private double importPrice;
    @Column(name = "listed_price")
    private double listedPrice;
    @Column(name = "selling_price")
    private double sellingPrice;
    @Column(name = "random_memory")
    private String randomMemory;
    @Column(name = "upgrade_ability_ram")
    private String upgradeAbilityRAM;
    @Column(name = "weigh")
    private String weigh;
    @Column(name = "colour")
    private String colour;
    @Column(name = "dimension")
    private String dimension;
    @Column(name = "bluetooth")
    private String bluetooth;
    @Column(name = "port")
    private String port;
    @Column(name = "pin")
    private String pin;
    @Column(name = "upgrade_ability_disk_drive")
    private String upgradeAbilityDiskDrive;
    @Column(name = "webcam")
    private String webCam;
    @Column(name = "operating_system")
    private String operatingSystem;
    @Column(name = "display_size")
    private String displaySize;
    @Column(name="coating")
    private String coating;

    @ManyToOne(cascade = {
            CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @ManyToOne(cascade = {
            CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "model_id")
    private Model model;

    @ManyToMany( fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(
            name = "laptop_graphics_card",
            joinColumns = @JoinColumn(name = "laptop_id"),
            inverseJoinColumns = @JoinColumn(name = "graphics_card_id"))
    private List<GraphicsCard> listGraphicsCard;

    @ManyToMany( fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH
    })
    @JoinTable(
            name = "laptop_hard_driver",
            joinColumns = @JoinColumn(name = "laptop_id"),
            inverseJoinColumns = @JoinColumn(name = "hard_driver_id"))
    private List<HardDriver> listHardDriver;

    @ManyToOne(cascade = {
            CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "processor_id")
    private Processor processor;

    @OneToMany(mappedBy = "laptop", fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    private List<ItemsInCast> listItemsInCast;

    @OneToMany(mappedBy = "laptop", fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    private List<Picture> listPictures;
    @OneToMany(mappedBy = "laptop", fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    private List<Review> listReview;

    @ManyToOne(cascade = {
            CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "screen_resolution_id")
    private ScreenResolution screenResolution;


}

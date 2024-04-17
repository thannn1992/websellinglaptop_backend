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
    private String webcam;
    @Column(name = "operating_system")
    private String operatingSystem;
    @Column(name = "display_size")
    private String displaySize;
    @Column(name = "coating")
    private String coating;

    @Column(name = "audio_technology")
    private String audioTechnology;

    @Column(name = "sd_micro_sd_card_slot")
    private String sdMicroSdCardSlot;

    @Column(name = "wifi")
    private String wifi;

    @Column(name = "ltd_wwan_connection")
    private String lteWwanConnection;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @ManyToOne
    //No cascade here //No cascade here as when laptop update brand don't update, if have cascade will have error: detached entity passed to persist
    @JoinColumn(name = "model_id")
    private Model model;

    @ManyToMany(  cascade = {
            CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.REMOVE
    })
    @JoinTable(
            name = "laptop_graphics_card",
            joinColumns = @JoinColumn(name = "laptop_id"),
            inverseJoinColumns = @JoinColumn(name = "graphics_card_id"))
    private List<GraphicsCard> listGraphicsCard;

    @ManyToMany (  cascade = {
          CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.REMOVE
   })
    @JoinTable(
            name = "laptop_hard_driver",
            joinColumns = @JoinColumn(name = "laptop_id"),
            inverseJoinColumns = @JoinColumn(name = "hard_driver_id"))
    private List<HardDriver> listHardDriver;

    @ManyToOne
    //No cascade here as when laptop update brand don't update, if have cascade will have error: detached entity passed to persist
    @JoinColumn(name = "processor_id")
    private Processor processor;

    @OneToMany(mappedBy = "laptop", cascade = {
            CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    private List<ItemsInCast> listItemsInCast;

    @OneToMany(mappedBy = "laptop", cascade = {
            CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.REMOVE})
    private List<Picture> listPictures;

    @OneToMany(mappedBy = "laptop", cascade = {
            CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.REMOVE})
    private List<Review> listReview;

    @ManyToOne
    //No cascade here as when laptop update brand don't update, if have cascade will have error: detached entity passed to persist
    @JoinColumn(name = "screen_resolution_id")
    private ScreenResolution screenResolution;

}

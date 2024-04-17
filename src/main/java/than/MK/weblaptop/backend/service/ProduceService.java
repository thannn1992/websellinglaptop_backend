package than.MK.weblaptop.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import than.MK.weblaptop.backend.dao.BrandRepository;
import than.MK.weblaptop.backend.dao.LaptopRepository;
import than.MK.weblaptop.backend.dao.ModelRepository;
import than.MK.weblaptop.backend.dao.PictureRepository;
import than.MK.weblaptop.backend.entity.*;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProduceService {

    @Autowired
    private LaptopRepository laptopRepository;

    @Autowired
    private PictureRepository pictureRepository;

    @Autowired
    private ModelRepository modelRepository;

    @Autowired
    private BrandRepository brandRepository;

    public ResponseEntity<?> addProduce(Laptop laptop) {

        List<Picture> listPicture = laptop.getListPictures();
        List<HardDriver> listHardDriver = laptop.getListHardDriver();
        List<GraphicsCard> listGraphicsCard = laptop.getListGraphicsCard();

        laptop.setListPictures(null);
        laptop.setListHardDriver(null);
        laptop.setListGraphicsCard(null);

        // Check laptop exist
        if (laptopRepository.existsByLaptopName(laptop.getLaptopName())) {
            return ResponseEntity.badRequest().body(new Inform("Laptop has existed"));
        }
        // Save laptop into database
        Laptop laptopSave = laptopRepository.save(laptop);

        laptopSave.setListHardDriver(listHardDriver);
        laptopSave.setListGraphicsCard(listGraphicsCard);

        Laptop laptopSaveSecond = laptopRepository.save(laptopSave);

        for (int i = 0; i < listPicture.size(); i++) {
            listPicture.get(i).setLaptop(laptopSave);
        }
        pictureRepository.saveAll(listPicture);
        return ResponseEntity.ok(laptopSave.getLaptopID());

    }

}

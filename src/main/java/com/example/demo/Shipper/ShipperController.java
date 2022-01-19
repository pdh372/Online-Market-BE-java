package com.example.demo.Shipper;

import com.example.demo.Area.AreaEntity;
import com.example.demo.Area.AreaRepository;
import com.example.demo.User.ImgCIEntity;
import com.example.demo.ImgLicense.ImgLicenseEntity;
import com.example.demo.Product.ProductEntity;
import com.example.demo.Product.ProductRepository;
import com.example.demo.Request.RegisterShipperRequestEntity;
import com.example.demo.User.UserEntity;
import com.example.demo.User.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/shipper")
public class ShipperController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    ShipperRepository shipperRepository;

    @Autowired
    AreaRepository areaRepository;

    @Autowired
    ProductRepository productRepository;

    @GetMapping("/{userId}")
    public ResponseEntity<?> getShippersByStatusRegister(@PathVariable("userId")  String userId){
        Optional<UserEntity> user =  userRepository.findById(userId);

        List<ShipperEntity> shippers = shipperRepository.findByOwner(userId);

        RegisterShipperRequestEntity temp = new RegisterShipperRequestEntity();

        if(shippers.size() == 1 && user.isPresent()) {
            temp.setShipper(shippers.get(0));
            temp.setUser(user.get());
            return new ResponseEntity<>(temp, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);

    }

    @PostMapping("/registershipper")
    public ResponseEntity<?> RegisterShipper(@RequestBody RegisterShipperRequestEntity regEntity) {
        try {
            UserEntity user = regEntity.getUser();
            AreaEntity area = regEntity.getArea();
            ShipperEntity shipper = regEntity.getShipper();
            ImgCIEntity imgCI = regEntity.getImgCI();
            ImgLicenseEntity imgLicense = regEntity.getImgLicense();
            List<UserEntity> users = userRepository.findByEmail(user.getEmail());

            if (users.isEmpty()) {
                user.setStatus("pending");
                user.setRole("shipper");
                user.setImgCI(imgCI);
                shipper.setImgLicense(imgLicense);

                List<AreaEntity> city = areaRepository.findAreaEntityByCityAndDistrictAndWard(area.getCity(), area.getDistrict(), area.getWard());

                if (city.isEmpty()){
                    return new ResponseEntity<>("Invalid address. Please check again.", HttpStatus.BAD_REQUEST);
                }
                else {
                    ObjectId address = city.get(0).get_id();
                    user.getAddress().setArea(address.toString());
                    userRepository.save(user);

                    String owner = userRepository.findByEmail(user.getEmail()).get(0).get_id();
                    shipper.setOwner(owner);

                    shipperRepository.save(shipper);

                    return new ResponseEntity<>("Registered Successfully.", HttpStatus.CREATED);
                }
            }
            else {
                return new ResponseEntity<>("Email is registered. Please choose another email.", HttpStatus.BAD_REQUEST);
            }
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
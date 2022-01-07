package com.example.demo.Shipper;
import com.example.demo.Area.AreaEntity;
import com.example.demo.Area.AreaRepository;
import com.example.demo.DonHang.OrderEntity;
import com.example.demo.Request.RegisterShipperRequestEntity;
import com.example.demo.User.UserEntity;
import com.example.demo.User.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/Shipper")
public class ShipperController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    ShipperRepository shipperRepository;

    @Autowired
    AreaRepository areaRepository;

    @PostMapping("/RegisterShipper")
    public ResponseEntity<?> RegisterShipper(@RequestBody RegisterShipperRequestEntity regEntity) {
        try {
            UserEntity user = regEntity.getUser();
            AreaEntity area = regEntity.getArea();
            ShipperEntity shipper = regEntity.getShipper();
            List<UserEntity> users = userRepository.findByEmail(user.getEmail());

            if (users.isEmpty()) {
                user.setStatus("pending");
                user.setRole("shipper");

                List<AreaEntity> city = areaRepository.findAreaEntityByCityAndDistrictAndWard(area.getCity(), area.getDistrict(), area.getWard());

                if (city.isEmpty()){
                    return new ResponseEntity<>(users, HttpStatus.INTERNAL_SERVER_ERROR);
                }
                else {
                    ObjectId address = city.get(0).get_id();
                    user.getAddress().setArea(address.toString());
                    userRepository.save(user);

                    ObjectId owner = userRepository.findByEmail(user.getEmail()).get(0).get_id();
                    shipper.setOwner(owner.toString());

                    shipperRepository.save(shipper);

                    return new ResponseEntity<>(shipper, HttpStatus.CREATED);
                }
            }
            else {
                return new ResponseEntity<>(shipper, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
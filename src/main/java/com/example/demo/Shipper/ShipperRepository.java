//package com.example.demo.Shipper;
//
//import org.springframework.data.mongodb.repository.MongoRepository;
//
//public interface ShipperRepository extends MongoRepository<ShipperEntity, String> {
//}

package com.example.demo.Shipper;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShipperRepository extends MongoRepository<ShipperEntity, String> {
    List<ShipperEntity> findByOwner(String userid);
}

package com.example.demo.Shipper;

import com.example.demo.User.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShipperRepository extends MongoRepository<ShipperEntity, String> {

}

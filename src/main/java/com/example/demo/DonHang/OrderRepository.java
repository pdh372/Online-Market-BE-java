package com.example.demo.DonHang;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;


import java.util.List;

public interface OrderRepository extends MongoRepository<OrderEntity, String> {
    List<OrderEntity> findByUserId(ObjectId userId);
    List<OrderEntity> findByShipper(String shipperId);
    List<OrderEntity> findByCurrentStatusAndUserId(String currentStatus, ObjectId userId);
}

package com.example.demo.OrderStatus;

import com.example.demo.DonHang.OrderEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface StatusRepository extends  MongoRepository<StatusHistory, String> {

}



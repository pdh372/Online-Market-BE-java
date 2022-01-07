package com.example.demo.User;

import com.example.demo.DonHang.OrderEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Repository
public interface UserRepository extends MongoRepository<UserEntity, String> {
    List <UserEntity> findByEmail(String email);
}

package com.example.demo.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

//@Repository
public interface UserRepository extends MongoRepository<UserEntity, String> {
    List<UserEntity> findByEmail(String email);
    List<UserEntity> findByStatus(String status);
    List<UserEntity> findUserEntitiesByRoleAndStatus(String role, String status);
}

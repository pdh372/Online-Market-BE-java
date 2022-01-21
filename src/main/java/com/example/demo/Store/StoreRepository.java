package com.example.demo.Store;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface StoreRepository extends MongoRepository<StoreEntity, String> {
}

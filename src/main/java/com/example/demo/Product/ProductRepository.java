package com.example.demo.Product;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<ProductEntity, String> {
    List<ProductEntity> findByNameRegex(String keyWord);
}
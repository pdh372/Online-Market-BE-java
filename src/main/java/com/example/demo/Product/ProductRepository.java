package com.example.demo.Product;

import com.example.demo.DonHang.OrderEntity;
import com.example.demo.NhanVien.NhanVienEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<ProductEntity, String>{
    List<ProductEntity> findByStore(ObjectId store);
}

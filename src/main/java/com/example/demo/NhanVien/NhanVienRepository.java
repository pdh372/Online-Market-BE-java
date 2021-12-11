package com.example.demo.NhanVien;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NhanVienRepository extends MongoRepository<NhanVienEntity, String> {

}
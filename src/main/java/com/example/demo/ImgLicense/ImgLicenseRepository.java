package com.example.demo.ImgLicense;

import com.example.demo.Address.AddressEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImgLicenseRepository extends MongoRepository<ImgLicenseEntity, String> {

}
package com.example.demo.Area;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AreaRepository extends MongoRepository<AreaEntity, String> {
    List<AreaEntity> findByCity(String city);
    List<AreaEntity> findByDistrict(String district);
    List<AreaEntity> findByWard(String ward);
    List<AreaEntity> findAreaEntityByCityAndDistrictAndWard(String city, String district, String ward);
}
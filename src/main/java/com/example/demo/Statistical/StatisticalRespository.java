package com.example.demo.Statistical;

import com.example.demo.User.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatisticalRespository extends MongoRepository<StatisticEntity, String> {

}
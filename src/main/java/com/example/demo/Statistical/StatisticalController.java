package com.example.demo.Statistical;


import com.example.demo.User.UserEntity;
import com.example.demo.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/statisticals")
public class StatisticalController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("buyer")
    public ResponseEntity<List<UserEntity>> getdata(){
        Query query = new Query();

        query.addCriteria(Criteria.where("color").is("ahihi"));

        List<UserEntity> amount = userRepository.findAll();

        StatisticEntity statisticEntity = new StatisticEntity();


        statisticEntity.setName("ahihi");

        return new ResponseEntity<List<UserEntity>>(amount, HttpStatus.OK);
    }
}

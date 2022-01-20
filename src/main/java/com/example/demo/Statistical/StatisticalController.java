package com.example.demo.Statistical;


import com.example.demo.Area.AreaEntity;
import com.example.demo.Area.AreaRepository;
import com.example.demo.User.UserEntity;
import com.example.demo.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    AreaRepository areaRepository;

    @GetMapping("users")
    public ResponseEntity<StatisticEntity> getdata(){

        List<UserEntity> users = userRepository.findAll();

        List<AreaEntity> areas =  areaRepository.findAll();

        StatisticEntity statisticEntity = new StatisticEntity(users, areas);

        return new ResponseEntity<StatisticEntity>(statisticEntity, HttpStatus.OK);
    }
}

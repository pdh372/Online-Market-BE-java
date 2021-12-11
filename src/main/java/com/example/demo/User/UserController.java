package com.example.demo.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/users")
public class UserController {
    @Autowired UserRepository userRepository;

    @GetMapping("")
    public ResponseEntity<?> getListUser(){
//        List<UserEntity> users = UserService.getListUser();
//        System.out.print(users);
        List<UserEntity> users =  userRepository.findAll();
//        return ResponseEntity.ok(users);
        if(users.size() > 0)
            return new ResponseEntity<List<UserEntity>>(users, HttpStatus.OK);
        else
            return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable String id, @RequestParam(required = false) String test){
        Optional<UserEntity> userOptional =  userRepository.findById(id);
        if(userOptional.isPresent())
            return new ResponseEntity<UserEntity>(userOptional.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>("Not Found id: " + id, HttpStatus.NOT_FOUND);

    }


}

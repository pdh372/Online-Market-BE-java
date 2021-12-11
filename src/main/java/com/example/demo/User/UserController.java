package com.example.demo.User;

import com.example.demo.DonHang.OrderEntity;
import com.example.demo.DonHang.OrderRepository;
import com.example.demo.NhanVien.NhanVienEntity;
import com.mongodb.BasicDBObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/users")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    OrderRepository orderRepository;


    @GetMapping("/{userId}/orders")
    public ResponseEntity<?> getOrdersByUser(@PathVariable("userId") String userID){
//        return new ResponseEntity<String>(userID, HttpStatus.OK);
//        Criteria toolQuery = Criteria.where("userID").in(userID);
//        BasicDBObject criteria = new BasicDBObject();
//        criteria.append("userID", userID);
//        Query query = new Query();
//        query.addCriteria(Criteria.where("userID").in(userID));

        List<OrderEntity> orders =  orderRepository.findByUserID(userID);

//        MatchOperation toolMatchOperation = new MatchOperation(toolQuery);
//        LookupOperation lookupOperation = LookupOperation.newLookup().
//                from("users").
//                localField("users._id").
//                foreignField("orders.id").
//                as("usedIn.car");

//        TypedAggregation<OrderEntity> aggregation = Aggregation.newAggregation(OrderEntity.class, toolMatchOperation, Aggregation.unwind("usedIn"), lookupOperation, Aggregation.unwind("usedIn.car"),
//                Aggregation.group("id").push("usedIn").as("usedIn"));

        if(orders.size() > 0)
            return new ResponseEntity<List<OrderEntity>>(orders, HttpStatus.OK);
        else
            return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
    }
}

//    @GetMapping("")
//    public ResponseEntity<?> getListUser(){
////        List<UserEntity> users = UserService.getListUser();
////        System.out.print(users);
//        List<UserEntity> users =  userRepository.findAll();
////        return ResponseEntity.ok(users);
//        if(users.size() > 0)
//            return new ResponseEntity<List<UserEntity>>(users, HttpStatus.OK);
//        else
//            return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<?> getUser(@PathVariable String id, @RequestParam(required = false) String test){
//        Optional<UserEntity> userOptional =  userRepository.findById(id);
//        if(userOptional.isPresent())
//            return new ResponseEntity<UserEntity>(userOptional.get(), HttpStatus.OK);
//        else
//            return new ResponseEntity<>("Not Found id: " + id, HttpStatus.NOT_FOUND);
//
//    }





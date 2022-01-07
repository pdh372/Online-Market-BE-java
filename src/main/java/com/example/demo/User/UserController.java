package com.example.demo.User;

import com.example.demo.Area.AreaEntity;
import com.example.demo.Area.AreaRepository;
import com.example.demo.DonHang.OrderEntity;
import com.example.demo.DonHang.OrderRepository;
import com.example.demo.OrderStatus.StatusHistory;
import com.example.demo.OrderStatus.StatusRepository;
import com.example.demo.OrderStatus.UpdateStatusInput;
import com.example.demo.Shipper.ShipperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    StatusRepository statusRepository;

    @Autowired
    ShipperRepository shipperRepository;

    @Autowired
    AreaRepository areaRepository;


    @GetMapping("/{userId}/orders")
    public ResponseEntity<?> getOrdersByUser(@PathVariable("userId") String userID) {
        List<OrderEntity> orders = orderRepository.findByUserID(userID);

        if (orders.size() > 0)
            return new ResponseEntity<List<OrderEntity>>(orders, HttpStatus.OK);
        else
            return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
    }


    @PutMapping ("/{userID}/orders/{orderID}/status")
    public ResponseEntity<?> UpdateStatus (@PathVariable  String orderID,
                                           @RequestBody UpdateStatusInput status)
    {
//        return new ResponseEntity<Boolean>(status.getName() =="Đã hủy", HttpStatus.OK);
        List<String> validStatus = Arrays.asList(
                "Chờ xác nhận",
                "Đang chuẩn bị hàng",
                "Chờ lấy hàng",
                "Đang giao",
                "Đã giao",
                "Đã hủy");

        Boolean contains = validStatus.contains(status.getName());
        if (contains == false) {
            return new ResponseEntity<String>("Invalid Status Name", HttpStatus.BAD_REQUEST);
        }

        Optional<OrderEntity> order = orderRepository.findById(orderID);

        if (order.isPresent()) {
            OrderEntity resOrder = order.get();
            String currentStatus = resOrder.getCurrentStatus();
            Boolean flag = false;

            //Hủy đơn hàng
            if (Objects.equals(status.getName(), new String("Đã hủy"))) {
                List<String> cancelStatus = Arrays.asList(
                        "Chờ xác nhận",
                        "Đang chuẩn bị hàng");

                flag = cancelStatus.contains(currentStatus);
                if (flag == true) {

                    StatusHistory history = new StatusHistory();

                    history.setOrderID(orderID);
                    history.setStatusName(status.getName());
                    history.setCreatedDate(LocalDateTime.now());
                    statusRepository.save(history);
                    //Insert new status into status history table

                    //Update status in order table
                    resOrder.setCurrentStatus(status.getName());
                    orderRepository.save(resOrder);

                    return new ResponseEntity<OrderEntity>(resOrder, HttpStatus.OK);
                } else {
                    return new ResponseEntity<String>("Can't cancel", HttpStatus.BAD_REQUEST);
                }
            }
            else {
                return new ResponseEntity<String>("Another status", HttpStatus.BAD_REQUEST);
                //Chức năng khác, cập nhật sau
            }
        } else {
            return new ResponseEntity<String>("Not Found id: " + orderID, HttpStatus.NOT_FOUND);
        }

    }

}




//    @DeleteMapping ("/{userID}/order/{orderID}/cancel")
//    public ResponseEntity <?>



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





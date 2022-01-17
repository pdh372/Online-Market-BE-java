package com.example.demo.User;

import com.example.demo.Area.AreaRepository;
import com.example.demo.DonHang.OrderEntity;
import com.example.demo.DonHang.OrderRepository;
import com.example.demo.ImgCI.ImgCIRepository;
import com.example.demo.OrderStatus.StatusHistory;
import com.example.demo.OrderStatus.StatusRepository;
import com.example.demo.OrderStatus.UpdateStatusInput;
import com.example.demo.Shipper.ShipperRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@CrossOrigin
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

    @Autowired
    ImgCIRepository imgCIRepository;


    @GetMapping("/{userId}/orders")
    public ResponseEntity<?> getOrdersByUser(@PathVariable("userId") String userId) {
        List<OrderEntity> orders = orderRepository.findByUserId(new ObjectId(userId));

        if (orders.size() > 0)
            return new ResponseEntity<List<OrderEntity>>(orders, HttpStatus.OK);
        else
            return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
    }


    @PutMapping ("/{userID}/orders/{orderID}/status")
    public ResponseEntity<?> UpdateStatus (@PathVariable  String orderID,
                                           @RequestBody UpdateStatusInput status)
    {
        List<String> validStatus = Arrays.asList(
                "comfirming",
                "preparing",
                "waiting-shipper",
                "delivering",
                "paid",
                "canceled");

        boolean contains = validStatus.contains(status.getName());
        if (!contains) {
            return new ResponseEntity<String>("Invalid Status Name", HttpStatus.BAD_REQUEST);
        }

        Optional<OrderEntity> order = orderRepository.findById(orderID);

        if (order.isPresent()) {
            OrderEntity resOrder = order.get();
            String currentStatus = resOrder.getCurrentStatus();
            boolean flag = false;

            //Hủy đơn hàng
            if (Objects.equals(status.getName(), new String("canceled"))) {
                List<String> cancelStatus = Arrays.asList(
                        "comfirming",
                        "preparing");

                flag = cancelStatus.contains(currentStatus);
                if (flag) {

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

            else if(Objects.equals(status.getName(), new String("delivering")))
            {
                if(Objects.equals(currentStatus, new String("waiting-shipper")))
                {
                    StatusHistory history = new StatusHistory();

                    //Insert new status into status history table
                    history.setOrderID(orderID);
                    history.setStatusName(status.getName());
                    history.setCreatedDate(LocalDateTime.now());
                    statusRepository.save(history);

                    //Update status in order table
                    resOrder.setCurrentStatus(status.getName());
                    orderRepository.save(resOrder);

                    return new ResponseEntity<OrderEntity>(resOrder, HttpStatus.OK);
                }
                else{
                    return new ResponseEntity<String>("Can't ship order", HttpStatus.BAD_REQUEST);
                }
            }
            else {
                return new ResponseEntity<String>("Another status", HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<String>("Not Found id: " + orderID, HttpStatus.NOT_FOUND);
        }

    }

}





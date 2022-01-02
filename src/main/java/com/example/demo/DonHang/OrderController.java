package com.example.demo.DonHang;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "http://localhost:3000")
public class OrderController {
    @Autowired
    OrderRepository orderRepository;

    @GetMapping("filterByStatusAndUserId")
    public ResponseEntity<?> getUsers(HttpServletRequest request){
        try {
            String status = request.getParameter("status");
            String userId = request.getParameter("userId");

            List<OrderEntity> orders;
            if(status == null){
                orders = orderRepository.findByUserId(new ObjectId(userId));
            }
            else {
                orders = orderRepository.findByCurrentStatusAndUserId(status, new ObjectId(userId));
            }

            return new ResponseEntity<>(orders, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

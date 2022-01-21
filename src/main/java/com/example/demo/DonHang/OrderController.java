package com.example.demo.DonHang;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

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

    @PutMapping("/commission/{orderId}")
    public ResponseEntity<?> Commission(@PathVariable("orderId") String orderId) {
        try {
            Optional<OrderEntity> orders = orderRepository.findById(orderId);

            if (orders.isPresent()) {
                OrderEntity order = orders.get();
                List<ProductEntity> products = order.getProducts();
                order.setOrderFee(0);
                order.setShippingFee(3000);
                for (ProductEntity product : products) {
                    order.setOrderFee(order.getOrderFee() + product.getQuantity() * product.getUnitPrice());
                }
                order.setTotal(order.getOrderFee() + order.getShippingFee());
                order.setShipperFee((float) (order.getShippingFee()*0.98));
                order.setProviderFee((float) (order.getOrderFee()*0.95));
                return new ResponseEntity<>(order, HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>("Not exists OrderId", HttpStatus.BAD_REQUEST);
            }
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

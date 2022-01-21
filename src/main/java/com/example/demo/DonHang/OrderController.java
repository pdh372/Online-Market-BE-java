package com.example.demo.DonHang;
//import com.example.demo.Order.ProductOrderEntity;
import com.example.demo.Product.ProductRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "http://localhost:3000")
public class OrderController {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ProductRepository productRepository;

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

    @PostMapping ("/add")
    public ResponseEntity addOrder(@RequestBody ProductEntity orderProductInfo) {

        var newOrder = new OrderEntity();
        newOrder.setProducts(new ArrayList<ProductEntity>());

        var orderDate = LocalDateTime.now();
        var deliveryDate = orderDate.plusDays(4);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss", Locale.ENGLISH);
        var orderDateString = formatter.format(orderDate);
        var deliveryDateString = formatter.format(deliveryDate);


        //Product Entity from Product package
        var productIsExist = productRepository.existsById(orderProductInfo.getProductId());
        if (!productIsExist) {
            return new ResponseEntity<>("ProductId does not exists", HttpStatus.NOT_FOUND);
        }


        newOrder.getProducts().add(orderProductInfo);
        newOrder.setOrderDate(orderDateString);
        newOrder.setDeliveryDate(deliveryDateString);
        newOrder.setUpdatedtime(orderDateString);
        newOrder.setTotal(orderProductInfo.getQuantity() * orderProductInfo.getUnitPrice());
        newOrder.setShippingfee("2000");
        newOrder.setCurrentStatus("paid");

        orderRepository.save(newOrder);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

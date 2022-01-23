package com.example.demo.DonHang;
import com.example.demo.Product.ProductRepository;
import com.example.demo.Product.ProductStoreRequestEntity;
import com.example.demo.Store.StoreEntity;
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
    public ResponseEntity addOrder(@RequestBody OrderEntity newOrder) {
        var orderDate = LocalDateTime.now();
        var deliveryDate = orderDate.plusDays(4);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss", Locale.ENGLISH);
        var orderDateString = formatter.format(orderDate);
        var deliveryDateString = formatter.format(deliveryDate);


//        Product Entity from Product package
        var productIsExist = productRepository.existsById(newOrder.getProducts().get(0).getProductId());
        if (!productIsExist) {
            return new ResponseEntity<>("ProductId does not exists", HttpStatus.NOT_FOUND);
        }

        var currentStatus = "preparing";

       float orderFee = newOrder.getProducts().get(0).getQuantity() * newOrder.getProducts().get(0).getUnitPrice();
       float shippingFee = 15000;

       float total = orderFee + shippingFee;

       float providerFee = orderFee * (float)0.95;
       float shipperFee = shippingFee * (float)0.98;

        newOrder.setOrderDate(orderDateString);
        newOrder.setDeliveryDate(deliveryDateString);
        newOrder.setTotal(total);
        newOrder.setCurrentStatus(currentStatus);
        newOrder.setOrderFee(orderFee);
        newOrder.setShippingFee(shippingFee);
        newOrder.setShipperFee(shipperFee);
        newOrder.setProviderFee(providerFee);
        newOrder.setUpdatedtime(orderDateString);

        orderRepository.save(newOrder);
        return new ResponseEntity<>(HttpStatus.OK);
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

    @GetMapping("shipper/{_id}")
    public ResponseEntity<?> getOrders(@PathVariable("_id") String _id){
        try {
            List<OrderEntity> orders = orderRepository.findByShipper(_id);
            return new ResponseEntity<>(orders, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<?> getProduct(@PathVariable("orderId") String orderId){
        Optional<OrderEntity> order =  orderRepository.findById(orderId);

        if(order.isPresent()) {
            return new ResponseEntity<>(order, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
    }
}

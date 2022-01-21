// ĐỪNG CODE Ở ĐÂY VÔ PACKAGE ĐƠN HÀNG
// package com.example.demo.Order;

import com.example.demo.DonHang.OrderRepository;
import com.example.demo.Product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

//
//import com.example.demo.Area.AreaEntity;
//import com.example.demo.DonHang.ProductEntity;
//import com.example.demo.User.ImgCIEntity;
//import com.example.demo.ImgLicense.ImgLicenseEntity;
//import com.example.demo.Order.ProductOrderEntity;
//import com.example.demo.Request.RegisterShipperRequestEntity;
//import com.example.demo.Shipper.ShipperEntity;
//import com.example.demo.User.UserEntity;
//import org.bson.types.ObjectId;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Optional;
//
//@RestController
//@CrossOrigin
//@RequestMapping("/api/order")
//public class OrderController {
//    @Autowired
//    OrderRepository orderRepository;
//
//    @Autowired
//    ProductRepository productRepository;
//
//
//
//}

//    @PutMapping("/commission/{orderId}")
//    public ResponseEntity<?> Commission(@PathVariable("orderId") String orderId) {
//        try {
//            Optional<OrderEntity> orders = orderRepository.findById(orderId);
//
//            if (orders.isPresent()) {
//                OrderEntity order = orders.get();
//                List<ProductOrderEntity> products = order.getProducts();
//                order.setOrderFee(0);
//                order.setShippingFee(3000);
//                for(int i = 0; i < products.size(); i++){
//                    order.setOrderFee(order.getOrderFee() + products.get(i).getQuantity() * products.get(i).getUnitPrice()) ;
//                }
//                order.setTotal(order.getOrderFee() + order.getShippingFee());
//                order.setShipperFee((float) (order.getShippingFee()*0.98));
//                order.setProviderFee((float) (order.getOrderFee()*0.95));
//                return new ResponseEntity<>(order, HttpStatus.OK);
//            }
//            else {
//                return new ResponseEntity<>("Not exists OrderId", HttpStatus.BAD_REQUEST);
//            }
//        }
//        catch (Exception e) {
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//}

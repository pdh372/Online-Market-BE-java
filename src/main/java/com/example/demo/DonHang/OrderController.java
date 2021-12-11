package com.example.demo.DonHang;
import com.example.demo.NhanVien.NhanVienEntity;
import com.example.demo.NhanVien.NhanVienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/orders")

public class OrderController {
    @Autowired
    OrderRepository orderRepository;

//    @GetMapping("")
//    public ResponseEntity<?> getOrderByUser(String orderID){
//
//        List<NhanVienEntity> nhanViens =  nhanvienRepository.findAll();
//
//        if(nhanViens.size() > 0)
//            return new ResponseEntity<List<NhanVienEntity>>(nhanViens, HttpStatus.OK);
//        else
//            return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
//    }
}

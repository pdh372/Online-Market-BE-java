package com.example.demo.DonHang;

import com.example.demo.User.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;


import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "orders")
public class OrderEntity {
    private String userId;
    private String orderDate;
    private String deliveryDate;
    private float total;
    private List<ProductEntity> products;
    //Chờ xác nhận
    //Chờ lấy hàng
    //Đang giao
    //Đã giao
    //Đã hủy
    private String shippingfee;
    private String updatedtime;
    private String currentStatus;
}

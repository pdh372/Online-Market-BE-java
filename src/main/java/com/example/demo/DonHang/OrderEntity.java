package com.example.demo.DonHang;

import com.example.demo.User.UserEntity;
import lombok.*;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;


import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document(collection = "orders")
public class OrderEntity {
    @Id
    private String _id;

    private ObjectId userId;
    private String orderDate;
    private String deliveryDate;
    private float total;
    private List<ProductEntity> products;
    private String currentStatus;
    private String shipper;
    private String provider;
    private float orderFee;
    private float shippingFee;
    private float shipperFee;
    private float providerFee;
    private String status;
    private String updateTime;
}

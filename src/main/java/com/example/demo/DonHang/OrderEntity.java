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
    @MongoId
    private String id;
    private Date orderDate;
    private Date deliveryDate;
    private float total;
    @DBRef
    private List<ProductEntity> products;
    private String status;
//    @DBRef
    private String userID;
}

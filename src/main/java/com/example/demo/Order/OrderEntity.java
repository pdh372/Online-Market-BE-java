package com.example.demo.Order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "order")
public class OrderEntity {
    private String _id;
    private String customer;
    private String shipper;
    private String provider;
    private String orderDate;
    private String deliveryDate;
    private float total;
    private float orderFee;
    private float shippingFee;
    private float shipperFee;
    private float providerFee;
    private List<ProductOrderEntity> products;
    private String status;
    private String updateTime;
}

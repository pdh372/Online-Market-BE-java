package com.example.demo.DonHang;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;


import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ProductEntity {

    private String productId;
    private  int quantity;
    private float unitPrice;
}

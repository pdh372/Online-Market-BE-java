package com.example.demo.DonHang;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;


import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductEntity {

    private String productId;
    private  int quantity;
    private float unitPrice;
}

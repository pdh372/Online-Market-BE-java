package com.example.demo.DonHang;

import com.example.demo.Product.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductOrderEntity {
    private ProductEntity product;
    private int quantity;
    private float unitPrice;
}

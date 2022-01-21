package com.example.demo.Product;

import com.example.demo.Store.StoreEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductStoreRequestEntity {
    private StoreEntity store;
    private ProductEntity product;
}

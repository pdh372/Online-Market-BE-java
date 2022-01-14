package com.example.demo.Product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "product")
public class ProductEntity {
    private String _id;
    private String store;
    private String category;
    private String name;
    private String description;
    private Float price;
    private String unit;
    private Integer quantity;
    private String image;
}

package com.example.demo.Product;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
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
    private String name;
    private String description;
    private String image;
    private float price;
    private String unit;
    private String category;
    private String store;
    private float quality;
    private String productOrigin;
    private float rating;
}

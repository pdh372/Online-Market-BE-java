package com.example.demo.Store;

import com.example.demo.Area.AddressEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "store")
public class StoreEntity {
    private String owner;
    private String name;
    private String type;
    private AddressEntity address;
    private String licence;
    private String foodSafety;
    private String productOrigin;
}

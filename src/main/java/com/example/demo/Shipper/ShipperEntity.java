package com.example.demo.Shipper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.tomcat.jni.User;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "shipper")
public class ShipperEntity {
    private String owner;
    private float rate;
    private String feedbackNo;
    private String status;
}

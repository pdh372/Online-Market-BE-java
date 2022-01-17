package com.example.demo.ImgLicense;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ImgLicenseEntity {
    private String front;
    private String backside;
}

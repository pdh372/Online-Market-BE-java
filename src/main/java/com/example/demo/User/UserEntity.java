package com.example.demo.User;

import com.example.demo.Area.AddressEntity;
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
@Document(collection = "user")
public class UserEntity {
    private String _id;
    private String name;
    private String ciNum;
    private String email;
    private String dob;
    private String phoneNumber;
    private AddressEntity address;
    private ImgCIEntity imgCI;
    private String password;
    private String role;
    private String status;
    private String bankAccount;
    private float wallet;
    private String registerDate;
}

package com.example.demo.User;

import com.example.demo.Address.AddressEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "users")
public class UserEntity {

    private String _id;
    private String email;
    private String password;
    private String role;
    private String name;
    private AddressEntity address;
    private Date dob;
    private String bankAccount;
    private float wallet;
}

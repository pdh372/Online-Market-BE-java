package com.example.demo.User;

import com.example.demo.Address.AddressEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
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
@Document(collection = "user")
public class UserEntity {
    private ObjectId _id;
    private String name;
    private String ciNum;
    private String email;
    @JsonFormat(pattern="dd-mm-YYYY")
    private Date dob;
    private String phoneNumber;
    private AddressEntity address;
    private String password;
    private String role;
    private String status;
    //private String bankAccount;
    //private float wallet;
}

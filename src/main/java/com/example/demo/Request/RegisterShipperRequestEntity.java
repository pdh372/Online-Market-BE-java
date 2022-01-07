package com.example.demo.Request;

import com.example.demo.Area.AreaEntity;
import com.example.demo.Shipper.ShipperEntity;
import com.example.demo.User.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterShipperRequestEntity {
    private UserEntity user;
    private AreaEntity area;
    private ShipperEntity shipper;
}

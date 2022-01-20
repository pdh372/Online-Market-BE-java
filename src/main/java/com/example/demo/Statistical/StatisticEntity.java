package com.example.demo.Statistical;

import com.example.demo.Area.AreaEntity;
import com.example.demo.User.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "statistics")
public class StatisticEntity {

    @MongoId
    private List<UserEntity> users;
    private List<AreaEntity> areas;
}

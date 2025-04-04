package com.service.mental_health_therapy_center.entity;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
//    @Data
    @Entity
    @Table(name = "user")
public class User {


    @Id
    @Column(name = "user_Id")
    private String id;
    private String name;
    private String password;
    private String role;


}

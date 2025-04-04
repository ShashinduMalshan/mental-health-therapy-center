package com.service.mental_health_therapy_center.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;


    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    @Entity
    @Table(name = "payment")
public class Payment {

    @Id
    @Column(name = "paymentId")
    private String id;
    private double amount;
    private Date date;
    private String status;

    @ManyToOne
    private Patient patient;

}

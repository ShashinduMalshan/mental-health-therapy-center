package com.service.mental_health_therapy_center.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;


    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    @Entity
    @Table(name = "patient")
public class Patient {

    @Id
    @Column(name = "patientId")
    String id;
    String name;
    int age;
    int phone;
    String email;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<TherapySession> therapy_session;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<Payment> payment;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<Registration> registrations;

}

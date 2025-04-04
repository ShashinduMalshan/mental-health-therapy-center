package com.service.mental_health_therapy_center.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Time;
import java.util.Date;

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
//    @Data
    @Entity
    @Table(name = "therapySession")
public class TherapySession {

    @Id
    @Column(name = "therapySessionId")
    private String id;
    private Date date;
    private Time time;
    private String status;

    @ManyToOne
    @JoinColumn(name = "patientId")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "therapistId")
    private Therapist therapist;

}

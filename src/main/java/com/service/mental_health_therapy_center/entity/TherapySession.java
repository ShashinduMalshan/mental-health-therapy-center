package com.service.mental_health_therapy_center.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Time;
import java.time.LocalDate;
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
    private LocalDate date;
    private Time startTime;
    private Time endTime;
    private String status;

    @ManyToOne
    @JoinColumn(name = "patientId")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "therapistId")
    private Therapist therapist;


    }

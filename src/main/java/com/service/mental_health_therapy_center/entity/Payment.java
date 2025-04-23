package com.service.mental_health_therapy_center.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Session;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;


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
    private LocalDate date;
    private String status;

    @ManyToOne
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "therapyProgramId")
    private TherapyProgram therapy_program;

    @OneToMany(mappedBy = "payment")
    private List<TherapySession> therapySessions;

}



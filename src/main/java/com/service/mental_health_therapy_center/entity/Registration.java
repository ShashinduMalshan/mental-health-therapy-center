package com.service.mental_health_therapy_center.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    @Entity
    @Table(name = "registration")
public class Registration {



    @Id
    @Column(name = "registrationId")
    String id;
    Date registration_date;

    @ManyToOne
    @JoinColumn (name = "patientId")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "therapyProgramId")
    private TherapyProgram therapy_program;




}

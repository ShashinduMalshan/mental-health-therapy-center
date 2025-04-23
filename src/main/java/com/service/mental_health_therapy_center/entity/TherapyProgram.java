package com.service.mental_health_therapy_center.entity;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;


    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    @Entity
    @Table(name = "therapyProgram")
public class TherapyProgram {

    @Id
    @Column(name = "therapyProgramId")
    private String id;
    private String ProGramName;
    private String duration;
    private double cost;




    @OneToMany(mappedBy = "therapyProgram", cascade = CascadeType.ALL)
    private List<Therapist> therapists;

    @OneToMany(mappedBy = "therapy_program")
    private List<Registration> registrations;

    @OneToMany(mappedBy = "therapy_program")
    private List<Payment> payments;



}

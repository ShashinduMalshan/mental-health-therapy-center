package com.service.mental_health_therapy_center.entity;



import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;


    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
//    @Data
    @Entity
    @Table(name = "therapyProgram")
public class TherapyProgram {

    @Id
    @Column(name = "therapyProgramId")
    private String id;
    private String ProGramName;
    private Date duration;
    private double cost;


    @ManyToOne
    @JoinColumn(name = "therapist_id")
    private Therapist therapist;

    @OneToMany(mappedBy = "therapy_program")
     private List<Registration> registrations;



}

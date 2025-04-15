package com.service.mental_health_therapy_center.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

    @AllArgsConstructor
    @NoArgsConstructor
//    @Data
    @Entity
    @Getter
    @Setter
    @Table(name = "therapist")
public class Therapist {

    @Id
    @Column(name = "therapistId")
    private String id;
    private String name;
    private String Specialization;
    private int phone;
    private String email;



    @OneToMany(mappedBy = "therapist", cascade = CascadeType.ALL)
    private List<TherapySession> therapySession;


    @ManyToOne
    @JoinColumn(name = "therapistProgram_id" )
    private TherapyProgram therapyProgram;


}

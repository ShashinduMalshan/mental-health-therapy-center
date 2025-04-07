package com.service.mental_health_therapy_center.dto;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PatientDto {



    String id;
    String name;
    int age;
    int phone;
    String email;
    String medicalHistory;
}

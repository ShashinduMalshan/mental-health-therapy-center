package com.service.mental_health_therapy_center.dto;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TherapyProgramDto {


    private String id;
    private String ProgramName;
    private String duration;
    private double cost;


}

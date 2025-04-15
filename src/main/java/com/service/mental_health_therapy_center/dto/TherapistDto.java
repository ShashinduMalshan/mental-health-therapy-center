package com.service.mental_health_therapy_center.dto;

import com.service.mental_health_therapy_center.entity.TherapyProgram;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TherapistDto {


    private String Id;
    private String name;
    private String Specialization;
    private int ContactNo;
    private String Email;
    private TherapyProgram therapyProgram;



}



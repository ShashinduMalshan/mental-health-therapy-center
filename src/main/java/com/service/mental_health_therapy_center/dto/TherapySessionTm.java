package com.service.mental_health_therapy_center.dto;

import com.service.mental_health_therapy_center.entity.Patient;
import com.service.mental_health_therapy_center.entity.Therapist;
import com.service.mental_health_therapy_center.entity.TherapyProgram;
import lombok.*;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TherapySessionTm {


    private String sessionId;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private Patient patient;
    private TherapyProgram therapyProgram;
    private Therapist therapist;
    private String status;
}

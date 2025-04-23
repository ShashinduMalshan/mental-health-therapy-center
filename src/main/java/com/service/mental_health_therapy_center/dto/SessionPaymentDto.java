package com.service.mental_health_therapy_center.dto;

import com.service.mental_health_therapy_center.entity.*;
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
public class SessionPaymentDto {

    private String sessionId;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private Patient patient;
    private TherapyProgram therapyProgram;
    private Therapist therapist;
    private String status;
    private Payment payment;

}

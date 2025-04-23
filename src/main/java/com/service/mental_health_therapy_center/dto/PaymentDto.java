package com.service.mental_health_therapy_center.dto;

import com.service.mental_health_therapy_center.entity.Patient;
import com.service.mental_health_therapy_center.entity.TherapyProgram;
import com.service.mental_health_therapy_center.entity.TherapySession;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDto {
    private String id;
    private double amount;
    private LocalDate date;
    private String status;
    private Patient patient;
    private TherapyProgram therapyProgram;
}

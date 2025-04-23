package com.service.mental_health_therapy_center.dto;

import com.service.mental_health_therapy_center.entity.Patient;
import com.service.mental_health_therapy_center.entity.Therapist;
import com.service.mental_health_therapy_center.entity.TherapyProgram;
import javafx.scene.control.Button;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartTM {

    private String sessionId;
    private Patient patient;
    private Therapist therapist;
    private TherapyProgram therapyProgram;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private Button removeBtn;


}

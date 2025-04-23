package com.service.mental_health_therapy_center.Bo.custom;

import com.service.mental_health_therapy_center.dto.*;
import com.service.mental_health_therapy_center.entity.Patient;
import com.service.mental_health_therapy_center.entity.Payment;
import com.service.mental_health_therapy_center.entity.Therapist;
import com.service.mental_health_therapy_center.entity.TherapyProgram;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public interface PaymentBo {

    ObservableList<TherapyProgramTm> loadTherapyProgram();

    ArrayList<Payment> getAll();


    double getalreadyPaid(String therapyProgramId , String patientId);

    ObservableList<TherapistDto> loadTherapist();
    ObservableList<PatientTm> loadTherapyPatient();
    List<TherapyProgram> getValueByName(String Name);
    List<Patient> getValueById(String Name);
    boolean save(PaymentDto paymentDto, ArrayList<SessionPaymentDto> therapySessionDtos);
    List<Therapist> getValueByTherapyProgram(TherapyProgram therapyProgram);
    String getNextId();
}

package com.service.mental_health_therapy_center.Bo.custom;

import com.service.mental_health_therapy_center.dto.PatientTm;
import com.service.mental_health_therapy_center.dto.TherapistDto;
import com.service.mental_health_therapy_center.dto.TherapyProgramTm;
import com.service.mental_health_therapy_center.dto.TherapySessionDto;
import com.service.mental_health_therapy_center.entity.Patient;
import com.service.mental_health_therapy_center.entity.Therapist;
import com.service.mental_health_therapy_center.entity.TherapyProgram;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public interface TherapySessionBo {

        ArrayList<TherapySessionDto> loadTable();
        ObservableList<TherapyProgramTm> loadTherapyProgram();
        ObservableList<PatientTm> loadTherapyPatient();
        List<TherapyProgram> getValueByName(String Name);
        List<Patient> getValueById(String Id);
        List<Therapist> getValueByTherapyProgram(TherapyProgram therapyProgram);
        boolean save(TherapySessionDto therapySessionDto);
        boolean update(TherapySessionDto therapySessionDto);
        String getNextId();


}

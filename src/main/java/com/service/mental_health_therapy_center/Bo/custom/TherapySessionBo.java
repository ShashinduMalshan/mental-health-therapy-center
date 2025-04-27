package com.service.mental_health_therapy_center.Bo.custom;

import com.service.mental_health_therapy_center.dto.*;
import com.service.mental_health_therapy_center.entity.Patient;
import com.service.mental_health_therapy_center.entity.Therapist;
import com.service.mental_health_therapy_center.entity.TherapyProgram;
import com.service.mental_health_therapy_center.entity.TherapySession;
import javafx.collections.ObservableList;
import org.hibernate.Session;

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
        List<DateDto> MostChosenTherapyProgram();
        boolean update(TherapySessionDto therapySessionDto);
        String getNextId();
        List<TopRateTherapistDto> topRateTherapist();



}

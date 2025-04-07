package com.service.mental_health_therapy_center.Bo.custom;

import com.service.mental_health_therapy_center.dto.PatientDto;
import com.service.mental_health_therapy_center.dto.TherapistDto;

import java.util.ArrayList;

public interface PatientBo {

    ArrayList<PatientDto> loadTable();
    boolean save(PatientDto patientDto);
    boolean update(PatientDto patientDto);
    boolean delete(String Id);
    String getNextId();

}

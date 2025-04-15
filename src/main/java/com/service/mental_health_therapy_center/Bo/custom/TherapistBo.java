package com.service.mental_health_therapy_center.Bo.custom;

import com.service.mental_health_therapy_center.dto.TherapistDto;
import com.service.mental_health_therapy_center.dto.TherapyProgramTm;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public interface TherapistBo {

    ArrayList<TherapistDto> loadTable();
    boolean save(TherapistDto therapistDto);
    boolean update(TherapistDto therapistDto);
    boolean delete(String Id);
    String getNextId();
    ObservableList<TherapyProgramTm> loadTherapyProgram();

}

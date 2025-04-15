package com.service.mental_health_therapy_center.Bo.custom;

import com.service.mental_health_therapy_center.dto.TherapyProgramDto;
import com.service.mental_health_therapy_center.entity.TherapyProgram;
import com.service.mental_health_therapy_center.entity.User;

import java.util.ArrayList;
import java.util.List;

public interface TherapyProgramBo {
    ArrayList<TherapyProgramDto> loadTable();
    boolean save(TherapyProgramDto therapyProgramDto);
    boolean update(TherapyProgramDto therapyProgramDto);
    boolean delete(String Id);
    String getNextId();
    List<TherapyProgram> getValuByName(String Name);
}

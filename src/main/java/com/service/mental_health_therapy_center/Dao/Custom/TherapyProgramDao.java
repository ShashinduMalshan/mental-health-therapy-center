package com.service.mental_health_therapy_center.Dao.Custom;

import com.service.mental_health_therapy_center.Dao.CrudDao;
import com.service.mental_health_therapy_center.entity.TherapyProgram;

import java.util.List;

public interface TherapyProgramDao extends CrudDao<TherapyProgram> {
    List<TherapyProgram> getProgramsByUsername(String Id);


}

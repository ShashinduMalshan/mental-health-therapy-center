package com.service.mental_health_therapy_center.Dao.Custom;

import com.service.mental_health_therapy_center.Dao.CrudDao;
import com.service.mental_health_therapy_center.dto.LoginDto;
import com.service.mental_health_therapy_center.dto.TherapistDto;
import com.service.mental_health_therapy_center.entity.Patient;
import com.service.mental_health_therapy_center.entity.Therapist;
import com.service.mental_health_therapy_center.entity.TherapyProgram;

import java.util.List;

public interface TherapistDao extends CrudDao<Therapist> {

        List<Therapist> getValueByTherapyProgram(TherapyProgram therapyProgram);
        int therapistCount();



}

package com.service.mental_health_therapy_center.Dao.Custom;

import com.service.mental_health_therapy_center.Dao.CrudDao;
import com.service.mental_health_therapy_center.entity.Patient;
import com.service.mental_health_therapy_center.entity.Therapist;

import java.util.List;

public interface PatientDao extends CrudDao<Patient>{

    List<Patient> getValueById(String id);
    int patentCount();




}

package com.service.mental_health_therapy_center.Bo.custom.Impl;

import com.service.mental_health_therapy_center.Bo.custom.PatientBo;
import com.service.mental_health_therapy_center.Dao.Custom.PatientDao;
import com.service.mental_health_therapy_center.Dao.DAOFactory;
import com.service.mental_health_therapy_center.configuration.FactoryConfiguration;
import com.service.mental_health_therapy_center.dto.PatientDto;
import com.service.mental_health_therapy_center.dto.TherapistDto;
import com.service.mental_health_therapy_center.entity.Patient;
import com.service.mental_health_therapy_center.entity.Therapist;
import com.service.mental_health_therapy_center.entity.TherapyProgram;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class PatientBoImpl implements PatientBo {

    PatientDao patientDao = (PatientDao) DAOFactory.getInstance().getDAOType(DAOFactory.DAOType.PATIENT);
    Patient patient = new Patient();

    @Override
    public ArrayList<PatientDto> loadTable() {
        List<Patient> allPatient = patientDao.getAll();
        ArrayList<PatientDto> patientDtos = new ArrayList<>();

         for (Patient patient : allPatient) {
             PatientDto patientDto = new PatientDto();
             patientDto.setId(patient.getId());
             patientDto.setName(patient.getName());
             patientDto.setAge(patient.getAge());
             patientDto.setPhone(patient.getPhone());
             patientDto.setEmail(patient.getEmail());
             patientDto.setMedicalHistory(patient.getMedicalHistory());

             patientDtos.add(patientDto);
         }
         return patientDtos;    }

    @Override
    public boolean save(PatientDto patientDto) {

        patient.setId(patientDto.getId());
        patient.setName(patientDto.getName());
        patient.setAge(patientDto.getAge());
        patient.setPhone(patientDto.getPhone());
        patient.setEmail(patientDto.getEmail());
        patient.setMedicalHistory(patientDto.getMedicalHistory());

        return patientDao.save(patient);
    }

    @Override
    public boolean update(PatientDto patientDto) {

        patient.setId(patientDto.getId());
        patient.setName(patientDto.getName());
        patient.setAge(patientDto.getAge());
        patient.setPhone(patientDto.getPhone());
        patient.setEmail(patientDto.getEmail());
        patient.setMedicalHistory(patientDto.getMedicalHistory());

        return patientDao.update(patient);
    }

    @Override
    public boolean delete(String Id) {
        return patientDao.delete(Id);
    }

    @Override
    public String getNextId() {
        String id = patientDao.getLastId() ;

        if (id != null){
            String substring = id.substring(1);
            int i = Integer.parseInt(substring);
            int newIdIndex = i + 1;
            return String.format("P%03d", newIdIndex);
        }
        return "P001";
    }



}

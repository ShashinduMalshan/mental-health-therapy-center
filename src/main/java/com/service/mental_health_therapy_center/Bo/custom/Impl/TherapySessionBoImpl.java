package com.service.mental_health_therapy_center.Bo.custom.Impl;

import com.service.mental_health_therapy_center.Bo.custom.TherapySessionBo;
import com.service.mental_health_therapy_center.Dao.Custom.PatientDao;
import com.service.mental_health_therapy_center.Dao.Custom.TherapistDao;
import com.service.mental_health_therapy_center.Dao.Custom.TherapyProgramDao;
import com.service.mental_health_therapy_center.Dao.Custom.TherapySessionDao;
import com.service.mental_health_therapy_center.Dao.DAOFactory;
import com.service.mental_health_therapy_center.dto.PatientTm;
import com.service.mental_health_therapy_center.dto.TherapyProgramTm;
import com.service.mental_health_therapy_center.dto.TherapySessionDto;
import com.service.mental_health_therapy_center.entity.Patient;
import com.service.mental_health_therapy_center.entity.Therapist;
import com.service.mental_health_therapy_center.entity.TherapyProgram;
import com.service.mental_health_therapy_center.entity.TherapySession;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class TherapySessionBoImpl implements TherapySessionBo {

    TherapyProgramDao therapyProgramDao = (TherapyProgramDao) DAOFactory.getInstance().getDAOType(DAOFactory.DAOType.THERAPISTPROGRAM);
    TherapySessionDao therapySessionDao = (TherapySessionDao) DAOFactory.getInstance().getDAOType(DAOFactory.DAOType.THERAPYSESSION);
    PatientDao patientDao = (PatientDao) DAOFactory.getInstance().getDAOType(DAOFactory.DAOType.PATIENT);
    TherapistDao therapistDao = (TherapistDao) DAOFactory.getInstance().getDAOType(DAOFactory.DAOType.THERAPIST);
    TherapySession therapySession = new TherapySession();

    @Override
    public ArrayList<TherapySessionDto> loadTable() {
        List<TherapySession> getAllTherapySessions = therapySessionDao.getAll();
        ArrayList<TherapySessionDto> therapySessionDtos = new ArrayList<>();

        for (TherapySession therapySession : getAllTherapySessions){
            TherapySessionDto therapySessionDto = new TherapySessionDto();
            therapySessionDto.setSessionId(therapySession.getId());
            therapySessionDto.setDate(therapySession.getDate());
            therapySessionDto.setStartTime(therapySession.getStartTime().toLocalTime());
            therapySessionDto.setEndTime(therapySession.getEndTime().toLocalTime());
            therapySessionDto.setStatus(therapySession.getStatus());
            therapySessionDto.setPatient(therapySession.getPatient());
            therapySessionDto.setTherapist(therapySession.getTherapist());
            therapySessionDto.setTherapyProgram(therapySession.getTherapist().getTherapyProgram());

            therapySessionDtos.add(therapySessionDto);
        }
        return therapySessionDtos;
    }

    public ObservableList<TherapyProgramTm> loadTherapyProgram(){


        List<TherapyProgram> allTherapyProgram = therapyProgramDao.getAll();

        ObservableList<TherapyProgramTm> observableList = FXCollections.observableArrayList();


         for (TherapyProgram therapyProgram : allTherapyProgram) {
              TherapyProgramTm therapyProgramTm = new TherapyProgramTm(
                    therapyProgram.getId(),
                    therapyProgram.getProGramName(),
                    therapyProgram.getDuration(),
                    therapyProgram.getCost()
                );

             observableList.add(therapyProgramTm);
         }
         return observableList;
    }


    public ObservableList<PatientTm> loadTherapyPatient(){


         List<Patient> allTherapyPatient = patientDao.getAll();

        ObservableList<PatientTm> observableList = FXCollections.observableArrayList();


         for (Patient patient : allTherapyPatient) {
              PatientTm patientTm = new PatientTm(
                    patient.getId(),
                    patient.getName(),
                    patient.getAge(),
                    patient.getPhone(),
                    patient.getMedicalHistory(),
                    patient.getEmail()

                );

             observableList.add(patientTm);
         }
         return observableList;
    }

    public List<TherapyProgram> getValueByName(String Name) {
        return therapyProgramDao.getProgramsByUsername(Name);
    }

    public List<Patient> getValueById(String Name) {
        return patientDao.getValueById(Name);
    }

    @Override
    public List<Therapist> getValueByTherapyProgram(TherapyProgram therapyProgram) {
        return therapistDao.getValueByTherapyProgram(therapyProgram);
    }

    @Override
    public boolean save(TherapySessionDto therapySessionDto) {

        therapySession.setId(therapySessionDto.getSessionId());
        therapySession.setDate(therapySessionDto.getDate());
        therapySession.setStartTime(Time.valueOf(therapySessionDto.getStartTime()));
        therapySession.setEndTime(Time.valueOf(therapySessionDto.getEndTime()));
        therapySession.setPatient(therapySessionDto.getPatient());
        therapySession.setTherapist(therapySessionDto.getTherapist());
        therapySession.setStatus(therapySessionDto.getStatus());

        therapySessionDao.save(therapySession);
        return true;
    }



    @Override
    public boolean update(TherapySessionDto therapySessionDto) {
        therapySession.setId(therapySessionDto.getSessionId());
        therapySession.setDate(therapySessionDto.getDate());
        therapySession.setStartTime(Time.valueOf(therapySessionDto.getStartTime()));
        therapySession.setEndTime(Time.valueOf(therapySessionDto.getEndTime()));
        therapySession.setPatient(therapySessionDto.getPatient());
        therapySession.setTherapist(therapySessionDto.getTherapist());
        therapySession.setStatus(therapySessionDto.getStatus());

        therapySessionDao.update(therapySession);
        return true;
    }

    public String getNextId(){
        String id = therapySessionDao.getLastId() ;

        if (id != null){
            String substring = id.substring(1);
            int i = Integer.parseInt(substring);
            int newIdIndex = i + 1;
            return String.format("S%03d", newIdIndex);
        }
        return "S001";
    }
}

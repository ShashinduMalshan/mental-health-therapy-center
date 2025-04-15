package com.service.mental_health_therapy_center.Bo.custom.Impl;

import com.service.mental_health_therapy_center.Bo.custom.TherapistBo;
import com.service.mental_health_therapy_center.Dao.Custom.TherapistDao;
import com.service.mental_health_therapy_center.Dao.Custom.TherapyProgramDao;
import com.service.mental_health_therapy_center.Dao.DAOFactory;
import com.service.mental_health_therapy_center.dto.TherapistDto;
import com.service.mental_health_therapy_center.dto.TherapyProgramTm;
import com.service.mental_health_therapy_center.entity.Therapist;
import com.service.mental_health_therapy_center.entity.TherapyProgram;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


import java.util.ArrayList;
import java.util.List;


public class TherapistBoImpl implements TherapistBo {

    Therapist therapist = new Therapist();
    TherapistDao therapistDao = (TherapistDao) DAOFactory.getInstance().getDAOType(DAOFactory.DAOType.THERAPIST);
    TherapyProgramDao therapyProgramDao = (TherapyProgramDao) DAOFactory.getInstance().getDAOType(DAOFactory.DAOType.THERAPISTPROGRAM);

    @Override
    public ArrayList<TherapistDto> loadTable() {
        List<Therapist> allTherapist = therapistDao.getAll();
         ArrayList<TherapistDto> therapistDtos = new ArrayList<>();

         for (Therapist therapist : allTherapist) {
             TherapistDto therapistDto = new TherapistDto();
             therapistDto.setId(therapist.getId());
             therapistDto.setName(therapist.getName());
             therapistDto.setSpecialization(therapist.getSpecialization());
             therapistDto.setContactNo(therapist.getPhone());
             therapistDto.setEmail(therapist.getEmail());
             therapistDto.setTherapyProgram(therapist.getTherapyProgram());

             therapistDtos.add(therapistDto);
         }
         return therapistDtos;
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

    @Override
    public boolean save(TherapistDto therapistDto) {

        therapist.setId(therapistDto.getId());
        therapist.setName(therapistDto.getName());
        therapist.setSpecialization(therapistDto.getSpecialization());
        therapist.setEmail(therapistDto.getEmail());
        therapist.setPhone(therapistDto.getContactNo());
        therapist.setTherapyProgram(therapistDto.getTherapyProgram());

        therapistDao.save(therapist);

        return true;
    }

    @Override
    public boolean update(TherapistDto therapistDto) {

        therapist.setId(therapistDto.getId());
        therapist.setName(therapistDto.getName());
        therapist.setSpecialization(therapistDto.getSpecialization());
        therapist.setEmail(therapistDto.getEmail());
        therapist.setPhone(therapistDto.getContactNo());
        therapist.setTherapyProgram(therapistDto.getTherapyProgram());


        therapistDao.update(therapist);

        return true;
    }

    @Override
    public boolean delete(String Id) {
        return therapistDao.delete(Id);
    }

    public String getNextId(){
        String id = therapistDao.getLastId() ;

        if (id != null){
            String substring = id.substring(1);
            int i = Integer.parseInt(substring);
            int newIdIndex = i + 1;
            return String.format("T%03d", newIdIndex);
        }
        return "T001";
    }
}

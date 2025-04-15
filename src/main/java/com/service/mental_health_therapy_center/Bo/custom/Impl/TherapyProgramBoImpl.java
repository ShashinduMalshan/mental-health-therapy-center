package com.service.mental_health_therapy_center.Bo.custom.Impl;

import com.service.mental_health_therapy_center.Bo.custom.TherapyProgramBo;
import com.service.mental_health_therapy_center.Dao.Custom.TherapyProgramDao;
import com.service.mental_health_therapy_center.Dao.DAOFactory;
import com.service.mental_health_therapy_center.dto.TherapyProgramDto;
import com.service.mental_health_therapy_center.entity.TherapyProgram;
import com.service.mental_health_therapy_center.entity.User;

import java.util.ArrayList;
import java.util.List;


public class TherapyProgramBoImpl implements TherapyProgramBo {


    TherapyProgram therapyProgram = new TherapyProgram();
    TherapyProgramDao therapyProgramDao = (TherapyProgramDao) DAOFactory.getInstance().getDAOType(DAOFactory.DAOType.THERAPISTPROGRAM);

    @Override
    public ArrayList<TherapyProgramDto> loadTable() {
        List<TherapyProgram> allTherapyProgram = therapyProgramDao.getAll();
        ArrayList<TherapyProgramDto> therapyProgramDtos = new ArrayList<>();

        for (TherapyProgram therapyProgram : allTherapyProgram) {
            TherapyProgramDto therapyProgramDto = new TherapyProgramDto();
            therapyProgramDto.setId(therapyProgram.getId());
            therapyProgramDto.setProgramName(therapyProgram.getProGramName());
            therapyProgramDto.setDuration(therapyProgram.getDuration());
            therapyProgramDto.setCost(therapyProgram.getCost());

            therapyProgramDtos.add(therapyProgramDto);
        }
        return therapyProgramDtos;
    }

    @Override
    public boolean save(TherapyProgramDto therapyProgramDto) {
        therapyProgram.setId(therapyProgramDto.getId());
        therapyProgram.setProGramName(therapyProgramDto.getProgramName());
        therapyProgram.setDuration(therapyProgramDto.getDuration());
        therapyProgram.setCost(therapyProgramDto.getCost());

        therapyProgramDao.save(therapyProgram);
        return true;
    }

    @Override
    public boolean update(TherapyProgramDto therapyProgramDto) {

        therapyProgram.setId(therapyProgramDto.getId());
        therapyProgram.setProGramName(therapyProgramDto.getProgramName());
        therapyProgram.setDuration(therapyProgramDto.getDuration());
        therapyProgram.setCost(therapyProgramDto.getCost());

        therapyProgramDao.save(therapyProgram);
        return true;
    }

    @Override
    public boolean delete(String Id) {
        return therapyProgramDao.delete(Id);
    }

    @Override
    public String getNextId() {
        String id = therapyProgramDao.getLastId();


        if (id != null) {
            String subString = id.substring(2);
            int i = Integer.parseInt(subString);
            int newIdIndex = i + 1;
            return String.format("MT%03d", newIdIndex);
        }
        return "MT001";
    }

    @Override
    public List<TherapyProgram> getValuByName(String Name) {
        return therapyProgramDao.getProgramsByUsername(Name);
    }




}




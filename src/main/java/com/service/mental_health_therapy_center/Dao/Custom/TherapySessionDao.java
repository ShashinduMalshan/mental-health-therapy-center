package com.service.mental_health_therapy_center.Dao.Custom;

import com.service.mental_health_therapy_center.Dao.CrudDao;
import com.service.mental_health_therapy_center.dto.DateDto;
import com.service.mental_health_therapy_center.dto.TopRateTherapistDto;
import com.service.mental_health_therapy_center.entity.Patient;
import com.service.mental_health_therapy_center.entity.TherapySession;
import org.hibernate.Session;

import java.util.List;

public interface TherapySessionDao extends CrudDao<TherapySession> {
    boolean saveSession(Session session, TherapySession therapySession);
    List<DateDto> MostChosenTherapyProgram();
    List<TopRateTherapistDto> topRateTherapist();


}

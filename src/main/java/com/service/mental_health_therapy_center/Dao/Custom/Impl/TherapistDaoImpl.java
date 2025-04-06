package com.service.mental_health_therapy_center.Dao.Custom.Impl;

import com.service.mental_health_therapy_center.Dao.Custom.TherapistDao;
import com.service.mental_health_therapy_center.configuration.FactoryConfiguration;
import com.service.mental_health_therapy_center.dto.TherapistDto;
import com.service.mental_health_therapy_center.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class TherapistDaoImpl implements TherapistDao {
    @Override
    public List<TherapistDto> getAll() {
        return List.of();
    }

    @Override
    public boolean save(TherapistDto dto) {

            Session session = FactoryConfiguration.getInstance().getSession();
            Transaction transaction = session.beginTransaction();

            session.persist(dto);
            transaction.commit();

            return true;
    }

    @Override
    public boolean update(User dto) {
        return false;
    }

    @Override
    public boolean delete(String Id) {
        return false;
    }

    @Override
    public String getLastId() {
        return "";
    }
}

package com.service.mental_health_therapy_center.Dao.Custom.Impl;

import com.service.mental_health_therapy_center.Dao.Custom.TherapySessionDao;
import com.service.mental_health_therapy_center.configuration.FactoryConfiguration;
import com.service.mental_health_therapy_center.entity.Patient;
import com.service.mental_health_therapy_center.entity.Therapist;
import com.service.mental_health_therapy_center.entity.TherapyProgram;
import com.service.mental_health_therapy_center.entity.TherapySession;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class TherapySessionDaoImpl implements TherapySessionDao {
    @Override
    public List<TherapySession> getAll() {
        Session session= FactoryConfiguration.getInstance().getSession();

        List<TherapySession> query = session.createQuery("from TherapySession " , TherapySession.class).list();

        session.close();
        return query;
    }

    @Override
    public boolean save(TherapySession dto) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.persist(dto);
        transaction.commit();

        session.close();

        return true;
    }

    @Override
    public boolean update(TherapySession dto) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.merge(dto);
        transaction.commit();

        session.close();

        return true;
    }

    @Override
    public boolean delete(String Id) {
        return false;
    }

    @Override
    public String getLastId() {

        Session session = FactoryConfiguration.getInstance().getSession();
        Query<TherapySession> query = session.createQuery("FROM TherapySession ORDER BY id DESC LIMIT 1", TherapySession.class);
        List<TherapySession> therapySessions = query.list();

        session.close();
        return therapySessions.isEmpty() ? null : therapySessions.get(0).getId();

    }



}

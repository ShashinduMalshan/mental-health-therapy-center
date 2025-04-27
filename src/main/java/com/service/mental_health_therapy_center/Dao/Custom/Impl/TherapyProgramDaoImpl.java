package com.service.mental_health_therapy_center.Dao.Custom.Impl;

import com.service.mental_health_therapy_center.Dao.Custom.TherapyProgramDao;
import com.service.mental_health_therapy_center.configuration.FactoryConfiguration;
import com.service.mental_health_therapy_center.dto.PaymentDto;
import com.service.mental_health_therapy_center.entity.TherapyProgram;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class TherapyProgramDaoImpl implements TherapyProgramDao {
    @Override
    public List<TherapyProgram> getAll() {
        Session session = FactoryConfiguration.getInstance().getSession();
        List<TherapyProgram> query = session.createQuery("from TherapyProgram", TherapyProgram.class).list();

        session.close();
        return query;
    }

    @Override
    public boolean save(TherapyProgram therapyProgram) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.persist(therapyProgram);
        transaction.commit();

        session.close();

        return true;
    }

    @Override
    public boolean update(TherapyProgram therapyProgram) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.merge(therapyProgram);
        transaction.commit();

        session.close();
        return true;
    }

    @Override
    public boolean delete(String Id) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.remove(session.get(TherapyProgram.class, Id));
        transaction.commit();

        session.close();
        return true;
    }

    @Override
    public String getLastId() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Query<TherapyProgram> query = session.createQuery("FROM TherapyProgram ORDER BY id DESC LIMIT 1", TherapyProgram.class);
        List<TherapyProgram> therapyPrograms = query.list();

        session.close();
        return therapyPrograms.isEmpty() ? null : therapyPrograms.get(0).getId();

    }


    public List<TherapyProgram> getProgramsByUsername(String Id) {
    Session session = FactoryConfiguration.getInstance().getSession();
    List<TherapyProgram> programList = null;

    try {
        Query<TherapyProgram> query = session.createQuery(
            "FROM TherapyProgram WHERE ProGramName = :name", TherapyProgram.class);
        query.setParameter("name",Id);
        programList = query.getResultList();
    } finally {
        session.close();
    }

    return programList;


    }



}

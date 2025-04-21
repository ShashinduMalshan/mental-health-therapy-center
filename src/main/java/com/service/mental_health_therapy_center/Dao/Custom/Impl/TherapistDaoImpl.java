package com.service.mental_health_therapy_center.Dao.Custom.Impl;

import com.service.mental_health_therapy_center.Dao.Custom.TherapistDao;
import com.service.mental_health_therapy_center.configuration.FactoryConfiguration;
import com.service.mental_health_therapy_center.entity.Therapist;
import com.service.mental_health_therapy_center.entity.TherapyProgram;
import com.service.mental_health_therapy_center.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class TherapistDaoImpl implements TherapistDao {
    @Override
    public List<Therapist> getAll() {

        Session session= FactoryConfiguration.getInstance().getSession();

        List<Therapist> query = session.createQuery("from Therapist" , Therapist.class).list();

        session.close();
        return query;
    }

    @Override
    public boolean save(Therapist therapist) {

            Session session = FactoryConfiguration.getInstance().getSession();
            Transaction transaction = session.beginTransaction();

            session.persist(therapist);
            transaction.commit();

            session.close();

            return true;
    }

    @Override
    public boolean update(Therapist therapist) {

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.merge(therapist);
        transaction.commit();

        session.close();

        return true;
    }

    @Override
    public boolean delete(String Id) {

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Therapist therapist = session.get(Therapist.class, Id);

        session.remove(therapist);
        transaction.commit();

        session.close();

        return true;
    }

    @Override
    public String getLastId() {

        Session session = FactoryConfiguration.getInstance().getSession();
        Query<Therapist> query = session.createQuery(
            "FROM Therapist ORDER BY id DESC limit 1", Therapist.class);
        List<Therapist> result = query.getResultList();
        session.close();
        return result.isEmpty() ? null : result.get(0).getId();

    }

    @Override
    public List<Therapist>
    getValueByTherapyProgram(TherapyProgram therapyProgram) {
        Session session = FactoryConfiguration.getInstance().getSession();
        List<Therapist> therapistList = null;

            try {
                Query<Therapist> query = session.createQuery(
                    "FROM Therapist WHERE therapyProgram = :therapyProgram", Therapist.class);
                query.setParameter("therapyProgram",therapyProgram);
                therapistList = query.getResultList();
            } finally {
                session.close();
            }

            return therapistList;


    }
}

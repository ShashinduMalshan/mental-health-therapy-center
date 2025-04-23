package com.service.mental_health_therapy_center.Dao.Custom.Impl;

import com.service.mental_health_therapy_center.Dao.Custom.PaymentDao;
import com.service.mental_health_therapy_center.configuration.FactoryConfiguration;
import com.service.mental_health_therapy_center.entity.Payment;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class PaymentDaoImpl implements PaymentDao {

    @Override
    public List<Payment> getAll(String therapyProgramId, String id) {
        Session session = FactoryConfiguration.getInstance().getSession();
        List<Payment> programCost = null;

        try {
            Query<Payment> query = session.createQuery("FROM Payment WHERE therapy_program.id = :therapyProgramId AND patient.id = :id", Payment.class);
            query.setParameter("therapyProgramId", therapyProgramId);
            query.setParameter("id", id);
            programCost = query.getResultList();


        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error retrieving payments", e);
        } finally {
            session.close();
        }
        return programCost;
    }

    @Override
    public boolean savePayment(Session session,Payment payment) {
        try {
            session.persist(payment);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);

        }

    }


    @Override
    public List<Payment> getAll() {
        return List.of();
    }

    public boolean save(Payment payment) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.persist(payment);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean update(Payment dto) {
        return false;
    }

    @Override
    public boolean delete(String Id) {
        return false;
    }

    @Override
    public String getLastId() {

        Session session = FactoryConfiguration.getInstance().getSession();
        Query<Payment> query = session.createQuery("FROM Payment ORDER BY id DESC LIMIT 1", Payment.class);
        List<Payment> therapySessions = query.list();

        session.close();
        return therapySessions.isEmpty() ? null : therapySessions.get(0).getId();    }
}

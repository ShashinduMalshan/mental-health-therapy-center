package com.service.mental_health_therapy_center.Dao.Custom.Impl;

import com.service.mental_health_therapy_center.Dao.Custom.PaymentDao;
import com.service.mental_health_therapy_center.configuration.FactoryConfiguration;
import com.service.mental_health_therapy_center.dto.PaymentDto;
import com.service.mental_health_therapy_center.dto.PaymentHistory;
import com.service.mental_health_therapy_center.dto.PaymentTm;
import com.service.mental_health_therapy_center.entity.Payment;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
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
    public boolean savePayment(Session session, Payment payment) {
        try {
            session.persist(payment);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);

        }

    }


    @Override
    public List<PaymentTm> getAllPatentCount() {

       Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = null;
        List<PaymentTm> countList = new ArrayList<>();

        try {
            transaction = session.beginTransaction();

            String hql = "SELECT p.therapy_program.id, COUNT(DISTINCT p.patient.id), SUM(p.amount) " +
                         "FROM Payment p " +
                         "GROUP BY p.therapy_program.id";

            List<Object[]> results = session.createQuery(hql, Object[].class).getResultList();

            for (Object[] row : results) {
                String therapyProgramId = (String) row[0];
                Long patientCount = (Long) row[1];
                double totalAmount = (double) row[2];

                // You can decide what you want to set in PaymentHistory
                PaymentTm paymentTm = new PaymentTm(therapyProgramId, patientCount.intValue(), (int) totalAmount);
                countList.add(paymentTm);
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return countList;
    }

        public List<PaymentHistory> financialHistoryByMonth() {
            Session session = FactoryConfiguration.getInstance().getSession();
            List<PaymentHistory> countList = new ArrayList<>();

            try {
                Query<Object[]> query = session.createQuery(
                    "SELECT FUNCTION('DATE_FORMAT', s.date, '%b'), SUM(s.amount) " +
                    "FROM Payment s " +
                    "GROUP BY FUNCTION('DATE_FORMAT', s.date, '%b'), FUNCTION('MONTH', s.date) " +
                    "ORDER BY FUNCTION('MONTH', s.date) ASC",
                    Object[].class
                );



                List<Object[]> resultList = query.getResultList();

                for (Object[] row : resultList) {
                    String month = (String) row[0];
                    double count = (double) row[1];
                    countList.add(new PaymentHistory(month, (int) count));
                }

            } finally {
                session.close();
            }
            return countList;
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
        return therapySessions.isEmpty() ? null : therapySessions.get(0).getId();
    }

}



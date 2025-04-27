package com.service.mental_health_therapy_center.Dao.Custom.Impl;

import com.service.mental_health_therapy_center.Dao.Custom.TherapySessionDao;
import com.service.mental_health_therapy_center.configuration.FactoryConfiguration;
import com.service.mental_health_therapy_center.dto.DateDto;
import com.service.mental_health_therapy_center.dto.PaymentDto;
import com.service.mental_health_therapy_center.dto.TopRateTherapistDto;
import com.service.mental_health_therapy_center.entity.TherapySession;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class TherapySessionDaoImpl implements TherapySessionDao {
    @Override
    public List<TherapySession> getAll() {
        Session session = FactoryConfiguration.getInstance().getSession();

        List<TherapySession> query = session.createQuery("from TherapySession ", TherapySession.class).list();

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


    @Override
    public boolean saveSession(Session session, TherapySession therapySession) {
        session.persist(therapySession);
        return true;
    }

        public List<DateDto> MostChosenTherapyProgram() {
            Session session = FactoryConfiguration.getInstance().getSession();
            List<DateDto> countList = new ArrayList<>();

            try {
                Query<Object[]> query = session.createQuery(
                    "SELECT FUNCTION('DATE_FORMAT', s.date, '%b'), COUNT(s) " +
                    "FROM TherapySession s " +
                    "GROUP BY FUNCTION('DATE_FORMAT', s.date, '%b') " +
                    "ORDER BY FUNCTION('DATE_FORMAT', s.date, '%b') ASC",
                    Object[].class
                );

                List<Object[]> resultList = query.getResultList();

                for (Object[] row : resultList) {
                    String month = (String) row[0];
                    Long count = (Long) row[1];
                    countList.add(new DateDto(month, count.intValue()));
                }

            } finally {
                session.close();
            }

            return countList;
        }

        public List<TopRateTherapistDto> topRateTherapist() {
            Session session = FactoryConfiguration.getInstance().getSession();
            List<TopRateTherapistDto> countList = new ArrayList<>();

            try {
              Query<Object[]> query = session.createQuery(
                    "SELECT s.therapist.name, COUNT(s) " +
                    "FROM TherapySession s " +
                    "GROUP BY s.therapist.name, s.therapist.id " +
                    "ORDER BY s.therapist.id ASC ",
                    Object[].class
                );



                List<Object[]> resultList = query.getResultList();

                for (Object[] row : resultList) {
                    String month = (String) row[0];
                    Long count = (Long) row[1];
                    countList.add(new TopRateTherapistDto(month, count.intValue()));
                }

            } finally {
                session.close();
            }

            return countList;
        }

}

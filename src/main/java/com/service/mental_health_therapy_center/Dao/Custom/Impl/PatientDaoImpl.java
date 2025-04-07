package com.service.mental_health_therapy_center.Dao.Custom.Impl;

import com.service.mental_health_therapy_center.Dao.CrudDao;
import com.service.mental_health_therapy_center.Dao.Custom.PatientDao;
import com.service.mental_health_therapy_center.configuration.FactoryConfiguration;
import com.service.mental_health_therapy_center.entity.Patient;
import com.service.mental_health_therapy_center.entity.Therapist;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class PatientDaoImpl implements PatientDao {

    @Override
    public List<Patient> getAll() {
       Session session= FactoryConfiguration.getInstance().getSession();
       List<Patient> query = session.createQuery("from Patient" , Patient.class).list();
       session.close();
       return query;
    }

    @Override
    public boolean save(Patient dto) {

        Session session= FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.persist(dto);
        transaction.commit();

        session.close();
        return true;
    }

    @Override
    public boolean update(Patient dto) {
        Session session= FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.merge(dto);
        transaction.commit();

        session.close();
        return true;
    }

    @Override
    public boolean delete(String Id) {
        Session session= FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Patient patient = session.get(Patient.class, Id);

        session.remove(patient);
        transaction.commit();

        session.close();
        return true;
    }

    @Override
    public String getLastId() {

        Session session = FactoryConfiguration.getInstance().getSession();
        Query<Patient> query = session.createQuery(
            "FROM Patient ORDER BY id DESC limit 1", Patient.class);
        List<Patient> result = query.getResultList();
        session.close();
        return result.isEmpty() ? null : result.get(0).getId();
    }
}

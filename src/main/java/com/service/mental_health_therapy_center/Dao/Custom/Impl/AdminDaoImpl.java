package com.service.mental_health_therapy_center.Dao.Custom.Impl;

import com.service.mental_health_therapy_center.Dao.Custom.AdminDao;
import com.service.mental_health_therapy_center.configuration.FactoryConfiguration;
import com.service.mental_health_therapy_center.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class AdminDaoImpl implements AdminDao {

    public List<User> getAll(){

        Session session= FactoryConfiguration.getInstance().getSession();

        List<User> query = session.createQuery("from User" , User.class).list();

        session.close();
        return query;
    }
    public boolean save(User user) {

            Session session = FactoryConfiguration.getInstance().getSession();
            Transaction transaction = session.beginTransaction();

            session.persist(user);
            transaction.commit();

            return true;
    }

    public boolean update(User user) {
            Session session = FactoryConfiguration.getInstance().getSession();
            Transaction transaction = session.beginTransaction();

            session.merge(user);
            transaction.commit();

            return true;
    }

    @Override
    public boolean delete(String Id) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        User user = session.get(User.class, Id);

        session.remove(user);
        transaction.commit();
        return true;
    }

    public String getLastId() {

        Session session = FactoryConfiguration.getInstance().getSession();
        Query<User> query = session.createQuery(
            "FROM User ORDER BY id DESC limit 1", User.class);
        List<User> result = query.getResultList();
        return result.isEmpty() ? null : result.get(0).getId();

    }



}

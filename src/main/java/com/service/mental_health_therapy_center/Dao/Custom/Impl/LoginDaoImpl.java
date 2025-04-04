package com.service.mental_health_therapy_center.Dao.Custom.Impl;

import com.service.mental_health_therapy_center.Dao.Custom.LoginDao;
import com.service.mental_health_therapy_center.configuration.FactoryConfiguration;
import com.service.mental_health_therapy_center.dto.LoginDto;
import com.service.mental_health_therapy_center.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class LoginDaoImpl implements LoginDao {




    public List<User> getUserByUsername(LoginDto loginDto) {
        Session session = FactoryConfiguration.getInstance().getSession();

        String name =loginDto.getName();

             Query<User> query = session.createQuery("from User where name = :name", User.class);
             query.setParameter("name", name);



        return query.getResultList();

    }




    public boolean save(User login) {

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {

        session.persist(login);
        System.out.println(login.toString());
        transaction.commit();
        return true;

        } catch (Exception e) {
            transaction.rollback();
            return false;
        } finally {
            session.close();
        }
    }
}
package com.service.mental_health_therapy_center.Dao.Custom.Impl;

import com.service.mental_health_therapy_center.Dao.Custom.RegisterDao;
import com.service.mental_health_therapy_center.configuration.FactoryConfiguration;
import com.service.mental_health_therapy_center.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class RegisterDaoImpl implements RegisterDao {


    public boolean save(User user) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(user);
        transaction.commit();

        return true;



    }
}

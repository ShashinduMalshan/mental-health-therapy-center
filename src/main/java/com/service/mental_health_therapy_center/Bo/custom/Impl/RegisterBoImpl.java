package com.service.mental_health_therapy_center.Bo.custom.Impl;

import com.service.mental_health_therapy_center.Bo.custom.RegisterBo;
import com.service.mental_health_therapy_center.Dao.Custom.Impl.RegisterDaoImpl;
import com.service.mental_health_therapy_center.Dao.Custom.RegisterDao;
import com.service.mental_health_therapy_center.configuration.FactoryConfiguration;
import com.service.mental_health_therapy_center.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class RegisterBoImpl implements RegisterBo {

    RegisterDao registerDao = new RegisterDaoImpl();
    User user = new User();

    @Override
     public boolean registerUser(String username, String password, String roleName) {
         Session session = FactoryConfiguration.getInstance().getSession();
         Transaction transaction = session.beginTransaction();

         user.setName(username);
         user.setPassword(password);
         user.setRole(roleName);



        return registerDao.save(user);

     }
}

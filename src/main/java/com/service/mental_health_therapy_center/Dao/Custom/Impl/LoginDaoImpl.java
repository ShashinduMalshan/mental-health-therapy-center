package com.service.mental_health_therapy_center.Dao.Custom.Impl;

import com.service.mental_health_therapy_center.Dao.Custom.LoginDao;
import com.service.mental_health_therapy_center.configuration.FactoryConfiguration;
import com.service.mental_health_therapy_center.dto.LoginDto;
import com.service.mental_health_therapy_center.entity.User;
import org.hibernate.Session;
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


    @Override
    public List<LoginDto> getAll() {
        return List.of();
    }

    @Override
    public boolean save(LoginDto user) {
        return false;
    }

    @Override
    public boolean update(User user) {
        return false;
    }

    @Override
    public boolean delete(String Id) {
        return false;
    }

    @Override
    public String getLastId() {
        return "";
    }
}
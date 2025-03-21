package com.service.mental_health_therapy_center.Bo.custom.Impl;


import com.service.mental_health_therapy_center.Bo.custom.LoginBo;
import com.service.mental_health_therapy_center.Dao.Custom.Impl.LoginDaoImpl;
import com.service.mental_health_therapy_center.Dao.Custom.LoginDao;
import com.service.mental_health_therapy_center.dto.LoginDto;
import com.service.mental_health_therapy_center.entity.User;

import java.util.List;

public class LoginBoImpl implements LoginBo {


    LoginDao loginDao = new LoginDaoImpl();
    User user = new User();


    @Override
    public boolean authenticateUser(LoginDto loginDto) {

        List<User> userByUsername = loginDao.getUserByUsername(loginDto);



        if (loginDto.getPassword().equals(userByUsername.get(0).getPassword())) {
            return true;
        }
        return false;

    }

}

package com.service.mental_health_therapy_center.Dao.Custom;

import com.service.mental_health_therapy_center.dto.LoginDto;
import com.service.mental_health_therapy_center.entity.User;

import java.util.List;

public interface LoginDao {

    boolean save(User login);
    List<User> getUserByUsername(LoginDto loginDto);

}

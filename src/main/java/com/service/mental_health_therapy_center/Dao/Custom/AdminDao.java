package com.service.mental_health_therapy_center.Dao.Custom;

import com.service.mental_health_therapy_center.entity.User;

import java.util.List;

public interface AdminDao {

    List<User> getAll();
    boolean save(User user);
    public String getLastId();
}

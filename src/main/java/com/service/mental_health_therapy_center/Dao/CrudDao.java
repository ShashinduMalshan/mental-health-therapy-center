package com.service.mental_health_therapy_center.Dao;



import com.service.mental_health_therapy_center.entity.User;

import java.util.List;

public interface CrudDao<T> {

    List<T> getAll();
    boolean save(T user);
    boolean update(User user);
    String getLastId();

}

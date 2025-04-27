package com.service.mental_health_therapy_center.Dao;



import java.util.List;

public interface CrudDao<T> {

    List<T> getAll();
    boolean save(T dto);
    boolean update(T dto);
    boolean delete(String Id);
    String getLastId();

}

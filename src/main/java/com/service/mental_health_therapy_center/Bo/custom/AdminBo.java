package com.service.mental_health_therapy_center.Bo.custom;

import com.service.mental_health_therapy_center.dto.UserDto;

import java.util.ArrayList;

public interface AdminBo {

    ArrayList<UserDto> loadTable();
    boolean save(String username,String password,String role);
    boolean update(String Id, String username, String password, String role);


}



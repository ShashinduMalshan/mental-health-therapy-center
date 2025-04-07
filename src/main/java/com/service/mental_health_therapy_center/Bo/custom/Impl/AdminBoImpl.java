package com.service.mental_health_therapy_center.Bo.custom.Impl;

import com.service.mental_health_therapy_center.Bo.custom.AdminBo;
import com.service.mental_health_therapy_center.Dao.Custom.AdminDao;
import com.service.mental_health_therapy_center.Dao.Custom.Impl.AdminDaoImpl;
import com.service.mental_health_therapy_center.Dao.DAOFactory;
import com.service.mental_health_therapy_center.dto.UserDto;
import com.service.mental_health_therapy_center.entity.User;

import java.util.ArrayList;
import java.util.List;


public class AdminBoImpl implements AdminBo {

    AdminDao adminDao = (AdminDao) DAOFactory.getInstance().getDAOType(DAOFactory.DAOType.ADMIN);
    User user = new User();


     public ArrayList<UserDto> loadTable() {

         List<User> allUsers = adminDao.getAll();
         ArrayList<UserDto> userDtoList = new ArrayList<>();

         for (User user : allUsers) {
             UserDto userDto = new UserDto();
             userDto.setId(user.getId());
             userDto.setName(user.getName());
             userDto.setPassword(user.getPassword());
             userDto.setRole(user.getRole());

             userDtoList.add(userDto);
         }
         return userDtoList;
     }

    public boolean save(String username, String password, String role) {

         user.setId(getNextId());
         user.setName(username);
         user.setPassword(password);
         user.setRole(role);



        return adminDao.save(user);

    }

    public boolean update(String Id, String username, String password, String role) {

         user.setId(Id);
         user.setName(username);
         user.setPassword(password);
         user.setRole(role);
        return adminDao.update(user);
    }
    public boolean delete(String Id){
        return adminDao.delete(Id);
    }



    public String getNextId(){
        String id = adminDao.getLastId() ;

        if (id != null){
            String substring = id.substring(1);
            int i = Integer.parseInt(substring);
            int newIdIndex = i + 1;
            return String.format("U%03d", newIdIndex);
        }
        return "U001";
    }
}

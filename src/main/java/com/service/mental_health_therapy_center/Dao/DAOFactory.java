package com.service.mental_health_therapy_center.Dao;

import com.service.mental_health_therapy_center.Dao.Custom.AdminDao;
import com.service.mental_health_therapy_center.Dao.Custom.Impl.*;
import com.service.mental_health_therapy_center.Dao.Custom.TherapyProgramDao;

public class DAOFactory {

    private static DAOFactory instance;

    public DAOFactory() {
    }

    public static DAOFactory getInstance() {
        return (instance == null) ? instance=new DAOFactory() : instance;
    }

    public enum DAOType {
        ADMIN,Login,Register,THERAPIST,PATIENT,THERAPISTPROGRAM,THERAPYSESSION
















    }


    public CrudDao  getDAOType(DAOType type) {

        switch (type) {
            case ADMIN:
                return new AdminDaoImpl();

            case Login:
                return new LoginDaoImpl();

            case THERAPIST:
                return new TherapistDaoImpl();

            case PATIENT:
                return new PatientDaoImpl();

            case THERAPISTPROGRAM:
                return new TherapyProgramDaoImpl();

            case THERAPYSESSION:
                return new TherapySessionDaoImpl();
            default:
                return null;

        }
    }
}

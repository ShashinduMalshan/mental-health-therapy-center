package com.service.mental_health_therapy_center.Bo.custom.Impl;

import com.service.mental_health_therapy_center.Bo.custom.PaymentBo;
import com.service.mental_health_therapy_center.Bo.custom.TherapySessionBo;
import com.service.mental_health_therapy_center.Dao.Custom.*;
import com.service.mental_health_therapy_center.Dao.DAOFactory;
import com.service.mental_health_therapy_center.configuration.FactoryConfiguration;
import com.service.mental_health_therapy_center.dto.*;
import com.service.mental_health_therapy_center.entity.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class PaymentBoImpl implements PaymentBo {

    TherapyProgramDao therapyProgramDao = (TherapyProgramDao) DAOFactory.getInstance().getDAOType(DAOFactory.DAOType.THERAPISTPROGRAM);
    TherapySessionDao therapySessionDao = (TherapySessionDao) DAOFactory.getInstance().getDAOType(DAOFactory.DAOType.THERAPYSESSION);
    TherapistDao therapistDao = (TherapistDao) DAOFactory.getInstance().getDAOType(DAOFactory.DAOType.THERAPIST);
    PatientDao patientDao = (PatientDao) DAOFactory.getInstance().getDAOType(DAOFactory.DAOType.PATIENT);
    PaymentDao paymentDao = (PaymentDao) DAOFactory.getInstance().getDAOType(DAOFactory.DAOType.PAYMENT);
    TherapySessionBo therapySessionBo =new TherapySessionBoImpl();
    Payment payment = new Payment();


    @Override
    public double getalreadyPaid(String therapyProgramId , String patientId) {
        List<Payment> allPayments = paymentDao.getAll(therapyProgramId,patientId);

        double cost = 0;

        for (Payment payment : allPayments) {
            cost += payment.getAmount();
            System.out.println(cost);
        }
        return cost;
    }
    
    @Override
        public ObservableList<TherapistDto> loadTherapist() {
            List<Therapist> allTherapist = therapistDao.getAll();
        ObservableList<TherapistDto> therapistDtos = FXCollections.observableArrayList();


             for (Therapist therapist : allTherapist) {
                 TherapistDto therapistDto = new TherapistDto();
                 therapistDto.setId(therapist.getId());
                 therapistDto.setName(therapist.getName());
                 therapistDto.setSpecialization(therapist.getSpecialization());
                 therapistDto.setContactNo(therapist.getPhone());
                 therapistDto.setEmail(therapist.getEmail());
                 therapistDto.setTherapyProgram(therapist.getTherapyProgram());

                 therapistDtos.add(therapistDto);
             }
             return therapistDtos;
        }

    @Override
    public ObservableList<TherapyProgramTm> loadTherapyProgram() {

        List<TherapyProgram> allTherapyProgram = therapyProgramDao.getAll();

        ObservableList<TherapyProgramTm> observableList = FXCollections.observableArrayList();


         for (TherapyProgram therapyProgram : allTherapyProgram) {
              TherapyProgramTm therapyProgramTm = new TherapyProgramTm(
                    therapyProgram.getId(),
                    therapyProgram.getProGramName(),
                    therapyProgram.getDuration(),
                    therapyProgram.getCost()
                );

             observableList.add(therapyProgramTm);
         }
         return observableList;
    }

    @Override
    public ArrayList<Payment> getAll() {
        return null;
    }



    @Override
    public ObservableList<PatientTm> loadTherapyPatient() {
        List<Patient> allTherapyPatient = patientDao.getAll();

        ObservableList<PatientTm> observableList = FXCollections.observableArrayList();


         for (Patient patient : allTherapyPatient) {
              PatientTm patientTm = new PatientTm(
                    patient.getId(),
                    patient.getName(),
                    patient.getAge(),
                    patient.getPhone(),
                    patient.getMedicalHistory(),
                    patient.getEmail()

                );

             observableList.add(patientTm);
         }
         return observableList;    }

    public List<TherapyProgram> getValueByName(String Name) {
        return therapyProgramDao.getProgramsByUsername(Name);
    }

    public List<Patient> getValueById(String Name) {
        return patientDao.getValueById(Name);
    }

    @Override
    public boolean save(PaymentDto paymentDto, ArrayList<SessionPaymentDto> therapySessionDtos) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = null;

        try {

            transaction = session.beginTransaction();

            payment.setId(paymentDto.getId());
            payment.setAmount(paymentDto.getAmount());
            payment.setDate(paymentDto.getDate());
            payment.setStatus(paymentDto.getStatus());
            payment.setPatient(paymentDto.getPatient());
            payment.setTherapy_program(paymentDto.getTherapyProgram());

            boolean isSavePayment = paymentDao.savePayment(session, payment);


            if (!isSavePayment) {
                transaction.rollback();
                return false;
            }

            String sessionId = therapySessionBo.getNextId();
            int counter = 0;



            for (SessionPaymentDto sessionPaymentDto : therapySessionDtos) {


                if (counter != 0){

                    String substring = sessionId.substring(1);
                    int i = Integer.parseInt(substring);
                    int newIdIndex = i + 1;
                    sessionId = String.format("S%03d", newIdIndex);
                }



                TherapySession therapySession = new TherapySession();

                therapySession.setId(sessionId);
                therapySession.setDate(sessionPaymentDto.getDate());
                therapySession.setStartTime(Time.valueOf(sessionPaymentDto.getStartTime()));
                therapySession.setEndTime(Time.valueOf(sessionPaymentDto.getEndTime()));
                therapySession.setPatient(sessionPaymentDto.getPatient());
                therapySession.setTherapist(sessionPaymentDto.getTherapist());
                therapySession.setStatus(sessionPaymentDto.getStatus());
                therapySession.setPayment(payment);


                boolean isSaveSession = therapySessionDao.saveSession(session, therapySession);
                counter++;

                if (!isSaveSession) {
                    transaction.rollback();
                    new Alert(Alert.AlertType.ERROR, "Saving Error! Please try again.");
                    return false;
                }
            }


            transaction.commit();
            return true;

        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

    public List<Therapist> getValueByTherapyProgram(TherapyProgram therapyProgram) {
        return therapistDao.getValueByTherapyProgram(therapyProgram);
    }


    public String getNextId(){
        String id = paymentDao.getLastId() ;

        if (id != null){
            String substring = id.substring(1);
            int i = Integer.parseInt(substring);
            int newIdIndex = i + 1;
            return String.format("P%03d", newIdIndex);
        }
        return "P001";
    }
}

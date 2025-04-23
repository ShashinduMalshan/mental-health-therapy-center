package com.service.mental_health_therapy_center.controller;

import com.service.mental_health_therapy_center.Bo.custom.Impl.PatientBoImpl;
import com.service.mental_health_therapy_center.Bo.custom.Impl.TherapySessionBoImpl;
import com.service.mental_health_therapy_center.Bo.custom.PatientBo;
import com.service.mental_health_therapy_center.Bo.custom.TherapySessionBo;
import com.service.mental_health_therapy_center.Exceptions.SchedulingConflictException;
import com.service.mental_health_therapy_center.dto.*;
import com.service.mental_health_therapy_center.entity.Patient;
import com.service.mental_health_therapy_center.entity.Therapist;
import com.service.mental_health_therapy_center.entity.TherapyProgram;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class TherapySessionController implements Initializable {
    public AnchorPane TherapySessionAnc;
    public TextField FeeField;
    public TextField searchField;
    public Button searchFieldBtn;
    public Button updateBtn;

    public Button resetBtn;
    public Spinner hours;
    public Spinner minutes;
    public ComboBox <String> Program;
    public ComboBox <String> comboPatient;
    public TextField AppointmentID;
    public DatePicker Date;
    public Button bookBtn;
    public TableColumn <TherapySessionTm , String> colId;
    public TableColumn <TherapySessionTm , String> colPatient;
    public TableColumn <TherapySessionTm , String> colDate;
    public TableColumn <TherapySessionTm , String> colProgram;
    public TableColumn <TherapySessionTm , String> colStartTime;
    public TableColumn <TherapySessionTm , String> colEndTime;
    public TableColumn <TherapySessionTm , String> colTherapist;
    public TableColumn <TherapySessionTm , String> colStatus;
    public TableView <TherapySessionTm> therapySessionTable;
    public Button cancelBtn;

    Map<String, String> patientMap = new HashMap<>();
    TherapySessionBo therapySessionBo = new TherapySessionBoImpl();
    PatientBo patientBo = new PatientBoImpl();


    private void configureTable() {

        colId.setCellValueFactory(new PropertyValueFactory<>("sessionId"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colStartTime.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        colEndTime.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colProgram.setCellValueFactory(cellData -> {
            TherapyProgram program = cellData.getValue().getTherapyProgram();
            return new ReadOnlyStringWrapper(program != null ? program.getProGramName() : "No Program");
        });
        colPatient.setCellValueFactory(cellData  -> {
            Patient patient = cellData.getValue().getPatient();
            return new ReadOnlyStringWrapper(patient != null ? patient.getName():"No Patient");
        });
        colTherapist.setCellValueFactory(cellData -> {
            Therapist therapist = cellData.getValue().getTherapist();
            return new ReadOnlyStringWrapper(therapist != null ? therapist.getName() : "No Therapist");
        });




        colStatus.setCellFactory(Column -> new TableCell<TherapySessionTm, String>() {

           protected void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            if (empty || item == null) {
                setText(null);
                setStyle("");
            } else {
                setText(item);

                String buttonStyle = "-fx-alignment: CENTER; " +
                                    "-fx-background-radius: 3px; " +
                                    "-fx-padding: 5px 8px; " +
                                    "-fx-font-weight: bold; " +
                                    "-fx-cursor: hand; ";

                if (item.equalsIgnoreCase("Cancelled")) {
                    setStyle(buttonStyle + "-fx-background-color: #FF0000; -fx-text-fill: #FFFFFF; -fx-background-radius: 20px; ");
                } else if (item.equalsIgnoreCase("pending")) {
                    setStyle(buttonStyle + "-fx-background-color: #FFA500; -fx-text-fill: #FFFFFF; -fx-background-radius: 20px;");
                } else if (item.equalsIgnoreCase("Paid")) {
                    setStyle(buttonStyle + "-fx-background-color: #4CAF50; -fx-text-fill: #FFFFFF; -fx-background-radius: 20px;");
                }
            }
        }});



        colId.setStyle("-fx-alignment: CENTER;");
        colPatient.setStyle("-fx-alignment: CENTER;");
        colDate.setStyle("-fx-alignment: CENTER;");
        colStartTime.setStyle("-fx-alignment: CENTER;");
        colEndTime.setStyle("-fx-alignment: CENTER;");
        colProgram.setStyle("-fx-alignment: CENTER;");
        colTherapist.setStyle("-fx-alignment: CENTER;");
    }

     public void loadTable()  {
         ArrayList<TherapySessionDto> therapySessionDtos = therapySessionBo.loadTable();
         ObservableList<TherapySessionTm> observableList = FXCollections.observableArrayList();

         for (TherapySessionDto therapySessionDto : therapySessionDtos){
             TherapySessionTm therapySessionTm = new TherapySessionTm();

             therapySessionTm.setSessionId(therapySessionDto.getSessionId());
             therapySessionTm.setDate(therapySessionDto.getDate());
             therapySessionTm.setStartTime(therapySessionDto.getStartTime());
             therapySessionTm.setEndTime(therapySessionDto.getEndTime());
             therapySessionTm.setStatus(therapySessionDto.getStatus());
             therapySessionTm.setPatient(therapySessionDto.getPatient());
             therapySessionTm.setTherapist(therapySessionDto.getTherapist());
             therapySessionTm.setTherapyProgram(therapySessionDto.getTherapyProgram());

             observableList.add(therapySessionTm);
         }
         therapySessionTable.setItems(observableList);

     }

    public void searchFieldBtnAction(ActionEvent actionEvent) {
    }

    public void updateBtnOnAction(ActionEvent actionEvent) {

            String id = AppointmentID.getText();
            String selected = comboPatient.getSelectionModel().getSelectedItem();
            String patientId = patientMap.get(selected);
            String program = Program.getValue();
            LocalDate date = Date.getValue();
            int hour = (int) hours.getValue();
            int minute = (int) minutes.getValue();
            LocalTime startTime = LocalTime.of(hour, minute);
            LocalTime endTime = startTime.plusHours(1);




        boolean isValidPatientId = !( comboPatient.getValue() == null|| comboPatient.getValue().isEmpty()) ;
        boolean isValidProgram = !( Program.getValue() == null|| Program.getValue().isEmpty()) ;
        boolean isValidDate = !( Date.getValue() == null) ;




        comboPatient.setStyle(comboPatient.getStyle() + ";-fx-border-color: #7367F0;");
        Program.setStyle(Program.getStyle() + ";-fx-border-color: #7367F0;");
        Date.setStyle(Date.getStyle() + ";-fx-border-color: #7367F0;");
        hours.setStyle(hours.getStyle() + ";-fx-border-color: #7367F0;");
        minutes.setStyle(minutes.getStyle() + ";-fx-border-color: #7367F0;");



        if (!isValidPatientId) {
            comboPatient.setStyle(comboPatient.getStyle() + ";-fx-border-color: red;");
        }
        if (!isValidProgram) {
            Program.setStyle(Program.getStyle() + ";-fx-border-color: red;");
        }
        if (!isValidDate) {
            Date.setStyle(Date.getStyle() + ";-fx-border-color: red;");
        }



        if (isValidPatientId&&isValidProgram&&isValidDate) {

            List<TherapyProgram> valueByName = therapySessionBo.getValueByName(program);
            if (valueByName.isEmpty()) {
                new Alert(Alert.AlertType.ERROR, "Therapy program not found!").show();
                return;
            }
            TherapyProgram therapyProgram = valueByName.get(0);

            List<Patient> valueById = therapySessionBo.getValueById(patientId);
            if (valueById.isEmpty()) {
                new Alert(Alert.AlertType.ERROR, "Patient not found!").show();
                return;
            }
            Patient patient = valueById.get(0);


            List<Therapist> TherapistValue = therapySessionBo.getValueByTherapyProgram(therapyProgram);

            if (TherapistValue.isEmpty()) {
                new Alert(Alert.AlertType.ERROR, "No therapists available for the selected therapy program!").show();
                return;
            }
            Therapist therapist = TherapistValue.get(0);


            TherapySessionDto therapySessionDto = new TherapySessionDto(
                    id, date, startTime, endTime, patient, therapyProgram, therapist,"Pending"
            );


             boolean isSave = therapySessionBo.update(therapySessionDto);

             if(isSave){

                refresh();
                new Alert(Alert.AlertType.INFORMATION, "Session Update").show();

            }else {
                new Alert(Alert.AlertType.ERROR, "Fail to Update Session...!").show();
            }

        }
    }




    public void saveBtnOnAction(ActionEvent actionEvent) {

         String id = AppointmentID.getText();
            String selected = comboPatient.getSelectionModel().getSelectedItem();
            String patientId = patientMap.get(selected);
            String program = Program.getValue();
            LocalDate date = Date.getValue();
            int hour = (int) hours.getValue();
            int minute = (int) minutes.getValue();
            LocalTime startTime = LocalTime.of(hour, minute);
            LocalTime endTime = startTime.plusHours(1);




        boolean isValidPatientId = !( comboPatient.getValue() == null|| comboPatient.getValue().isEmpty()) ;
        boolean isValidProgram = !( Program.getValue() == null|| Program.getValue().isEmpty()) ;
        boolean isValidDate = !( Date.getValue() == null) ;
        boolean isValidDateAndTime = true;

        ArrayList<TherapySessionDto> therapySessionDtos = therapySessionBo.loadTable();

        try {
            for (TherapySessionDto session : therapySessionDtos) {
                if (session.getDate().isEqual(date)) {
                    boolean overlaps = !(endTime.isBefore(session.getStartTime()) ||
                            startTime.isAfter(session.getEndTime()));

                    if (overlaps) {
                        isValidDateAndTime = false;
                        throw new SchedulingConflictException("This time slot is already booked. Please select a different time.");
                    }
                }
            }
        } catch (SchedulingConflictException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            return;
        }




        comboPatient.setStyle(comboPatient.getStyle() + ";-fx-border-color: #7367F0;");
        Program.setStyle(Program.getStyle() + ";-fx-border-color: #7367F0;");
        Date.setStyle(Date.getStyle() + ";-fx-border-color: #7367F0;");
        hours.setStyle(hours.getStyle() + ";-fx-border-color: #7367F0;");
        minutes.setStyle(minutes.getStyle() + ";-fx-border-color: #7367F0;");



        if (!isValidPatientId) {
            comboPatient.setStyle(comboPatient.getStyle() + ";-fx-border-color: red;");
        }
        if (!isValidProgram) {
            Program.setStyle(Program.getStyle() + ";-fx-border-color: red;");
        }
        if (!isValidDate) {
            Date.setStyle(Date.getStyle() + ";-fx-border-color: red;");
        }



        if (isValidPatientId&&isValidProgram&&isValidDate&&isValidDateAndTime) {





            List<TherapyProgram> valueByName = therapySessionBo.getValueByName(program);
            if (valueByName.isEmpty()) {
                new Alert(Alert.AlertType.ERROR, "Therapy program not found!").show();
                return;
            }
            TherapyProgram therapyProgram = valueByName.get(0);

            List<Patient> valueById = therapySessionBo.getValueById(patientId);
            if (valueById.isEmpty()) {
                new Alert(Alert.AlertType.ERROR, "Patient not found!").show();
                return;
            }
            Patient patient = valueById.get(0);


            List<Therapist> TherapistValue = therapySessionBo.getValueByTherapyProgram(therapyProgram);

            if (TherapistValue.isEmpty()) {
                new Alert(Alert.AlertType.ERROR, "No therapists available for the selected therapy program!").show();
                return;
            }
            Therapist therapist = TherapistValue.get(0);


            TherapySessionDto therapySessionDto = new TherapySessionDto(
                    id, date, startTime, endTime, patient, therapyProgram, therapist,"Pending"
            );


             boolean isSave = therapySessionBo.save(therapySessionDto);

             if(isSave){

                refresh();
                new Alert(Alert.AlertType.INFORMATION, "Session Saved").show();
            }else {
                new Alert(Alert.AlertType.ERROR, "Fail to save Session...!").show();
                 }
        }

    }
    
    
    public void cancelBtnAction(ActionEvent actionEvent) {


        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to cancel this therapy session?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.YES) {


            String id = AppointmentID.getText();
            String selected = comboPatient.getSelectionModel().getSelectedItem();
            String patientId = patientMap.get(selected);
            String program = Program.getValue();
            LocalDate date = Date.getValue();
            int hour = (int) hours.getValue();
            int minute = (int) minutes.getValue();
            LocalTime startTime = LocalTime.of(hour, minute);
            LocalTime endTime = startTime.plusHours(1);


            boolean isValidPatientId = !( comboPatient.getValue() == null|| comboPatient.getValue().isEmpty()) ;
            boolean isValidProgram = !( Program.getValue() == null|| Program.getValue().isEmpty()) ;
            boolean isValidDate = !( Date.getValue() == null) ;




            comboPatient.setStyle(comboPatient.getStyle() + ";-fx-border-color: #7367F0;");
            Program.setStyle(Program.getStyle() + ";-fx-border-color: #7367F0;");
            Date.setStyle(Date.getStyle() + ";-fx-border-color: #7367F0;");
            hours.setStyle(hours.getStyle() + ";-fx-border-color: #7367F0;");
            minutes.setStyle(minutes.getStyle() + ";-fx-border-color: #7367F0;");



            if (!isValidPatientId) {
                comboPatient.setStyle(comboPatient.getStyle() + ";-fx-border-color: red;");
            }
            if (!isValidProgram) {
                Program.setStyle(Program.getStyle() + ";-fx-border-color: red;");
            }
            if (!isValidDate) {
                Date.setStyle(Date.getStyle() + ";-fx-border-color: red;");
            }



            if (isValidPatientId&&isValidProgram&&isValidDate) {


                List<TherapyProgram> valueByName = therapySessionBo.getValueByName(program);

                if (valueByName.isEmpty()) {
                    new Alert(Alert.AlertType.ERROR, "Therapy program not found!").show();
                    return;
                }
                TherapyProgram therapyProgram = valueByName.get(0);

                List<Patient> valueById = therapySessionBo.getValueById(patientId);
                if (valueById.isEmpty()) {
                    new Alert(Alert.AlertType.ERROR, "Patient not found!").show();
                    return;
                }
                Patient patient = valueById.get(0);


                List<Therapist> TherapistValue = therapySessionBo.getValueByTherapyProgram(therapyProgram);

                if (TherapistValue.isEmpty()) {
                    new Alert(Alert.AlertType.ERROR, "No therapists available for the selected therapy program!").show();
                    return;
                }
                Therapist therapist = TherapistValue.get(0);


                TherapySessionDto therapySessionDto = new TherapySessionDto(
                        id, date, startTime, endTime, patient, therapyProgram, therapist,"Cancelled"
                );


                 boolean isSave = therapySessionBo.update(therapySessionDto);

                 if(isSave){

                    refresh();
                    new Alert(Alert.AlertType.INFORMATION, "Session Cancelled").show();
                }else {
                    new Alert(Alert.AlertType.ERROR, "Fail to Cancel Session...!").show();
                     }
            }
        }
    }

    
    

    public void resetBtnOnAction(ActionEvent actionEvent) {
        refresh();
    }

    public void OnClicked(MouseEvent mouseEvent) {

        TherapySessionTm therapySessionTm = therapySessionTable.getSelectionModel().getSelectedItem();


        if (therapySessionTm != null) {

            AppointmentID.setText(therapySessionTm.getSessionId());

            String patientDisplayText = therapySessionTm.getPatient().getId() + " : " + therapySessionTm.getPatient().getName();
            comboPatient.setValue(patientDisplayText);

            Program.setValue(therapySessionTm.getTherapyProgram().getProGramName());

            LocalTime startTime = therapySessionTm.getStartTime();
            Date.setValue(therapySessionTm.getDate());
            hours.getValueFactory().setValue(startTime.getHour());
            minutes.getValueFactory().setValue(startTime.getMinute());

        }


    }

    public void loadPrograms() {

        ObservableList<TherapyProgramTm> programs = therapySessionBo.loadTherapyProgram();
        ObservableList<String> programDisplayStrings = FXCollections.observableArrayList();

        for (TherapyProgramTm program : programs) {
            programDisplayStrings.add(program.getProGramName());
        }
        Program.setItems(programDisplayStrings);

    }

    public void loadPatient() {

            ObservableList<PatientTm> patient = therapySessionBo.loadTherapyPatient();

            ObservableList<String> displayList = FXCollections.observableArrayList();

            for (PatientTm patientTm : patient) {
                String id = patientTm.getId();
                String name = patientTm.getName();
                String displayText = id + " : " + name;

                displayList.add(displayText);
                patientMap.put(displayText, id);
            }

            comboPatient.setItems(displayList);


}

    public void refresh() {

        SpinnerValueFactory<Integer> hourFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(9, 22, 9);
        hours.setValueFactory(hourFactory);

        SpinnerValueFactory<Integer> minuteFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, 0, 5);
        minutes.setValueFactory(minuteFactory);

        loadPrograms();
        loadPatient();
        loadTable();

        AppointmentID.setText(therapySessionBo.getNextId());
        comboPatient.getSelectionModel().clearSelection();
        Program.getSelectionModel().clearSelection();
        Date.setValue(LocalDate.now());
        Date.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                setDisable(empty || date.isBefore(LocalDate.now()));
            }
        });
        hours.getValueFactory().setValue(00);
        minutes.getValueFactory().setValue(00);

    }





  @Override
    public void initialize(URL location, ResourceBundle resources) {
      try {
          refresh();
          configureTable();
      } catch (Exception e) {
          e.printStackTrace();
          new Alert(Alert.AlertType.ERROR, "Failed to initialize therapy session view").show();
        }


    }
}
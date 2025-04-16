package com.service.mental_health_therapy_center.controller;

import com.service.mental_health_therapy_center.Bo.custom.Impl.TherapistBoImpl;
import com.service.mental_health_therapy_center.Bo.custom.Impl.TherapyProgramBoImpl;
import com.service.mental_health_therapy_center.Bo.custom.TherapistBo;
import com.service.mental_health_therapy_center.Bo.custom.TherapyProgramBo;
import com.service.mental_health_therapy_center.dto.*;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class TherapistController implements Initializable {
    public AnchorPane therapistAnc;
    public TextField idField;
    public TextField nameField;
    public TextField specializationField;
    public TextField contactNoField;
    public TextField emailField;
    public TextField searchField;
    public Button searchFieldBtn;
    public Button updateBtn;
    public Button deleteBtn;
    public Button saveBtn;
    public TableView <TherapistTm> therapistTable;
    public TableColumn <TherapistTm ,String>colId;
    public TableColumn <TherapistTm ,String>colName;
    public TableColumn <TherapistTm ,String>colSpecialization;
    public TableColumn <TherapistTm ,Integer>colContactNo;
    public TableColumn <TherapistTm ,String>colEmail;
    public TableColumn  <TherapistTm ,String>colPlan;

    public Button resetBtn;
    public ComboBox <String>planId;

    TherapistBo therapistBo = new TherapistBoImpl();
    TherapyProgramBo therapyProgramBo = new TherapyProgramBoImpl();



    private void configureTable() {

        colId.setCellValueFactory(new PropertyValueFactory<>("Id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        colSpecialization.setCellValueFactory(new PropertyValueFactory<>("Specialization"));
        colContactNo.setCellValueFactory(new PropertyValueFactory<>("ContactNo"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("Email"));
        colPlan.setCellValueFactory(cellData -> {
            TherapyProgram program = cellData.getValue().getTherapyProgram();
            return new ReadOnlyStringWrapper(program != null ? program.getProGramName() : "No Program");
        });

    }

    public void loadTable()  {

        ArrayList<TherapistDto> therapistDtos = therapistBo.loadTable();
        ObservableList<TherapistTm> observableList = FXCollections.observableArrayList();



        for (TherapistDto therapistDto : therapistDtos) {

            TherapistTm therapistTm = new TherapistTm(
                therapistDto.getId(),
                therapistDto.getName(),
                therapistDto.getSpecialization(),
                therapistDto.getContactNo(),
                therapistDto.getEmail(),
                therapistDto.getTherapyProgram()
            );
            observableList.add(therapistTm);
        }

        therapistTable.setItems(observableList);

    }

    public void searchFieldBtnAction(ActionEvent actionEvent) {
    }

    public void updateBtnOnAction(ActionEvent actionEvent) {

        String id = idField.getText();
        String name = nameField.getText();
        String specialization = specializationField.getText();
        String stringContact = contactNoField.getText();
        String email = emailField.getText();
        String plan  = planId.getValue();




        int contactNo = 0;

        String namePattern = "^[A-Za-z ]+$";
        String specializationPattern = "^[A-Za-z ]+$";
        String contactPattern = "^[0-9]{7,12}$";
        String EmailPattern = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";





        boolean isValidName = name.matches(namePattern);
        boolean isValidSpecialization =specialization.matches(specializationPattern);
        boolean isValidContact = stringContact.matches(contactPattern);
        boolean isValidEmail = email.matches(EmailPattern);
        boolean isValidPlan = planId.getValue().isEmpty() || planId.getValue() == null;

        nameField.setStyle(nameField.getStyle() + ";-fx-border-color: #7367F0;");
        specializationField.setStyle(specializationField.getStyle() + ";-fx-border-color: #7367F0;");
        contactNoField.setStyle(contactNoField.getStyle() + ";-fx-border-color: #7367F0;");
        emailField.setStyle(emailField.getStyle() + ";-fx-border-color: #7367F0;");
        planId.setStyle(planId.getStyle() + ";-fx-border-color: #7367F0");




         if (!isValidName) {
            nameField.setStyle(nameField.getStyle() + ";-fx-border-color: red;");
        }

         if (!isValidSpecialization) {
            specializationField.setStyle(specializationField.getStyle() + ";-fx-border-color: red;");
        }


         if (!isValidContact) {
            contactNoField.setStyle(contactNoField.getStyle() + ";-fx-border-color: red;");
        }else {
              contactNo = Integer.parseInt(stringContact);
        }


         if (!isValidEmail) {
            emailField.setStyle(emailField.getStyle() + ";-fx-border-color: red;");
        }

         if (!isValidPlan) {
             planId.setStyle(planId.getStyle() + ";-fx-border-color: red;");
         }

        if (isValidName&&isValidSpecialization&&isValidContact&&isValidEmail) {

             List<TherapyProgram> valueByName = therapyProgramBo.getValuByName(plan);
            TherapyProgram therapyProgram = valueByName.get(0);


            TherapistDto therapistDto = new TherapistDto(
                id,name,specialization,contactNo,email,therapyProgram


            );

            boolean isSave = therapistBo.update(therapistDto);

            if(isSave){

                refresh();
                new Alert(Alert.AlertType.INFORMATION, "Therapist Update").show();
            }else {
                new Alert(Alert.AlertType.ERROR, "Fail to Update Therapist...!").show();
                 }

            }
    }

    public void deleteBtnOnAction(ActionEvent actionEvent) {

        String Id = therapistTable.getSelectionModel().getSelectedItem().getId();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.YES) {

            boolean isDelete = therapistBo.delete(Id);

             if(isDelete){
                refresh();
                new Alert(Alert.AlertType.INFORMATION, "Therapist Delete").show();
             }else {
                new Alert(Alert.AlertType.ERROR, "Fail to Delete Therapist...!").show();
             }
        }
    }

    public void saveBtnOnAction(ActionEvent actionEvent) {

        String id = idField.getText();
        String name = nameField.getText();
        String specialization = specializationField.getText();
        String stringContact = contactNoField.getText();
        String email = emailField.getText();
        String plan  = planId.getValue();
        System.out.println(plan);

        int contactNo = 0;

        String namePattern = "^[A-Za-z ]+$";
        String specializationPattern = "^[A-Za-z ]+$";
        String contactPattern = "^[0-9]{7,12}$";
        String EmailPattern = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";




        boolean isValidName = name.matches(namePattern);
        boolean isValidSpecialization =specialization.matches(specializationPattern);
        boolean isValidContact = stringContact.matches(contactPattern);
        boolean isValidEmail = email.matches(EmailPattern);
        boolean isValidPlan = !( planId.getValue() == null|| planId.getValue().isEmpty()) ;


        nameField.setStyle(nameField.getStyle() + ";-fx-border-color: #7367F0;");
        specializationField.setStyle(specializationField.getStyle() + ";-fx-border-color: #7367F0;");
        contactNoField.setStyle(contactNoField.getStyle() + ";-fx-border-color: #7367F0;");
        emailField.setStyle(emailField.getStyle() + ";-fx-border-color: #7367F0;");
        planId.setStyle(planId.getStyle() + ";-fx-border-color: #7367F0;");




         if (!isValidName) {
            nameField.setStyle(nameField.getStyle() + ";-fx-border-color: red;");
        }

         if (!isValidSpecialization) {
            specializationField.setStyle(specializationField.getStyle() + ";-fx-border-color: red;");
        }


         if (!isValidContact) {
            contactNoField.setStyle(contactNoField.getStyle() + ";-fx-border-color: red;");
        }else {
              contactNo = Integer.parseInt(stringContact);
        }


         if (!isValidEmail) {
            emailField.setStyle(emailField.getStyle() + ";-fx-border-color: red;");
        }


         if (!isValidPlan) {
             planId.setStyle(planId.getStyle() + ";-fx-border-color: red;");
         }



        if (isValidName&&isValidSpecialization&&isValidContact&&isValidEmail&&isValidPlan) {

            List<TherapyProgram> valueByName = therapyProgramBo.getValuByName(plan);
            TherapyProgram therapyProgram = valueByName.get(0);


            TherapistDto therapistDto = new TherapistDto(
                id,name,specialization,contactNo,email,therapyProgram


            );

            boolean isSave = therapistBo.save(therapistDto);

            if(isSave){

                refresh();
                new Alert(Alert.AlertType.INFORMATION, "Therapist Saved").show();
            }else {
                new Alert(Alert.AlertType.ERROR, "Fail to save Therapist...!").show();
                 }

            }
    }


    public void refresh(){
        loadTable();
        idField.setText(therapistBo.getNextId());
        nameField.setText("");
        specializationField.setText("");
        contactNoField.setText("");
        emailField.setText("");
        planId.getSelectionModel().clearSelection();

        ObservableList<TherapyProgramTm> programs = therapistBo.loadTherapyProgram();


        ObservableList<String> programDisplayStrings = FXCollections.observableArrayList();

        for (TherapyProgramTm program : programs) {
            programDisplayStrings.add(program.getProGramName());
        }

        planId.setItems(programDisplayStrings);





    }

    public void OnClicked(MouseEvent mouseEvent) {

        TherapistTm therapistTm = therapistTable.getSelectionModel().getSelectedItem();

        if (therapistTm != null) {
            idField.setText(therapistTm.getId());
            nameField.setText(therapistTm.getName());
            specializationField.setText(therapistTm.getSpecialization());
            emailField.setText(therapistTm.getEmail());
            contactNoField.setText(String.valueOf(therapistTm.getContactNo()));
            planId.setValue(therapistTm.getTherapyProgram().getProGramName());
        }


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        try {
            configureTable();
            refresh();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void resetBtnOnAction(ActionEvent actionEvent) {
        refresh();
    }
}

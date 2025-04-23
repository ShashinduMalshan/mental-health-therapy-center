package com.service.mental_health_therapy_center.controller;

import com.service.mental_health_therapy_center.Bo.custom.Impl.PatientBoImpl;
import com.service.mental_health_therapy_center.Bo.custom.PatientBo;
import com.service.mental_health_therapy_center.dto.PatientDto;
import com.service.mental_health_therapy_center.dto.PatientTm;
import com.service.mental_health_therapy_center.dto.TherapySessionTm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class PatientController implements Initializable {


    public AnchorPane therapistAnc;
    public TextField idField;
    public TextField nameField;
    public TextField medHisField;
    public TextField ageField;
    public TextField contactNoField;
    public TextField emailField;
    public TextField searchField;
    public Button searchFieldBtn;
    public Button updateBtn;
    public Button deleteBtn;
    public Button saveBtn;
    public Button resetBtn;
    public TableView <PatientTm> patientTable;
    public TableColumn <PatientTm,String> colId;
    public TableColumn <PatientTm,String> colName;
    public TableColumn <PatientTm, Integer> colAge;
    public TableColumn <PatientTm,Integer> colContactNo;
    public TableColumn <PatientTm,String> colEmail;
    public TableColumn <PatientTm,String> colMedicalHistory;

    PatientBo patientBo = new PatientBoImpl();
    ObservableList<PatientTm> observableList;


    private void configureTable() {

        colId.setCellValueFactory(new PropertyValueFactory<>("Id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        colAge.setCellValueFactory(new PropertyValueFactory<>("Age"));
        colContactNo.setCellValueFactory(new PropertyValueFactory<>("phone"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("Email"));
        colMedicalHistory.setCellValueFactory(new PropertyValueFactory<>("MedicalHistory"));

        colId.setStyle("-fx-alignment: CENTER;");
        colName.setStyle("-fx-alignment: CENTER;");
        colAge.setStyle("-fx-alignment: CENTER;");
        colContactNo.setStyle("-fx-alignment: CENTER;");
        colEmail.setStyle("-fx-alignment: CENTER;");
        colMedicalHistory.setStyle("-fx-alignment: CENTER;");


    }

    public void loadTable()  {

        ArrayList<PatientDto> patientDtos = patientBo.loadTable();
        observableList = FXCollections.observableArrayList();



        for (PatientDto patientDto : patientDtos) {

            PatientTm patientTm = new PatientTm(
                patientDto.getId(),
                patientDto.getName(),
                patientDto.getAge(),
                patientDto.getPhone(),
                patientDto.getEmail(),
                patientDto.getMedicalHistory()
            );
            observableList.add(patientTm);
        }

        patientTable.setItems(observableList);

    }

    public void refresh(){
        loadTable();
        idField.setText(patientBo.getNextId());
        nameField.clear();
        medHisField.clear();
        ageField.clear();
        contactNoField.clear();
        emailField.clear();
        searchField.clear();

    }

    public void updateBtnOnAction(ActionEvent actionEvent) {

        String id = idField.getText();
        String name = nameField.getText();
        String age = ageField.getText();
        String phone = contactNoField.getText();
        String email = emailField.getText();
        String medicalHistory = medHisField.getText();

        String namePattern = "^[A-Za-z ]+$";
        String agePattern = "^(1[0-1][0-9]|120|[1-9][0-9]?)$";
        String medicalHistoryPattern = "^[A-Za-z ]+$";
        String phonePattern = "^[0-9]{7,12}$";
        String EmailPattern = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

        boolean isValidName = name.matches(namePattern);
        boolean isValidAge =age.matches(agePattern);
        boolean isValidMedicalHistory =medicalHistory.matches(medicalHistoryPattern);
        boolean isValidPhone = phone.matches(phonePattern);
        boolean isValidEmail = email.matches(EmailPattern);

        nameField.setStyle(nameField.getStyle() + ";-fx-border-color: #7367F0;");
        ageField.setStyle(ageField.getStyle() + ";-fx-border-color: #7367F0;");
        medHisField.setStyle(medHisField.getStyle() + ";-fx-border-color: #7367F0;");
        contactNoField.setStyle(contactNoField.getStyle() + ";-fx-border-color: #7367F0;");
        emailField.setStyle(emailField.getStyle() + ";-fx-border-color: #7367F0;");

        if (!isValidName) {
            nameField.setStyle(nameField.getStyle() + ";-fx-border-color: red;");
        }

        if (!isValidAge) {
            ageField.setStyle(ageField.getStyle() + ";-fx-border-color: red;");
        }

        if (!isValidMedicalHistory) {
            medHisField.setStyle(medHisField.getStyle() + ";-fx-border-color: red;");
        }

        if (!isValidEmail) {
            emailField.setStyle(emailField.getStyle() + ";-fx-border-color: red;");
        }

        if (!isValidPhone) {
            contactNoField.setStyle(contactNoField.getStyle() + ";-fx-border-color: red;");
        }


        if (isValidName&&isValidAge&&isValidMedicalHistory&&isValidPhone&&isValidEmail) {

            int concertIntAge = Integer.parseInt(age);
            int concertIntPhone = Integer.parseInt(phone);

            PatientDto patientDto = new PatientDto(
                    id,
                    name,
                    concertIntAge,
                    concertIntPhone,
                    email,
                    medicalHistory
                    );

            boolean isSave = patientBo.update(patientDto);
            if (isSave) {
                refresh();
                new Alert(Alert.AlertType.INFORMATION, "Update").show();
            }else {
                new Alert(Alert.AlertType.ERROR, "Fail to Update Patient...!").show();
            }
        }
    }

    public void deleteBtnOnAction(ActionEvent actionEvent) {

        String id = patientTable.getSelectionModel().getSelectedItem().getId();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this Patient?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent()&&result.get() == ButtonType.YES) {
            boolean isDelete = patientBo.delete(id);
            if(isDelete){
                refresh();
                new Alert(Alert.AlertType.INFORMATION, "Patient Delete").show();
            }else {
                new Alert(Alert.AlertType.ERROR, "Fail to Delete Patient...!").show();
             }
        }


    }

    public void saveBtnOnAction(ActionEvent actionEvent) {

        String id = idField.getText();
        String name = nameField.getText();
        String age = ageField.getText();
        String phone = contactNoField.getText();
        String email = emailField.getText();
        String medicalHistory = medHisField.getText();

        String namePattern = "^[A-Za-z ]+$";
        String agePattern = "^(1[0-1][0-9]|120|[1-9][0-9]?)$";
        String medicalHistoryPattern = "^[A-Za-z ]+$";
        String phonePattern = "^[0-9]{7,12}$";
        String EmailPattern = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

        boolean isValidName = name.matches(namePattern);
        boolean isValidAge =age.matches(agePattern);
        boolean isValidMedicalHistory =medicalHistory.matches(medicalHistoryPattern);
        boolean isValidPhone = phone.matches(phonePattern);
        boolean isValidEmail = email.matches(EmailPattern);

        nameField.setStyle(nameField.getStyle() + ";-fx-border-color: #7367F0;");
        ageField.setStyle(ageField.getStyle() + ";-fx-border-color: #7367F0;");
        medHisField.setStyle(medHisField.getStyle() + ";-fx-border-color: #7367F0;");
        contactNoField.setStyle(contactNoField.getStyle() + ";-fx-border-color: #7367F0;");
        emailField.setStyle(emailField.getStyle() + ";-fx-border-color: #7367F0;");

        if (!isValidName) {
            nameField.setStyle(nameField.getStyle() + ";-fx-border-color: red;");
        }

        if (!isValidAge) {
            ageField.setStyle(ageField.getStyle() + ";-fx-border-color: red;");
        }

        if (!isValidMedicalHistory) {
            medHisField.setStyle(medHisField.getStyle() + ";-fx-border-color: red;");
        }

        if (!isValidEmail) {
            emailField.setStyle(emailField.getStyle() + ";-fx-border-color: red;");
        }

        if (!isValidPhone) {
            contactNoField.setStyle(contactNoField.getStyle() + ";-fx-border-color: red;");
        }


        if (isValidName&&isValidAge&&isValidMedicalHistory&&isValidPhone&&isValidEmail) {

            int concertIntAge = Integer.parseInt(age);
            int concertIntPhone = Integer.parseInt(phone);

            PatientDto patientDto = new PatientDto(
                    id,
                    name,
                    concertIntAge,
                    concertIntPhone,
                    email,
                    medicalHistory
                    );

            boolean isSave = patientBo.save(patientDto);
            if (isSave) {
                refresh();
                new Alert(Alert.AlertType.INFORMATION, "Saved").show();
            }else {
                new Alert(Alert.AlertType.ERROR, "Fail to Save Patient...!").show();
            }
        }

    }

    public void resetBtnOnAction(ActionEvent actionEvent) {
        refresh();
    }

    public void OnClicked(MouseEvent mouseEvent) {

        PatientTm patientTm = patientTable.getSelectionModel().getSelectedItem();

        if (patientTm != null) {
            idField.setText(patientTm.getId());
            nameField.setText(patientTm.getName());
            medHisField.setText(patientTm.getMedicalHistory());
            ageField.setText(String.valueOf(patientTm.getAge()));
            emailField.setText(patientTm.getEmail());
            contactNoField.setText(String.valueOf(patientTm.getPhone()));
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

    public void searchFieldBtnAction(ActionEvent actionEvent) {
         String value = searchField.getText();
                if (value.isEmpty()) {
                    return;
                }

                FilteredList<PatientTm> filteredData = new FilteredList<>(observableList, row -> {
                    return row.getId().equalsIgnoreCase(value) || row.getName().equalsIgnoreCase(value) ||
                            String.valueOf(row.getPhone()).equals(value);
                });

                patientTable.setItems(filteredData);
        }
}


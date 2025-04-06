package com.service.mental_health_therapy_center.controller;

import com.service.mental_health_therapy_center.dto.TherapistDto;
import com.service.mental_health_therapy_center.dto.TherapistTm;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class TherapistController{
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
    public TableView <TherapistTm>therapistTable;
    public TableColumn <TherapistTm ,String>colId;
    public TableColumn <TherapistTm ,String>colName;
    public TableColumn <TherapistTm ,String>colSpecialization;
    public TableColumn <TherapistTm ,Integer>colContactNo;
    public TableColumn <TherapistTm ,String>colEmail;

    public void searchFieldBtnAction(ActionEvent actionEvent) {
    }

    public void updateBtnOnAction(ActionEvent actionEvent) {
    }

    public void deleteBtnOnAction(ActionEvent actionEvent) {
    }

    public void saveBtnOnAction(ActionEvent actionEvent) {

        String id = idField.getText();
        String name = nameField.getText();
        String specialization = specializationField.getText();
        int contactNo = Integer.parseInt(contactNoField.getText());
        String email = emailField.getText();

        TherapistDto therapistDto = new TherapistDto(
                id,name,specialization,contactNo,email
        );



    }

    public void OnClicked(MouseEvent mouseEvent) {
    }

}

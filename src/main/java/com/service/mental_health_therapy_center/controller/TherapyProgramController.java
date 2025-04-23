package com.service.mental_health_therapy_center.controller;

import com.service.mental_health_therapy_center.Bo.custom.Impl.TherapistBoImpl;
import com.service.mental_health_therapy_center.Bo.custom.Impl.TherapyProgramBoImpl;
import com.service.mental_health_therapy_center.Bo.custom.TherapistBo;
import com.service.mental_health_therapy_center.Bo.custom.TherapyProgramBo;
import com.service.mental_health_therapy_center.dto.*;
import com.service.mental_health_therapy_center.entity.TherapyProgram;
import jakarta.persistence.Id;
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

public class TherapyProgramController implements Initializable {
    public AnchorPane TherapyProgramAnc;
    public TextField IdField;
    public TextField DurationField;
    public TextField nameField;
    public TextField FeeField;
    public TextField searchField;
    public Button searchFieldBtn;
    public Button updateBtn;
    public Button deleteBtn;
    public Button saveBtn;
    public Button resetBtn;
    public TableView <TherapyProgramTm>therapyProgramTable;
    public TableColumn  <TherapyProgramTm ,String> colDuration;
    public TableColumn  <TherapyProgramTm ,Double> colFee;
    public TableColumn   <TherapyProgramTm ,String> colName;
    public TableColumn  <TherapyProgramTm ,String> colId;

    TherapyProgramBo therapyProgramBo = new TherapyProgramBoImpl();
    ObservableList<TherapyProgramTm> observableList;


    private void configureTable() {

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("ProGramName"));
        colDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        colFee.setCellValueFactory(new PropertyValueFactory<>("cost"));


    }


    public void loadTable()  {

        ArrayList<TherapyProgramDto> therapyProgramDtos = therapyProgramBo.loadTable();
        observableList = FXCollections.observableArrayList();



        for (TherapyProgramDto therapyProgramDto : therapyProgramDtos) {

            TherapyProgramTm therapyProgramTm = new TherapyProgramTm(
                therapyProgramDto.getId(),
                therapyProgramDto.getProgramName(),
                therapyProgramDto.getDuration(),
                therapyProgramDto.getCost()

            );
            observableList.add(therapyProgramTm);
        }

        therapyProgramTable.setItems(observableList);

    }


    public void searchFieldBtnAction(ActionEvent actionEvent) {
         String value = searchField.getText();
                if (value.isEmpty()) {
                    return;
                }

                FilteredList<TherapyProgramTm> filteredData = new FilteredList<>(observableList, row -> {
                    return row.getId().equalsIgnoreCase(value) || row.getProGramName().equalsIgnoreCase(value);
                });

                therapyProgramTable.setItems(filteredData);
    }

    public void saveBtnOnAction(ActionEvent actionEvent) {

        String id = IdField.getText();
        String name = nameField.getText();
        String duration = DurationField.getText();
        String fee = FeeField.getText();
        double feeDouble = 0;


        String idPattern = "^[A-Za-z0-9]+$";
        String namePattern = "^[A-Za-z ]+$";
        String durationPattern = "^[0-9]+\\s[A-Za-z]+$";
        String feePattern = "^[0-9]+(\\.[0-9]{1,2})?$";

        boolean isValidId = id.matches(idPattern);
        boolean isValidName = name.matches(namePattern);
        boolean isValidDuration = duration.matches(durationPattern);
        boolean isValidFee = fee.matches(feePattern);

        nameField.setStyle(nameField.getStyle() + ";-fx-border-color: #7367F0;");
        DurationField.setStyle(DurationField.getStyle() + ";-fx-border-color: #7367F0;");
        FeeField.setStyle(FeeField.getStyle() + ";-fx-border-color: #7367F0;");




         if (!isValidName) {
            nameField.setStyle(nameField.getStyle() + ";-fx-border-color: red;");
        }

         if (!isValidDuration) {
            DurationField.setStyle(DurationField.getStyle() + ";-fx-border-color: red;");
        }


         if (!isValidFee) {
            FeeField.setStyle(FeeField.getStyle() + ";-fx-border-color: red;");
        }else{
              feeDouble = Double.parseDouble(fee);
        }



        if (isValidId&&isValidName&&isValidDuration&&isValidFee) {

             TherapyProgramDto therapyProgramDto = new TherapyProgramDto(
                     id,name,duration,feeDouble
             );

             boolean isSave = therapyProgramBo.save(therapyProgramDto);
             if (isSave) {
                refresh();
                new Alert(Alert.AlertType.INFORMATION, "Therapist Saved").show();
            }else {
                new Alert(Alert.AlertType.ERROR, "Fail to save Therapist...!").show();
                 }
        }

    }


    public void refresh(){
        loadTable();
        IdField.setText(therapyProgramBo.getNextId());
        nameField.clear();
        DurationField.clear();
        FeeField.clear();
        searchField.clear();
    }


    public void updateBtnOnAction(ActionEvent actionEvent) {


        String id = IdField.getText();
        String name = nameField.getText();
        String duration = DurationField.getText();
        String fee = FeeField.getText();
        double feeDouble = 0;


        String idPattern = "^[A-Za-z0-9]+$";
        String namePattern = "^[A-Za-z ]+$";
        String durationPattern = "^[0-9]+\\s[A-Za-z]+$";
        String feePattern = "^[0-9]+(\\.[0-9]{1,2})?$";

        boolean isValidId = id.matches(idPattern);
        boolean isValidName = name.matches(namePattern);
        boolean isValidDuration = duration.matches(durationPattern);
        boolean isValidFee = fee.matches(feePattern);

        nameField.setStyle(nameField.getStyle() + ";-fx-border-color: #7367F0;");
        DurationField.setStyle(DurationField.getStyle() + ";-fx-border-color: #7367F0;");
        FeeField.setStyle(FeeField.getStyle() + ";-fx-border-color: #7367F0;");




         if (!isValidName) {
            nameField.setStyle(nameField.getStyle() + ";-fx-border-color: red;");
        }

         if (!isValidDuration) {
            DurationField.setStyle(DurationField.getStyle() + ";-fx-border-color: red;");
        }


         if (!isValidFee) {
            FeeField.setStyle(FeeField.getStyle() + ";-fx-border-color: red;");
        }else{
              feeDouble = Double.parseDouble(fee);
        }



        if (isValidId&&isValidName&&isValidDuration&&isValidFee) {

             TherapyProgramDto therapyProgramDto = new TherapyProgramDto(
                     id,name,duration,feeDouble
             );

             boolean isSave = therapyProgramBo.update(therapyProgramDto);
             if (isSave) {
                refresh();
                new Alert(Alert.AlertType.INFORMATION, "Therapist Saved").show();
            }else {
                new Alert(Alert.AlertType.ERROR, "Fail to save Therapist...!").show();
                 }
        }

    }


    public void deleteBtnOnAction(ActionEvent actionEvent) {

        String Id = therapyProgramTable.getSelectionModel().getSelectedItem().getId();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.YES) {

            boolean isDelete = therapyProgramBo.delete(Id);

             if(isDelete){
                refresh();
                new Alert(Alert.AlertType.INFORMATION, "Therapy Program Delete").show();
             }else {
                new Alert(Alert.AlertType.ERROR, "Fail to Delete Therapy Program...!").show();
             }
        }
    }


    public void resetBtnOnAction(ActionEvent actionEvent) {
        refresh();
    }


    public void OnClicked(MouseEvent mouseEvent) {

        TherapyProgramTm therapyProgramTm = therapyProgramTable.getSelectionModel().getSelectedItem();

        if (therapyProgramTm != null) {
            IdField.setText(therapyProgramTm.getId());
            nameField.setText(therapyProgramTm.getProGramName());
            DurationField.setText(therapyProgramTm.getDuration());
            FeeField.setText(String.valueOf(therapyProgramTm.getCost()));
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
}

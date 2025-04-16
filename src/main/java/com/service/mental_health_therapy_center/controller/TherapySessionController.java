package com.service.mental_health_therapy_center.controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class TherapySessionController implements Initializable {
    public AnchorPane TherapySessionAnc;
    public TextField FeeField;
    public TextField searchField;
    public Button searchFieldBtn;
    public Button updateBtn;
    public Button deleteBtn;
    public Button saveBtn;
    public Button resetBtn;
    public TableView therapyProgramTable;
    public Spinner hours;
    public Spinner minutes;

    public void searchFieldBtnAction(ActionEvent actionEvent) {
    }

    public void updateBtnOnAction(ActionEvent actionEvent) {
    }

    public void deleteBtnOnAction(ActionEvent actionEvent) {
    }

    public void saveBtnOnAction(ActionEvent actionEvent) {
    }

    public void resetBtnOnAction(ActionEvent actionEvent) {
    }

    public void OnClicked(MouseEvent mouseEvent) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SpinnerValueFactory<Integer> hourFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(9, 22, 9);
        hours.setValueFactory(hourFactory);

        SpinnerValueFactory<Integer> minuteFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, 0, 15);
        minutes.setValueFactory(minuteFactory);
    }
}

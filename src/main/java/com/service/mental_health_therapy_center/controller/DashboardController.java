package com.service.mental_health_therapy_center.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {
    @FXML
    public Label test;
    public AnchorPane DashAnc;
    public AnchorPane loadAnc;
    public Button logoutButton;
    public Button PerformanceAnalytics;
    public Button dashboardButton;
    public Button patientsButton;
    public Button appointmentsButton;
    public Button adminButton;
    public Button performanceAnalyticsButton;
    public Button paymentButton;
    public Button treatmentPlansButton;
    public Button therapistsButton;
    @FXML
    private Label userNameLbl;
    @FXML
    private Label roleLbl;

    String userName;
    String role;


    @FXML
    private void initialize() {


    }


    public void setLabel(String username, String role) {
        this.userName = username;
        this.role = role;
        userNameLbl.setText(userName);
        roleLbl.setText(role);
         if ("Receptionist".equals(role)) {
        adminButton.setDisable(true);
        therapistsButton.setDisable(true);
        treatmentPlansButton.setDisable(true);
}


    }


    public void adminBtnOnAction(MouseEvent mouseEvent) {navigateTo("/view/Admin.fxml");}

    public void therapistsBtnOnAction(MouseEvent mouseEvent) {navigateTo("/view/Therapist.fxml");}

    public void PatientsOnAction(MouseEvent mouseEvent) {navigateTo("/view/Patient.fxml");}

    public void treatmentPlanOnAction(MouseEvent mouseEvent) {navigateTo("/view/TherapyProgram.fxml");}

    public void AppointmentsOnAction(MouseEvent mouseEvent) {navigateTo("/view/TherapySession.fxml");}

    public void paymentOnAction(MouseEvent mouseEvent) {navigateTo("/view/Payment.fxml");}

    public void PerformanceAnalyticsOnAction(MouseEvent mouseEvent) {navigateTo("/view/MonthlyRevenueReport.fxml");}

    public void navigateTo(String fxmlPath) {

        try {
            loadAnc.getChildren().clear();
            AnchorPane load = FXMLLoader.load(getClass().getResource(fxmlPath));


            load.prefWidthProperty().bind(loadAnc.widthProperty());
            load.prefHeightProperty().bind(loadAnc.heightProperty());

            AnchorPane.setTopAnchor(load, 0.0);
            AnchorPane.setRightAnchor(load, 0.0);
            AnchorPane.setBottomAnchor(load, 0.0);
            AnchorPane.setLeftAnchor(load, 0.0);

            loadAnc
                    .getChildren().add(load);

        } catch (IOException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load page!").show();
        }
    }

    public void logoutButtonAction(MouseEvent mouseEvent) {
          try {
            DashAnc.getChildren().clear();
            AnchorPane load = FXMLLoader.load(getClass().getResource("/view/login.fxml"));


            load.prefWidthProperty().bind(DashAnc.widthProperty());
            load.prefHeightProperty().bind(DashAnc.heightProperty());

            AnchorPane.setTopAnchor(load, 0.0);
            AnchorPane.setRightAnchor(load, 0.0);
            AnchorPane.setBottomAnchor(load, 0.0);
            AnchorPane.setLeftAnchor(load, 0.0);

            DashAnc.getChildren().add(load);

        } catch (IOException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load page!").show();
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {



        try {
            loadAnc.getChildren().clear();
            AnchorPane load = FXMLLoader.load(getClass().getResource("/view/PerformanceAnalytics.fxml"));


            load.prefWidthProperty().bind(loadAnc.widthProperty());
            load.prefHeightProperty().bind(loadAnc.heightProperty());

            AnchorPane.setTopAnchor(load, 0.0);
            AnchorPane.setRightAnchor(load, 0.0);
            AnchorPane.setBottomAnchor(load, 0.0);
            AnchorPane.setLeftAnchor(load, 0.0);

            loadAnc
                    .getChildren().add(load);

        } catch (IOException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load page!").show();
        }

    }


}

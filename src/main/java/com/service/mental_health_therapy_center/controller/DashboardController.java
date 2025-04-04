package com.service.mental_health_therapy_center.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class DashboardController {
    @FXML
    public Label test;
    public AnchorPane DashAnc;
    public AnchorPane loadAnc;
    @FXML
    private Label userNameLbl;
    @FXML
    private Label roleLbl;

    String userName;
    String role;


    @FXML
    private void initialize() {

    }


    public void
    setLabel(String username, String role) {
        this.userName = username;
        this.role = role;
        userNameLbl.setText(userName);
        roleLbl.setText(role);

    }


    public void adminBtnOnAction(MouseEvent mouseEvent) {navigateTo("/view/Admin.fxml");}

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

}

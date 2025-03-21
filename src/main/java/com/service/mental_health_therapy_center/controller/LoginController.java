package com.service.mental_health_therapy_center.controller;

import com.service.mental_health_therapy_center.Bo.custom.Impl.LoginBoImpl;
import com.service.mental_health_therapy_center.Bo.custom.LoginBo;
import com.service.mental_health_therapy_center.Main;
import com.service.mental_health_therapy_center.dto.LoginDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    public ComboBox roleComboBox;
    public Button registorButton;
    public AnchorPane loginAnc;
    @FXML
    private Button loginButton;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField usernameField;

    LoginBo loginBo = new LoginBoImpl(); // Business logic layer instance

    @FXML
    void handleLoginButtonAction(ActionEvent event) {
        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();
        String role = roleComboBox.getValue().toString();

        System.out.println(username+" "+password+" "+role);


        // Encrypt password before saving
        LoginDto loginDto = new LoginDto(username, password,role);

        if (loginBo.authenticateUser(loginDto)) {
            System.out.println("login success");
        }
        else {
            System.out.println("login failed");
        }



    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        roleComboBox.getItems().addAll("Admin", "Reception");
        roleComboBox.setValue("Admin");
}

    public void handleregistorButtonAction(ActionEvent actionEvent) throws IOException {
        loginAnc.getChildren().clear();
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/Register.fxml"));
        loginAnc.getChildren().add(load);


    }
}

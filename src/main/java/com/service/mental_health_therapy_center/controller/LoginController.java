package com.service.mental_health_therapy_center.controller;

import com.service.mental_health_therapy_center.Bo.custom.Impl.LoginBoImpl;
import com.service.mental_health_therapy_center.Bo.custom.LoginBo;
import com.service.mental_health_therapy_center.Exceptions.LoginException;
import com.service.mental_health_therapy_center.Main;
import com.service.mental_health_therapy_center.dto.LoginDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    public ComboBox <String>  roleComboBox;
    public VBox loginAnc;
    public AnchorPane loginAncerPane;
    @FXML
    private Button loginButton;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField usernameField;

     String username;
     String password;
     String role ;

    LoginBo loginBo = new LoginBoImpl(); // Business logic layer instance



    public void loginAction(MouseEvent mouseEvent) throws IOException {
        username = usernameField.getText().trim();
        password = passwordField.getText().trim();
        role = roleComboBox.getValue();

        try {
            if (username.isEmpty() || password.isEmpty() || role == null) {
                throw new LoginException("Username, password, or role cannot be empty.");
            }


            LoginDto loginDto = new LoginDto(username, password, role);

            if (loginBo.authenticateUser(loginDto)) {
                System.out.println("Login success");

                FXMLLoader load = new FXMLLoader(getClass().getResource("/view/DashBord.fxml"));
                AnchorPane dashboard = load.load();

                DashboardController dashboardController = load.getController();
                dashboardController.setLabel(username, role);

                loginAncerPane.getChildren().clear();
                loginAncerPane.getChildren().add(dashboard);
                new Alert(Alert.AlertType.CONFIRMATION, "Login Success", ButtonType.OK).show();

            } else {
                throw new LoginException("Invalid username or password or role");
            }
        }catch (LoginException e) {
                    new Alert(Alert.AlertType.ERROR, "Login failed").show();
                    e.printStackTrace();

        }


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        roleComboBox.getItems().addAll("Admin", "Receptionist");
        roleComboBox.setValue("Admin");


    }


}


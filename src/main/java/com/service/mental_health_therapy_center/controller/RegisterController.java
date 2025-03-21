package com.service.mental_health_therapy_center.controller;

import com.service.mental_health_therapy_center.Bo.custom.Impl.LoginBoImpl;
import com.service.mental_health_therapy_center.Bo.custom.Impl.RegisterBoImpl;
import com.service.mental_health_therapy_center.Bo.custom.LoginBo;
import com.service.mental_health_therapy_center.Bo.custom.RegisterBo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {

    public AnchorPane registerAnc;
    @FXML
    private Button backButton;

    @FXML
    private TextField UsernameField;

    @FXML
    private Button registerButton;

    @FXML
    private PasswordField PasswordField;


    @FXML
    private ComboBox<String> role;


    RegisterBo registerBo = new RegisterBoImpl();


    public void backButtonAction(ActionEvent actionEvent) throws IOException {

        registerAnc.getChildren().clear();
        AnchorPane load = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/login.fxml")));
        registerAnc.getChildren().add(load);

    }

    public void handleRegisterButtonAction(ActionEvent actionEvent) {

        String username = UsernameField.getText();
        String password = PasswordField.getText();
        String roleName = role.getValue();


        boolean isSaveUser = registerBo.registerUser(username, password, roleName);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if (isSaveUser) {

            alert.setTitle("Registration Successful");
            alert.setContentText("User registered successfully.");
            alert.showAndWait();

        } else {
            alert.setTitle("Registration Failed");
            alert.setContentText("User already exists.");
            alert.showAndWait();
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        role.getItems().addAll("Admin", "Reception");
        role.setValue("Admin");
    }
}

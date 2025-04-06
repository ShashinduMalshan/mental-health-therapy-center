package com.service.mental_health_therapy_center.controller;

import com.service.mental_health_therapy_center.Bo.custom.AdminBo;
import com.service.mental_health_therapy_center.Bo.custom.Impl.AdminBoImpl;
import com.service.mental_health_therapy_center.Utill.PasswordUtil;
import com.service.mental_health_therapy_center.dto.UserDto;
import com.service.mental_health_therapy_center.dto.UserTm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class AdminController implements Initializable {


    public TableView <UserTm> userTable;
    public TableColumn <UserTm, String> colName;
    public TableColumn <UserTm,Integer>colId;
    public TableColumn <UserTm,String> colPassword;
    public TableColumn <UserTm,String> colRole;
    public PasswordField passwordField;
    public TextField nameField;
    public ComboBox <String> roleComboBox;
    public Button resetBtn;

    AdminBo adminBo = new AdminBoImpl();


    private void configureTable() {

        colId.setCellValueFactory(new PropertyValueFactory<>("Id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        colPassword.setCellValueFactory(new PropertyValueFactory<>("Password"));
        colRole.setCellValueFactory(new PropertyValueFactory<>("role"));

    }

    public void loadTable()  {

        ArrayList<UserDto> userDtos = adminBo.loadTable();
        ObservableList<UserTm> observableList = FXCollections.observableArrayList();



        for (UserDto userDto : userDtos) {

            UserTm userTm = new UserTm(
                userDto.getId(),
                userDto.getName(),
                userDto.getPassword(),
                userDto.getRole()
            );
            observableList.add(userTm);
        }

        userTable.setItems(observableList);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("AdminController initialize");
        roleComboBox.getItems().addAll("Admin", "Receptionist");
        roleComboBox.setPromptText("Select Role");

        try {
            configureTable();
            refresh();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void refresh(){
        loadTable();

        nameField.setText("");
        passwordField.setText("");

    }

    public void deleteBtnOnAction(ActionEvent actionEvent) {

        String Id = userTable.getSelectionModel().getSelectedItem().getId();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.YES) {

            boolean isDelete = adminBo.delete(Id);

             if(isDelete){
                refresh();
                new Alert(Alert.AlertType.INFORMATION, "User Delete").show();
             }else {
                new Alert(Alert.AlertType.ERROR, "Fail to Delete User...!").show();
             }
        }


    }

    public void searchFieldBtnAction(ActionEvent actionEvent) {}

    public void updateBtnOnAction(ActionEvent actionEvent) {

        String Id = userTable.getSelectionModel().getSelectedItem().getId();
        String username = nameField.getText();
        String password = PasswordUtil.encryptPassword(passwordField.getText());
        String role = roleComboBox.getValue();

        String namePattern = "^[A-Za-z ]+$";
        String passwordPattern = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d@#$%^&+=!]{6,}$";



        boolean isValidName = username.matches(namePattern);
        boolean isValidPassword =/* password.matches(passwordPattern)*/true;
        boolean isValidRole = "Admin".equals(roleComboBox.getValue()) || "Receptionist".equals(roleComboBox.getValue());

        nameField.setStyle(nameField.getStyle() + ";-fx-border-color: #7367F0;");
        passwordField.setStyle(passwordField.getStyle() + ";-fx-border-color: #7367F0;");
        roleComboBox.setStyle(roleComboBox.getStyle() + ";-fx-border-color: #7367F0;");



         if (!isValidName) {
            nameField.setStyle(nameField.getStyle() + ";-fx-border-color: red;");
        }

         if (!isValidPassword) {
            passwordField.setStyle(passwordField.getStyle() + ";-fx-border-color: red;");
        }

         if (!isValidRole) {
            roleComboBox.setStyle(roleComboBox.getStyle() + ";-fx-border-color: red;");
        }

         if (isValidName&&isValidPassword&&isValidRole) {
            boolean isUpdate = adminBo.update(Id, username, password, role);

             if(isUpdate){
                refresh();
             new Alert(Alert.AlertType.INFORMATION, "User Update").show();
             }
         }else {
                new Alert(Alert.AlertType.ERROR, "Fail to Update User...!").show();
             }



    }

    public void saveBtnOnAction(ActionEvent actionEvent) {


        String username = nameField.getText();
        String password = PasswordUtil.encryptPassword(passwordField.getText());
        String role = roleComboBox.getValue();

        String namePattern = "^[A-Za-z ]+$";
        String passwordPattern = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d@#$%^&+=!]{6,}$";



        boolean isValidName = username.matches(namePattern);
        boolean isValidPassword =/* password.matches(passwordPattern)*/true;
        boolean isValidRole = "Admin".equals(roleComboBox.getValue()) || "Receptionist".equals(roleComboBox.getValue());

        nameField.setStyle(nameField.getStyle() + ";-fx-border-color: #7367F0;");
        passwordField.setStyle(passwordField.getStyle() + ";-fx-border-color: #7367F0;");
        roleComboBox.setStyle(roleComboBox.getStyle() + ";-fx-border-color: #7367F0;");



         if (!isValidName) {
            nameField.setStyle(nameField.getStyle() + ";-fx-border-color: red;");
        }

         if (!isValidPassword) {
            passwordField.setStyle(passwordField.getStyle() + ";-fx-border-color: red;");
        }

         if (!isValidRole) {
            roleComboBox.setStyle(roleComboBox.getStyle() + ";-fx-border-color: red;");
        }

         if (isValidName&&isValidPassword&&isValidRole) {
            boolean isSave = adminBo.save(username, password, role);

             if(isSave){
                refresh();
             new Alert(Alert.AlertType.INFORMATION, "User Saved").show();
             }
         }else {
                new Alert(Alert.AlertType.ERROR, "Fail to save User...!").show();
             }



    }

    public void OnClicked(MouseEvent mouseEvent) {

        UserTm userTm = userTable.getSelectionModel().getSelectedItem();

        if (userTm != null) {
            nameField.setText(userTm.getName());
            passwordField.setText(userTm.getPassword());
            roleComboBox.setValue(userTm.getRole());
        }


    }

    public void resetBtnOnAction(ActionEvent actionEvent) {

        refresh();
        nameField.setText("");
        passwordField.setText("");
        roleComboBox.setPromptText("Select Role");

    }
}


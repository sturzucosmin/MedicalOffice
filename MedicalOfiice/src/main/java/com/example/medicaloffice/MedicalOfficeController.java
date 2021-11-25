package com.example.medicaloffice;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
//import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
//import javafx.stage.StageStyle;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class MedicalOfficeController {
    @FXML
    private Button cancelButton;
    @FXML
    private Button loginButton;
    @FXML
    private Label loginMessage;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordTextField;


    public void LoginButtonOnAction(ActionEvent e) {
        if(!usernameTextField.getText().isBlank() && !passwordTextField.getText().isBlank()) {
            //loginMessage.setText("You try to login!");
            validateLogin();
        } else {
            loginMessage.setText("Please enter username or password");
        }

    }

    public void CancelButtonOnAction(ActionEvent e) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void validateLogin() {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String verifyLogin = "SELECT count(1) FROM medic where username = '" + usernameTextField.getText() + "' AND password = '" + passwordTextField.getText() + "'";

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while (queryResult.next()) {
                if(queryResult.getInt(1) == 1) {
                    loginMessage.setText("Welcome!");
                    Stage stage = (Stage) loginButton.getScene().getWindow();
                    stage.close();
                    openMenu();
                } else {
                    loginMessage.setText("Invalid Login. Please try again.");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }



    public void openMenu() {
        try {
            /*
            Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));
            Stage menuStage = new Stage();
            menuStage.initStyle(StageStyle.UNDECORATED);
            menuStage.setScene(new Scene(root, 1000, 720));
            menuStage.setTitle("MedicalOffice");
            menuStage.show();*/

            Stage menuStage = new Stage();
            FXMLLoader fxmlLoaderLogin = new FXMLLoader(MedicalOfficeApplication.class.getResource("menu.fxml"));
            Scene sceneMenu = new Scene(fxmlLoaderLogin.load(), 1000, 720);

            menuStage.setTitle("MedicalOffice");
            menuStage.setScene(sceneMenu);
            menuStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




}
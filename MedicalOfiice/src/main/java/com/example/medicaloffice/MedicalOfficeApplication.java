package com.example.medicaloffice;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;

public class MedicalOfficeApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoaderLogin = new FXMLLoader(MedicalOfficeApplication.class.getResource("login.fxml"));
        Scene sceneLogin = new Scene(fxmlLoaderLogin.load(), 1000, 720);

        stage.setTitle("MedicalOffice");
        stage.setScene(sceneLogin);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}
package com.example.medicaloffice;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.PrimitiveIterator;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RetetaController implements Initializable {
    @FXML
    private Button refreshButton;

    @FXML
    private TableView<RetetaSearchModel> retetaTableView;

    @FXML
    private TableColumn<RetetaSearchModel, String> idRetetaColumn;
    @FXML
    private TableColumn<RetetaSearchModel, String> dataEliberareRetetaColumn;
    @FXML
    private TableColumn<RetetaSearchModel, String> valabilitateRetetaColumn;
    @FXML
    private TableColumn<RetetaSearchModel, String> prescriptieRetetaColumn;
    @FXML
    private TableColumn<RetetaSearchModel, String> idMedicRetetaColumn;
    @FXML
    private TableColumn<RetetaSearchModel, String> idPacientColumn;

    @FXML
    private TextField addIdReteta;
    @FXML
    private TextField addDataEliberareReteta;
    @FXML
    private TextField addValabilitateReteta;
    @FXML
    private TextField addPrescriptieReteta;
    @FXML
    private TextField addIdMedicReteta;
    @FXML
    private TextField addIdPacientReteta;


    ObservableList<RetetaSearchModel> retetaSearchModelObservableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String retetaViewQuery = "SELECT IDreteta, DataELiberare, Valabilitate, Prescriptia, IDmedic, IDpacient FROM reteta;";

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(retetaViewQuery);

            while(queryOutput.next()){
                String queryID = queryOutput.getString("IDreteta");
                String queryDataEliberarii = queryOutput.getString("DataELiberare");
                String queryValabilitate = queryOutput.getString("Valabilitate");
                String queryPrescriptia = queryOutput.getString("Prescriptia");
                String queryIDmedic = queryOutput.getString("IDmedic");
                String queryIDpacient = queryOutput.getString("IDpacient");


                retetaSearchModelObservableList.add(new RetetaSearchModel(queryID, queryDataEliberarii, queryValabilitate, queryPrescriptia, queryIDmedic, queryIDpacient));
            }

            idRetetaColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
            dataEliberareRetetaColumn.setCellValueFactory(new PropertyValueFactory<>("DataEliberarii"));
            valabilitateRetetaColumn.setCellValueFactory(new PropertyValueFactory<>("Valabilitate"));
            prescriptieRetetaColumn.setCellValueFactory(new PropertyValueFactory<>("Prescriptia"));
            idMedicRetetaColumn.setCellValueFactory(new PropertyValueFactory<>("IDmedic"));
            idPacientColumn.setCellValueFactory(new PropertyValueFactory<>("IDpacient"));


            retetaTableView.setItems(retetaSearchModelObservableList);

        } catch (SQLException e) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, e);
        }

    }



    public void RefreshButtonOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) refreshButton.getScene().getWindow();
        stage.close();
        refresh();
    }

    public void refresh() {
        try {

            Stage menuStage = new Stage();
            FXMLLoader fxmlLoaderLogin = new FXMLLoader(MedicalOfficeApplication.class.getResource("reteta.fxml"));
            Scene sceneLogin = new Scene(fxmlLoaderLogin.load(), 1000, 720);

            menuStage.setTitle("MedicalOffice");
            menuStage.setScene(sceneLogin);
            menuStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void LogoutButtonOnAction(ActionEvent actionEvent) {

    }

    public void AddButtonRetetaOnAction(ActionEvent actionEvent) {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();


        String addRetetaQuery = "INSERT INTO `cabinet_medical_final`.`reteta` (`IDreteta`, `DataELiberare`, `Valabilitate`, `Prescriptia`, `IDmedic`, `IDpacient`)" +
                "VALUES ('" +addIdReteta.getText() + "','" +  addDataEliberareReteta.getText() + "','" + addValabilitateReteta.getText() + "','" + addPrescriptieReteta.getText() + "','" +
                addIdMedicReteta.getText() + "','" + addIdPacientReteta.getText() + "')";

        try {
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(addRetetaQuery);
        } catch (SQLException e) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void UpdateButtonRetetaOnAction(ActionEvent actionEvent) {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();


        String updateRetetaQuery = "UPDATE `cabinet_medical_final`.`reteta` SET `DataELiberare` = '" + addDataEliberareReteta.getText() + "',`Valabilitate` = '" +
                addValabilitateReteta.getText() + "',`Prescriptia` = '" + addPrescriptieReteta.getText() + "', `IDmedic` = '" + addIdMedicReteta.getText() + "', `IDpacient` = '" + addIdPacientReteta.getText() +
                "' WHERE (`IDreteta` = '" + addIdReteta.getText() + "')";

        try {
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(updateRetetaQuery);
        } catch (SQLException e) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void DeleteButtonRetetaOnAction(ActionEvent actionEvent) {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();


        String deleteRetetaQuery = "DELETE FROM `cabinet_medical_final`.`reteta` WHERE (`IDreteta` = '" + addIdReteta.getText() + "')";

        try {
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(deleteRetetaQuery);
        } catch (SQLException e) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void onMouseClickedTable(MouseEvent mouseEvent) {
        int rowNum = retetaTableView.getSelectionModel().getSelectedIndex();

        addIdReteta.setText(idRetetaColumn.getCellData(rowNum));
        addDataEliberareReteta.setText(dataEliberareRetetaColumn.getCellData(rowNum));
        addValabilitateReteta.setText(valabilitateRetetaColumn.getCellData(rowNum));
        addPrescriptieReteta.setText(prescriptieRetetaColumn.getCellData(rowNum));
        addIdMedicReteta.setText(idMedicRetetaColumn.getCellData(rowNum));
        addIdPacientReteta.setText(idPacientColumn.getCellData(rowNum));
    }

    public void EliberareRetetaMenuOnAction(ActionEvent actionEvent) {
    }

}

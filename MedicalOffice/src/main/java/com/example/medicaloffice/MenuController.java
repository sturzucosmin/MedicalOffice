package com.example.medicaloffice;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;


public class MenuController implements Initializable {
    @FXML
    private Button logoutButton;
    @FXML
    private Button refreshButton;
    @FXML
    private MenuItem eliberareRetetaMenu;


    @FXML
    TextField addIdPacient;
    @FXML
    TextField addNumePacient;
    @FXML
    TextField addPrenumePacient;
    @FXML
    TextField addCnpPacient;
    @FXML
    TextField addVarstaPacient;
    @FXML
    TextField addDataNasteriiPacient;
    @FXML
    TextField addSexPacient;
    @FXML
    TextField addTelefonPacient;
    @FXML
    TextField addMailPacient;
    @FXML
    TextField addIdMedicPacient;
    @FXML
    TextField addIdAsigurarePacient;


    @FXML
    private TableView<NameSearchModel> pacientTableView;
    @FXML
    private TableColumn<NameSearchModel, String> idPacientColumn;
    @FXML
    private TableColumn<NameSearchModel, String> numePacientColumn;
    @FXML
    private TableColumn<NameSearchModel, String> prenumePacientColumn;
    @FXML
    private TableColumn<NameSearchModel, String> cnpPacientColumn;
    @FXML
    private TableColumn<NameSearchModel, String> varstaPacientColumn;
    @FXML
    private TableColumn<NameSearchModel, String> telefonPacientColumn;
    @FXML
    private TableColumn<NameSearchModel, String> mailPacientColumn;
    @FXML
    private TextField keywordNameTextField;

    ObservableList<NameSearchModel> nameSearchModelObservableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resource) {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();


        String pacientViewQuery = "SELECT IDpacient, Nume, Prenume, CNP, Varsta, Telefon, Mail FROM pacient;";


        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(pacientViewQuery);

            while(queryOutput.next()){
                String queryID = queryOutput.getString("IDpacient");
                String queryNume = queryOutput.getString("Nume");
                String queryPrenume = queryOutput.getString("Prenume");
                String queryCNP = queryOutput.getString("CNP");
                String queryVarsta = queryOutput.getString("Varsta");
                String queryTelefon = queryOutput.getString("Telefon");
                String queryMail = queryOutput.getString("Mail");


                nameSearchModelObservableList.add(new NameSearchModel(queryID, queryNume, queryPrenume, queryCNP, queryVarsta, queryTelefon, queryMail));
            }

            idPacientColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
            numePacientColumn.setCellValueFactory(new PropertyValueFactory<>("Nume"));
            prenumePacientColumn.setCellValueFactory(new PropertyValueFactory<>("Prenume"));
            cnpPacientColumn.setCellValueFactory(new PropertyValueFactory<>("CNP"));
            varstaPacientColumn.setCellValueFactory(new PropertyValueFactory<>("Varsta"));
            telefonPacientColumn.setCellValueFactory(new PropertyValueFactory<>("Telefon"));
            mailPacientColumn.setCellValueFactory(new PropertyValueFactory<>("Mail"));

            pacientTableView.setItems(nameSearchModelObservableList);

            FilteredList<NameSearchModel> filteredData = new FilteredList<>(nameSearchModelObservableList, b->true);

            keywordNameTextField.textProperty().addListener((observable, oldValue, newValue)-> {
                filteredData.setPredicate(nameSearchModel -> {
                    if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                        return true;
                    }

                    String searchKeyword = newValue.toLowerCase();

                    if (nameSearchModel.getNume().toLowerCase().indexOf(searchKeyword) > -1) {
                        return true;
                    } else if(nameSearchModel.getPrenume().toLowerCase().indexOf(searchKeyword) > -1) {
                        return true;
                    } else if(nameSearchModel.getCNP().toLowerCase().indexOf(searchKeyword) > -1) {
                        return true;
                    } else
                        return false;
                });
            });

            SortedList<NameSearchModel> sortedData = new SortedList<>(filteredData);

            sortedData.comparatorProperty().bind(pacientTableView.comparatorProperty());

            pacientTableView.setItems(sortedData);



        } catch (SQLException e) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, e);
        }

    }



    public void RefreshButtonOnAction(ActionEvent e) {
        ///pacientTableView.setItems(nameSearchModelObservableList);
        Stage stage = (Stage) refreshButton.getScene().getWindow();
        stage.close();
        refresh();
    }

    public void refresh() {
        try {

            Stage menuStage = new Stage();
            FXMLLoader fxmlLoaderLogin = new FXMLLoader(MedicalOfficeApplication.class.getResource("menu.fxml"));
            Scene sceneLogin = new Scene(fxmlLoaderLogin.load(), 1000, 720);

            menuStage.setTitle("MedicalOffice");
            menuStage.setScene(sceneLogin);
            menuStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void AddButtonPacientOnAction(ActionEvent actionEvent) {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();


        String addPacientQuery = "INSERT INTO `cabinet_medical_final`.`pacient` (`IDpacient`, `Nume`, `Prenume`, `CNP`, `Varsta`, `DataNasterii`, `Sex`, `Telefon`, `Mail`, `IDmedic`, `IDasigurare`)" +
                "VALUES ('" +addIdPacient.getText() + "','" +  addNumePacient.getText() + "','" + addPrenumePacient.getText() + "','" + addCnpPacient.getText() + "','" +
                addVarstaPacient.getText() + "','" + addDataNasteriiPacient.getText() + "','" + addSexPacient.getText() + "','" + addTelefonPacient.getText()  + "','" +
                addMailPacient.getText() + "','" + addIdMedicPacient.getText() + "','" + addIdAsigurarePacient.getText()  + "')";

        try {
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(addPacientQuery);
        } catch (SQLException e) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    public void UpdateButtonPacientOnAction(ActionEvent actionEvent) {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();


        String updatePacientQuery = "UPDATE `cabinet_medical_final`.`pacient` SET `Nume` = " + "'" + addNumePacient.getText() + "',`Prenume` = '" + addPrenumePacient.getText() + "',`CNP` = '" +
                addCnpPacient.getText() + "', `Varsta` = '" + addVarstaPacient.getText() + "', `DataNasterii` = '" + addDataNasteriiPacient.getText() + "', `Sex` = '" + addSexPacient.getText() +
                "', `Telefon` = '" + addTelefonPacient.getText() + "', `Mail` = '" + addMailPacient.getText() + "', `IDmedic` = '" + addIdMedicPacient.getText() + "', `IDasigurare` = '" +
                addIdAsigurarePacient.getText() + "' WHERE (`IDpacient` = '" + addIdPacient.getText() + "')";

        try {
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(updatePacientQuery);
        } catch (SQLException e) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void DeleteButtonPacientOnAction(ActionEvent actionEvent) {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();


        String deletePacientQuery = "DELETE FROM `cabinet_medical_final`.`pacient` WHERE (`IDpacient` = '" + addIdPacient.getText() + "')";

        try {
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(deletePacientQuery);
        } catch (SQLException e) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, e);
        }
    }


    public void onMouseClickedTable(MouseEvent mouseEvent) {
        int rowNum = pacientTableView.getSelectionModel().getSelectedIndex();

        addIdPacient.setText(idPacientColumn.getCellData(rowNum));
        addNumePacient.setText(numePacientColumn.getCellData(rowNum));
        addPrenumePacient.setText(prenumePacientColumn.getCellData(rowNum));
        addCnpPacient.setText(cnpPacientColumn.getCellData(rowNum));
        addVarstaPacient.setText(varstaPacientColumn.getCellData(rowNum));
        addTelefonPacient.setText(telefonPacientColumn.getCellData(rowNum));
        addMailPacient.setText(mailPacientColumn.getCellData(rowNum));
    }

    public void LogoutButtonOnAction(ActionEvent e) {
        Stage stage = (Stage) logoutButton.getScene().getWindow();
        stage.close();
        logout();
    }

    public void logout() {
        try {

            Stage menuStage = new Stage();
            FXMLLoader fxmlLoaderLogin = new FXMLLoader(MedicalOfficeApplication.class.getResource("login.fxml"));
            Scene sceneLogin = new Scene(fxmlLoaderLogin.load(), 1000, 720);

            menuStage.setTitle("MedicalOffice");
            menuStage.setScene(sceneLogin);
            menuStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void EliberareRetetaMenuOnAction(ActionEvent actionEvent) {
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

    public void InterogariMenuOnAction(ActionEvent actionEvent) {
        try {

            Stage menuStage = new Stage();
            FXMLLoader fxmlLoaderLogin = new FXMLLoader(MedicalOfficeApplication.class.getResource("interogari.fxml"));
            Scene sceneLogin = new Scene(fxmlLoaderLogin.load(), 1200, 720);

            menuStage.setTitle("MedicalOffice");
            menuStage.setScene(sceneLogin);
            menuStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

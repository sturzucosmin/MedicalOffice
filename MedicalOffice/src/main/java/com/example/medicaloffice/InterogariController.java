package com.example.medicaloffice;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InterogariController {

    @FXML
    Button refreshButton;
    @FXML
    TextField cautaNumeMedic;

    @FXML
    private TableView<MedicNameSearchModel> pacientiMedicTableView;
    @FXML
    private TableColumn<MedicNameSearchModel, String> numePacientMedicColumn;
    @FXML
    private TableColumn<MedicNameSearchModel, String> prenumePacientMedicColumn;

    @FXML
    private TableView<NrPacientiSearchModel> NrPacientiTableView;
    @FXML
    private TableColumn<NrPacientiSearchModel, String> numeMedicColumn;
    @FXML
    private TableColumn<NrPacientiSearchModel, String> prenumeMedicColumn;
    @FXML
    private TableColumn<NrPacientiSearchModel, String> nrPacientiColumn;

    @FXML
    private TableView<AsigurareSearchModel> asigurareTableView;
    @FXML
    private TableColumn<AsigurareSearchModel, String> tipAsigurareColumn;
    @FXML
    private TableColumn<AsigurareSearchModel, String> nrPacientiAsigurareColumn;



    ObservableList<MedicNameSearchModel> MedicNameSearchModelObservableList = FXCollections.observableArrayList();
    ObservableList<NrPacientiSearchModel> NrPacientiSearchModelObservableList = FXCollections.observableArrayList();
    ObservableList<AsigurareSearchModel> AsigurareSearchModelObservableList = FXCollections.observableArrayList();



    public void CautaPacientiOnAction(ActionEvent actionEvent) {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        MedicNameSearchModelObservableList.clear();
        pacientiMedicTableView.setItems(MedicNameSearchModelObservableList);

        String searchByMedicName = "select P.Nume, P.Prenume from pacient P join medic M on M.IDmedic = P.IDmedic where M.Nume = '" + cautaNumeMedic.getText() + "'";

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(searchByMedicName);

            while(queryOutput.next()) {
                String queryNume = queryOutput.getString("Nume");
                String queryPrenume = queryOutput.getString("Prenume");

                MedicNameSearchModelObservableList.add(new MedicNameSearchModel(queryNume, queryPrenume));
            }
            numePacientMedicColumn.setCellValueFactory(new PropertyValueFactory<>("Nume"));
            prenumePacientMedicColumn.setCellValueFactory(new PropertyValueFactory<>("Prenume"));

            pacientiMedicTableView.setItems(MedicNameSearchModelObservableList);

        } catch (SQLException e) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void NrPacientiButtonOnAction(ActionEvent actionEvent) {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        NrPacientiSearchModelObservableList.clear();
        NrPacientiTableView.setItems(NrPacientiSearchModelObservableList);

        String searchNrPacienti = "select M.Nume, M.Prenume, count(P.Nume) NrPacienti from pacient P join medic M on M.IDmedic = P.IDmedic\n" +
                "group by M.Nume;";

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(searchNrPacienti);

            while(queryOutput.next()) {
                String queryNume = queryOutput.getString("Nume");
                String queryPrenume = queryOutput.getString("Prenume");
                String queryNrPacienti = queryOutput.getString("NrPacienti");

                NrPacientiSearchModelObservableList.add(new NrPacientiSearchModel(queryNume, queryPrenume, queryNrPacienti));
            }
            numeMedicColumn.setCellValueFactory(new PropertyValueFactory<>("Nume"));
            prenumeMedicColumn.setCellValueFactory(new PropertyValueFactory<>("Prenume"));
            nrPacientiColumn.setCellValueFactory(new PropertyValueFactory<>("NrPacienti"));

            NrPacientiTableView.setItems(NrPacientiSearchModelObservableList);

        } catch (SQLException e) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, e);
        }
    }


    public void AsigurareButtonOnAction(ActionEvent actionEvent) {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        AsigurareSearchModelObservableList.clear();
        asigurareTableView.setItems(AsigurareSearchModelObservableList);

        String searchByMedicName = "select A.TipAsigurare as TipAsigurare, count(P.IDpacient) as NrPacienti from asigurare A left join pacient P on A.IDasigurare = P.IDasigurare group by A.TipAsigurare;";

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(searchByMedicName);

            while(queryOutput.next()) {
                String queryTipAsigurare = queryOutput.getString("TipAsigurare");
                String queryNrPacienti = queryOutput.getString("NrPacienti");

                AsigurareSearchModelObservableList.add(new AsigurareSearchModel(queryTipAsigurare, queryNrPacienti));
            }
            tipAsigurareColumn.setCellValueFactory(new PropertyValueFactory<>("TipAsigurare"));
            nrPacientiAsigurareColumn.setCellValueFactory(new PropertyValueFactory<>("NrPacienti"));

            asigurareTableView.setItems(AsigurareSearchModelObservableList);

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
            FXMLLoader fxmlLoaderLogin = new FXMLLoader(MedicalOfficeApplication.class.getResource("interogari.fxml"));
            Scene sceneLogin = new Scene(fxmlLoaderLogin.load(), 1200, 720);

            menuStage.setTitle("MedicalOffice");
            menuStage.setScene(sceneLogin);
            menuStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    private TableView<SectieSearchModel> sectieTableView;
    @FXML
    private TableColumn<SectieSearchModel, String> numeSectieColumn;
    @FXML
    private TableColumn<SectieSearchModel, String> prenumeSectieColumn;

    @FXML TextField cautaSectie;

    ObservableList<SectieSearchModel> SectieSearchModelObservableList = FXCollections.observableArrayList();

    public void CautaSectieOnAction(ActionEvent actionEvent) {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        SectieSearchModelObservableList.clear();
        sectieTableView.setItems(SectieSearchModelObservableList);

        String searchByTrimitere = "select P.Nume, P.Prenume from pacient P join trimitere T on P.IDpacient = T.IDpacient where T.SectieTrimitere = '" + cautaSectie.getText() + "'";

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(searchByTrimitere);

            while(queryOutput.next()) {
                String queryNume = queryOutput.getString("Nume");
                String queryPrenume = queryOutput.getString("Prenume");

                SectieSearchModelObservableList.add(new SectieSearchModel(queryNume, queryPrenume));
            }
            numeSectieColumn.setCellValueFactory(new PropertyValueFactory<>("Nume"));
            prenumeSectieColumn.setCellValueFactory(new PropertyValueFactory<>("Prenume"));

            sectieTableView.setItems(SectieSearchModelObservableList);

        } catch (SQLException e) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @FXML
    private TableView<RetetaMedicSearchModel> reteteMedicTableView;
    @FXML
    private TableColumn<RetetaMedicSearchModel, String> dataEliberareColumn;
    @FXML
    private TableColumn<RetetaMedicSearchModel, String> prescriptiaColumn;
    @FXML
    private TableColumn<RetetaMedicSearchModel, String> idPacientColumn;


    @FXML TextField numeMedicReteta;

    ObservableList<RetetaMedicSearchModel>RetetaMedicSearchModelObservableList = FXCollections.observableArrayList();
    public void medicRetetaButtonOnAction(ActionEvent actionEvent) {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        RetetaMedicSearchModelObservableList.clear();
        reteteMedicTableView.setItems(RetetaMedicSearchModelObservableList);

        String searchByRetetaMedic = "select DataEliberare, Prescriptia, IDpacient from reteta  " +
                "where IDmedic in (select IDmedic from medic where Nume like '%" + numeMedicReteta.getText() + "%')";

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(searchByRetetaMedic);

            while(queryOutput.next()) {
                String queryData = queryOutput.getString("DataEliberare");
                String queryPrescriptia = queryOutput.getString("Prescriptia");
                String queryIDpacient = queryOutput.getString("IDpacient");

                RetetaMedicSearchModelObservableList.add(new RetetaMedicSearchModel(queryData, queryPrescriptia, queryIDpacient));
            }
            dataEliberareColumn.setCellValueFactory(new PropertyValueFactory<>("DataEliberare"));
            prescriptiaColumn.setCellValueFactory(new PropertyValueFactory<>("Prescriptia"));
            idPacientColumn.setCellValueFactory(new PropertyValueFactory<>("IDpacient"));

            reteteMedicTableView.setItems(RetetaMedicSearchModelObservableList);

        } catch (SQLException e) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}

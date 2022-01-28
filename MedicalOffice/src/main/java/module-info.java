module com.example.medicalofiice {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires eu.hansolo.tilesfx;

    requires java.sql;
    requires mysql.connector.java;

    opens com.example.medicaloffice to javafx.fxml;
    exports com.example.medicaloffice;
}
package com.example.medicaloffice;

import javafx.beans.property.StringProperty;

public class RetetaMedicSearchModel {
    String DataEliberare;
    String Prescriptia;
    String IDpacient;

    public RetetaMedicSearchModel(String dataEliberare, String prescriptia, String idPacient){
        this.DataEliberare = dataEliberare;
        this.Prescriptia = prescriptia;
        this.IDpacient = idPacient;
    }

    public String getDataEliberare() {
        return DataEliberare;
    }

    public void setDataEliberare(String dataEliberare) {
        DataEliberare = dataEliberare;
    }

    public String getPrescriptia() {
        return Prescriptia;
    }

    public void setPrescriptia(String prescriptia) {
        Prescriptia = prescriptia;
    }

    public String getIDpacient() {
        return IDpacient;
    }

    public void setIDpacient(String IDpacient) {
        this.IDpacient = IDpacient;
    }




}

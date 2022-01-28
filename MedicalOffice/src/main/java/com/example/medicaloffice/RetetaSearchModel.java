package com.example.medicaloffice;

public class RetetaSearchModel {
    String IDreteta;
    String DataEliberarii;
    String Valabilitate;
    String Prescriptia;
    String IDmedic;
    String IDpacient;

    public RetetaSearchModel(String idReteta, String dataEliberarii, String valabilitate, String prescriptia, String idMedic, String idPacient) {
        this.IDreteta = idReteta;
        this.DataEliberarii = dataEliberarii;
        this.Valabilitate = valabilitate;
        this.Prescriptia = prescriptia;
        this.IDmedic = idMedic;
        this.IDpacient = idPacient;

    }

    public String getID() {
        return IDreteta;
    }

    public String getDataEliberarii() {
        return DataEliberarii;
    }

    public String getValabilitate() {
        return Valabilitate;
    }

    public String getPrescriptia() {
        return Prescriptia;
    }

    public String getIDmedic() {
        return IDmedic;
    }

    public String getIDpacient() {
        return IDpacient;
    }

    public void setID(String ID) {
        this.IDreteta = ID;
    }

    public void setDataEliberarii(String dataEliberarii) {
        DataEliberarii = dataEliberarii;
    }

    public void setValabilitate(String valabilitate) {
        Valabilitate = valabilitate;
    }

    public void setPrescriptia(String prescriptia) {
        Prescriptia = prescriptia;
    }

    public void setIDmedic(String IDmedic) {
        this.IDmedic = IDmedic;
    }

    public void setIDpacient(String IDpacient) {
        this.IDpacient = IDpacient;
    }
}

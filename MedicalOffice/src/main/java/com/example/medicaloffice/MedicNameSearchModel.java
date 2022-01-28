package com.example.medicaloffice;

public class MedicNameSearchModel {
    String Nume;
    String Prenume;

    public String getNume() {
        return Nume;
    }

    public void setNume(String nume) {
        Nume = nume;
    }

    public String getPrenume() {
        return Prenume;
    }

    public void setPrenume(String prenume) {
        Prenume = prenume;
    }




    public MedicNameSearchModel(String nume, String prenume) {
        this.Nume = nume;
        this.Prenume = prenume;
    }
}

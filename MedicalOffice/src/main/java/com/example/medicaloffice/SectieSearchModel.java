package com.example.medicaloffice;

public class SectieSearchModel {
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




    public SectieSearchModel(String nume, String prenume) {
        this.Nume = nume;
        this.Prenume = prenume;
    }
}

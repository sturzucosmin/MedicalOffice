package com.example.medicaloffice;

public class NrPacientiSearchModel {
    String Nume;
    String Prenume;
    String NrPacienti;

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

    public String getNrPacienti() {
        return NrPacienti;
    }

    public void setNrPacienti(String nrPacienti) {
        NrPacienti = nrPacienti;
    }



    public NrPacientiSearchModel(String nume, String prenume, String nrPacienti) {
        this.Nume = nume;
        this.Prenume = prenume;
        this.NrPacienti = nrPacienti;
    }
}

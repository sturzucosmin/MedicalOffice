package com.example.medicaloffice;

public class AsigurareSearchModel {
    String TipAsigurare;
    String NrPacienti;

    public AsigurareSearchModel(String tipAsigurare, String nrPacienti) {
        this.TipAsigurare = tipAsigurare;
        this.NrPacienti = nrPacienti;
    }
    public String getTipAsigurare() {
        return TipAsigurare;
    }

    public void setTipAsigurare(String tipAsigurare) {
        TipAsigurare = tipAsigurare;
    }

    public String getNrPacienti() {
        return NrPacienti;
    }

    public void setNrPacienti(String nrPacienti) {
        NrPacienti = nrPacienti;
    }
}

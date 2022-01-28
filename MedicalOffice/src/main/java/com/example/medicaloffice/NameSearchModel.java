package com.example.medicaloffice;

public class NameSearchModel {
     String ID;
     String Nume;
     String Prenume;
     String CNP;
     String Varsta;
     String Telefon;
     String Mail;

     public NameSearchModel(String idPacient, String numePacient, String prenumePacient, String cnpPacient, String varstaPacient, String telefonPacient, String mailPacient) {
          this.ID = idPacient;
          this.Nume = numePacient;
          this.Prenume = prenumePacient;
          this.CNP = cnpPacient;
          this.Varsta = varstaPacient;
          this.Telefon = telefonPacient;
          this.Mail = mailPacient;
     }

     public String getID() {
          return ID;
     }

     public String getNume() {
          return Nume;
     }

     public String getPrenume() {
          return Prenume;
     }

     public String getCNP() {
          return CNP;
     }


     public String getVarsta() {
          return Varsta;
     }

     public String getTelefon() {
          return Telefon;
     }

     public String getMail() {
          return Mail;
     }

     public void setID(String id) {
          this.ID = id;
     }

     public void setNume(String nume) {
          this.Nume = nume;
     }

     public void setPrenume(String prenume) {
          this.Prenume = prenume;
     }

     public void setCNP(String CNP) {
          this.CNP = CNP;
     }

     public void setVarsta(String varsta) {
          this.Varsta = varsta;
     }

     public void setTelefon(String telefon) {
          this.Telefon = telefon;
     }

     public void setMail(String mail) {
          this.Mail = mail;
     }
}

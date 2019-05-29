package com.pdm.entitati;

import java.util.ArrayList;

public class Intrebare {
    private int idIntrebare;
    private int idTest;
    private String numeIntrebare;
    private ArrayList<Varianta> listaVariante;

    public Intrebare() {

    }

    @Override
    public String toString() {
        return "Intrebare{" +
                "idIntrebare=" + idIntrebare +
                ", idTest=" + idTest +
                ", numeIntrebare='" + numeIntrebare + '\'' +
                ", listaVariante=" + listaVariante +
                '}';
    }

    public Intrebare(int idIntrebare, int idTest, String numeIntrebare, ArrayList<Varianta> listaVariante) {
        this.idIntrebare = idIntrebare;
        this.idTest = idTest;
        this.numeIntrebare = numeIntrebare;
        this.listaVariante = listaVariante;
    }

    public int getIdIntrebare() {
        return idIntrebare;
    }

    public int getIdTest() {
        return idTest;
    }

    public String getNumeIntrebare() {
        return numeIntrebare;
    }

    public ArrayList<Varianta> getListaVariante() {
        return listaVariante;
    }

    public void setIdIntrebare(int idIntrebare) {
        this.idIntrebare = idIntrebare;
    }

    public void setIdTest(int idTest) {
        this.idTest = idTest;
    }

    public void setNumeIntrebare(String numeIntrebare) {
        this.numeIntrebare = numeIntrebare;
    }

    public void setListaVariante(ArrayList<Varianta> listaVariante) {
        this.listaVariante = listaVariante;
    }
}

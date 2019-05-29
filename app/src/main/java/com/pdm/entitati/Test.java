package com.pdm.entitati;

import java.sql.Timestamp;
import java.util.ArrayList;

public class Test {

    private int idTest;
    private String numeTest;
    private double nota;

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    private ArrayList<Intrebare> listaIntrebari;

    public Test(int idTest, String numeTest, ArrayList<Intrebare> listaIntrebari) {
        this.idTest = idTest;
        this.numeTest = numeTest;
        this.listaIntrebari = listaIntrebari;
    }

    public Test() {

    }

    @Override
    public String toString() {
        return "Test{" +
                "idTest=" + idTest +
                ", numeTest='" + numeTest + '\'' +
                ", listaIntrebari=" + listaIntrebari +
                '}';
    }

    public int getIdTest() {
        return idTest;
    }

    public void setIdTest(int idTest) {
        this.idTest = idTest;
    }

    public String getNumeTest() {
        return numeTest;
    }

    public void setNumeTest(String numeTest) {
        this.numeTest = numeTest;
    }

    public ArrayList<Intrebare> getListaIntrebari() {
        return listaIntrebari;
    }

    public void setListaIntrebari(ArrayList<Intrebare> listaIntrebari) {
        this.listaIntrebari = listaIntrebari;
    }
}
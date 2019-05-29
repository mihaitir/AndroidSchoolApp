package com.pdm.entitati;

public class GestiuneTest {
    private int idGestiune;
    private int idStudent;
    private int idTest;
    private double nota;
    private String numeStudent;

    public GestiuneTest(int idGestiune, int idStudent, int idTest, double nota, String numeStudent) {
        this.idGestiune = idGestiune;
        this.idStudent = idStudent;
        this.idTest = idTest;
        this.nota = nota;
        this.numeStudent = numeStudent;
    }

    @Override
    public String toString() {
        return "GestiuneTest{" +
                "idGestiune=" + idGestiune +
                ", idStudent=" + idStudent +
                ", idTest=" + idTest +
                ", nota=" + nota +
                ", numeStudent='" + numeStudent + '\'' +
                '}';
    }

    public int getIdGestiune() {
        return idGestiune;
    }

    public void setIdGestiune(int idGestiune) {
        this.idGestiune = idGestiune;
    }

    public int getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(int idStudent) {
        this.idStudent = idStudent;
    }

    public int getIdTest() {
        return idTest;
    }

    public void setIdTest(int idTest) {
        this.idTest = idTest;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public String getNumeStudent() {
        return numeStudent;
    }

    public void setNumeStudent(String numeStudent) {
        this.numeStudent = numeStudent;
    }

    public GestiuneTest() {
    }


}

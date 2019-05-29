package com.pdm.entitati;

public class Student {
    private int idStudent;
    private String numeStudent;

    public Student() {
    }

    public Student(int idStudent, String numeStudent) {
        this.idStudent = idStudent;
        this.numeStudent = numeStudent;
    }

    public int getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(int idStudent) {
        this.idStudent = idStudent;
    }

    public String getNumeStudent() {
        return numeStudent;
    }

    public void setNumeStudent(String numeStudent) {
        this.numeStudent = numeStudent;
    }
}

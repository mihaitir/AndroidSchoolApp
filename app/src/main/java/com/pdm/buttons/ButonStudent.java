package com.pdm.buttons;

import android.content.Context;
import android.widget.Button;

public class ButonStudent extends Button {

    private int idStudent;
    private String numeStudent;

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

    public ButonStudent(Context context) {
        super(context);
    }
}

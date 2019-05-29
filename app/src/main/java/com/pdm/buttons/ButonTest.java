package com.pdm.buttons;

import android.content.Context;
import android.widget.Button;

public class ButonTest extends Button {

    private int id;
    private double nota;

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public ButonTest(Context context, int id) {
        super(context);
        this.id = id;
    }
}

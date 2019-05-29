package com.pdm.entitati;

public class Varianta {
    private int idVarianta;
    private int idIntrebare;
    private String text;
    private boolean corect;

    public Varianta(int idVarianta, int idIntrebare, String text, boolean corect) {
        this.idVarianta = idVarianta;
        this.idIntrebare = idIntrebare;
        this.text = text;
        this.corect = corect;
    }

    public  Varianta() {
    }

    public int getIdVarianta() {
        return idVarianta;
    }

    @Override
    public String toString() {
        return "Varianta{" +
                "idVarianta=" + idVarianta +
                ", idIntrebare=" + idIntrebare +
                ", text='" + text + '\'' +
                ", corect=" + corect +
                '}';
    }

    public void setIdVarianta(int idVarianta) {
        this.idVarianta = idVarianta;
    }

    public int getIdIntrebare() {
        return idIntrebare;
    }

    public void setIdIntrebare(int idIntrebare) {
        this.idIntrebare = idIntrebare;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isCorect() {
        return corect;
    }

    public void setCorect(boolean corect) {
        this.corect = corect;
    }
}
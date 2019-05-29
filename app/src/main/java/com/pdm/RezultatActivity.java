package com.pdm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.pdm.entitati.GestiuneTest;

public class RezultatActivity extends AppCompatActivity {

    int numarIntrebari;
    int numarIntrebariCorecte;
    double procentaj;
    TextView textView;
    ImageButton home;
    int idTest;
    int idStudent;
    String  numeStudent;

    //suprascriem metoda onBackPressed(), ceea ce ne permite ca la apasarea butonului back sa nu ne mai intoarcem inapoi la
    //intrebarile testului
    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(), "Testul s-a incheiat, nu mai puteti reveni!", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rezultat);
        textView = (TextView)findViewById(R.id.textViewPunctaj);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            numarIntrebari = extras.getInt("numarRaspunsuri");
            numarIntrebariCorecte = extras.getInt("numarRaspunsuriCorecte");
            idTest = extras.getInt("idTest");
            idStudent = extras.getInt("idStudent");
            numeStudent = extras.getString("numeStudent");

        }
        procentaj = numarIntrebariCorecte*100/numarIntrebari;
        textView.setText(procentaj+"%");


        DatabaseHandler dh = new DatabaseHandler(this);
        GestiuneTest gt = new GestiuneTest();
        gt.setIdGestiune(0); //o pun 0, dar nu afecteaza pt ca in baza de date coloana idGestiune este setata pe autoincrement
        gt.setIdTest(idTest);
        gt.setNumeStudent(numeStudent);
        gt.setIdStudent(idStudent);
        gt.setNota(procentaj);
        dh.addGestiune(gt);
        System.out.println(idTest+"RezultattActivti @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");

        home = (ImageButton)findViewById(R.id.imageHome_rezultat);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(RezultatActivity.this, StudentActivity.class);
                myIntent.putExtra("idStudent", idStudent);
                myIntent.putExtra("numeStudent", numeStudent);

                RezultatActivity.this.startActivity(myIntent);
            }
        });
    }
}

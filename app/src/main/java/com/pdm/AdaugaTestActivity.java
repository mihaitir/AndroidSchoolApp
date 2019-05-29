package com.pdm;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.pdm.entitati.Test;

public class AdaugaTestActivity extends AppCompatActivity {

    Button butonCatreIntrebari;
    EditText numeTest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adauga_test);
        final DatabaseHandler dh = new DatabaseHandler(this); //trebuie sa o pun final ca da eroare altfel
        butonCatreIntrebari = (Button)findViewById(R.id.butonCatreIntrebari);
        numeTest = (EditText)findViewById(R.id.editTextAdaugaTest);

        butonCatreIntrebari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Test t = new Test(0,String.valueOf(numeTest.getText()),null);
                dh.addTest(t);
                //trebuie sa trimit id-ul Testului ce l-am creat acum, deoarece am nevoie de el pentru clasa intrebare
                int id = dh.getNumberOfTests();
                Intent myIntent = new Intent(AdaugaTestActivity.this, AdaugareIntrebareActivity.class);
                myIntent.putExtra("numarTeste", id);
                AdaugaTestActivity.this.startActivity(myIntent);
            }
        });
    }
}

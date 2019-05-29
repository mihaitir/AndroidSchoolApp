package com.pdm;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.pdm.entitati.Intrebare;
import com.pdm.entitati.Varianta;

import java.util.ArrayList;

public class AdaugareIntrebareActivity extends AppCompatActivity {

    ImageButton butonAdaugaIntrebare;
    EditText numeIntrebare;
    EditText textVar1;
    EditText textVar2;
    EditText textVar3;
    EditText textVar4;
    CheckBox casuta1;
    CheckBox casuta2;
    CheckBox casuta3;
    CheckBox casuta4;
    boolean b1 = false;
    boolean b2 = false;
    boolean b3 = false;
    boolean b4 = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adaugare_intrebare);
        final DatabaseHandler dh = new DatabaseHandler(this);

        casuta1 = (CheckBox)findViewById(R.id.casuta1);
        casuta2 = (CheckBox)findViewById(R.id.casuta2);
        casuta3 = (CheckBox)findViewById(R.id.casuta3);
        casuta4 = (CheckBox)findViewById(R.id.casuta4);

        casuta1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    b1 = true;
                }
                else{
                    b1 = false;
                }
            }
        });

        casuta2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    b2 = true;
                }
                else
                {
                    b2 = false;
                }
            }
        });

        casuta3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    b3 = true;
                }
                else b3 = false;
            }
        });

        casuta4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    b4 = true;
                }
                else b4 = false;
            }
        });

        butonAdaugaIntrebare = (ImageButton) findViewById(R.id.butonAdaugaIntrebare);
        butonAdaugaIntrebare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //preluam idTest ce a fost transim de catre activitatea de dinainte
                int id = 0;
                Bundle extras = getIntent().getExtras();
                if (extras != null) {
                    id = extras.getInt("numarTeste");
                }

                int numarIntrebari = dh.getNumarIntrebari()+1;
                numeIntrebare = (EditText)findViewById(R.id.editTextIntrebare);
                //creem un obiect de tipul intrebare si il adaugam in baza de date
                Intrebare intrebare = new Intrebare(0,id,String.valueOf(numeIntrebare.getText()),null);
                dh.addIntrebare(intrebare);

                //pentru aceasta intrebare trebuie adaugate in baza de date cele 4 varianta de raspuns
                textVar1 = (EditText)findViewById(R.id.textVar1);
                Varianta varianta = new Varianta(0,numarIntrebari,String.valueOf(textVar1.getText()),b1);
                dh.addVarianta(varianta);

                textVar2 = (EditText)findViewById(R.id.textVar2);
                varianta = new Varianta(0,numarIntrebari,String.valueOf(textVar2.getText()),b2);
                dh.addVarianta(varianta);

                textVar3 = (EditText)findViewById(R.id.textVar3);
                varianta = new Varianta(0,numarIntrebari,String.valueOf(textVar3.getText()),b3);
                dh.addVarianta(varianta);

                textVar4 = (EditText)findViewById(R.id.textVar4);
                varianta = new Varianta(0, numarIntrebari, String.valueOf(textVar4.getText()), b4);
                dh.addVarianta(varianta);

                Toast.makeText(getApplicationContext(),"Intrebare Adaugata cu succes!!!", Toast.LENGTH_LONG).show();
                numeIntrebare.setText("");
                textVar1.setText("");
                textVar2.setText("");
                textVar3.setText("");
                textVar4.setText("");
                casuta1.setChecked(false);
                casuta2.setChecked(false);
                casuta3.setChecked(false);
                casuta4.setChecked(false);
                System.out.println("Si acum in bd avem in lista cu intrebari");


                ArrayList<Intrebare> l= dh.getToateIntrebari();
                for(int i =0;i<l.size();i++)
                {
                    System.out.println(l.get(i));
                }

                System.out.println("Si acum in bd avem urm variante");
                ArrayList<Varianta> l2 = dh.getToateVariantele();
                for(int i=0; i<l2.size();i++)
                {
                    System.out.println(l2.get(i));
                }
                System.out.println();
                System.out.println();
                System.out.println("#####################################################################################");
            }
        });

    }
}

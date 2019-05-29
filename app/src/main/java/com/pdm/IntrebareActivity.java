package com.pdm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.pdm.entitati.Intrebare;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class IntrebareActivity extends AppCompatActivity {


    int contor = 0;
    int numarIntrebariRaspunseCorect = 0;
    int idTest;
    ArrayList<Intrebare> listaIntrebari;
    ImageButton butonNext;
    int idStudent;
    String numeStudent;

    TextView textIntrebare;
    CheckBox checkBox1;
    CheckBox checkBox2;
    CheckBox checkBox3;
    CheckBox checkBox4;

    DatabaseHandler dh = new DatabaseHandler(this);

    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(), "Nu se poate realiza aceasta operatie", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intrebare);

        textIntrebare = (TextView) findViewById(R.id.text_enunt_intrebare);
        checkBox1 = (CheckBox)findViewById(R.id.checkBox1);
        checkBox2 = (CheckBox)findViewById(R.id.checkBox2);
        checkBox3 = (CheckBox)findViewById(R.id.checkBox3);
        checkBox4 = (CheckBox)findViewById(R.id.checkBox4);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            idTest = extras.getInt("idTest");
            idStudent = extras.getInt("idStudent");
            numeStudent = extras.getString("numeStudent");
        }


        //obtinem lista cu intrebarile aferenta testului selectat...
        listaIntrebari = dh.getToateIntrebariCuVariante(idTest);
        //completez prima intrebare prima intrebare
        textIntrebare.setText(listaIntrebari.get(0).getNumeIntrebare());
        checkBox1.setText(listaIntrebari.get(0).getListaVariante().get(0).getText());
        checkBox2.setText(listaIntrebari.get(0).getListaVariante().get(1).getText());
        checkBox3.setText(listaIntrebari.get(0).getListaVariante().get(2).getText());
        checkBox4.setText(listaIntrebari.get(0).getListaVariante().get(3).getText());

        butonNext = (ImageButton)findViewById(R.id.butonNext);
        butonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //testez daca am reusit sa raspund corect la intrebare
                //daca un raspuns este bifat si nu ar fi trebuie sa fie
                //sau daca un raspuns nu este bifat si ar trebuii sa fie
                //atunci raspunsul oferit este incorect, si nu se primeste punct pe intrebare
                //facem asta pentru cele 4 casute:
                //initial presupunem ca s-a raspuns corect
                boolean raspunsulAFostCorect = true;

                if (checkBox1.isChecked() == true && listaIntrebari.get(contor).getListaVariante().get(0).isCorect() == false
                        || checkBox1.isChecked() == false && listaIntrebari.get(contor).getListaVariante().get(0).isCorect() == true)
                    raspunsulAFostCorect = false;

                if (checkBox2.isChecked() == true && listaIntrebari.get(contor).getListaVariante().get(1).isCorect() == false
                        || checkBox2.isChecked() == false && listaIntrebari.get(contor).getListaVariante().get(1).isCorect() == true)
                    raspunsulAFostCorect = false;

                if (checkBox3.isChecked() == true && listaIntrebari.get(contor).getListaVariante().get(2).isCorect() == false
                        || checkBox3.isChecked() == false && listaIntrebari.get(contor).getListaVariante().get(2).isCorect() == true)
                    raspunsulAFostCorect = false;

                if (checkBox4.isChecked() == true && listaIntrebari.get(contor).getListaVariante().get(3).isCorect() == false
                        || checkBox4.isChecked() == false && listaIntrebari.get(contor).getListaVariante().get(3).isCorect() == true)
                    raspunsulAFostCorect = false;

                if (raspunsulAFostCorect == true)
                {   System.out.println("Ati raspuns corect");
                    numarIntrebariRaspunseCorect++;
                }
                contor++;
                //daca asta a fost ultima intrebare atunci nu mai completez

                //completez activitatea cu intrebarea urmatoare (uncheck la casute, setare text la restul;

                //daca am ajuns la ultima intrebare atunci merg la activitatea cu rezultatul testului
                if (contor == listaIntrebari.size()) {
                    //daca am parcurs toate intrebarile, atunci ajungem la activitatea care prezinta rezultatul final
                    Intent myIntent = new Intent(IntrebareActivity.this, RezultatActivity.class);
                    myIntent.putExtra("numarRaspunsuri", contor); //Optional parameters
                    myIntent.putExtra("numarRaspunsuriCorecte", numarIntrebariRaspunseCorect);
                    myIntent.putExtra("idTest",idTest);
                    myIntent.putExtra("numeStudent", numeStudent);
                    myIntent.putExtra("idStudent",idStudent);
                    IntrebareActivity.this.startActivity(myIntent);
                } else {
                    //atunci completez cu urmatoarea intrebare
                    checkBox1.setChecked(false);
                    checkBox2.setChecked(false);
                    checkBox3.setChecked(false);
                    checkBox4.setChecked(false);
                    textIntrebare.setText(listaIntrebari.get(contor).getNumeIntrebare());
                    checkBox1.setText(listaIntrebari.get(contor).getListaVariante().get(0).getText());
                    checkBox2.setText(listaIntrebari.get(contor).getListaVariante().get(1).getText());
                    checkBox3.setText(listaIntrebari.get(contor).getListaVariante().get(2).getText());
                    checkBox4.setText(listaIntrebari.get(contor).getListaVariante().get(3).getText());
                }
            }
        });
    }
}

package com.pdm;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.pdm.buttons.ButonTest;
import com.pdm.entitati.Test;
import java.util.ArrayList;

public class StudentActivity extends AppCompatActivity {


    ArrayList<Test> listaTeste;
    int i2;
    DatabaseHandler dh = new DatabaseHandler(this);
    int idStudent;
    ImageButton butonLogout;
    String numeStudent;
    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(), "Delogati-va folosind butonul de sus", Toast.LENGTH_SHORT).show();
    }

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            idStudent = extras.getInt("idStudent");
            numeStudent = extras.getString("numeStudent");
        }
        setContentView(R.layout.activity_student);


        butonLogout = (ImageButton)findViewById(R.id.button_back_activity_student);
        butonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(StudentActivity.this,MainActivity.class);
                StudentActivity.this.startActivity(myIntent);

            }
        });
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            LinearLayout linear = findViewById(R.id.linear);
            params.setMargins(10,10,10,10);

     listaTeste = dh.getToateTestele();

        for(int i=0;i<listaTeste.size();i++) {
            i2 = i;
            final ButonTest b = new ButonTest(StudentActivity.this, i);
            b.setNota(dh.testRezolvat(idStudent, listaTeste.get(i).getIdTest()));
            if (dh.testRezolvat(idStudent, listaTeste.get(i).getIdTest()) != -1)
            {
                b.setText(listaTeste.get(i).getNumeTest()+ " nota "+ b.getNota());
                if (b.getNota() >= 50.0)
                b.setBackgroundColor(Color.rgb(0,200,10));
                else  b.setBackgroundColor(Color.rgb(255,0,1));
            }
            else{
                b.setText(listaTeste.get(i).getNumeTest());
                b.setBackgroundColor(Color.rgb(70, 80, 90));
            }

            //trimit prin intent numele testului si id-ul testului ca sa pot scoate din BD intrebarile coresp testului
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (b.getNota() == -1) {
                        Intent myIntent = new Intent(StudentActivity.this, StartTestActivity.class);
                        myIntent.putExtra("numeTest", listaTeste.get(b.getId()).getNumeTest());
                        myIntent.putExtra("idTest", listaTeste.get(b.getId()).getIdTest());
                        myIntent.putExtra("idStudent", idStudent);
                        myIntent.putExtra("numeStudent", numeStudent);
                        System.out.println(listaTeste.get(b.getId()).getIdTest()+"StudentActivty@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");

                        //fac un select count in tabela intreabari ca sa vad cate intrebari are testul;
                        int nr = 0;
                        nr = dh.getNumarIntrebari(listaTeste.get(b.getId()).getIdTest());
                        myIntent.putExtra("numarIntrebari", nr);
                        StudentActivity.this.startActivity(myIntent);
                    }
                    else Toast.makeText(getApplicationContext(),"Testul a fost dat!!!!!", Toast.LENGTH_LONG).show();
                }
            });
            linear.addView(b,params);
        }
    }
}
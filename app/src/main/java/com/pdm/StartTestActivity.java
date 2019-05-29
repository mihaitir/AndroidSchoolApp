package com.pdm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class StartTestActivity extends AppCompatActivity {

    TextView textViewNumeTest;
    TextView textViewNumarIntrebari;
    ImageButton buttonPlay;
    int idTest = 0;
    int numarIntrebari = 0;
    int idStudent;
    String numeTest = "";
    String numeStudent = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_test);

        final DatabaseHandler dh = new DatabaseHandler(this);
        textViewNumeTest = (TextView)findViewById(R.id.textViewNumeTest);
        textViewNumarIntrebari = (TextView)findViewById(R.id.textViewNumarIntrebari);
        buttonPlay = (ImageButton)findViewById(R.id.butonPlay);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            idTest = extras.getInt("idTest");
            numarIntrebari = extras.getInt("numarIntrebari");
            numeTest = extras.getString("numeTest");
            idStudent = extras.getInt("idStudent");
            numeStudent = extras.getString("numeStudent");
        }
        textViewNumeTest.setText(numeTest);
        textViewNumarIntrebari.setText(String.valueOf(numarIntrebari));

        buttonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dh.getNumarIntrebari(idTest) == 0) {
                    Toast.makeText(getApplicationContext(),"Testul nu contine intrebari", Toast.LENGTH_LONG).show();
                }else {
                    Intent myIntent = new Intent(StartTestActivity.this, IntrebareActivity.class);
                    //trimitem id-ul testului, pentru a stii ce intrebari sa preluam din baza de date
                    myIntent.putExtra("idTest", idTest);
                    myIntent.putExtra("idStudent", idStudent);
                    myIntent.putExtra("numeStudent", numeStudent);
                    System.out.println(idTest +"StudentActivty@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                    StartTestActivity.this.startActivity(myIntent);
                }
            }
        });
    }
}

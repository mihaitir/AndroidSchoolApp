package com.pdm;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class ProfesorActivity extends AppCompatActivity {

    ImageButton butonAdaugaTest;
    ImageButton butonScoreTest;
    ImageButton butonLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profesor);

        butonAdaugaTest = (ImageButton)findViewById(R.id.buttonAdaugaTest);
        butonAdaugaTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(ProfesorActivity.this,AdaugaTestActivity.class);
                ProfesorActivity.this.startActivity(myIntent);
            }
        });

        butonScoreTest = (ImageButton)findViewById(R.id.buttonClasament);
        butonScoreTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfesorActivity.this, ScoreActivity.class);
                ProfesorActivity.this.startActivity(intent);
            }
        });

        butonLogout = (ImageButton)findViewById(R.id.buttonLogout);
        butonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfesorActivity.this, MainActivity.class);
                ProfesorActivity.this.startActivity(intent);
            }
        });
    }
}

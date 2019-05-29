package com.pdm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.pdm.entitati.Test;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    DatabaseHandler dh = new DatabaseHandler(this);
    Button login;
    Button signin;
    EditText nume;
    EditText parola;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DatabaseHandler db = new DatabaseHandler(this);

        login = (Button)findViewById(R.id.button_login);
        signin = (Button)findViewById(R.id.buttonSignIn_Main_Activity);
        nume = (EditText)findViewById(R.id.editTextUsername);
        parola = (EditText)findViewById(R.id.editTextPassword);


        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this,SignInActivity.class);
                MainActivity.this.startActivity(myIntent);
            }
         });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean[] contGasit = dh.onLogin(String.valueOf(nume.getText()),String.valueOf(parola.getText()));
                if (contGasit[0]==true) {
                    if (contGasit[1]) {
                        Intent myIntent = new Intent(MainActivity.this, ProfesorActivity.class);
                        MainActivity.this.startActivity(myIntent);
                    }
                    else{
                        Intent myIntent = new Intent(MainActivity.this, StudentActivity.class);
                        myIntent.putExtra("idStudent", dh.getIdContDupaNume(String.valueOf(nume.getText())));
                        myIntent.putExtra("numeStudent", String.valueOf(nume.getText()));
                        MainActivity.this.startActivity(myIntent);
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(),"Nume sau parola gresita",Toast.LENGTH_LONG).show();
                }
            }
        });

    }


}

package com.pdm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class SignInActivity extends AppCompatActivity {

    Button signin;
    EditText nume;
    EditText parola;
    EditText parola2;
    CheckBox casuta;

    DatabaseHandler dh = new DatabaseHandler(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        signin = (Button)findViewById(R.id.buttonCreeareCont_SignInActivity);
        nume = (EditText)findViewById(R.id.editText_numeSignIn);
        parola = (EditText)findViewById(R.id.editTextParola_SignIn);
        parola2 = (EditText)findViewById(R.id.editTextParola_SignIn2);
        casuta = (CheckBox) findViewById(R.id.checkBoxProfesor);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (String.valueOf(parola.getText()).equals(String.valueOf(parola2.getText()))==false){
                    Toast.makeText(getApplicationContext(),"Parolele nu se potrivesc!", Toast.LENGTH_LONG).show();
                }
                else {
                    int bifat = 0;
                    if (casuta.isChecked()) bifat = 1;
                    String parolaEncriptata = dh.md5(String.valueOf(parola.getText()));
                    boolean reusit = dh.onSignIn(String.valueOf(nume.getText()), parolaEncriptata, bifat);
                    if (reusit) Toast.makeText(getApplicationContext(),"Contul a fost inserat cu succes", Toast.LENGTH_LONG).show();
                    else Toast.makeText(getApplicationContext(),"Nu s-a putut creea contul, numele este folosit!", Toast.LENGTH_LONG).show();
                }
                }
        });

    }
}

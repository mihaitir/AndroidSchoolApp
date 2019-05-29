package com.pdm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.pdm.buttons.ButonStudent;
import com.pdm.buttons.ButonTest;
import com.pdm.entitati.Student;

import java.util.ArrayList;

public class ScoreActivity extends AppCompatActivity {

    ArrayList<Student> listaStudenti;
    int idStudent = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        DatabaseHandler dh = new DatabaseHandler(this);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        LinearLayout linear = findViewById(R.id.linear2);
        params.setMargins(10,10,10,10);

        listaStudenti = dh.getListaStudent();

        for(int i=0; i<listaStudenti.size();i++)
        {
            final ButonStudent b = new ButonStudent(ScoreActivity.this);
            idStudent = listaStudenti.get(i).getIdStudent();
            String numeStudent;
            numeStudent = listaStudenti.get(i).getNumeStudent();
            b.setIdStudent(listaStudenti.get(i).getIdStudent());
            b.setNumeStudent(listaStudenti.get(i).getNumeStudent());
            b.setText(listaStudenti.get(i).getNumeStudent()+"");
        b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ScoreActivity.this, ClasamentActivity.class);
                    intent.putExtra("idStudent", b.getIdStudent());
                    intent.putExtra("numeStudent", b.getNumeStudent());
                    ScoreActivity.this.startActivity(intent);
                }
            });
            linear.addView(b,params);
        }


    }
}

package com.pdm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.pdm.entitati.GestiuneTest;
import com.pdm.entitati.Test;

import java.util.ArrayList;

public class ClasamentActivity extends AppCompatActivity {

    int idStudent;
    String numeStudent;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clasament);
        listView = (ListView)findViewById(R.id.listView);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            idStudent = extras.getInt("idStudent");
            numeStudent = extras.getString("numeStudent");
        }
        DatabaseHandler dh = new DatabaseHandler(this);
        ArrayList<GestiuneTest> toataListaGestiune = dh.getListaGestiuni();
        System.out.println(toataListaGestiune);
        ArrayList<GestiuneTest> listaGestiune = dh.getListaGestiuniDupaNumeStudent(numeStudent);
        ArrayList<String> detaliiTest = new ArrayList<>();
        detaliiTest.add("Studentul "+ numeStudent + " are urmatoarele note:");
        for(int i=0;i<listaGestiune.size();i++)
        {
            detaliiTest.add(listaGestiune.get(i).getNota()+"  "+dh.numeTestAnumitId(listaGestiune.get(i).getIdTest()));
        }
       System.out.println(detaliiTest);
        listView = (ListView)findViewById(R.id.listView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, detaliiTest);
        listView.setAdapter(adapter);

    }
}

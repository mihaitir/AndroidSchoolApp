package com.pdm;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.pdm.entitati.GestiuneTest;
import com.pdm.entitati.Intrebare;
import com.pdm.entitati.Student;
import com.pdm.entitati.Test;
import com.pdm.entitati.Varianta;

import java.lang.reflect.Array;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;


public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "BazaCuNote";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //3rd argument to be passed is CursorFactory instance
    }

    // Creearea tabelelor
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TEST_TABLE = "CREATE TABLE Test ( " + "idTest INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "numeTest TEXT )";
        db.execSQL(CREATE_TEST_TABLE);

        String CREATE_INTREBARE_TABLE = "CREATE TABLE Intrebare ( " + "idIntrebare INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " idTest INTEGER, " + "numeIntrebare TEXT )";
        db.execSQL(CREATE_INTREBARE_TABLE);

        String CREATE_VARIANTA_TABLE = "CREATE TABLE Varianta ( " + "idVarianta INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " idIntrebare INTEGER, " + "numeVarianta TEXT," + "corect boolean )";
        db.execSQL(CREATE_VARIANTA_TABLE);

        String CREATE_CONT_TABLE = "CREATE TABLE Cont ( idCont INTEGER PRIMARY KEY AUTOINCREMENT, nume TEXT, parola TEXT, " +
                "bifat INTEGER) ";
        db.execSQL(CREATE_CONT_TABLE);

        String CREATE_GESTIUNE_TEST_TABLE = "CREATE TABLE GestiuneTest ( idGestiune INTEGER PRIMARY KEY AUTOINCREMENT, idStudent " +
                "INTEGER, idTest INTEGER, nota REAL, numeStudent TEXT)";
        db.execSQL(CREATE_GESTIUNE_TEST_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + "Test");
        db.execSQL("DROP TABLE IF EXISTS " + "Intrebare");
        db.execSQL("DROP TABLE IF EXISTS " + "Varianta");
        db.execSQL("DROP TABLE IF EXISTS " + "Cont");
        db.execSQL("DROP TABLE IF EXISTS " + "GestiuneTest" );

        onCreate(db);
    }

    void addTest(Test test) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("numeTest", test.getNumeTest());
        try {
            db.insert("Test", null, values);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Testul adaugat cu succes");
        db.close();
    }


    public ArrayList<Test> getToateTestele() {
        ArrayList<Test> teste = new ArrayList<Test>();
        String query = "SELECT  * FROM  Test";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);


        Test test = null;
        if (cursor.moveToFirst()) {
            do {
                test = new Test();
                test.setIdTest(Integer.parseInt(cursor.getString(0)));
                test.setNumeTest(cursor.getString(1));
                   teste.add(test);
            } while (cursor.moveToNext());
        }
        return teste;
    }

    public String numeTestAnumitId(int idTest){
        SQLiteDatabase db = this.getReadableDatabase();
        db.up
        String query = "SELECT numeTest from Test where idTest="+idTest;
        Cursor cursor = db.rawQuery(query,null);
        if (cursor.moveToFirst())
        {
            return  cursor.getString(0);
        }
        return "";
    }
    public ArrayList<GestiuneTest> getListaGestiuni(){
        ArrayList<GestiuneTest> gestiuneTests = new ArrayList<GestiuneTest>();
        String query = "SELECT  * FROM  GestiuneTest";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        GestiuneTest gestiuneTest = null;
        if (cursor.moveToFirst()) {
            do {
                gestiuneTest = new GestiuneTest();
                gestiuneTest.setIdGestiune(cursor.getInt(0));
                gestiuneTest.setIdStudent(cursor.getInt(1));
                gestiuneTest.setIdTest(cursor.getInt(2));
                gestiuneTest.setNota(cursor.getDouble(3));
                gestiuneTest.setNumeStudent(cursor.getString(4));
                gestiuneTests.add(gestiuneTest);
            } while (cursor.moveToNext());
        }
        return gestiuneTests;
    }

    public ArrayList<GestiuneTest> getListaGestiuniDupaNumeStudent(String numeStudent){
        ArrayList<GestiuneTest> gestiuneTests = new ArrayList<GestiuneTest>();
        String query = "SELECT  * FROM  GestiuneTest where numeStudent='"+  numeStudent+ "'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        GestiuneTest gestiuneTest = null;
        if (cursor.moveToFirst()) {
            do {
                gestiuneTest = new GestiuneTest();
                gestiuneTest.setIdGestiune(cursor.getInt(0));
                gestiuneTest.setIdStudent(cursor.getInt(1));
                gestiuneTest.setIdTest(cursor.getInt(2));
                gestiuneTest.setNota(cursor.getDouble(3));
                gestiuneTest.setNumeStudent(cursor.getString(4));
                gestiuneTests.add(gestiuneTest);
            } while (cursor.moveToNext());
        }
        return gestiuneTests;
    }



    public ArrayList<Test> getToateTesteleRezolvateDeUnStudent(int idStudent)
    {
        ArrayList<Test> teste = new ArrayList<Test>();
        String query = "SELECT  * FROM  GestiuneTest where idStudent="+idStudent;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Test test = null;
        if (cursor.moveToFirst()) {
            do {
                test = new Test();
                test.setIdTest(cursor.getInt(2));
                test.setNota(cursor.getDouble(3));
                teste.add(test);
            } while (cursor.moveToNext());
        }
        return teste;
    }


    //metoda ce returneaza numarul de inregistrari idTest, adica cate elemente am in coloana idTest...
    int getNumberOfTests() {
        String countQuery = "SELECT  idTest FROM  Test";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int nr = cursor.getCount();
        cursor.close();
        return nr;
    }

    void addIntrebare(Intrebare intrebare) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("idTest", intrebare.getIdTest());
        values.put("numeIntrebare", intrebare.getNumeIntrebare());
        try {
            db.insert("Intrebare", null, values);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Intrebare adaugata cu succes");
        db.close();
    }

    public ArrayList<Intrebare> getToateIntrebari() {
        ArrayList<Intrebare> intrebari = new ArrayList<Intrebare>();

        String query = "SELECT  * FROM  Intrebare";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        Intrebare intrebare = null;
        if (cursor.moveToFirst()) {
            do {
                intrebare = new Intrebare();
                intrebare.setIdIntrebare(Integer.parseInt(cursor.getString(0)));
                intrebare.setIdTest(Integer.parseInt(cursor.getString(1)));
                intrebare.setNumeIntrebare(cursor.getString(2));
                intrebari.add(intrebare);
            } while (cursor.moveToNext());
        }
        return intrebari;
    }

    public int getNumarIntrebari() {
        String countQuery = "SELECT  idIntrebare FROM  Intrebare";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int nr = cursor.getCount();
        cursor.close();
        return nr;
    }

    /*
    metoda ce intoarce numarul de intrebari al unui test cu un anumit id
     */
    public int getNumarIntrebari(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor mCount = db.rawQuery("select count(*) from Intrebare where idTest=" + id, null);
        mCount.moveToFirst();
        int count = mCount.getInt(0);
        mCount.close();
        return count;
    }

    public ArrayList<Intrebare> getToateIntrebariCuVariante(int idTest) {
        ArrayList<Intrebare> intrebari = new ArrayList<Intrebare>();

        String query = "SELECT  * FROM  Intrebare where idTest=" + idTest;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Intrebare intrebare = null;
        if (cursor.moveToFirst()) {
            do {
                intrebare = new Intrebare();
                intrebare.setIdIntrebare(Integer.parseInt(cursor.getString(0)));
                intrebare.setIdTest(Integer.parseInt(cursor.getString(1)));
                intrebare.setNumeIntrebare(cursor.getString(2));
                intrebare.setListaVariante(getToateVariantele(intrebare.getIdIntrebare()));
                intrebari.add(intrebare);
            } while (cursor.moveToNext());
        }
        return intrebari;

    }

    void addVarianta(Varianta varianta) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("idIntrebare", varianta.getIdIntrebare());
        values.put("numeVarianta", varianta.getText());
        values.put("corect", varianta.isCorect());
        try {
            db.insert("Varianta", null, values);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Varianta adaugata cu succes");
        db.close();
    }

    public ArrayList<Varianta> getToateVariantele() {
        ArrayList<Varianta> variante = new ArrayList<Varianta>();

        String query = "SELECT  * FROM  Varianta";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);


        Varianta varianta = null;
        if (cursor.moveToFirst()) {
            do {
                varianta = new Varianta();
                varianta.setIdVarianta(Integer.parseInt(cursor.getString(0)));
                varianta.setIdIntrebare(Integer.parseInt(cursor.getString(1)));
                varianta.setText(cursor.getString(2));
                //pt ca in Android nu am getBoolean ca si metoda la cursor, atunci o simulam prin 1 true, si 0 false
                if (Integer.parseInt(cursor.getString(3)) == 1)
                    varianta.setCorect(true);
                else
                    varianta.setCorect(false);
                variante.add(varianta);
            } while (cursor.moveToNext());
        }
        return variante;
    }


    //metoda ce returneaza variantele unei intrebari in functie de id-ul intrebarii
    public ArrayList<Varianta> getToateVariantele(int idIntrebare) {
        ArrayList<Varianta> variante = new ArrayList<Varianta>();
        String query = "SELECT  * FROM  Varianta where idIntrebare=" + idIntrebare;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Varianta varianta = null;
        if (cursor.moveToFirst()) {
            do {
                varianta = new Varianta();
                varianta.setIdVarianta(Integer.parseInt(cursor.getString(0)));
                varianta.setIdIntrebare(Integer.parseInt(cursor.getString(1)));
                varianta.setText(cursor.getString(2));
                if (Integer.parseInt(cursor.getString(3)) == 1)
                    varianta.setCorect(true);
                else
                    varianta.setCorect(false);
                variante.add(varianta);
            } while (cursor.moveToNext());
        }
        return variante;

    }

    //metoda ce realizeaza encriptarea parolei folosind algoritm de tipul md5
    public String md5(String s) {
        final String MD5 = "MD5";
        try {
            MessageDigest digest = java.security.MessageDigest
                    .getInstance(MD5);
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();
            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                String h = Integer.toHexString(0xFF & aMessageDigest);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    //metoda ce returneaza doua lucruri, primul daca contul este unul valid, iar in cazul in care
    //contul a fost valid si este de tip elev returneaza false, in cazul in care utilizatorul este
    //un profesror returneaza a doua valoare ca true;
    public boolean[] onLogin(String nume, String parola) {
        //inteorghrz baza de date si vad daca am un cont valid...pana atunci sa fac activitatea de sign in
        boolean[] tupluSimulat = new boolean[]{false, false};

        String query = "SELECT  * FROM  Cont";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {

                if (nume.equals(cursor.getString(1)) && md5(parola).equals(cursor.getString(2))) {
                    tupluSimulat[0] = true;
                    if (cursor.getInt(3) == 1)
                        tupluSimulat[1] = true; //verificam daca contul este unul de profesor,
                    break; // parasim ciclul, deoarece am identificat un cont
                }
            } while (cursor.moveToNext());
        }
        return tupluSimulat;
    }

    public boolean onSignIn(String nume, String parola, int casutaBifataProfesor) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nume", nume);
        values.put("parola", parola);
        values.put("bifat", casutaBifataProfesor);
        boolean ok = true;
        //aici trebuie sa parcurc toate numele din baza de date si sa nu se repete cu numele trimis ca parametru,deoarece nu pot
        //introduce un cont cu acelasi nume de doua ori...
        SQLiteDatabase db2 = this.getReadableDatabase();
        String query = "Select nume from Cont";
        Cursor cursor = db2.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                if (nume.equals(cursor.getString(0))) {
                    ok = false;
                    break;
                }
            } while (cursor.moveToNext());
        }

        if (ok) {
            db.insert("Cont", null, values);
            db.close();
            return ok;
        } else //cazul in care contul se gaseste deja
        {
            db.close();
            return ok;
        }
    }

    public int getIdContDupaNume(String nume) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "select idCont from Cont where nume='" + nume + "'";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            return cursor.getInt(0);
        }
        return 0;
    }

    public String getNumeContDupaId(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "select nume from Cont where idCont=" + id;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            return cursor.getString(0);
        }
        return "";
    }

    void addGestiune(GestiuneTest gestiuneTest) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("idStudent", gestiuneTest.getIdStudent());
        values.put("idTest", gestiuneTest.getIdTest());
        values.put("nota", gestiuneTest.getNota());
        values.put("numeStudent", gestiuneTest.getNumeStudent());
            db.insert("GestiuneTest", null, values);
        System.out.println("Gestiunea adaugata cu succes");
        db.close();
    }

    //metoda ce cauta si returneaza daca un student a dat sau nu un test cu un anumit id;
    //daca testul nu a fost dat returneaza -1;
    public double testRezolvat(int idStudent, int idTest) {
        String query = "SELECT  * FROM  GestiuneTest";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                if (idStudent == cursor.getInt(1) && idTest == cursor.getInt(2))
                {
                    return cursor.getDouble(3);
                    //parasim ciclul, nu mai are rost sa cautam daca am gasit ceea ce cautam
                }
            } while (cursor.moveToNext());
        }
        return -1;
    }

    public ArrayList<Student> getListaStudent(){
        ArrayList<Student> lista = new ArrayList<Student>();
        String query = "SELECT  * FROM  Cont";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        Student student = null;
        if (cursor.moveToFirst()) {
            do {
                student = new Student();
                if (cursor.getInt(3) == 0)
                {
                    student.setIdStudent(cursor.getInt(0));
                    student.setNumeStudent(cursor.getString(1));
                    lista.add(student);
                }
            } while (cursor.moveToNext());
        }
        return lista;
    }
}
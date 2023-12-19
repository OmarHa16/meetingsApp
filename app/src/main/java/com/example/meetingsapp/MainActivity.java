package com.example.meetingsapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    Button find, create, cancel, postponed, todays, postponeded, outline, settings;
    EditText date, time, place, placedes, person, persondes, ndate, ntime;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        find = findViewById(R.id.findDone_btn);
        create = findViewById(R.id.create_btn);
        cancel = findViewById(R.id.cancel_btn);
        postponed = findViewById(R.id.postponed_btn);
        todays = findViewById(R.id.todays_btn);
        postponeded = findViewById(R.id.postponeded_btn);
        outline = findViewById(R.id.outline_btn);
        settings = findViewById(R.id.settings_btn);


        date = findViewById(R.id.dateID);
        time = findViewById(R.id.timeID);
        place = findViewById(R.id.placeID);
        placedes = findViewById(R.id.placedesID);
        person = findViewById(R.id.personID);
        persondes = findViewById(R.id.persondesID);
        ndate = findViewById(R.id.ndateID);
        ntime = findViewById(R.id.ntimeID);


        db = openOrCreateDatabase("meetingsdb", Context.MODE_PRIVATE,null);
        onCreatedatabase(db);

    }
    public void onClick(View view){
        if(view == find){

        }
        if(view == create){}
        if(view == cancel){}
        if(view == postponed){}
        if(view == todays){}
        if(view == postponeded){}
        if(view == outline){}
        if(view == settings){}
    }
    public static void onCreatedatabase(SQLiteDatabase db) {
        String query = "CREATE TABLE IF NOT EXISTS Place (PlaceID INTEGER PRIMARY KEY AUTOINCREMENT, PlaceName VARCHAR, Description VARCHAR);";
        db.execSQL(query);

        String query1 = "CREATE TABLE IF NOT EXISTS Person(PersonID INTEGER PRIMARY KEY AUTOINCREMENT, PersonName VARCHAR, DescriptionP VARCHAR, Phone INTEGER);";
        db.execSQL(query1);

        String query2 = "CREATE TABLE IF NOT EXISTS Date (DateID INTEGER PRIMARY KEY AUTOINCREMENT, Date DATE ,Time TIME, Done INTEGER, Postponed INTGER, PlaceID INTEGER, PersonID INTEGER, FOREIGN KEY (PlaceID) REFERENCES Place(PlaceID), FOREIGN KEY (PersonID) REFERENCES Person(PersonID));";
        db.execSQL(query2);
    }
    public void clearText(){
        date.setText("");
        time.setText("");
        place.setText("");
        placedes.setText("");
        person.setText("");
        persondes.setText("");
        persondes.setText("");
    }
    public void setVisible(){
        ndate.setVisibility(View.VISIBLE);
        ntime.setVisibility(View.VISIBLE);
    }
    public void clearVisible(){
        ndate.setVisibility(View.GONE);
        ntime.setVisibility(View.GONE);
    }
    public void message(String title, String message, Boolean iconluncher){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message);
        builder.setTitle(title);
        builder.setCancelable(true);
        if(iconluncher = true){

        }
    }
}
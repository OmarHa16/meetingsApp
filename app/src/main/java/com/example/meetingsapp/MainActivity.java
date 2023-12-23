package com.example.meetingsapp;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toolbar;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;


public class MainActivity extends AppCompatActivity {

    Button find, create, cancel, postponed, todays, postponeded, outline, settings;
    EditText date, time, place, placedes, person, persondes, ndate, ntime;
    TextView newdate, newtime;
    SQLiteDatabase db;
    SharedPreferences sharedPreferences;
    ActivityResultLauncher<Intent> activityResultLauncher;
    TextView datev, timev, placev, placedesv, personv, persondesv, ndatev, ntimev;
    TableLayout tableLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tableLayout = findViewById(R.id.mainTable);

        find = findViewById(R.id.findDone_btn);
        create = findViewById(R.id.create_btn);
        cancel = findViewById(R.id.cancel_btn);
        postponed = findViewById(R.id.postponed_btn);
        todays = findViewById(R.id.todays_btn);
        postponeded = findViewById(R.id.postponeded_btn);
        outline = findViewById(R.id.outline_btn);
        settings = findViewById(R.id.settings_btn);


        datev = findViewById(R.id.DateViewID);
        timev = findViewById(R.id.TimeViewID);
        placev = findViewById(R.id.PlaceId);
        placedesv = findViewById(R.id.PlaceDesID);
        personv = findViewById(R.id.PersonID);
        persondesv = findViewById(R.id.PersonDesID);
        ndatev = findViewById(R.id.newdateID);
        ntimev = findViewById(R.id.newtimeID);


        find.setSoundEffectsEnabled(GetSoundEffects());
        create.setSoundEffectsEnabled(GetSoundEffects());
        cancel.setSoundEffectsEnabled(GetSoundEffects());
        todays.setSoundEffectsEnabled(GetSoundEffects());
        postponed.setSoundEffectsEnabled(GetSoundEffects());
        postponeded.setSoundEffectsEnabled(GetSoundEffects());
        outline.setSoundEffectsEnabled(GetSoundEffects());
        settings.setSoundEffectsEnabled(GetSoundEffects());



        if(getcolor()!=getResources().getColor(R.color.colorPrimary)){
            find.setBackgroundColor(getcolor());
            create.setBackgroundColor(getcolor());
            cancel.setBackgroundColor(getcolor());
            postponed.setBackgroundColor(getcolor());
            todays.setBackgroundColor(getcolor());
            postponeded.setBackgroundColor(getcolor());
            outline.setBackgroundColor(getcolor());
            settings.setBackgroundColor(getcolor());
        }
        if(getColor()!= getResources().getColor(R.color.colorPrimary)){
            tableLayout.setBackgroundColor(getColor());
        }
        if(GetColor()!= getResources().getColor(R.color.colorPrimary)){
            datev.setTextColor(GetColor());
            timev.setTextColor(GetColor());
            placev.setTextColor(GetColor());
            placedesv.setTextColor(GetColor());
            personv.setTextColor(GetColor());
            persondesv.setTextColor(GetColor());
            ndatev.setTextColor(GetColor());
            ntimev.setTextColor(GetColor());
        }


        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult o) {
                if(o.getResultCode()==RESULT_OK && o.getData() != null) {
                    int background = o.getData().getIntExtra("backgroundcolor", -1);
                    int button = o.getData().getIntExtra("buttoncolor", -1);
                    int text = o.getData().getIntExtra("textcolor", -1);
                    int s = o.getData().getIntExtra("buttonssuond",1);
                    buttonscolor(button);
                    backgroundColor(background);
                    textColor(text);
                    buttonssound(s==1);
                }
            }

        });



        date = findViewById(R.id.dateID);
        time = findViewById(R.id.timeID);
        place = findViewById(R.id.placeID);
        placedes = findViewById(R.id.placedesID);
        person = findViewById(R.id.personID);
        persondes = findViewById(R.id.persondesID);
        ndate = findViewById(R.id.ndateID);
        ntime = findViewById(R.id.ntimeID);


        db = openOrCreateDatabase("meetingdb", Context.MODE_PRIVATE,null);
        onCreatedatabase(db);

    }
    public void buttonscolor(int color){

        find.setBackgroundColor(getResources().getColor(color));
        create.setBackgroundColor(getResources().getColor(color));
        cancel.setBackgroundColor(getResources().getColor(color));
        todays.setBackgroundColor(getResources().getColor(color));
        postponed.setBackgroundColor(getResources().getColor(color));
        postponeded.setBackgroundColor(getResources().getColor(color));
        outline.setBackgroundColor(getResources().getColor(color));
        settings.setBackgroundColor(getResources().getColor(color));
        storcolor(getResources().getColor(color));

    }
    public void backgroundColor(int color){
        tableLayout.setBackgroundColor(getResources().getColor(color));
        storeColor(getResources().getColor(color));
    }
    public void textColor(int color){
        datev.setTextColor(getResources().getColor(color));
        timev.setTextColor(getResources().getColor(color));
        placev.setTextColor(getResources().getColor(color));
        placedesv.setTextColor(getResources().getColor(color));
        personv.setTextColor(getResources().getColor(color));
        persondesv.setTextColor(getResources().getColor(color));
        ndatev.setTextColor(getResources().getColor(color));
        ntimev.setTextColor(getResources().getColor(color));
        StoreColor(getResources().getColor(color));
    }

    public void buttonssound(boolean s){

            find.setSoundEffectsEnabled(s);
            create.setSoundEffectsEnabled(s);
            cancel.setSoundEffectsEnabled(s);
            postponed.setSoundEffectsEnabled(s);
            todays.setSoundEffectsEnabled(s);
            postponeded.setSoundEffectsEnabled(s);
            outline.setSoundEffectsEnabled(s);
            settings.setSoundEffectsEnabled(s);
            StoreSoundEffects(s);


    }

    public void onClick(View view){



        if(view == find){
            clicklesforfindfirst();
        }



        if(view == create){
            if(date.getText().toString().trim().length()==0 || time.getText().toString().trim().length()==0 || place.getText().toString().trim().length()==0 || person.getText().toString().trim().length()==0 || placedes.getText().toString().trim().length()==0 || persondes.getText().toString().trim().length()==0){
                message("Wrong Input", "some information is missing",false);
                return;
            }
            else{
                Cursor x = db.rawQuery("SELECT COUNT(*) FROM DateTable WHERE Date = '"+date.getText().toString()+"' AND Time = '"+time.getText().toString()+"';", null);
                if(x.moveToFirst()){
                    if(Integer.parseInt(x.getString(0))>=1){
                        message("Wrong Input", "you can't create meeting on the same date and time with other meeting; try change the time or both", false);
                        time.requestFocus();
                        x.close();
                        return;
                    }
                    else{
                        String q = "INSERT INTO Place(PlaceName, Description) VALUES('"+place.getText().toString()+"', '"+placedes.getText().toString()+"');";
                        String q2 = "INSERT INTO Person(PersonName, DescriptionP) VALUES('"+person.getText().toString()+"', '"+persondes.getText().toString()+"');";
                        SQLiteStatement s1 = db.compileStatement(q);
                        SQLiteStatement s2 = db.compileStatement(q2);
                        long id1 = s1.executeInsert();
                        long id2 = s2.executeInsert();
                        db.execSQL("INSERT INTO DateTable(Date, Time, Done, Postponed, PlaceID, PersonID) VALUES('"+date.getText().toString()+"', '"+time.getText().toString()+"', 0, 0, "+id1+", "+id2+");");
                        s1.close();
                        s2.close();
                        message("Done", "Meeting created", false);
                        clearText();
                        x.close();
                        return;
                    }
                }

            }
        }




        if(view == cancel) {
            if (date.getText().toString().trim().length() == 0 || time.getText().toString().trim().length() == 0) {
                message("Wrong Input", "you need to fill the date and the time", false);
                date.requestFocus();
                return;
            }
            else{
                Cursor x = db.rawQuery("SELECT COUNT(*) FROM DateTable WHERE Date = '"+date.getText().toString()+"' AND Time = '"+time.getText().toString()+"';", null);
                if(x.moveToFirst()){
                    if(Objects.equals(x.getString(0), "1")){
                        db.execSQL("DELETE FROM DateTable WHERE Date = '"+date.getText().toString()+"' AND Time = '"+time.getText().toString()+"';");
                        message("Done", "the meeting has been deleted successfully",false);
                        clearText();
                        x.close();
                        return;
                    }
                    else{
                        message("None", "you don't have meeting on this date and time", false);
                        x.close();
                        return;
                    }
                }
            }
        }




        if(view == postponed){
            onclicklesforpostponedfirst();
        }




        if(view == todays){
            LocalDateTime myDateObj = LocalDateTime.now();
            DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            String formattedDate = myDateObj.format(myFormatObj);
            Cursor c = db.rawQuery("SELECT PlaceName, Description, PersonName, DescriptionP, Date, Time FROM DateTable JOIN Place JOIN Person ON Date = '"+ formattedDate+"' AND DateTable.PlaceID = Place.PlaceID AND DateTable.PersonID = Person.PersonID",null);
            StringBuffer buffer = new StringBuffer();
            while (c.moveToNext()){
                buffer.append("Date: "+c.getString(4)+"\n");
                buffer.append("Time: "+c.getString(5)+"\n");
                buffer.append("Place: "+c.getString(0)+"\n");
                buffer.append("Place Description: "+c.getString(1)+"\n");
                buffer.append("Person: "+c.getString(2)+"\n");
                buffer.append("Person Description: "+c.getString(3)+"\n\n");
            }
            message("Todays Meetings", buffer.toString(), false);
        }



        if(view == postponeded){
            LocalDateTime myDateObj = LocalDateTime.now();
            DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            String formattedDate = myDateObj.format(myFormatObj);
            Cursor c = db.rawQuery("SELECT PlaceName, Description, PersonName, DescriptionP, Date, Time, Postponed FROM DateTable JOIN Place JOIN Person ON DateTable.PlaceID = Place.PlaceID AND DateTable.PersonID = Person.PersonID",null);
            StringBuffer buffer = new StringBuffer();
            while (c.moveToNext()){
                if(Objects.equals(c.getString(6), "1")){
                    buffer.append("Date: "+c.getString(4)+"\n");
                    buffer.append("Time: "+c.getString(5)+"\n");
                    buffer.append("Place: "+c.getString(0)+"\n");
                    buffer.append("Place Description: "+c.getString(1)+"\n");
                    buffer.append("Person: "+c.getString(2)+"\n");
                    buffer.append("Person Description: "+c.getString(3)+"\n\n");
                }
            }
            message("Postponeded Meetings", buffer.toString(), false);
        }



        if(view == outline){
            LocalDateTime myDateObj = LocalDateTime.now();
            DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            String formattedDate = myDateObj.format(myFormatObj);
            Cursor c = db.rawQuery("SELECT PlaceName, Description, PersonName, DescriptionP, Date, Time, Done FROM DateTable JOIN Place JOIN Person ON DateTable.PlaceID = Place.PlaceID AND DateTable.PersonID = Person.PersonID",null);
            StringBuffer buffer = new StringBuffer();
            int s=0;
            while (c.moveToNext()){
                LocalDateTime x = LocalDateTime.parse(c.getString(4),myFormatObj);
                if(!Objects.equals(c.getString(6), "1") && x.compareTo(myDateObj)==-1){
                    buffer.append("Date: "+c.getString(4)+"\n");
                    buffer.append("Time: "+c.getString(5)+"\n");
                    buffer.append("Place: "+c.getString(0)+"\n");
                    buffer.append("Place Description: "+c.getString(1)+"\n");
                    buffer.append("Person: "+c.getString(2)+"\n");
                    buffer.append("Person Description: "+c.getString(3)+"\n\n");
                    s++;
                }
            }
            message("Outline Meetings", buffer.toString(), s >= 3);
        }



        if(view == settings){
            Intent intent = new Intent(this,SettingsActivity.class);
            activityResultLauncher.launch(intent);
        }
    }
    public static void onCreatedatabase(SQLiteDatabase db) {
        String query = "CREATE TABLE IF NOT EXISTS Place(PlaceID INTEGER PRIMARY KEY AUTOINCREMENT, PlaceName VARCHAR, Description VARCHAR);";
        db.execSQL(query);

        String query1 = "CREATE TABLE IF NOT EXISTS Person(PersonID INTEGER PRIMARY KEY AUTOINCREMENT, PersonName VARCHAR, DescriptionP VARCHAR, Phone INTEGER);";
        db.execSQL(query1);

        String query2 = "CREATE TABLE IF NOT EXISTS DateTable(DateID INTEGER PRIMARY KEY AUTOINCREMENT, Date TEXT ,Time TEXT, Done INTEGER, Postponed INTGER, PlaceID INTEGER, PersonID INTEGER, FOREIGN KEY (PlaceID) REFERENCES Place(PlaceID), FOREIGN KEY (PersonID) REFERENCES Person(PersonID));";
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
        newdate.setVisibility(View.VISIBLE);
        newtime.setVisibility(View.VISIBLE);
        ndate.setVisibility(View.VISIBLE);
        ntime.setVisibility(View.VISIBLE);
    }
    public void clearVisible(){
        newdate.setVisibility(View.GONE);
        newtime.setVisibility(View.GONE);
        ndate.setVisibility(View.GONE);
        ntime.setVisibility(View.GONE);
    }
    public void message(String title, String Message, Boolean iconluncher){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.setCancelable(true);
        if(iconluncher = true){
            builder.setIcon(R.drawable.outlineicon);
        }
        else {
            builder.setIcon(R.drawable.ic_launcher_background);
        }
        builder.show();
    }
    public void clicklesforfind(){
        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor x = db.rawQuery("SELECT Done FROM DateTable WHERE Date = '"+date.getText().toString()+"' AND Time = '"+time.getText().toString()+"';", null);
                if(x.moveToFirst()){
                    if(Objects.equals(x.getString(0), "1")){
                        x.close();
                        message("Wrong Input", "Date is already done", false);
                        find.setText("find meeting");
                        clearText();
                        clicklesforfindfirst();
                        return;
                    }
                }
                x.close();
                db.execSQL("UPDATE DateTable SET Done = 1 WHERE Date = '"+date.getText().toString()+"' AND Time = '"+time.getText().toString()+"';");
                message("Done", "the meeting has been saved as done meeting", false);
                find.setText("find meeting");
                clearText();
                clicklesforfindfirst();
            }
        });
    }
    public void clicklesforfindfirst(){
        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(date.getText().toString().trim().length()==0 || time.getText().toString().trim().length()==0){
                    message("Wrong Input", "You need to input the date and the time of the meeting you want to find", false);
                    date.requestFocus();
                }
                else{
                    Cursor c = db.rawQuery("SELECT PlaceID, PersonID FROM DateTable WHERE Date = '"+date.getText().toString()+"' AND Time = '"+time.getText().toString()+"';",null);

                    if (c.moveToFirst()){
                        Cursor p = db.rawQuery("SELECT PlaceName, Description FROM Place WHERE PlaceID = "+c.getString(0)+";",null);
                        Cursor per = db.rawQuery("SELECT PersonName, DescriptionP FROM Person WHERE PersonID = "+c.getString(1)+";",null);
                        if (p.moveToFirst() && per.moveToFirst()){
                            place.setText(p.getString(0));
                            placedes.setText(p.getString(1));
                            person.setText(p.getString(0));
                            persondes.setText(p.getString(1));
                        }
                        p.close();
                        per.close();

                    }else{
                        message("Not Found", "no meeting found",false);
                    }
                    c.close();
                    find.setText("done the meeting");
                    clicklesforfind();
                }
            }
        });

    }
    public void onclicklesforpostponedfirst(){
        postponed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (date.getText().toString().trim().length() == 0 || time.getText().toString().trim().length() == 0) {
                    message("Wrong Input", "you need to fill the date and the time", false);
                    date.requestFocus();

                }else{

                    Cursor x = db.rawQuery("SELECT COUNT(*) FROM DateTable WHERE Date = '"+date.getText().toString()+"' AND Time = '"+time.getText().toString()+"';", null);
                    if(x.moveToFirst()){
                        if(Objects.equals(x.getString(0), "1")){
                            x.close();
                            setVisible();
                            onclicklesforpostpoend();

                        }
                        else{
                            message("None", "you don't have meeting on this date and time", false);
                            x.close();
                        }
                    }
                }
            }
        });
    }
    public void onclicklesforpostpoend(){
        postponed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ndate.getText().toString().trim().length() == 0 || ntime.getText().toString().trim().length() == 0) {
                    message("Wrong Input", "you need to set the new time and date",false);
                    ndate.requestFocus();
                }else {
                    Cursor x = db.rawQuery("SELECT COUNT(*) FROM DateTable WHERE Date = '" + ndate.getText().toString() + "' AND Time = '" + ntime.getText().toString() + "';", null);
                    if (x.moveToFirst()) {
                        if (Objects.equals(x.getString(0), "1")) {
                            message("Wrong Input", "you can't postponed meeting on the same date and time with other meeting; try change the time or both", false);
                            ntime.requestFocus();
                            x.close();
                        } else {
                            db.execSQL("UPDATE DateTable SET Date = '"+ndate.getText().toString()+"', Time = '"+ntime.getText().toString()+"', Postponed = 1 WHERE Date = '" + date.getText().toString() + "' AND Time = '" + time.getText().toString() + "';");
                            message("Done", "the meeting has been postponeded successfully", false);
                            clearText();
                            x.close();
                            clearVisible();
                            onclicklesforpostponedfirst();
                        }

                    }
                }


            }
        });
    }

    private void storcolor(int color){
        SharedPreferences sharedPreferences=getSharedPreferences("Button",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putInt("color",color);
        editor.apply();
    }
    private int getcolor(){
        SharedPreferences sharedPreferences=getSharedPreferences("Button",MODE_PRIVATE);
        int selectedcolor=sharedPreferences.getInt("color",getResources().getColor(R.color.colorPrimary));
        return selectedcolor;
    }
    private void storeColor(int color){
        SharedPreferences mSharedPreferencesre = getSharedPreferences("Tablelayout",MODE_PRIVATE);
        SharedPreferences.Editor mEditor=mSharedPreferencesre.edit();
        mEditor.putInt("color",color);
        mEditor.apply();
    }
    private int getColor(){
        SharedPreferences mSharedPreferencesre = getSharedPreferences("Tablelayout",MODE_PRIVATE);
        int selectedColor= mSharedPreferencesre.getInt("color",getResources().getColor(R.color.colorPrimary));
        return selectedColor;
    }
    private void StoreColor(int color){
        SharedPreferences mSharedPreferencesre = getSharedPreferences("Text",MODE_PRIVATE);
        SharedPreferences.Editor mEditor=mSharedPreferencesre.edit();
        mEditor.putInt("color",color);
        mEditor.apply();
    }
    private int GetColor(){
        SharedPreferences mSharedPreferencesre = getSharedPreferences("Text",MODE_PRIVATE);
        int selectedColor= mSharedPreferencesre.getInt("color",getResources().getColor(R.color.colorPrimary));
        return selectedColor;
    }
    private void StoreSoundEffects(boolean store){
        SharedPreferences mSharedPreferencesre = getSharedPreferences("store",MODE_PRIVATE);
        SharedPreferences.Editor mEditor=mSharedPreferencesre.edit();
        mEditor.putBoolean("store",store);
        mEditor.apply();
    }
    private boolean GetSoundEffects(){
        SharedPreferences mSharedPreferencesre = getSharedPreferences("store",MODE_PRIVATE);
        boolean selecteds= mSharedPreferencesre.getBoolean("store",true);
        return selecteds;
    }
}
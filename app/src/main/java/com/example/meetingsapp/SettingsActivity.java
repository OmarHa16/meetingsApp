package com.example.meetingsapp;

import static com.example.meetingsapp.R.*;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;

public class SettingsActivity extends AppCompatActivity {
    androidx.appcompat.widget.Toolbar mToolbar;
    Button Greyone;
    Button beigeone;
    Button deepblueone, switchb;
   LinearLayout linearLayout;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_settings);
        mToolbar=(Toolbar)findViewById(id.toolbar2);
        switchb = findViewById(id.btn_Switch);
        Greyone =(Button) findViewById(id.btn_blue);
        beigeone =(Button) findViewById(id.btn_Grey);
        deepblueone =(Button) findViewById(id.btn_Pink);
        linearLayout=(LinearLayout) findViewById(id.layout);







        if (getColor()!=getResources().getColor(color.colorPrimary)){
            mToolbar.setBackgroundColor(getColor());

        }
        if(getcolor()!=getResources().getColor(color.colorPrimary)){
            Greyone.setBackgroundColor(getcolor());
            beigeone.setBackgroundColor(getcolor());
            deepblueone.setBackgroundColor(getcolor());
        }
        if (GetColor()!=getResources().getColor(color.colorPrimary)){
            linearLayout.setBackgroundColor(GetColor());
        }
        Intent i = new Intent();

        Greyone.setOnClickListener(view -> {
            i.putExtra("buttoncolor", color.colorBlue);
            i.putExtra("backgroundcolor", color.colorgrey);
            i.putExtra("textcolor", color.black);
            setResult(RESULT_OK,i);
            finish();
        });

        beigeone.setOnClickListener(view -> {
            i.putExtra("buttoncolor", color.Teal);
            i.putExtra("backgroundcolor", color.beige);
            i.putExtra("textcolor", color.navyblue);
            setResult(RESULT_OK,i);
            finish();
        });

        deepblueone.setOnClickListener(view -> {
            i.putExtra("buttoncolor", color.Vred);
            i.putExtra("backgroundcolor", color.Deepblue);
            i.putExtra("textcolor", color.white);
            setResult(RESULT_OK,i);
            finish();
        });
        switchb.setOnClickListener(v -> {
            if(switchb.getText().toString().equals("Enable")){
                i.putExtra("buttonssuond",1);
                switchb.setText("Disable");
                setResult(RESULT_OK,i);
                finish();
            }else{
                i.putExtra("buttonssuond",0);
                switchb.setText("Enable");
                setResult(RESULT_OK,i);
                finish();
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
        int selectedcolor=sharedPreferences.getInt("color",getResources().getColor(color.colorPrimary));
        return selectedcolor;
    }
    private void storeColor(int color){
        SharedPreferences mSharedPreferencesre = getSharedPreferences("Toolbar",MODE_PRIVATE);
        SharedPreferences.Editor mEditor=mSharedPreferencesre.edit();
        mEditor.putInt("color",color);
        mEditor.apply();
    }
    private int getColor(){
        SharedPreferences mSharedPreferencesre = getSharedPreferences("Toolbar",MODE_PRIVATE);
        int selectedColor= mSharedPreferencesre.getInt("color",getResources().getColor(color.colorPrimary));
        return selectedColor;
    }
    private void StoreColor(int color){
        SharedPreferences mSharedPreferencesre = getSharedPreferences("LinearLayout",MODE_PRIVATE);
        SharedPreferences.Editor mEditor=mSharedPreferencesre.edit();
        mEditor.putInt("color",color);
        mEditor.apply();
    }
    private int GetColor(){
        SharedPreferences mSharedPreferencesre = getSharedPreferences("LinearLayout",MODE_PRIVATE);
        int selectedColor= mSharedPreferencesre.getInt("color",getResources().getColor(color.colorPrimary));
        return selectedColor;
    }

}
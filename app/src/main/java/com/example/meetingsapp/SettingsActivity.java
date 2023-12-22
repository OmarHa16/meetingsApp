package com.example.meetingsapp;

import static com.example.meetingsapp.R.*;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.ToggleButton;

public class SettingsActivity extends AppCompatActivity {
    androidx.appcompat.widget.Toolbar mToolbar;
    Button mBlueColor;
    Button mGreyColor;
    Button mPinkColor;
   LinearLayout linearLayout;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_settings);
        mToolbar=(Toolbar)findViewById(id.toolbar2);
        mBlueColor=(Button) findViewById(id.btn_blue);
        mGreyColor=(Button) findViewById(id.btn_Grey);
        mPinkColor=(Button) findViewById(id.btn_Pink);
        linearLayout=(LinearLayout) findViewById(id.layout);




        if (getColor()!=getResources().getColor(color.colorPrimary)){
            mToolbar.setBackgroundColor(getColor());

        }
        if(getcolor()!=getResources().getColor(color.colorPrimary)){
            mBlueColor.setBackgroundColor(getcolor());
            mGreyColor.setBackgroundColor(getcolor());
            mPinkColor.setBackgroundColor(getcolor());
        }
        if (GetColor()!=getResources().getColor(color.colorPrimary)){
            linearLayout.setBackgroundColor(GetColor());
        }


        mBlueColor.setOnClickListener(view -> {
            mToolbar.setBackgroundColor(getResources().getColor(color.colorBlue));
            storeColor(getResources().getColor(color.colorBlue));
            mBlueColor.setBackgroundColor(getResources().getColor(color.colorBlue));
            storcolor(getResources().getColor(color.colorBlue));
            linearLayout.setBackgroundColor(getResources().getColor(color.colorblue));
            StoreColor(getResources().getColor(color.colorblue));

        });

        mGreyColor.setOnClickListener(view -> {
            mToolbar.setBackgroundColor(getResources().getColor(color.colorGrey));
            storeColor(getResources().getColor(color.colorGrey));

            mGreyColor.setBackgroundColor(getResources().getColor(color.colorGrey));
            storcolor(getResources().getColor(color.colorGrey));
            linearLayout.setBackgroundColor(getResources().getColor(color.colorgrey));
            StoreColor(getResources().getColor(color.colorgrey));
        });

        mPinkColor.setOnClickListener(view -> {
            mToolbar.setBackgroundColor(getResources().getColor(color.colorPink));
            storeColor(getResources().getColor(color.colorPink));
            mPinkColor.setBackgroundColor(getResources().getColor(color.colorPink));
            storcolor(getResources().getColor(color.colorPink));
            linearLayout.setBackgroundColor(getResources().getColor(color.colorpink));
            StoreColor(getResources().getColor(color.colorpink));
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
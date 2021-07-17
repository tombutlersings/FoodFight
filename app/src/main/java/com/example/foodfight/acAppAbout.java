package com.example.foodfight;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

/**
 *  acAppAbout class activity is a fixed screen showing some information about
 *  the team and the app. It doesn't do anything, therefore needs no code.
 *  The values shown on this screen need to be edited in \values\strings.xml
 */
public class acAppAbout extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_about);
    }
}
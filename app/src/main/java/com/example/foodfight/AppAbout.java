package com.example.foodfight;

/* This activity is a fixed screen showing some information
 * about the team and the app.  It doesn't do anything.
 */


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class AppAbout extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_about);
    }
}
package com.example.foodfight;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

/** This activity is a fixed screen showing some information
 *  about the team and the app.  It doesn't do anything.
 *  The values shown on this screen need to be edited in
 *  \values\strings.xml
 *
 *  Driver: P Proctor
 */
public class acAppAbout extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_about);
    }
}
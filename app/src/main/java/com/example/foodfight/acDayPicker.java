package com.example.foodfight;

/* This activity is used to select a day from the built-in Android
 * calendar tool.
 */

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class acDayPicker extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_picker);
    }
}
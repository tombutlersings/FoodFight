package com.example.foodfight;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

/* This activity is part of the stretch goal where calorie trends
 * are graphically displayed. As-yet-to-be-define buttons will select
 * different trend types.
 */
public class acTrend extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trend);
    }
}
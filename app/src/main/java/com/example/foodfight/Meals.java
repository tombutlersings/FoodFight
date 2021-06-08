package com.example.foodfight;

/* This activity is where the user will select one of six meal times and
 * add food items to update the calorie count for that meal.
 * An button at the bottom of the screen navigates to the DayPicker activity
 * where a different day can be selected.
 */

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Meals extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meals);
    }
}
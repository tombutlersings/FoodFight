package com.example.foodfight;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

/**
 * TODO: rename this to MealsList???? -TB 9Jun21
 */

public class acMeals extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meals);

    }

    public void getAnotherDay(View view) {
        Intent intent = new Intent(this, acCalendar.class);
        startActivity(intent);
    }
}

package com.example.foodfight;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

/**
 * TODO: rename this to MealsList???? -TB 9Jun21
 */

/* FILE NAME CHANGE.
 * This enumeration should be renamed Meal.
 * The Meal class should be renamed Meals because it has totalCalories object
 * that is the total calories of all selected meal enumerations and we will analyze all
 * selected meal enumerations there.
 */

public class acMeals extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meals);

    }
}

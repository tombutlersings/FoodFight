package com.example.foodfight;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class acFoodItem extends AppCompatActivity {

    String foodName;
    String manufacturer;
    int calories;
    int servings;
    String servingUnit;
    int hhServings;
    String hhServingUnits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ac_food_item);
    }
    //  TODO: 1. get food information from database
    //  TODO: 2. display the food information, calories, name and the serving sizes with serving unit
    //  TODO: METHOD increasing quantity tied to green button
    //  TODO: METHOD decreasing quantity tied to red button
    //  TODO: METHOD SAVE DATA

}
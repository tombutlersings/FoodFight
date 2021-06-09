package com.example.foodfight;

/* This activity is where the embedded food list can be edited.  Adding new items
 * to the list navigates to the MenuEditor activity
 * TODO: This list is within the Meal, correct? - Tom 8-jun-21
 */

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class FoodList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list);
    }
}
package com.example.foodfight;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class acFoodItem extends AppCompatActivity {

    String foodName, manufacturer, selectedDate, mealType;
    float calories;
    int servings = 0;
    String servingUnit;
    String hhServingUnits;
    int hhServings;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ac_food_item);

        //Variables to get database information
        Intent intent = getIntent();
        selectedDate = intent.getStringExtra("MealDate");
        mealType = intent.getStringExtra("MealType");
        ArrayList<String> foodType = intent.getStringArrayListExtra("FoodType");
        foodName = foodType.get(0);
        manufacturer = foodType.get(1);
        calories = Float.parseFloat(foodType.get(2));

        Toast.makeText(this, manufacturer, Toast.LENGTH_SHORT).show();





    }

    // Called when user taps the red minus button
    public void btnCalorieDown(View view) {
        // TODO: Add code to decrement serving count IF current value is greater than zero
        if (servings > 0) {
            servings -= 1;
        }
    }

    // Called when user taps the green plus button
    public void btnCalorieUp(View view) {
        // TODO: Add code to increment serving count (is there a max?)
            servings += 1;
    }

    // Called when user taps the blue Done button
    public void btnDone(View view) {
        /* TODO: Code to calculate calories from the given servings (is this being kept in the
                 DB for later use/update?).  The fooditem should be updated for calorie count
                 when passed back to acFoodList.
         */
    }


}
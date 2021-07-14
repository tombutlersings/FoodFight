package com.example.foodfight;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class acFoodItem extends AppCompatActivity {

    String foodName;
    String manufacturer;
    float calories;
    int servings = 0;
    String servingUnit;
    int hhServings;
    String hhServingUnits;
    String selectedDate;
    String mealType;
    TextView tvFoodName;
    TextView tvHHServingSize;
    TextView tvHHServingSizeUnit;
    TextView tvCalories;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ac_food_item);

        //Variables to get database information
        Intent intent = getIntent();
        selectedDate = intent.getStringExtra("MealDate"); // passes meal date between intents
        mealType = intent.getStringExtra("MealType"); // passes meal type between intents
        ArrayList<String> foodType = intent.getStringArrayListExtra("FoodType"); // passes list between intents
        foodName = foodType.get(0);
        manufacturer = foodType.get(1);
        calories = Float.parseFloat(foodType.get(2));

        Toast.makeText(this, manufacturer, Toast.LENGTH_SHORT).show();

    }
    //  TODO: 1. get food information from database
    //  TODO: 2. display the food information, calories, name and the serving sizes with serving unit
    //  TODO: METHOD increasing quantity tied to green button
    //  TODO: METHOD decreasing quantity tied to red button
    //  TODO: METHOD SAVE DATA

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
        if (servings >= 0) {
            servings += 1;
        }
    }

    // Called when user taps the blue Done button
    public void btnDone(View view) {
        // TODO: Add code to pass info back to acFoodList
    }


}
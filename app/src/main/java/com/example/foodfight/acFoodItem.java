package com.example.foodfight;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class acFoodItem extends AppCompatActivity {

    String foodName, manufacturer, selectedDate, mealType, servingUnit;
    float calories, servingSize, totalCalories;
    float qty = (float) 1.0;

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
        servingUnit = foodType.get(3);
        servingSize = Float.parseFloat(foodType.get(4));

        Toast.makeText(this, manufacturer, Toast.LENGTH_SHORT).show();
        TextView showQty = findViewById(R.id.showQty);
        TextView displayFood = findViewById(R.id.displayFood);
        TextView displayCalories = findViewById(R.id.displayCalories);
        TextView displayServingSize = findViewById(R.id.displayServingSize);
        TextView displayServingUnit = findViewById(R.id.displayServingUnit);
        TextView displayManufacturer = findViewById(R.id.displayManufacturer);

        String caloriesConverted = Float.toString(calories);
        String sizeConverted = Float.toString(servingSize);
        String qtyConverted = Float.toString(qty);

        displayFood.setText(foodName);
        displayCalories.setText(caloriesConverted);
        showQty.setText(qtyConverted);
        displayServingUnit.setText(servingUnit);
        displayServingSize.setText(sizeConverted);
        displayManufacturer.setText(manufacturer);




    }

    // Called when user taps the red minus button
    public void btnCalorieDown(View view) {
        // TODO: Add code to decrement serving count IF current value is greater than zero
        if (qty > 0) {
            qty -= 0.5;
            TextView showQty = findViewById(R.id.showQty);
            String qtyConverted = Float.toString(qty);
            showQty.setText(qtyConverted);
            TextView totalCalories2 = findViewById(R.id.displayTotalCalories);
            totalCalories = qty * calories;
            String totcalsConverted = Float.toString(totalCalories);
            totalCalories2.setText(totcalsConverted);
        }



    }

    // Called when user taps the green plus button
    public void btnCalorieUp(View view) {
        // TODO: Add code to increment serving count (is there a max?)
            qty += 0.5;
        TextView showQty = findViewById(R.id.showQty);
        String qtyConverted = Float.toString(qty);
        showQty.setText(qtyConverted);
        TextView totalCalories2 = findViewById(R.id.displayTotalCalories);
        totalCalories = qty * calories;
        String totcalsConverted = Float.toString(totalCalories);
        totalCalories2.setText(totcalsConverted);
    }

    // Called when user taps the blue Done button
    public void btnDone(View view) {
        /* TODO: Code to calculate calories from the given servings (is this being kept in the
                 DB for later use/update?).  The fooditem should be updated for calorie count
                 when passed back to acFoodList.
         */
    }
//getIdFromAPI

}
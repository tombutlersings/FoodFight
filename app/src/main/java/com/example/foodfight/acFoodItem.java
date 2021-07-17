package com.example.foodfight;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * acFoodItem class use retrieved food data (food name, calories,
 * serving size, serving unit, and brand name) from database and other
 * acMeal class to update activity_food_item.xml
 */
public class acFoodItem extends AppCompatActivity {

    String foodName, manufacturer, selectedDate, mealName, servingUnit;
    float calories, servingSize, totalCalories;
    float qty = 1;
    int conCals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_item);

        // Variables to get database information
        Intent intent = getIntent();
        selectedDate = intent.getStringExtra("MealDate");
        mealName = intent.getStringExtra("MealName");
        ArrayList<String> foodType = intent.getStringArrayListExtra("FoodType");
        foodName = foodType.get(0);
        manufacturer = foodType.get(1);
        calories = Float.parseFloat(foodType.get(2));
        servingUnit = foodType.get(3);
        servingSize = Float.parseFloat(foodType.get(4));
        conCals = Math.round(calories);

        // Initialize TextViews
        TextView showQty = findViewById(R.id.showQty);
        TextView displayFood = findViewById(R.id.displayFood);
        TextView displayCalories = findViewById(R.id.displayCalories);
        TextView displayServingSize = findViewById(R.id.displayServingSize);
        TextView displayServingUnit = findViewById(R.id.displayServingUnit);
        TextView displayManufacturer = findViewById(R.id.displayManufacturer);
        TextView displayEaten = findViewById((R.id.headerQty));

        // Convert floats to strings for display in TextViews
        String caloriesConverted = Float.toString(conCals);
        String sizeConverted = Float.toString(servingSize);
        String qtyConverted = Float.toString(qty);
        String unitEaten = servingUnit + " eaten";

        // Display initial data
        displayFood.setText(foodName);
        displayCalories.setText(caloriesConverted);
        showQty.setText(qtyConverted);
        displayServingUnit.setText(servingUnit);
        displayServingSize.setText(sizeConverted);
        displayManufacturer.setText(manufacturer);
        displayEaten.setText(unitEaten);
        updateDisplay();

    }

    public void btnCalorieDown(View view) {
        if (qty > 0) { qty -= 0.5; }
        updateDisplay();
    }

    public void btnCalorieUp(View view) {
        qty += 0.5;
        updateDisplay();
    }

    private void updateDisplay() {
        TextView showQty = findViewById(R.id.showQty);
        String qtyConverted = Float.toString(qty);
        showQty.setText(qtyConverted);

        // Update the views with new data
        TextView totalCalories2 = findViewById(R.id.displayTotalCalories);
        totalCalories = qty * calories;
        String totcalsConverted = Float.toString(totalCalories);
        totalCalories2.setText(totcalsConverted);
    }


    public void btnDone(View view) {
        FoodItem newFoodItem = new FoodItem(0, foodName, conCals,manufacturer, Double.parseDouble(Float.toString( servingSize)), servingUnit);
       DatabaseHandler db = new DatabaseHandler(getApplicationContext());
       db.AddFood(newFoodItem);
       int foodId = db.getIdFromAPI(newFoodItem);
       int mealIdInt = db.getMealID(selectedDate, mealName);
       db.AddToMeal(mealIdInt, foodId, Math.round(qty));

        //inter-activity info passing via intent
        Intent intent = new Intent(this, acAddFood.class);
        selectedDate = intent.getStringExtra("MealDate");
        mealName = intent.getStringExtra("MealName");
        intent.putExtra("previousActivity","acFoodItem");
        intent.putExtra("foodId",foodId);
        intent.putExtra("selectedDate", selectedDate);
        intent.putExtra("MealName", mealName);
        intent.putExtra("FoodName", foodName);
        intent.putExtra("Calories",calories);
        String mealId = String.valueOf(mealIdInt);
        intent.putExtra("mealId", mealId);

        this.finish();
    }
}
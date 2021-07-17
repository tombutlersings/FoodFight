package com.example.foodfight;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

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
        // Toast.makeText(this, manufacturer, Toast.LENGTH_SHORT).show();
        TextView showQty = findViewById(R.id.showQty);
        TextView displayFood = findViewById(R.id.displayFood);
        TextView displayCalories = findViewById(R.id.displayCalories);
        TextView displayServingSize = findViewById(R.id.displayServingSize);
        TextView displayServingUnit = findViewById(R.id.displayServingUnit);
        TextView displayManufacturer = findViewById(R.id.displayManufacturer);

        // Convert floats to strings for display in TextViews
        String caloriesConverted = Float.toString(conCals);
        String sizeConverted = Float.toString(servingSize);
        String qtyConverted = Float.toString(qty);

        // Display initial data
        displayFood.setText(foodName);
        displayCalories.setText(caloriesConverted);
        showQty.setText(qtyConverted);
        displayServingUnit.setText(servingUnit);
        displayServingSize.setText(sizeConverted);
        displayManufacturer.setText(manufacturer);

        updateDisplay();

    }

    /** Called when user taps the red minus button
     *
     * @param view
     */
    public void btnCalorieDown(View view) {
        if (qty > 0) { qty -= 0.5; }
        updateDisplay();
    }


    /** Called when user taps the green plus button
     *
     * @param view
     */
    public void btnCalorieUp(View view) {
        qty += 0.5;
        updateDisplay();
    }


    /** Updates Food Item consumption fields
     */
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



    /** Called when user taps the blue Done button, consumption data is pushed into database
     *  and then drops back to the FoodList activity
     */
//    public void addFoodEnter(View view){
//        FoodItem newFoodItem = new FoodItem(0, foodName, conCals,manufacturer, Double.parseDouble(Float.toString( servingSize)), servingUnit);
//        //open database
//        // add food to database and getID
//        DatabaseHandler db = new DatabaseHandler(getApplicationContext());
//        //
//        // add the food to the list of foods
//        db.AddFood(newFoodItem);
//        // get foodid
//        int foodId = db.getIdFromAPI(newFoodItem);
//        Toast.makeText(acFoodItem.this,"Food Id:" + foodId, Toast.LENGTH_LONG).show();
//    }

    public void btnDone(View view) {
        /* TODO: Code to calculate calories from the given servings (is this being kept in the
                 DB for later use/update?).  The fooditem should be updated for calorie count
                 when passed back to acFoodList.
         */

        // TODO: create a food item out of info on the page
        //Integer.parseInt(Float.toString(calories))

       FoodItem newFoodItem = new FoodItem(0, foodName, conCals,manufacturer, Double.parseDouble(Float.toString( servingSize)), servingUnit);
       //open database
        // add food to database and getID
        DatabaseHandler db = new DatabaseHandler(getApplicationContext());
        //
        // add the food to the list of foods
        db.AddFood(newFoodItem);
        // get foodid
        int foodId = db.getIdFromAPI(newFoodItem);
        Toast.makeText(acFoodItem.this,"Food Id:" + foodId, Toast.LENGTH_LONG).show();
        // get the meal
        int mealIdInt = db.getMealID(selectedDate, mealName);
        // add the food using foodid to the meal
        db.AddToMeal(mealIdInt, foodId, Math.round(qty));


        Intent intent = new Intent(this, acAddFood.class);
        //pass information for data base access
        //todo: create a meal item
        selectedDate = intent.getStringExtra("MealDate");
        mealName = intent.getStringExtra("MealName");
        intent.putExtra("previousActivity","acFoodItem");
        intent.putExtra("foodId",foodId);
        intent.putExtra("selectedDate", selectedDate);
        intent.putExtra("MealName", mealName);
        intent.putExtra("FoodName", foodName);
        intent.putExtra("Calories",calories);
//        intent.putExtra("mealIdInt", mealIdInt);
        String mealId = String.valueOf(mealIdInt);//Now it will return "10"
        intent.putExtra("mealId", mealId);

        // TODO: open database handler and edit the mealId (or create it if it doesn't exist)


        this.finish();
    }

    //getIdFromAPI

}
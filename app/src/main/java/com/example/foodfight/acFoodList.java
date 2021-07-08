package com.example.foodfight;

/* This activity is where the embedded food list can be edited.  Adding new items
 * to the list navigates to the MenuEditor activity
 * TODO: This list is within the Meal, correct? - Tom 8-jun-21
 */

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
// import android.widget.ArrayAdapter;
// import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class acFoodList extends AppCompatActivity {
    // ListView for the list of foods that gets displayed
    // private ListView foodListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list);
    }
    //create date obj
    //date have meals inside
    //get toal calories

    // TODO: Drop down menu for the meal [PSP: Change to fixed label - populate from transfer of data from intent]
    // TODO: Display the date, items of food + quantities + total Calories  in the meal
    // TODO: METHOD AddFood
    // TODO: Show mealList for the selected Date


    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        String selectedDate = intent.getStringExtra("MealDate");
        String mealType = intent.getStringExtra("MealType");
        Log.i("FF_FoodList","Activity starts with " + mealType + " for " + selectedDate);
        TextView textView = findViewById(R.id.labelSelectedMeal);
        textView.setText(mealType);

    }

        // Called when user taps the Add Food button
    public void btnAddFood(View view) {
        Intent intent = new Intent(this, acAddFood.class);
        startActivity(intent);
    }


    // This is where the list of foods for the selected day get populated into the ListView
    // This needs to be refactored to match the actual names of classes and methods
    //   that will feedback the meal items for a certain day
//    public void displayMealItems(MealItem mealItem) {
//        ArrayAdapter<MealItem> adapter = new ArrayAdapter<>
//                (this, android.R.layout.simple_list_item_1,mealItem.getMealItems());
//        foodListView.setAdapter(adapter);
//    }



}
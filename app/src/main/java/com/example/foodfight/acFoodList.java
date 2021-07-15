package com.example.foodfight;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
// import android.widget.ArrayAdapter;
// import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class acFoodList extends AppCompatActivity {
    String selectedDate;
    String mealType;
    String totalCalories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list);
        Intent intent = getIntent();
        selectedDate = intent.getStringExtra("MealDate");
        mealType = intent.getStringExtra("MealType");

    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        selectedDate = intent.getStringExtra("MealDate");
        mealType = intent.getStringExtra("MealType");
        Log.i("FF_FoodList","data starts with " + mealType + " for " + selectedDate);
        TextView textView = findViewById(R.id.labelSelectedMeal);
        textView.setText(mealType);

        /* TODO: The list of foods for the given 'selectedDate' and 'mealType' need to be
                 pushed into the ListView foodListView
         */

        /* TODO: The total calories need to be collected from every item in foodListView and set
                 into TextView tvCalories
         */


    }

    // Called when user taps the Add Food button
    public void btnAddFood(View view) {
        Intent acAddFood = new Intent(this, acAddFood.class);
        //pass information for data base access
        acAddFood.putExtra("MealDate",selectedDate);
        acAddFood.putExtra("MealType",mealType);
        startActivity(acAddFood);
    }

    /* TODO: When an item in foodListView is tapped, that information should be extracted
             and sent to acFoodItem (via Intent) where the amount of food (and its calories)
             is increased or decreased.  acFoodItem will drop back to this activity when
             the blue checkmark is touched where onResume will update the list.
    */




}
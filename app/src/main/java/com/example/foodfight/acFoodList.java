package com.example.foodfight;

/* This activity is where the embedded food list can be edited.  Adding new items
 * to the list navigates to the MenuEditor activity
 * TODO: This list is within the Meal, correct? - Tom 8-jun-21
 */

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class acFoodList extends AppCompatActivity {
    String selectedDate;
    String mealName;
    String totalCalories;
    String mealId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        selectedDate = intent.getStringExtra("MealDate");
        mealName = intent.getStringExtra("MealName");
        Log.i("FF_FoodList","data starts with " + mealName + " for " + selectedDate);

        TextView textView = findViewById(R.id.labelSelectedMeal);
        textView.setText(mealName);

        DatabaseHandler db = new DatabaseHandler(getApplicationContext());
        db.CreateMeal(selectedDate, mealName, 0);
        MealItem mealItem = db.GetMeal(selectedDate, mealName);
        Log.i("FoodListMealItem", mealItem.ID + "|" + mealItem.date + "|" + mealItem.getMealName());

        ArrayList<String> outputString = new ArrayList<>();
        ArrayList<List> ids = db.getFoodList(mealItem.ID);
        int calories = 0;
        for(int i = 0; i < ids.size(); i++) {
            FoodItem testItem = db.getFoodItemById((int) ids.get(i).get(0));
            Log.d("string", testItem.getName());
            String tempString = "";
            tempString += testItem.getName() + "      " + testItem.getCalories() * (int) ids.get(i).get(1);
            outputString.add(tempString);
            calories += testItem.getCalories() * ((int) ids.get(i).get(1));
        }

        totalCalories = String.valueOf(calories);
        TextView tvCalories = findViewById(R.id.tvCalories);
        String cals = String.valueOf(totalCalories);
        tvCalories.setText(cals);

        Log.i("FoodListCalsTotal",cals);

        ListView foodListView = findViewById(R.id.foodListView);
        ArrayAdapter<MealItem> adapter = new ArrayAdapter(acFoodList.this, android.R.layout.simple_list_item_1, outputString);
        foodListView.setAdapter(adapter);

    }

    public void btnAddFood(View view) {
        //inter-activity info passing via intent
        Intent acAddFood = new Intent(this, acAddFood.class);
        acAddFood.putExtra("previousActivity","acFoodList");
        acAddFood.putExtra("MealDate",selectedDate);
        acAddFood.putExtra("MealName", mealName);
        startActivity(acAddFood);
    }
}
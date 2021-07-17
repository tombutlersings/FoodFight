package com.example.foodfight;

/* This activity is where the embedded food list can be edited.  Adding new items
 * to the list navigates to the MenuEditor activity
 * TODO: This list is within the Meal, correct? - Tom 8-jun-21
 */

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * acFoodList class interact with the database to display on
 * activity_food_list.xml a list of foods added by user.
 * There is a btnAddfood method that jump to activity_add_food.xml,
 * allowing user to add food.
 */
public class acFoodList extends AppCompatActivity {
    String selectedDate;
    String mealName;
    String totalCalories;
    ListView foodListView;
    TextView textView;
    ArrayList<String> outputString = new ArrayList<>();
    ArrayList<List> foodSearchList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list);
        foodListView = (ListView) findViewById(R.id.foodListView);
        textView = (TextView) findViewById(R.id.labelSelectedMeal);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        selectedDate = intent.getStringExtra("MealDate");
        mealName = intent.getStringExtra("MealName");
        Log.i("FF_FoodList","data starts with " + mealName + " for " + selectedDate);

        textView.setText(mealName);

        DatabaseHandler db = new DatabaseHandler(getApplicationContext());
        db.CreateMeal(selectedDate, mealName, 0);
        MealItem mealItem = db.GetMeal(selectedDate, mealName);
        Log.i("FoodListMealItem", mealItem.ID + "|" + mealItem.date + "|" + mealItem.getMealName());

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

        ArrayAdapter<MealItem> adapter = new ArrayAdapter(acFoodList.this, android.R.layout.simple_list_item_1, outputString);
        foodListView.setAdapter(adapter);

//        itemClick();
    }

    public void itemClick(){
        foodListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                foodSearchList = Goals.getFoodSearchList();
                List foodData = foodSearchList.get(i);

                //inter-activity info passing via intent
                Intent foodItem = new Intent(acFoodList.this, acFoodItem.class);
                foodItem.putExtra("MealDate",selectedDate);
                foodItem.putExtra("MealName", mealName);
                foodItem.putStringArrayListExtra("FoodType", (ArrayList<String>) foodData);
                startActivity(foodItem);

            }
        });
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
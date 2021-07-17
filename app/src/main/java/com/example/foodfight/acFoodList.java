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

        //get information from the database
        DatabaseHandler db = new DatabaseHandler(getApplicationContext());
        db.CreateMeal(selectedDate, mealName, 0);

        MealItem mealItem = db.GetMeal(selectedDate, mealName);

        Log.i("FoodListMealItem", mealItem.ID + "|" + mealItem.date + "|" + mealItem.getMealName());


        TextView tvCalories = findViewById(R.id.tvCalories);
        String cals = String.valueOf(mealItem.getTotalCalories());
        tvCalories.setText(cals);

        Log.i("FoodListCalsTotal",cals);


        ListView foodListView = findViewById(R.id.foodListView);
        ArrayAdapter<MealItem> adapter = new ArrayAdapter(acFoodList.this, android.R.layout.simple_list_item_1, mealItem.foodItems);
        foodListView.setAdapter(adapter);



        /* TODO: The list of foods for the given 'selectedDate' and 'mealName' need to be
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
        //todo: create a meal item

        acAddFood.putExtra("previousActivity","acFoodList");
        acAddFood.putExtra("MealDate",selectedDate);
        acAddFood.putExtra("MealName", mealName);
        startActivity(acAddFood);
    }

    /* TODO: When an item in foodListView is tapped, that information should be extracted
             and sent to acFoodItem (via Intent) where the amount of food (and its calories)
             is increased or decreased.  acFoodItem will drop back to this activity when
             the blue checkmark is touched where onResume will update the list.
    */

    // This is where the list of foods for the selected day get populated into the ListView
    // This needs to be refactored to match the actual names of classes and methods
    //   that will feedback the meal items for a certain day

//        List<String> foodsList = null;
    //add food name to string
    //add food calories to sting
    //add string to foods list
//        String output;
//        FoodItem foodItem;
//        ArrayList<FoodItem> foodItems = mealItem.foodItems;
//        ArrayList<String> foodsList = new ArrayList<String>
//
//
//        for (int i = 0; i < foodItems.size(); i++){
//            foodItem = foodItems.get(i);
//            output = foodItem.getName() + "      " + foodItem.getCalories();
//            foodsList.add(output);
//        }




}
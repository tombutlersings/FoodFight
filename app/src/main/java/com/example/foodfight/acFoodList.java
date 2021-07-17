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
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class acFoodList extends AppCompatActivity {
    String selectedDate;
    String mealType;
    String totalCalories;
    String mealId;



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
        mealId = intent.getStringExtra("mealId");
        Integer mealIdIntConverted = Integer.valueOf(mealId);
        Log.i("FF_FoodList","data starts with " + mealType + " for " + selectedDate);
        Log.i("FF_FoodList","mealId = " + mealIdIntConverted);


        TextView textView = findViewById(R.id.labelSelectedMeal);
        textView.setText(mealType);


        //get information from the database
        DatabaseHandler db = new DatabaseHandler(getApplicationContext());
//        MealItem meal = new MealItem(0,selectedDate,mealType
//                ,new ArrayList<FoodItem>(),new ArrayList<Float>());

        //create meal item only i there is not already one


//        //my idea here is that if the meal id is not found it will return null but dbhandler is not set up to do that.
        //the next line is for testing to see what it did when a meal was present.
db.CreateMeal(selectedDate, mealType, 0);

//        if (db.getMealID(selectedDate, mealType) == null){
//            db.CreateMeal(selectedDate, mealType, 0);
//        }
//        MealItem displayMeal = db.GetMeal(selectedDate, mealType);
//        //display foods if there are any
//        if (displayMeal.foodItems.size() > 0 ){
//            displayMealItems(displayMeal);}

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
        //todo: create a meal item

        acAddFood.putExtra("previousActivity","acFoodList");
        acAddFood.putExtra("MealDate",selectedDate);
        acAddFood.putExtra("MealType",mealType);
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
    public void displayMealItems(MealItem mealItem) {
        List<String> foodsList = null;
        //add food name to string
        //add food calories to sting
        //add string to foods list
        ArrayList<FoodItem> foodItems = mealItem.foodItems;
        TextView tvCalories = findViewById(R.id.tvCalories);
        float cals = mealItem.getTotalCalories();
        tvCalories.setText(Float.toString(cals));
        for (int i = 0; i < foodItems.size(); i++){
            String output;
            FoodItem fooditem = foodItems.get(i);
            output = fooditem.getName() + "      " + fooditem.getCalories();
            foodsList.add(output);
        }
        ListView foodListView = findViewById(R.id.foodListView);
        ArrayAdapter<MealItem> adapter = new ArrayAdapter(acFoodList.this, android.R.layout.simple_list_item_1,foodsList);
        foodListView.setAdapter(adapter);
    }



}
package com.example.foodfight;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * acAddFood class controls the activity_add_food.xml, its elements and objects,
 * and updating it with data and information as the user interacts with it.
 */
public class acAddFood extends AppCompatActivity {

    EditText searchFood;
    Button searchButton;
    String previousActivity;
    ListView searchResults;
    String selectedDate;
    String mealName;
    public ArrayList<List> foodSearchList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);
        searchFood = (EditText) findViewById(R.id.searchFood);
        searchButton = (Button) findViewById(R.id.btnSearchFood);
        searchResults = (ListView) findViewById(R.id.searchResults);
        foodSearch();
        itemClick();

    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        previousActivity = intent.getStringExtra("previousActivity");
        selectedDate = intent.getStringExtra("MealDate");
        mealName = intent.getStringExtra("MealName");
        if(previousActivity.equals("acFoodItem")){
            this.finish();
        }
    }

    /**
     * The foodSearch searchButton onclick listener was pivotal
     * for us to be able to get data back to the acAddFood Screen
     * This makes a new thread, calls the ApiHandler.java,
     * and send the activity and foodsearch.
     * Citations:
     */
    public void foodSearch(){
        searchButton.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        String foodSearch = searchFood.getText().toString();
                        try {
                            FoodSearchThreadCreator onViewCreatedOne = new FoodSearchThreadCreator(acAddFood.this, foodSearch);

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
    }

    public void itemClick(){
        //ListView listView = findViewById(R.id.searchResults);
        searchResults.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                foodSearchList = Goals.getFoodSearchList();
                List foodData = foodSearchList.get(i);

                //inter-activity info passing via intent
                Intent foodItem = new Intent(acAddFood.this, acFoodItem.class);
                foodItem.putExtra("MealDate",selectedDate);
                foodItem.putExtra("MealName", mealName);
                foodItem.putStringArrayListExtra("FoodType", (ArrayList<String>) foodData);
                startActivity(foodItem);

            }
        });
    }
}
package com.example.foodfight;

/* This activity uses the USDA API to retrieve data on selected foods
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class acAddFood extends AppCompatActivity {

    EditText searchFood;
    // TextView searchStatus;
    Button searchButton;
    ListView searchResults;
    String selectedDate;
    String mealType;
    public ArrayList<List> foodSearchList;

    //variables for database access
    //Intent intent = getIntent();
    //String selectedDate = intent.getStringExtra("MealDate");
    //String mealType = intent.getStringExtra("MealType");

    /*
    put the next lines where we create the food item activity
     Intent intent = new Intent(this, acFoodItem.class);
        //pass information for data base access
        intent.putExtra("MealDate",selectedDate);
        intent.putExtra("MealType",mealType);
        startActivity(intent);
     */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);
        searchFood = (EditText) findViewById(R.id.searchFood);
        searchButton = (Button) findViewById(R.id.searchButton);
        searchResults = (ListView) findViewById(R.id.searchResults);
        foodSearch();
        itemClick();
        Intent intent = getIntent();
        selectedDate = intent.getStringExtra("MealDate");
        mealType = intent.getStringExtra("MealType");

    }


    private Activity currentActivity = null;
    public Activity getCurrentActivity(){
        return currentActivity;
    }
    public void setCurrentActivity(Activity currentActivity){
        this.currentActivity = currentActivity;
    }


    // TODO: METHOD search for food
    public void foodSearch(){
        searchButton.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        String foodSearch = searchFood.getText().toString();
                        Toast.makeText(acAddFood.this,foodSearch, Toast.LENGTH_LONG).show();
                        try {
                            /**
                             * STEP 1
                             * This makes a new thread, calls the ApiHandler.java , and send the activity and foodsearch
                             */
                            FoodSearchThreadCreator onViewCreatedOne = new FoodSearchThreadCreator(acAddFood.this, foodSearch);

//                            new Thread(new ApiHandler(acAddFood.this, foodSearch)).start();
                            Toast.makeText(acAddFood.this,"Search Button Clicked", Toast.LENGTH_LONG).show();

                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        //  DB Call for the food (searchFood.getText().toString());
                        //  Api Call for the food (searchFood.getText().toString());

                    }
                }
        );
    }

    // Todo: 2 threads (one for DB and one for Api)
    // top five results from DB
    // next five results from api
    // todo: method to call apiHandlerClass
    // provides the search string


    // first internal search
    // TODO: METHOD display results
    // TODO: Creating Connection between results and buttons
    // TODO: Click on the result to bring that food up on the food item page.
    // pass foodItem data to the acAddFood and AddFoodHandler screen

    public void itemClick(){
        ListView listView = findViewById(R.id.searchResults);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Dessert dessert = desserts.get(i);

                //get the search list
                foodSearchList = Goals.getFoodSearchList();
                List foodData = foodSearchList.get(i);
                Intent foodItem = new Intent(acAddFood.this, acFoodItem.class);
                foodItem.putExtra("MealDate",selectedDate);
                foodItem.putExtra("MealType",mealType);
                foodItem.putStringArrayListExtra("FoodType", (ArrayList<String>) foodData);
                startActivity(foodItem);

//
//                switch(i) {
//                    case 0:
//                        Toast.makeText(acAddFood.this,"Item 0", Toast.LENGTH_LONG).show();
//                        break;
//                    case 1:
//                        Toast.makeText(acAddFood.this,"Item 1", Toast.LENGTH_LONG).show();
//                        break;
//                    case 2:
//                        Toast.makeText(acAddFood.this,"Item 2", Toast.LENGTH_LONG).show();
//                        break;
//                    case 3:
//                        Intent foodItem = new Intent(acAddFood.this, acFoodItem.class);
//                        foodItem.putExtra("MealDate",selectedDate);
//                        foodItem.putExtra("MealType",mealType);
//                        foodItem.putStringArrayListExtra("FoodType", (ArrayList<String>) foodData);
//                        startActivity(foodItem);
//                        break;
//                }
            }
        });
    }

}
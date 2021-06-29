package com.example.foodfight;

/* This activity uses the USDA API to retrieve data on selected foods
 */

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class acAddFood extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);
    }

    // TODO: METHOD search for food
    // Todo: 2 threads (one for DB and one for Api)
        //top five results from DB
        //next five results from api
            //todo: method to call apiHandlerClass
                //provides the search string


            // first internal search
    // TODO: METHOD display results
        // TODO: Creating Connection between results and buttons
        // TODO: Click on the result to bring that food up on the food item page.
            // pass foodItem data to the acAddFood and AddFoodHandler screen

}
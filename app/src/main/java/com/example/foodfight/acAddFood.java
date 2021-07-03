package com.example.foodfight;

/* This activity uses the USDA API to retrieve data on selected foods
 */

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class acAddFood extends AppCompatActivity {

    EditText searchFood;
    Button searchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);
        searchFood = (EditText) findViewById(R.id.searchFood);
        searchButton = (Button) findViewById(R.id.searchButton);
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
                        try {
                            new Thread (new ApiHandler(acAddFood.this, foodSearch));

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
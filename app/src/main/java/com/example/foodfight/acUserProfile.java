package com.example.foodfight;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/** Collects user's specifics for BMI calculation and
 *  calorie goal settings.  A button at the bottom navigates to
 *  the AppAbout activity.  When the user data is saved, the food database
 *  will be initialized if it isn't already.
 */
public class acUserProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        //load values
        getValues();
    }

    // Called when the user taps the About button
    public void btnAppAbout (View view) {
        Intent intent = new Intent(this, acAppAbout.class);
        startActivity(intent);
    }

    // Update User Profile and push to SharedPreferences
    public void btnUpdate(View view){
        // Get field values
        EditText name = findViewById(R.id.etPersonName);
        EditText height = findViewById(R.id.numHeight);
        EditText weight = findViewById(R.id.numWeight);
        EditText dailyGoal = findViewById(R.id.numDayGoal);
        String dailyCurrent = String.valueOf(Goals.getCurrentDaily());
        String weeklyCurrent = String.valueOf(Goals.getCurrentWeekly());

        SharedPreferences sp = getSharedPreferences("profile", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        editor.putString("name", (name.getText().toString()));
        editor.putString("height", (height.getText().toString()));
        editor.putString("weight", (weight.getText().toString()));
        editor.putString("dailyGoal", (dailyGoal.getText().toString()));
        editor.putString("dailyCurrent",dailyCurrent);
        editor.putString("weeklyCurrent",weeklyCurrent);
        editor.commit();
        Log.d("FoodFight", "Saved SharedPreferences");
        getValues();
    }


    public void getValues(){
        //get values to display
        SharedPreferences sp = getSharedPreferences("profile", Context.MODE_PRIVATE);
        String name = sp.getString("name","Your name");
        String height = sp.getString("height","60");
        String weight = sp.getString("weight","120");
        String dailyGoal = sp.getString("dailyGoal", "2000");
        String dailyCurrent = sp.getString("dailyCurrent", "400");
        String weeklyCurrent = sp.getString("weeklyCurrent", "2857");

        Log.d("FoodFight_SP", "Found" + height + " " + weight + " " + dailyGoal + " " + dailyCurrent + " " + weeklyCurrent);

        // Set values for name, height and weight fields
        EditText nameView = findViewById(R.id.etPersonName);
        nameView.setText(name);
        EditText heightView = findViewById(R.id.numHeight);
        heightView.setText(height);
        EditText weightView = findViewById(R.id.numWeight);
        weightView.setText(weight);

        // Calculate BMI and set field value
        double wt = Double.parseDouble(weight);
        double ht = Double.parseDouble(height);
        double bmi = Math.round((wt * 45.36) / Math.pow(ht / 39.7, 2)) /100.0;

        String numBMI = String.valueOf(bmi);
        TextView BMIView = findViewById(R.id.numBMI);
        BMIView.setText(numBMI);

        // Set values for daily and weekly goal fields
        EditText dailyGoalView = findViewById(R.id.numDayGoal);
        dailyGoalView.setText(dailyGoal);

        int weekGoal = Integer.parseInt(dailyGoal) * 7;
        TextView weeklyGoalView = findViewById(R.id.numWeekGoal);
        weeklyGoalView.setText(String.valueOf(weekGoal));

        // Set values for daily and weekly current calorie counts
        TextView dailyCurrentView = findViewById(R.id.numDailyCurrent);
        dailyCurrentView.setText(dailyCurrent);
        TextView weeklyCurrentView = findViewById(R.id.numWeeklyCurrent);
        weeklyCurrentView.setText(weeklyCurrent);

        // Set global calorie variables for use elsewhere in the app
        // It is likely that we don't need this code because the data can
        //   be stored in SharedPreferences [Redundancy]
        Goals.setDaily(Integer.parseInt(dailyGoal));
        Goals.setWeekly(weekGoal);
        Goals.setCurrentDaily(Integer.parseInt(dailyCurrent));
        Goals.setCurrentWeekly(Integer.parseInt(weeklyCurrent));

    }

}
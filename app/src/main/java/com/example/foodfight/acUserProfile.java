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
 *
 *  Driver: P Proctor
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

    public void getValues(){
        //get values to display
        SharedPreferences sp = getSharedPreferences("profile", Context.MODE_PRIVATE);
        String name = sp.getString("name","Your Name");
        String height = sp.getString("height","60");
        String weight = sp.getString("weight","120");
        String dailyGoal = sp.getString("dailyGoal", "2000");

        Log.d("string", "found" + height + " " + weight + " " +  dailyGoal);

        //load values
        EditText nameView = findViewById(R.id.etPersonName);
        nameView.setText(name);
        EditText heightView = findViewById(R.id.numHeight);
        heightView.setText(height);
        EditText weightView = findViewById(R.id.numWeight);
        weightView.setText(weight);
        EditText dailyGoalView = findViewById(R.id.numDayGoal);
        dailyGoalView.setText(dailyGoal);

        double wt = Double.parseDouble(weight);
        double ht = Double.parseDouble(height);
        double bmi = Math.round((wt * 45.36) / Math.pow(ht / 39.7, 2)) /100.0;
        String numBMI = String.valueOf(bmi);
        TextView BMIView = findViewById(R.id.numBMI);
        BMIView.setText(numBMI);

        int weekGoal = Integer.parseInt(dailyGoal) * 7;
        String weeklyGoal = String.valueOf(weekGoal);
        TextView weeklyGoalView = findViewById(R.id.numWeekGoal);
        weeklyGoalView.setText(weeklyGoal);

        //set goals
        Goals.setDaily(Integer.parseInt(dailyGoal));
        Goals.setWeekly(weekGoal);

    }
    // Update User Profile and push to SharedPreferences
    public void btnUpdate(View view){
        //get and parse info
        EditText name = findViewById(R.id.etPersonName);
        EditText height = findViewById(R.id.numHeight);
        EditText weight = findViewById(R.id.numWeight);
        EditText dailyGoal = findViewById(R.id.numDayGoal);

        Log.d("string", "saved" + height.getText().toString());
        SharedPreferences sp = getSharedPreferences("profile", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        editor.putString("name", (name.getText().toString()));
        editor.putString("height", (height.getText().toString()));
        editor.putString("weight", (weight.getText().toString()));
        editor.putString("dailyGoal", (dailyGoal.getText().toString()));
        editor.commit();
        getValues();
    }

}
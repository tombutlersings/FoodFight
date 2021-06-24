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

    //get values
    public void getValues(){
        //get values to display
        SharedPreferences sp = getSharedPreferences("profile", Context.MODE_PRIVATE);
        String name = sp.getString("name","Your Name");
        String height = sp.getString("height","60");
        String weight = sp.getString("weight","120");
//        String age = sp.getString("age",1);
        String weeklyGoal = sp.getString("weeklyGoal", "14000");
        String dailyGoal = sp.getString("dailyGoal", "2000");

        Log.d("string", "found" + height + " " + weight + " " +  weeklyGoal + " " + dailyGoal);

        //load values
        EditText nameView = findViewById(R.id.etPersonName);
        nameView.setText(name);
        EditText heightView = findViewById(R.id.numHeight);
        heightView.setText(height);
        EditText weightView = findViewById(R.id.numWeight);
        weightView.setText(weight);
//        EditText ageView = findViewById(R.id.numAge);
//        ageView.setText(age + "");
        EditText dailyGoalView = findViewById(R.id.numDayGoal);
        dailyGoalView.setText(dailyGoal);
        EditText weeklyGoalView = findViewById(R.id.numWeekGoal);
        weeklyGoalView.setText(weeklyGoal);

        double wt = Double.parseDouble(weight);
        double ht = Double.parseDouble(height);
        double bmi = Math.round((wt * 45.36) / Math.pow(ht / 39.7, 2)) /100.0;
        String numBMI = String.valueOf(bmi);
        TextView BMIView = findViewById(R.id.numBMI);
        BMIView.setText(numBMI);

        //set goals
        Goals.setDaily(Double.parseDouble(dailyGoal));
        Goals.setWeekly(Double.parseDouble(weeklyGoal));

    }
    // Update
    public void btnUpdate(View view){
        //get and parse info
        EditText name = findViewById(R.id.etPersonName);
        EditText height = findViewById(R.id.numHeight);
        EditText weight = findViewById(R.id.numWeight);
//        EditText age = findViewById(R.id.numAge);
        EditText dailyGoal = findViewById(R.id.numDayGoal);
        EditText weeklyGoal = findViewById(R.id.numWeekGoal);

        Log.d("string", "saved" + height.getText().toString());
        SharedPreferences sp = getSharedPreferences("profile", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        editor.putString("name", (name.getText().toString()));
        editor.putString("height", (height.getText().toString()));
        editor.putString("weight", (weight.getText().toString()));
//        editor.putString("age", (age.getText().toString()));
        editor.putString("dailyGoal", (dailyGoal.getText().toString()));
        editor.putString("weeklyGoal", (weeklyGoal.getText().toString()));
        editor.commit();
        getValues();
    }

}
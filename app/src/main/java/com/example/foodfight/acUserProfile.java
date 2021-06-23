package com.example.foodfight;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

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
        int height = sp.getInt("height",1);
        int weight = sp.getInt("weight",1);
        int age = sp.getInt("age",1);
        int weeklyGoal = sp.getInt("weeklyGoal", 14000);
        int dailyGoal = sp.getInt("dailyGoal", 2000);
        Log.d("string", "found" + height + " " + weight + " " + age + " " + weeklyGoal + " " + dailyGoal);
        //load values
        EditText nameView = findViewById(R.id.etPersonName);
        nameView.setText(name);
        EditText heightView = findViewById(R.id.numHeight);
        heightView.setText(height + "");
        EditText weightView = findViewById(R.id.numWeight);
        weightView.setText(weight + "");
        EditText ageView = findViewById(R.id.numAge);
        ageView.setText(age + "");
        EditText BMIView = findViewById(R.id.numBMI);
        BMIView.setText((weight / (height * height)) + "");
        EditText dailyGoalView = findViewById(R.id.numDayGoal);
        dailyGoalView.setText(dailyGoal + "");
        EditText weeklyGoalView = findViewById(R.id.numWeekGoal);
        weeklyGoalView.setText(weeklyGoal + "");
        //set goals
        Goals.setDaily(dailyGoal);
        Goals.setWeekly(weeklyGoal);

    }
    // Update
    public void btnUpdate(View view){
        //get and parse info
        EditText height = findViewById(R.id.numHeight);
        EditText weight = findViewById(R.id.numWeight);
        EditText age = findViewById(R.id.numAge);
        EditText dailyGoal = findViewById(R.id.numDayGoal);
        EditText weeklyGoal = findViewById(R.id.numWeekGoal);
        EditText name = findViewById(R.id.etPersonName);

        Log.d("string", "saved" + height.getText().toString());
        SharedPreferences sp = getSharedPreferences("profile", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("name", (name.getText().toString()));
        editor.putInt("height", Integer.valueOf(height.getText().toString()));
        editor.putInt("weight", Integer.valueOf(weight.getText().toString()));
        editor.putInt("age", Integer.valueOf(age.getText().toString()));
        editor.putInt("dailyGoal", Integer.valueOf(dailyGoal.getText().toString()));
        editor.putInt("weeklyGoal", Integer.valueOf(weeklyGoal.getText().toString()));
        editor.commit();
        getValues();
    }

}
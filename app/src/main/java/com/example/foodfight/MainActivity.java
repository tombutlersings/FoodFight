package com.example.foodfight;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.util.Calendar;
/**
 * The main activity screen shows progress bars for daily and weekly calorie
 * counts against user goals.  Buttons at the bottom of the screen navigate to
 * the Meals, Trend and UserProfile activities
 */
public class MainActivity extends AppCompatActivity {
    DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* check if the user has used the app for the
        first time by checking the profile set up.*/
        // Pull SharedPreferences and check "name" for default value
        // If default then auto-nav to acUserProfile to get set up
        SharedPreferences sp = getSharedPreferences("profile", Context.MODE_PRIVATE);
        String name = sp.getString("name","Your name");
        if (name.equalsIgnoreCase("Your name")) {
            Intent intent = new Intent(this, acUserProfile.class);
            startActivity(intent);
        }

        // VERIFIES THE DATABASE IS THERE
        // SQLiteDatabase dbFood = openOrCreateDatabase("food", MODE_PRIVATE, null);
        db = new DatabaseHandler(this);
        //CREATE / INITIALIZE THE DATABASE
        //VERIFY IF THE DATABASE FILE EXISTS AND IF FOOD TABLE IS POPULATED
        // TODO: IF FOOD TABLE IS NOT POPULATED, LOAD FOOD LIBRARY
        // TODO: REMOVE THIS FOR FINAL CODE

        FoodItem apple = new FoodItem(1,"apple",150);
        FoodItem apple2 = new FoodItem(569,"1% LOWFAT MILK",88,240,"ml",1,"cup","Target Stores");
        db.AddFood(apple);
        db.AddFood(apple2);

        FoodLibraryCreator foodLib = new FoodLibraryCreator();
        foodLib.libCreator(this);

        File dbFile = this.getDatabasePath("food.db");
        if (dbFile.exists()) {
            Log.i("FF_Main_DBLoad","WAHOO! DB Exists!");
        } else {
            Log.i("FF_Main_DBLoad","NOT GOOD: DB does not exist!");
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        // When MainActivity resumes, pull shared preferences for: name, calorie goal,
        // current daily calorie count, current weekly calorie count
        // Update progress bars
        Log.i("[FoodFight]", "Main Activity resumes");
        SharedPreferences sp = getSharedPreferences("profile", Context.MODE_PRIVATE);
        String name = sp.getString("name", "Your name");

        //customize message to fit the time of dame
        int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        String msg;
        if (hour < 6) {
            msg = "Wow, " + name + ", you're up early!";
        } else if (hour < 12) {
            msg = "Good morning, " + name + ".";
        } else if (hour < 18) {
            msg = "Good afternoon, " + name + ".";
        } else {
            msg = "Good evening, " + name + ".";
        }
        TextView greeting = findViewById(R.id.textGreeting);
        greeting.setText(msg);

        //TODO: Poll database for current week calorie totals


        // Set the progress bars and captions
        String dailyGoal = sp.getString("dailyGoal", "2222");
        String dailyCurrent = sp.getString("dailyCurrent", "444");
        String weeklyCurrent = sp.getString("weeklyCurrent", "10000");

        int DG = Integer.parseInt(dailyGoal);
        int WG = DG * 7;
        int DC = Integer.parseInt(dailyCurrent);
        int WC = Integer.parseInt(weeklyCurrent);

        ProgressBar dailyBar = findViewById(R.id.barDaily);
        dailyBar.setMax(DG);
        dailyBar.setProgress(DC);

        ProgressBar weeklyBar = findViewById(R.id.barWeekly);
        weeklyBar.setMax(WG);
        weeklyBar.setProgress(WC);

        TextView dailyCaption = findViewById(R.id.tvDaily);
        String dCap = dailyCurrent + " / " + dailyGoal;
        dailyCaption.setText(dCap);

        TextView weeklyCaption = findViewById(R.id.tvWeekly);
        String wCap = weeklyCurrent + " / " + WG;
        weeklyCaption.setText(wCap);

        //if (dailygoal - 1000  >= current daily total) {tvDailyWords
        //   then display "you're doing great today on your goal!}
        // else if (dailygoal -250 >= current daily total ){ you're getting close} else { " you've exceeded your goal for the day}
        String message = "";
        float currentStatus = Float.parseFloat(dailyCurrent) / Float.parseFloat(dailyGoal);
        if (currentStatus >= 1.0) {
            message = "You're gonna stop now, right?";
        } else if (currentStatus >= 0.9) {
            message = "Slow down. You're getting close!";
        } else if (currentStatus >= 0.8) {
            message = "Starting to feel full now?";
        } else if (currentStatus >= 0.25 && currentStatus < 0.8) {
            message = "You Got This!";
        } else
            message = "You're allowed to eat a little more";

        TextView messageView = findViewById(R.id.tvDailyWords);
        messageView.setText(message);
    }

    // Called when user taps the Meals button
    public void btnMeals(View view) {
        Intent intent = new Intent(this, acMeals.class);
        Log.i("FF_Main","Launching acMeals");
        startActivity(intent);
    }

    // Called when user taps the User Profile button
    // This is a stretch part.
    public void btnTrend(View view) {
        Intent intent = new Intent(this, acTrend.class);
        // TODO: Pass current date for default trend
        startActivity(intent);

    }

    // Called when user taps the User Profile button
    public void btnProfile(View view) {
        Intent intent = new Intent(this, acUserProfile.class);
        startActivity(intent);
    }


}
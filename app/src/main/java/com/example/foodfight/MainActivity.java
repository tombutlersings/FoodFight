package com.example.foodfight;

/* The main activity screen shows progress bars for daily and weekly calorie
 * counts against user goals.  Buttons at the bottom of the screen navigate to
 * the Meals, Trend and UserProfile activities
 */

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    // TODO: DatabaseHandler dbNameHere;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //TODO: dbNameHere = new DatabaseHandler(this);

        // Pull SharedPreferences and check "name" for default value
        // If default then auto-nav to acUserProfile to get set up
        SharedPreferences sp = getSharedPreferences("profile", Context.MODE_PRIVATE);
        String name = sp.getString("name","Your name");
        if (name.equalsIgnoreCase("Your name")) {
            Intent intent = new Intent(this, acUserProfile.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // When MainActivity resumes, pull shared preferences for: name, calorie goal,
        // current daily calorie count, current weekly calorie count
        // Update progress bars
        Log.i("[FoodFight]","Main Activity resumes");
        SharedPreferences sp = getSharedPreferences("profile", Context.MODE_PRIVATE);
        String name = sp.getString("name","Your name");

        int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        String msg;
        if (hour < 6) {
            msg = "Wow, "+name+", you're up early!";
        } else if (hour <12) {
            msg = "Good morning, "+name+".";
        } else if (hour < 18) {
            msg = "Good afternoon, "+name+".";
        } else {
            msg = "Good evening, "+name+".";
        }
        TextView greeting = findViewById(R.id.textGreeting);
        greeting.setText(msg);

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
    }

    // Called when user taps the Meals button
    public void btnMeals(View view) {
        Intent intent = new Intent(this, acMeals.class);
        Log.i("FF_Main","Launching acMeals");
        startActivity(intent);
    }

    // Called when user taps the User Profile button
    public void btnTrend(View view) {
        Intent intent = new Intent(this, acTrend.class);
        startActivity(intent);

        // TODO: Pass current date for default trend
    }

    // Called when user taps the User Profile button
    public void btnProfile(View view) {
        Intent intent = new Intent(this, acUserProfile.class);
        startActivity(intent);
    }




}
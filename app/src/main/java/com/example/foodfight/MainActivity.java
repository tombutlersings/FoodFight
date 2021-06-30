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
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    // TODO: DatabaseHandler dbNameHere;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Pull SharedPreferences and check "name" for default value
        // If default then auto-nav to asUserProfile
        SharedPreferences sp = getSharedPreferences("profile", Context.MODE_PRIVATE);
        String name = sp.getString("name","Your name");
        if (name.equalsIgnoreCase("Your name")) {
            Intent intent = new Intent(this, acUserProfile.class);
            startActivity(intent);
            //TODO: dbNameHere = new DatabaseHandler(this);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
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

    }

    // Called when user taps the Meals button
    public void btnMeals(View view) {
        Intent intent = new Intent(this, acMeals.class);
        startActivity(intent);
    }

    // Called when user taps the User Profile button
    public void btnTrend(View view) {
        Intent intent = new Intent(this, acTrend.class);
        startActivity(intent);
    }

    // Called when user taps the User Profile button
    public void btnProfile(View view) {
        Intent intent = new Intent(this, acUserProfile.class);
        startActivity(intent);
    }




}
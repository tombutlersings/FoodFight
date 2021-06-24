package com.example.foodfight;

/* The main activity screen shows progress bars for daily and weekly calorie
 * counts against user goals.  Buttons at the bottom of the screen navigate to
 * the Meals, Trend and UserProfile activities
 */

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Pull SharedPreferences and check "name" for default value
        // If default then auto-nav to asUserProfile
        SharedPreferences sp = getSharedPreferences("profile", Context.MODE_PRIVATE);
        String name = sp.getString("name","Your Name");
        if (name == "Your name") {
            Intent intent = new Intent(this, acUserProfile.class);
            startActivity(intent);
        }

    }

    // Called when user taps the User Profile button
    public void btnProfile(View view) {
        Intent intent = new Intent(this, acUserProfile.class);
        startActivity(intent);
    }

    // Called when user taps the User Profile button
    public void btnMeals(View view) {
        Intent intent = new Intent(this, acMeals.class);
        startActivity(intent);
    }


//    public void testCalendar(View view) {
//        Intent intent = new Intent(this, acCalendar.class);
//        startActivity(intent);
//    }

}
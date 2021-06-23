package com.example.foodfight;

/* The main activity screen shows progress bars for daily and weekly calorie
 * counts against user goals.  Buttons at the bottom of the screen navigate to
 * the Meals, Trend and UserProfile activities
 */

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.io.FileNotFoundException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // Called when user taps the User Profile button
    public void btnProfile(View view) {
        Intent intent = new Intent(this, acUserProfile.class);
        startActivity(intent);

    }

    public void btnMeal(View view) {
        Intent intent = new Intent(this, acMeals.class);
        startActivity(intent);
    }

    public void btnTestCalendar(View view) {
        Intent intent = new Intent(this, acCalendar.class);
        startActivity(intent);
    }


}
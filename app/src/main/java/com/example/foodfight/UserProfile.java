package com.example.foodfight;

/* This activity collects the user's specifics for BMI calculation and
 * calorie goal settings.  A button at the bottom navigates to
 * the AppAbout activity.
 */

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class UserProfile extends AppCompatActivity {

    int height;
    int weight;
    int age;
    int weekGoal;
    int dayGoal;
    int bmi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
    }


    // Called with the user taps the About button
    public void btnAppAbout (View view) {
        Intent intent = new Intent(this, AppAbout.class);
        startActivity(intent);
    }

}
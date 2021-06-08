package com.example.foodfight;

/* This activity collects the user's specifics for BMI calculation and
 * calorie goal settings.  A button at the bottom navigates to
 * the AppAbout activity.
 */

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class UserProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
    }
}
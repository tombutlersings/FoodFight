package com.example.foodfight;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.widget.TextView;

import java.util.ArrayList;

/**
 * TODO:
 *
 */

public class acMeals extends AppCompatActivity {
    private String msg;
    public String date;

    private CalendarHandler calendarHandler = new CalendarHandler();

    private int breakfastCalories = 0;
    private int lunchCalories = 0;
    private int dinnerCalories = 0;
    private int snack1Calories = 0;
    private int snack2Calories = 0;
    private int snack3Calories = 0;

    private TextView dateView = findViewById(R.id.tvDate);
    private TextView bfCaloriesView = findViewById(R.id.tvBreakfast);
    private TextView lCaloriesView = findViewById(R.id.tvLunch);
    private TextView dCaloriesView = findViewById(R.id.tvDinner);
    private TextView s1CaloriesView = findViewById(R.id.tvSnack1);
    private TextView s2CaloriesView = findViewById(R.id.tvSnack2);
    private TextView s3CaloriesView = findViewById(R.id.tvSnack3);
    private TextView tCaloriesView = findViewById(R.id.tvTotal);

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meals);

        // Set dateView to the system date if the user
        // have not selected a date on the calendar.
        date = calendarHandler.getSystemDate();
        msg = "Calories for " + date;
        dateView.setText(msg);

        showMealCalories();
        showTotalCalories();
    }

    // Called when user taps the Breakfast button
    private void btnBreakfast(View view) {
        Intent intent = new Intent(this, acFoodList.class);
        startActivityForResult(intent, 1);
    }

    // Called when user taps the Snack 1 button
    private void btnSnack1(View view) {
        Intent intent = new Intent(this, acFoodList.class);
        startActivityForResult(intent, 2);
    }

    // Called when user taps the Lunch button
    private void btnLunch(View view) {
        Intent intent = new Intent(this, acFoodList.class);
        startActivityForResult(intent, 3);
    }

    // Called when user taps the Snack 2 button
    private void btnSnack2(View view) {
        Intent intent = new Intent(this, acFoodList.class);
        startActivityForResult(intent, 4);
    }

    // Called when user taps the Dinner button
    private void btnDinner(View view) {
        Intent intent = new Intent(this, acFoodList.class);
        startActivityForResult(intent, 5);
    }

    // Called when user taps the Snack 3 button
    private void btnSnack3(View view) {
        Intent intent = new Intent(this, acFoodList.class);
        startActivityForResult(intent, 6);
    }

    // Called when user taps the Select Another Day button
    private void getAnotherDay(View view) {
        Intent intent = new Intent(this, acCalendar.class);
        startActivityForResult(intent, 7);
    }

    // Get data from previous activity.
    // Different requestCode according above button methods.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                breakfastCalories = Integer.valueOf(data.getStringExtra("result"));
            }
        }

        if (requestCode == 2) {
            if (resultCode == RESULT_OK) {
                snack1Calories = Integer.valueOf(data.getStringExtra("result"));
            }
        }

        if (requestCode == 3) {
            if (resultCode == RESULT_OK) {
                lunchCalories = Integer.valueOf(data.getStringExtra("result"));
            }
        }

        if (requestCode == 4) {
            if (resultCode == RESULT_OK) {
                snack2Calories = Integer.valueOf(data.getStringExtra("result"));
            }
        }

        if (requestCode == 5) {
            if (resultCode == RESULT_OK) {
                dinnerCalories = Integer.valueOf(data.getStringExtra("result"));
            }
        }

        if (requestCode == 6) {
            if (resultCode == RESULT_OK) {
                snack3Calories = Integer.valueOf(data.getStringExtra("result"));
            }
        }

        if (requestCode == 7) {
            if (resultCode == RESULT_OK) {
                date = data.getStringExtra("result");
                msg = "Calories for " + date;
                dateView.setText(msg);
            }
        }
    }

    // Display meal calories in text view.
    private void showMealCalories () {
        bfCaloriesView.setText(String.valueOf(breakfastCalories));
        s1CaloriesView.setText(String.valueOf(snack1Calories));
        lCaloriesView.setText(String.valueOf(lunchCalories));
        s2CaloriesView.setText(String.valueOf(snack2Calories));
        dCaloriesView.setText(String.valueOf(dinnerCalories));
        s3CaloriesView.setText(String.valueOf(snack3Calories));
    }

    // Display total calories in text view.
    private void showTotalCalories () {
        int totalCalories = breakfastCalories + lunchCalories + dinnerCalories +
                snack1Calories + snack2Calories + snack3Calories;
        tCaloriesView.setText(String.valueOf(totalCalories));
    }
}
package com.example.foodfight;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.widget.TextView;

/**
 * TODO: Get date from Calendar.  Does CalendarHandler need to exist if there's already a
 *   Calendar method in Android?
 *
 */

public class acMeals extends AppCompatActivity {
    CalendarHandler calendarHandler = new CalendarHandler();
    private TextView dateView;
    private String selectedDate;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meals);
        // Set dateView to the system date if the user has not selected a date on the calendar.
        Log.i("FF_Meals","Get Date");
        selectedDate = calendarHandler.getSystemDate();

    }

    @Override
    protected void onResume() {
        super.onResume();

        dateView = findViewById(R.id.tvDate);
        String msg = "Calories for " + selectedDate;
        dateView.setText(msg);
        Log.i("FF_Meals","Set date on screen");
        // TODO: There needs to be a feedback on a selected date from

    }

    // Called when user taps Select Another Day
    public void getAnotherDay(View view) {
        Intent intent = new Intent(this, acCalendar.class);
        startActivity(intent);
    }

    // Might have to rework the date handling
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == 1) {
//            if (resultCode == RESULT_OK) {
//                String result = "Calories for " + data.getStringExtra("result");
//                dateView.setText(result);
//            }
//        }
//    }

    // Called when user taps the Breakfast button
    public void btnBreakfast(View view) {
        Intent intent = new Intent(this, acFoodList.class);
        intent.putExtra("MealDate",selectedDate);
        intent.putExtra("MealType","Breakfast");
        startActivity(intent);
    }

    // Called when user taps the Snack 1 button
    public void btnSnack1(View view) {
        Intent intent = new Intent(this, acFoodList.class);
        intent.putExtra("MealDate",selectedDate);
        intent.putExtra("MealType","Snack1");
        startActivity(intent);
    }

    // Called when user taps the Lunch button
    public void btnLunch(View view) {
        Intent intent = new Intent(this, acFoodList.class);
        intent.putExtra("MealDate",selectedDate);
        intent.putExtra("MealType","Lunch");
        startActivity(intent);
    }

    // Called when user taps the Snack 2 button
    public void btnSnack2(View view) {
        Intent intent = new Intent(this, acFoodList.class);
        intent.putExtra("MealDate",selectedDate);
        intent.putExtra("MealType","Snack2");
        startActivity(intent);
    }

    // Called when user taps the Dinner button
    public void btnDinner(View view) {
        Intent intent = new Intent(this, acFoodList.class);
        intent.putExtra("MealDate",selectedDate);
        intent.putExtra("MealType","Dinner");
        startActivity(intent);
    }

    // Called when user taps the Snack 3 button
    public void btnSnack3(View view) {
        Intent intent = new Intent(this, acFoodList.class);
        intent.putExtra("MealDate",selectedDate);
        intent.putExtra("MealType","Snack3");
        startActivity(intent);
    }



}
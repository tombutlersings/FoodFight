package com.example.foodfight;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * This Activity displays the meals that can be reviewed or edited by the user.
 * The display at the top showing the actual date or the selected date.
 * The select another day button takes opens acCalendar for the user to select a date
 * and will pass it back th new date on returning to this screen.
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
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyy", Locale.getDefault());
        Calendar rightNow = Calendar.getInstance();

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
        startActivityForResult(intent, 1);
    }

    // Set the date to the one passed from the acCalendar
    // RECEIVER OF INTENT from acCalendar
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                selectedDate = data.getStringExtra("result");
            }
        }
    }

    // Called when user taps the Breakfast button
    public void btnBreakfast(View view) {
        Intent intent = new Intent(this, acFoodList.class);
        intent.putExtra("MealDate",selectedDate);
        intent.putExtra("MealName","Breakfast");
        startActivity(intent);
    }

    // Called when user taps the Snack 1 button
    public void btnSnack1(View view) {
        Intent intent = new Intent(this, acFoodList.class);
        intent.putExtra("MealDate",selectedDate);
        intent.putExtra("MealName","Snack1");
        startActivity(intent);
    }

    // Called when user taps the Lunch button
    public void btnLunch(View view) {
        Intent intent = new Intent(this, acFoodList.class);
        intent.putExtra("MealDate",selectedDate);
        intent.putExtra("MealName","Lunch");
        startActivity(intent);
    }

    // Called when user taps the Snack 2 button
    public void btnSnack2(View view) {
        Intent intent = new Intent(this, acFoodList.class);
        intent.putExtra("MealDate", selectedDate);
        intent.putExtra("MealName","Snack2");
        startActivity(intent);
    }

    // Called when user taps the Dinner button
    public void btnDinner(View view) {
        Intent intent = new Intent(this, acFoodList.class);
        intent.putExtra("MealDate",selectedDate);
        intent.putExtra("MealName","Dinner");
        startActivity(intent);
    }

    // Called when user taps the Snack 3 button
    public void btnSnack3(View view) {
        Intent intent = new Intent(this, acFoodList.class);
        intent.putExtra("MealDate",selectedDate);
        intent.putExtra("MealName","Snack3");
        startActivity(intent);
    }

}
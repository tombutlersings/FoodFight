package com.example.foodfight;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import android.os.Bundle;

/**
 * TODO: rename this to MealsList???? -TB 9Jun21
 */

public class acMeals extends AppCompatActivity {
    CalendarHandler calendarHandler = new CalendarHandler();
    private TextView dateView;
    private String dateMessage = "Today is ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ac_meals);

        // Set dateView to the system date if the user
        // have not selected a date on the calendar.
        dateView = (TextView) findViewById(R.id.meals_date);
        Intent intent = getIntent();
        String calendarDate = intent.getStringExtra(acCalendar.DATE_MESSAGE);
        if (calendarDate == null) {
            dateMessage += calendarHandler.getSystemDate();
            dateView.setText(dateMessage);
        } else {
            dateMessage += calendarDate;
            dateView.setText(dateMessage);
        }
    }

    public void btnSelectDay(View view) {
        Intent intent = new Intent(this, acCalendar.class);
        startActivity(intent);
    }
}
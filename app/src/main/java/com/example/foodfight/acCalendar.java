package com.example.foodfight;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;

import android.content.Intent;
import android.view.View;
import android.widget.CalendarView;
import android.widget.TextView;
import android.os.Bundle;

/* Caleb 8Jun21:
 * GET SYSTEM DATE
 * getSystemDate() returns current android system date.
 */
public class acCalendar extends AppCompatActivity {
    public static final String DATE_MESSAGE = "com.example.foodfight.MESSAGE";

    // Create CalendarView and TextView objects, calender and date_view.
    private CalendarView calenderView;
    private TextView calendarDateView;

    public String calendarDate = null;
    public int calendarDay;
    public int calendarMonth;
    public int calendarYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        // Define view objects as their view ID.
        calenderView = (CalendarView)findViewById(R.id.calendar);
        calendarDateView = (TextView) findViewById(R.id.calendar_date);

        // Add Listener in calendar
        calenderView.setOnDateChangeListener(
                new CalendarView.OnDateChangeListener() {

                    @Override
                    // In this Listener have one method
                    // and in this method we will
                    // get the value of DAYS, MONTH, YEARS
                    public void onSelectedDayChange(
                            @NonNull CalendarView view,
                            int year,
                            int month,
                            int dayOfMonth)
                    {
                        calendarDay = dayOfMonth;
                        calendarMonth = month + 1;
                        calendarYear = year;

                        // Store the value of date with
                        // format in String type Variable
                        // Add 1 in month because month
                        // index is start with 0
                        calendarDate = (month + 1) + "-"
                                + dayOfMonth + "-" + year;

                        // set this date in TextView for Display
                        calendarDateView.setText(calendarDate);
                    }
                });

    }

    public void btnDone(View view) {
        Intent intent = new Intent(this, acMeals.class);
        intent.putExtra(DATE_MESSAGE, calendarDate);
        startActivity(intent);
    }
}
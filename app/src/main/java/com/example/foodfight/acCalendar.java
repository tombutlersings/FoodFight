package com.example.foodfight;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.core.view.GestureDetectorCompat;

import android.content.Intent;
import android.icu.util.Calendar;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CalendarView;
import android.widget.TextView;
import android.os.Bundle;

public class acCalendar extends AppCompatActivity {

    public String calendarDate = null;
    public int calendarDay;
    public int calendarMonth;
    public int calendarYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        // Define view objects as their view ID.
        // Create CalendarView and TextView objects, calender and date_view.
        CalendarView calendarView = findViewById(R.id.calendar);
        TextView calendarDateView = findViewById(R.id.calendar_date);

        // Add calendar constraint for maximum and minimum date
        // User can select a date one year earlier or a date
        // one year in the future.
        Calendar cal1 = null;
        Calendar cal2 = null;
        long afterOneYearsinMilli = 0;
        long beforeOneYearsinMilli = 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            // Max
            cal1 = Calendar.getInstance();
            cal1.add(Calendar.YEAR, 1);
            afterOneYearsinMilli = cal1.getTimeInMillis();

            // Min
            cal2 = Calendar.getInstance();
            cal2.add(Calendar.YEAR, -1);
            beforeOneYearsinMilli = cal2.getTimeInMillis();
        }
        calendarView.setMaxDate(afterOneYearsinMilli);
        calendarView.setMinDate(beforeOneYearsinMilli);

        // Add Listener in calendar
        // In this Listener have one method
        // and in this method we will
        // get the value of DAYS, MONTH, YEARS
        calendarView.setOnDateChangeListener(
                (view, year, month, dayOfMonth) -> {
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
                });
    }

    public void btnDone(View view) {
        Intent resultIntent = new Intent(this, acMeals.class);
        resultIntent.putExtra("result", calendarDate);
        setResult(RESULT_OK, resultIntent);
        this.finish();
    }
}
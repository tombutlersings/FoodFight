package com.example.foodfight;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import android.widget.CalendarView;
import android.widget.TextView;
import android.os.Bundle;

public class acCalendar extends AppCompatActivity {
    // Create CalendarView and TextView objects, calender and date_view.
    private CalendarView calender_view;
    private TextView date_view;

    public String date;
    public int calendarDay;
    public int calendarMonth;
    public int calendarYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        // Define view objects as their view ID.
        calender_view = (CalendarView)findViewById(R.id.calendar);
        date_view = (TextView) findViewById(R.id.date);

        // Add Listener in calendar
        calender_view.setOnDateChangeListener(
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
                        date = dayOfMonth + "-"
                                + (month + 1) + "-" + year;

                        // set this date in TextView for Display
                        date_view.setText(date);
                    }
                });
    }
}
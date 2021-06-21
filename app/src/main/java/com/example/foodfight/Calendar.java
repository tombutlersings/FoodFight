package com.example.foodfight;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.TextView;

public class Calendar extends AppCompatActivity {

    // Create CalendarView and TextView objects, calender and date_view.
    CalendarView calender;
    TextView date;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Define view objects as their view ID.
        calender = (CalendarView)findViewById(R.id.calendar);
        date = (TextView) findViewById(R.id.date);

        // Add Listener in calendar
        calender.setOnDateChangeListener(
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

                                // Store the value of date with
                                // format in String type Variable
                                // Add 1 in month because month
                                // index is start with 0
                                String Date
                                        = dayOfMonth + "-"
                                        + (month + 1) + "-" + year;

                                // set this date in TextView for Display
                                date.setText(Date);
                            }
                        });
    }
}
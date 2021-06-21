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

    }
}
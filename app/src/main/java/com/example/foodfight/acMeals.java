package com.example.foodfight;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import android.os.Bundle;

/**
 * TODO: rename this to MealsList???? -TB 9Jun21
 */

public final class acMeals extends AppCompatActivity {
    CalendarHandler calendarHandler = new CalendarHandler();
    private TextView dateView;
    private String dateMessage = "Today is ";

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meals);

        // Set dateView to the system date if the user
        // have not selected a date on the calendar.
        dateMessage += calendarHandler.getSystemDate();
        dateView = (TextView) findViewById(R.id.showDate);
        dateView.setText(dateMessage);
    }

    public void getAnotherDay(View view) {
        Intent intent = new Intent(this, acCalendar.class);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                String result = data.getStringExtra("result");
                dateView.setText("Today is " + result);
            }
        }
    }
}
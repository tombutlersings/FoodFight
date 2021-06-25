package com.example.foodfight;


import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.Date;
import java.util.Locale;

/**
* Tom 8Jun21:
* This is to handle calling up date objects to the
 * TODO is this going to be in the Presenter or Model layer?
 *
 * Caleb 8Jun21:
 * CALENDAR HANDLER
 * CalendarHandler class encapsulate the day, month, year, and date
 * values from acCalendar activity. It also encapsulate android
 * system date value.
 */
public class CalendarHandler {

    private acCalendar calendarActivity = new acCalendar();

    public int getCalendarDay() {
        return calendarActivity.calendarDay;
    }

    public int getCalendarMonth() {
        return calendarActivity.calendarMonth;
    }

    public int getCalendarYear() {
        return calendarActivity.calendarYear;
    }

    public String getCalendarDate() {
        return calendarActivity.calendarDate;
    }

    // Caleb 8Jun21:
    // GET SYSTEM DATE
    // getSystemDate() returns current android system date value.
    @RequiresApi(api = Build.VERSION_CODES.N)
    public String getSystemDate() {
        Date date = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("MM-dd-yyyy", Locale.getDefault());
        String formattedDate = df.format(date);
        return formattedDate;
    }
}

package com.example.foodfight;


import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.Date;
import java.util.Locale;

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

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static String getSystemDate() {
        Date date = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("MM-dd-yyyy", Locale.getDefault());
        return df.format(date);
    }
}

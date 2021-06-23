package com.example.foodfight;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
* Tom 8Jun21:
* This is to handle calling up date objects to the
 * TODO is this going to be in the Presenter or Model layer?
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

    public String getSystemDate() {
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("MM-dd-yyyy", Locale.getDefault());
        String formattedDate = df.format(c);
        return formattedDate;
    }
}

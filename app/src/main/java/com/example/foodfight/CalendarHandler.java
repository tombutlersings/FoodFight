package com.example.foodfight;
/**
* Tom 8Jun21:
* This is to handle calling up date objects to the
 * TODO is this going to be in the Presenter or Model layer?
*/
public class CalendarHandler {

    private acCalendar calendar;

    public String date = calendar.date;

    public int getCalendarDay() {
        return calendar.calendarDay;
    }

    public int getCalendarMonth() {
        return calendar.calendarMonth;
    }

    public int getCalendarYear() {
        return calendar.calendarYear;
    }
}

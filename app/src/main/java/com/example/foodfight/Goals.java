package com.example.foodfight;

public class Goals {
    private static int GoalDaily;
    public static int getDaily() { return GoalDaily; }
    public static void setDaily(int goalDaily) { GoalDaily = goalDaily; }

    private static int GoalWeekly;
    public static int getWeekly() { return GoalWeekly; }
    public static void setWeekly(int goalWeekly) { GoalWeekly = goalWeekly; }

    private static int CurrentDaily;
    public static int getCurrentDaily() { return CurrentDaily; }
    public static void setCurrentDaily(int currentDaily) { CurrentDaily = currentDaily; }

    private static int CurrentWeekly;
    public static int getCurrentWeekly() { return CurrentWeekly; }
    public static void setCurrentWeekly(int currentWeekly) { CurrentWeekly = currentWeekly; }

}

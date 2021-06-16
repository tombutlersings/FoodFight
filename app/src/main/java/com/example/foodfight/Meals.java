package com.example.foodfight;

/* This activity is where the user will select one of six meal times and
 * add food items to update the calorie count for that meal.
 * An button at the bottom of the screen navigates to the DayPicker activity
 * where a different day can be selected.
 */

/* FILE NAME CHANGE.
 * This enumeration should be renamed Meal.
 * The Meal class should be renamed Meals because it has totalCalories object
 * that is the total calories of all selected meal enumerations and we will analyze all
 * selected meal enumerations there.
 */

public enum Meals {
    Breakfast,Morning_Snack,Lunch,Afternoon_Snack,Dinner,Evening_Snack
}

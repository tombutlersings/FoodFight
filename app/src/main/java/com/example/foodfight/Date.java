package com.example.foodfight;

import java.util.ArrayList;

/**
 * This is to hold the date information. There will only be one date object for each day of the year
 *
 */
public class Date {
    // TODO datetime object or hashmap for each day of the year
    private static ArrayList<MealItem> mealList = new ArrayList<>(); // each date will start with a full meal object list. Each meal will start empty

    // Define meals types.
    private static MealsEnum Breakfast = MealsEnum.Breakfast;
    private static MealsEnum Lunch = MealsEnum.Lunch;
    private static MealsEnum Dinner = MealsEnum.Dinner;
    private static MealsEnum AfternoonSnack = MealsEnum.Afternoon_Snack;
    private static MealsEnum EveningSnack = MealsEnum.Evening_Snack;

    // Do we need constructor?
    Date(){

    }

    private static void setDate(){
        // These are food items as objects of FoodItem class.
        FoodItem sugar = new FoodItem("sugar", 1, 40, "2 spoons");
        FoodItem flour = new FoodItem("flour", 2, 62, "10 cups");
        FoodItem oil = new FoodItem("oil", 3, 35, "2 cups");
        FoodItem salt = new FoodItem("salt", 4, 45, "1 spoon");

        // FoodListHandler objects.
        // Use the FoodListHandler class to make a food list (array) for each meal.
        // Inside the handler, there is an array that stored all foods in the it
        // constructor argument.
        FoodListHandler breakfastFoodList = new FoodListHandler(sugar, flour);
        FoodListHandler lunchFoodList = new FoodListHandler(oil, salt);
        FoodListHandler dinnerFoodList = new FoodListHandler(flour, oil);
        FoodListHandler afternoonSnackFoodList = new FoodListHandler(sugar, oil);
        FoodListHandler eveningSnackFoodList = new FoodListHandler(flour, salt);

        // Use the above foodListHandler objects to make MealItem objects.
        // For the two parameters, use a MealsEnum for the first argument, and
        // total calories in foodListHandler(s) for the second argument.
        MealItem breakfast = new MealItem(Breakfast, breakfastFoodList.getTotalCalories());
        MealItem lunch = new MealItem(Lunch, lunchFoodList.getTotalCalories());
        MealItem dinner = new MealItem(Dinner, breakfastFoodList.getTotalCalories());
        MealItem afternoonSnack = new MealItem(AfternoonSnack, afternoonSnackFoodList.getTotalCalories());
        MealItem eveningSnack = new MealItem(EveningSnack, eveningSnackFoodList.getTotalCalories());

        // Add the above MealItem objects to the mealList array.
        mealList.add(breakfast);
        mealList.add(lunch);
        mealList.add(dinner);
        mealList.add(afternoonSnack);
        mealList.add(eveningSnack);
    }

    public static ArrayList<MealItem> getDate() {
        return mealList;
    }

    // TODO Pulley, what is set/get HashKey for? -Tom 8Jun21
    private static  void setHashKey(){
    }
    private static  void getHashKey(){
    }

}


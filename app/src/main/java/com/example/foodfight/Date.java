package com.example.foodfight;

import java.util.ArrayList;

/**
 * This is to hold the date information. There will only be one date object for each day of the year
 *
 */
public class Date {
    // TODO datetime object or hashmap for each day of the year
    private String date;
    private static ArrayList<MealItem> mealList = new ArrayList<>(); // each date will start with a full meal object list. Each meal will start empty

    // Define meals types.
    private static final MealsEnum Breakfast = MealsEnum.Breakfast;
    private static final MealsEnum Lunch = MealsEnum.Lunch;
    private static final MealsEnum Dinner = MealsEnum.Dinner;
    private static final MealsEnum AfternoonSnack = MealsEnum.Afternoon_Snack;
    private static final MealsEnum EveningSnack = MealsEnum.Evening_Snack;

    public Date(String date){
        this.date = date;
    }

    private static void setDate(){
        // Create foods items.
        FoodItem sugar = new FoodItem(1,"sugar",  40);
        FoodItem flour = new FoodItem(2,"flour",  62);
        FoodItem oil = new FoodItem(3,"oil", 35);
        FoodItem salt = new FoodItem(4,"salt",  45);

        // Store foods of the date in meal Items.
        /*MealItem breakfast = new MealItem(Breakfast, sugar, flour);
        MealItem lunch = new MealItem(Lunch, oil, salt);
        MealItem dinner = new MealItem(Dinner, flour, oil);
        MealItem afternoonSnack = new MealItem(AfternoonSnack, sugar, oil);
        MealItem eveningSnack = new MealItem(EveningSnack, flour, salt);

        // Store meals of the date in a meal list.
        mealList.add(breakfast);
        mealList.add(lunch);
        mealList.add(dinner);
        mealList.add(afternoonSnack);
        mealList.add(eveningSnack);*/
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


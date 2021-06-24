package com.example.foodfight;

import java.util.ArrayList;

public class MealItem {
    private MealsEnum name; //from meals enum
    //private int totalCalories; // math total for calories in the food lists of the meals
    private ArrayList<FoodItem> foodItems = new ArrayList<>();

    // Constructor for storing a food item
    // (object of the FoodItem class) in
    // foodItems array.
    public MealItem(MealsEnum name, FoodItem item1, FoodItem item2) {
        this.name = name;
        foodItems.add(item1);
        foodItems.add(item2);
    }

    public MealsEnum getMealName() {return name;}

    // Find the total calories of all food.
    public int getTotalCalories() {
        int totalCalories = 0;

        // Loop through foodItems array and
        // get the sum of calories of all food.
        for (FoodItem food : foodItems) {
            totalCalories += food.getCalories();
        }

        return totalCalories;
    }
}


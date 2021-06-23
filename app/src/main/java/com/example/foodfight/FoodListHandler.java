package com.example.foodfight;

import java.util.ArrayList;

/**
 * 8Jun21 Tom- This class is for pulling data from the food library and adding it to a foodlist within the selected meal
 * possible use for pushing new foods to the UserAddedFoodLibrary.json
 */

public class FoodListHandler {
    private ArrayList<FoodItem> foodItems = new ArrayList<>();

    // Constructor for storing a food item
    // (object of the FoodItem class) in
    // foodItems array.
    public FoodListHandler(FoodItem foodItem1,
                           FoodItem foodItem2)
    {
        foodItems.add(foodItem1);
        foodItems.add(foodItem2);
    }

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

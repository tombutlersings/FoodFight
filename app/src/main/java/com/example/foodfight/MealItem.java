package com.example.foodfight;

import java.util.ArrayList;
import java.util.List;

public class MealItem {
    public Integer ID;
    private String name; //from meals enum
    //private int totalCalories; // math total for calories in the food lists of the meals
    public ArrayList<FoodItem> foodItems = new ArrayList<>();

    // Constructor for storing a food item
    // (object of the FoodItem class) in
    // foodItems array.
    public MealItem(Integer ID, String name, ArrayList<FoodItem> foods) {
        this.name = name;
        this.foodItems = foods;
        this.ID = ID;
        //foodItems.add(item1);
        //foodItems.add(item2);
    }

    public String getMealName() {return name;}

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

    public void addFoodToList(FoodItem food){
        foodItems.add(food);
    }
}


package com.example.foodfight;

public class MealItem {
    private MealsEnum name; //from meals enum
    private int totalCalories; // math total for calories in the food lists of the meals

    public MealItem(MealsEnum name, int totalCalories) {
        this.name = name;
        this.totalCalories = totalCalories;
    }

    public MealsEnum getMealName() {return name;}

    public int getTotalCalories() {return totalCalories;}
}


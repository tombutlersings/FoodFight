package com.example.foodfight;

/**
* One of the most important Classes in this app
 * This holds the information for the food item that is added to the meal
 */

public class FoodItem {
    private String name;
    private int Id;
    private int calories;
    private String servingSize;

    // Suggestion 2:
    // If the suggestion 1 is implemented, this construction won't
    // need a calories parameter.
    public FoodItem(String name, int Id, int calories, String servingSize) {
        this.name = name;
        this.Id = Id;
        this.calories = calories;
        this.servingSize = servingSize;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    // Suggestion 1:
    // This method should get calories data of food from FoodLibrary.json
    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public String getServingSize() {
        return servingSize;
    }

    public void setServingSize(String servingSize) {
        this.servingSize = servingSize;
    }
}


package com.example.foodfight;

/**
* One of the most important Classes in this app
 * This holds the information for the food item that is added to the meal
 */

public class FoodItem {
    String name;
    int Id;
    int calories;
    String servingSize;
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

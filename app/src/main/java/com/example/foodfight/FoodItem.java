package com.example.foodfight;

/**
* One of the most important Classes in this app
 * This holds the information for the food item that is added to the meal
 */

public class FoodItem {
    public static int Id;
    public static String FOOD_NAME;
    public static int FOOD_CALORIES;
    public static float FOOD_METRIC_SERVING;
    public static String FOOD_METRIC_SERVING_UNIT;
    public static float FOOD_HOUSEHOLD_SERVING;
    public static String FOOD_HOUSEHOLD_UNIT;
    public static String FOOD_MANUFACTURER;
    public static String SourceDB;

    // Suggestion 2:
    // If the suggestion 1 is implemented, this construction won't
    // need a calories parameter.

    public FoodItem(int Id, String name, int calories, float serving_size_m, String serving_size_m_unit, float serving_size_hh, String serving_size_hh_unit,  String manufacturer, String sourceDB) {
        this.FOOD_NAME = name;
        this.Id = Id;
        this.FOOD_CALORIES = calories;
        this.FOOD_METRIC_SERVING = serving_size_m;
        this.FOOD_METRIC_SERVING_UNIT = serving_size_hh_unit;
        this.FOOD_HOUSEHOLD_SERVING = serving_size_hh;
        this.FOOD_HOUSEHOLD_UNIT = serving_size_m_unit;
        this.FOOD_MANUFACTURER = manufacturer;
        this.SourceDB = sourceDB;

    }
    public FoodItem(int Id, String name,  int calories, float serving_size_m, String serving_size_m_unit, float serving_size_hh, String serving_size_hh_unit,  String manufacturer) {
        this(Id, name, calories, serving_size_m, serving_size_m_unit, serving_size_hh, serving_size_hh_unit,  manufacturer, null);
    }

    public FoodItem(int Id, String name,  int calories) {
        this(Id, name,  calories, 0, null, 0, null, null, null);
    }





    // Adding Custom


    public String getName() {
        return FOOD_NAME;
    }

    public void setName(String name) {
        this.FOOD_NAME = name;
    }

    public void printName(String firstName, String lastName){
        printName(firstName + " " + lastName);
    }

    public void printName(String name){
        //Do stuff
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
        return FOOD_CALORIES;
    }

    public void setCalories(int calories) {
        this.FOOD_CALORIES = calories;
    }

    public float getServingSize() {
        return FOOD_METRIC_SERVING;
    }

    public void setServingSize(float servingSizeMetric) { this.FOOD_METRIC_SERVING = servingSizeMetric; }

    public static String getFoodMetricServingUnit() { return FOOD_METRIC_SERVING_UNIT; }

    public static void setFoodMetricServingUnit(String foodMetricServingUnit) { FOOD_METRIC_SERVING_UNIT = foodMetricServingUnit; }

    public static float getFoodHouseholdServing() { return FOOD_HOUSEHOLD_SERVING; }

    public static void setFoodHouseholdServing(float foodHouseholdServing) { FOOD_HOUSEHOLD_SERVING = foodHouseholdServing; }

    public static String getFoodHouseholdUnit() { return FOOD_HOUSEHOLD_UNIT; }

    public static void setFoodHouseholdUnit(String foodHouseholdUnit) { FOOD_HOUSEHOLD_UNIT = foodHouseholdUnit; }

    public static String getFoodManufacturer() { return FOOD_MANUFACTURER; }

    public static void setFoodManufacturer(String foodManufacturer) { FOOD_MANUFACTURER = foodManufacturer; }

    public static String getSourceDB() { return SourceDB; }

    public static void setSourceDB(String sourceDB) { SourceDB = sourceDB; }
}


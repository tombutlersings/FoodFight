package com.example.foodfight;

/**
* One of the most important Classes in this app
 * This holds the information for the food item that is added to the meal
 */

public class FoodItem {
    public static int Id;
    public static String FOOD_NAME;
    public static int FOOD_CALORIES;
    public static double FOOD_METRIC_SERVING;
    public static String FOOD_METRIC_SERVING_UNIT;
    public static double FOOD_HOUSEHOLD_SERVING;
    public static String FOOD_HOUSEHOLD_UNIT;
    public static String FOOD_MANUFACTURER;
    public static String SourceDB;


    // multiple methods for creating FoodItems depending on how much information we had and our db schema
    // citation:
    public FoodItem(int Id, String name, int calories, double serving_size_m, String serving_size_m_unit, double serving_size_hh, String serving_size_hh_unit,  String manufacturer, String sourceDB) {
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
    public FoodItem(int Id, String name,  int calories, double serving_size_m, String serving_size_m_unit, double serving_size_hh, String serving_size_hh_unit,  String manufacturer) {
        this(Id, name, calories, serving_size_m, serving_size_m_unit, serving_size_hh, serving_size_hh_unit,  manufacturer, null);
    }

    public FoodItem(int Id, String name,  int calories) {
        this(Id, name,  calories, 0, null, 0, null, null, null);
    }

    public FoodItem(int Id, String name, int calories, String manufacturer, double serving_size_hh, String serving_size_hh_unit){
        this(Id, name, calories, 0.0, null, serving_size_hh,serving_size_hh_unit,manufacturer, null);
    }

    public static String getName() {
        return FOOD_NAME;
    }

    public void setName(String name) {
        this.FOOD_NAME = name;
    }

    public static int getId() {
        return Id;
    }

    public static void setId(int id) {
        Id = id;
    }

    public static int getCalories() {
        return FOOD_CALORIES;
    }

    public void setCalories(int calories) {
        this.FOOD_CALORIES = calories;
    }

    public static double getServingSize() {
        return FOOD_METRIC_SERVING;
    }

    public void setServingSize(float servingSizeMetric) { this.FOOD_METRIC_SERVING = servingSizeMetric; }

    public static String getFoodMetricServingUnit() { return FOOD_METRIC_SERVING_UNIT; }

    public static void setFoodMetricServingUnit(String foodMetricServingUnit) { FOOD_METRIC_SERVING_UNIT = foodMetricServingUnit; }

    public static double getFoodHouseholdServing() { return FOOD_HOUSEHOLD_SERVING; }

    public static void setFoodHouseholdServing(float foodHouseholdServing) { FOOD_HOUSEHOLD_SERVING = foodHouseholdServing; }

    public static String getFoodHouseholdUnit() { return FOOD_HOUSEHOLD_UNIT; }

    public static void setFoodHouseholdUnit(String foodHouseholdUnit) { FOOD_HOUSEHOLD_UNIT = foodHouseholdUnit; }

    public static String getFoodManufacturer() { return FOOD_MANUFACTURER; }

    public static void setFoodManufacturer(String foodManufacturer) { FOOD_MANUFACTURER = foodManufacturer; }

    public static String getSourceDB() { return SourceDB; }

    public static void setSourceDB(String sourceDB) { SourceDB = sourceDB; }

}
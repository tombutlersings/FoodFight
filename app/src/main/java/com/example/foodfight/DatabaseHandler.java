package com.example.foodfight;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

/**
* TODO: hardcode 2-3 foods, all meals for one date June 30
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MealFood.db";

//name for the Food Table
    public static final String FOOD_TABLE_NAME =  "food";
//Column names
    public static final String FOOD_ID_NAME =  "foodID";
    public static final String FOOD_CALORIES =  "calories";
    public static final String FOOD_METRIC_SERVING =  "serving_size_m";
    public static final String FOOD_METRIC_SERVING_UNIT =  "serving_size_m_unit";
    public static final String FOOD_HOUSEHOLD_SERVING =  "serving_size_hh";
    public static final String FOOD_HOUSEHOLD_UNIT =  "serving_size_hh_unit";
    public static final String FOOD_NAME =  "name";
    public static final String FOOD_MANUFACTURER =  "manufacturer";



//names for the meal table
    public static final String MEAL_TABLE_NAME = "meal";
//Column names
    public static final String MEAL_ID_NAME =  "mealID";
    public static final String MEAL_DATE_NAME =  "date";
    public static final String PROFILE_NAME =  "profile";
    public static final String MEAL_NAME = "meal";

//Linking table
    public static final String LINKING_TABLE = "meal_food";
//Linking Table Column Names
    public static final String SERVINGS_NAME = "servings";
    public static final String MEAL_FOOD_ID = "id";





    public DatabaseHandler(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
        //when this construct is run this database will be created
        //SQLiteDatabase db = this.getWritableDatabase(); // line just for troubleshooting
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

         //code for the database tables

        db.execSQL("CREATE TABLE " + MEAL_TABLE_NAME
                + " (" + MEAL_DATE_NAME +" TEXT, "
                + MEAL_NAME +" TEXT, "
                + PROFILE_NAME + " INTEGER,"
                + MEAL_ID_NAME + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL)");

        db.execSQL("CREATE TABLE " + FOOD_TABLE_NAME + " (" + FOOD_ID_NAME + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                + FOOD_NAME + " TEXT,"
                + FOOD_CALORIES +" INTEGER,"
                + FOOD_HOUSEHOLD_SERVING +" FLOAT,"
                + FOOD_HOUSEHOLD_UNIT + " TEXT,"
                + FOOD_METRIC_SERVING + " FLOAT,"
                + FOOD_METRIC_SERVING_UNIT +" TEXT,"
                + FOOD_MANUFACTURER + " TEXT,"
                + "[SourceDB] TEXT,"
                + " [picture_link] TEXT)");

        db.execSQL("CREATE TABLE " + LINKING_TABLE
                + " (" + MEAL_FOOD_ID +" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                + SERVINGS_NAME + " FLOAT NOT NULL,"
                + " FOREIGN KEY(" + MEAL_ID_NAME + ") REFERENCES " + MEAL_TABLE_NAME + "(" + MEAL_ID_NAME + ") ON DELETE CASCADE,"
                + " FOREIGN KEY(" + FOOD_ID_NAME + ") REFERENCES " + FOOD_TABLE_NAME + "(" + FOOD_ID_NAME + ") ON DELETE CASCADE)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //pass for now
        //Creates copies of tables
        //inserts data into new tables
        onCreate(db);
    }


    public void GetMeal(){
        SQLiteDatabase db = this.getReadableDatabase();
        //gets information for a meal
    }

    public void DailyCalories(){
        // Handled Elsewhere, will delete if no longer needed
        //gets the calories for a day
    }

    public void AddFood(FoodItem fooditem){
        SQLiteDatabase db = this.getReadableDatabase();

        //adds a food to the database
        //db.execSQL("INSERT INTO " + FOOD_TABLE_NAME + " (" + ")");
    }

    public void SearchFood(String foodname){
        SQLiteDatabase db = this.getReadableDatabase();
        //gets a list of foods from the database

    }

    public void AddMeal(){
        SQLiteDatabase db = this.getReadableDatabase();
        //need date, meal, food,
        // adds a new meal and connects foods to them
    }



}

package com.example.foodfight;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
* TODO: hardcode 2-3 foods, all meals for one date June 30
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "food.db";
    // was "MealFood.db";

//name for the Food Table
    public static final String FOOD_TABLE_NAME =  "food";
//Column names
    public static final String FOOD_ID =  "foodID";
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
    public static final String MEAL_ID =  "mealID";
    public static final String MEAL_DATE =  "date";
    public static final String PROFILE_NAME =  "profile";
    public static final String MEAL_NAME = "mealName";

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


    //This is done for now
    @Override
    public void onCreate(SQLiteDatabase db) {

         //code for the database tables

        db.execSQL("CREATE TABLE " + MEAL_TABLE_NAME
                + " (" + MEAL_DATE +" TEXT, "
                + MEAL_NAME +" TEXT, "
                + PROFILE_NAME + " INTEGER,"
                + MEAL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL)");

        db.execSQL("CREATE TABLE " + FOOD_TABLE_NAME + " (" + FOOD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                + FOOD_NAME + " TEXT,"
                + FOOD_CALORIES +" INTEGER,"
                + FOOD_HOUSEHOLD_SERVING +" FLOAT,"
                + FOOD_HOUSEHOLD_UNIT + " TEXT,"
                + FOOD_METRIC_SERVING + " FLOAT,"
                + FOOD_METRIC_SERVING_UNIT +" TEXT,"
                + FOOD_MANUFACTURER + " TEXT,"
                + " [SourceDB] TEXT,"
                + " [picture_link] TEXT)");

        db.execSQL("CREATE TABLE " + LINKING_TABLE
                + " (" + MEAL_FOOD_ID +" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                + SERVINGS_NAME + " FLOAT NOT NULL,"
                + " FOREIGN KEY(" + MEAL_ID + ") REFERENCES " + MEAL_TABLE_NAME + "(" + MEAL_ID + ") ON DELETE CASCADE,"
                + " FOREIGN KEY(" + FOOD_ID + ") REFERENCES " + FOOD_TABLE_NAME + "(" + FOOD_ID + ") ON DELETE CASCADE)");

    }


    //not sure what to do here, but may revisit later if need be
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //pass for now
        //Creates copies of tables
        //inserts data into new tables
        onCreate(db);
    }


    /*** TODO get the meal query into its appropriate object */
    //Gets a Meal and all foods added to it
    public void GetMeal(String date, String mealName){
        SQLiteDatabase db = this.getReadableDatabase();
        //gets information for a meal
        db.execSQL("");
        /*
        this query gets the information from a meal
        SELECT meal.date, meal_food.servings, servings * calories as total_calories, food.name. food.id
            FROM meal
	            LEFT JOIN meal_food ON meal.mealID = meal_food.mealID
                LEFT JOIN food ON meal_food.foodID = food.foodID
        WHERE meal.date = date AND mealName = mealName
         */
    }

    public void DailyCalories(){
        // Handled Elsewhere, will delete if no longer needed
        //gets the calories for a day
    }


    //adds a food to the database using a FoodItem object
    public void AddFood(FoodItem food){
        SQLiteDatabase db = this.getReadableDatabase();

        //gets the column names and assigns the values to them
        ContentValues values = new ContentValues();
        values.put(FOOD_NAME, food.getName()); // Food Name
        values.put(FOOD_CALORIES, food.getCalories()); // Food calories
        values.put(FOOD_HOUSEHOLD_SERVING, food.getFoodHouseholdServing());
        values.put(FOOD_HOUSEHOLD_UNIT, food.getFoodHouseholdUnit());
        values.put(FOOD_METRIC_SERVING, food.getServingSize());
        values.put(FOOD_METRIC_SERVING_UNIT, food.getFoodMetricServingUnit());
        values.put(FOOD_MANUFACTURER, food.getFoodManufacturer());
        values.put("SourceDB", food.getSourceDB());


        // Inserting Row
        db.insert(FOOD_TABLE_NAME, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }


    public List<FoodItem> SearchFood(String foodname){
        SQLiteDatabase db = this.getReadableDatabase();
        //gets a list of foods from the database

        //Initializes the list of FoodItems that will be returned
        List<FoodItem> foodList = new ArrayList<FoodItem>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + FOOD_TABLE_NAME + "WHERE " + FOOD_NAME + " LIKE %" + foodname + "%";

        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                FoodItem food = new FoodItem(null,0,0);
                food.setId(Integer.parseInt(cursor.getString(0)));
                food.setName(cursor.getString(1));
                food.setCalories(Integer.parseInt(cursor.getString(2)));
                // Adding contact to list
                foodList.add(food);
            } while (cursor.moveToNext());
        }

        // return food list
        return foodList;

    }

    //Gets information on one food
    //public FoodItem GetFoodInfo(Integer foodID){}

    //Adds foods to a meal
    public void AddToMeal(Integer mealID, Integer foodID, Integer servings){
        SQLiteDatabase db = this.getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(MEAL_ID, mealID);
        values.put(FOOD_ID, foodID);
        values.put(SERVINGS_NAME, servings);
        db.insert(LINKING_TABLE, null, values);

        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }

    //Creates a meal that food can be added to
    public void CreateMeal(String date, String mealName, Integer profile){
        SQLiteDatabase db = this.getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(MEAL_DATE, date);
        values.put(MEAL_NAME, mealName);
        values.put(PROFILE_NAME, profile);
        db.insert(MEAL_TABLE_NAME, null, values);

        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }
//    public boolean VerifyExistance(){
//        // query to the database food table will go
//        if ( SELECT COUNT(*) * FROM FOOD_TABLE){
//            return true;
//        } else {
//            return false;
//        }
//    }






}

package com.example.foodfight;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

/**
* TODO: hardcode 2-3 foods, all meals for one date June 30
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "food.db";

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

    // This constructor will create the database
    public DatabaseHandler(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }


    /** Creates the database tables for Meals, Foods, and a linking table
     *
     * @param db: name of database
     */
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE IF NOT EXISTS " + MEAL_TABLE_NAME
                + " (" + MEAL_DATE +" TEXT, "
                + MEAL_NAME +" TEXT, "
                + PROFILE_NAME + " INTEGER,"
                + MEAL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL)");

        db.execSQL("CREATE TABLE IF NOT EXISTS " + FOOD_TABLE_NAME + " (" + FOOD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                + FOOD_NAME + " TEXT,"
                + FOOD_CALORIES +" INTEGER,"
                + FOOD_HOUSEHOLD_SERVING +" FLOAT,"
                + FOOD_HOUSEHOLD_UNIT + " TEXT,"
                + FOOD_METRIC_SERVING + " FLOAT,"
                + FOOD_METRIC_SERVING_UNIT +" TEXT,"
                + FOOD_MANUFACTURER + " TEXT,"
                + " [SourceDB] TEXT,"
                + " [picture_link] TEXT)");

        db.execSQL("CREATE TABLE IF NOT EXISTS " + LINKING_TABLE
                + " (" + MEAL_FOOD_ID +" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                + SERVINGS_NAME + " FLOAT NOT NULL," + MEAL_ID + " INTEGER, " + FOOD_ID + " INTEGER)");

    }


    // Future use method for upgrading table structure (deletes current content)
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //pass for now
        //Creates copies of tables
        //inserts data into new tables
        onCreate(db);
    }


    /** Gets a meal (Breakfast, Lunch, etc) and all the foods tied to that meal
     *
     * @param date: date of meal
     * @param mealName: name of meal (see MealsEnum)
     * @return MealItem
     */
    public MealItem GetMeal(String date, String mealName){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<FoodItem> foodList = new ArrayList<FoodItem>();
        // Select All Query
        String selectQuery = "SELECT " + FOOD_TABLE_NAME + "." + FOOD_NAME + ", "
                                + FOOD_TABLE_NAME + "." + FOOD_CALORIES +", "
                                + FOOD_TABLE_NAME + "." + FOOD_HOUSEHOLD_SERVING +", "
                                + FOOD_TABLE_NAME + "." + FOOD_HOUSEHOLD_UNIT + ", "
                                + FOOD_TABLE_NAME + "." + FOOD_METRIC_SERVING + ", "
                                + FOOD_TABLE_NAME + "." + FOOD_METRIC_SERVING_UNIT +", "
                                + FOOD_TABLE_NAME + "." + FOOD_MANUFACTURER + ", "
                                + FOOD_TABLE_NAME + ".SourceDB, "
                                + FOOD_TABLE_NAME + ".picture_link " +
                                "FROM " + MEAL_TABLE_NAME +
                                " LEFT JOIN " + LINKING_TABLE +" ON " + MEAL_TABLE_NAME + "." + MEAL_ID +"= " + LINKING_TABLE + "." + MEAL_ID +
                                " LEFT JOIN " + FOOD_TABLE_NAME +" ON " + LINKING_TABLE + "." + FOOD_ID +" =  " + FOOD_TABLE_NAME + "." + FOOD_ID +
                                " WHERE "+ MEAL_TABLE_NAME +"." + MEAL_DATE + " = " + date + " AND " + MEAL_NAME + " = '" + mealName + "'";

        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                FoodItem food = new FoodItem(1,null,150);
                food.setId(cursor.getInt(cursor.getColumnIndex(FOOD_ID)));
                food.setName(cursor.getString(cursor.getColumnIndex(FOOD_NAME)));
                food.setCalories(cursor.getInt(cursor.getColumnIndex(FOOD_CALORIES)));
                food.setFoodHouseholdServing(cursor.getInt(cursor.getColumnIndex(FOOD_HOUSEHOLD_SERVING)));
                food.setFoodHouseholdUnit(cursor.getString(cursor.getColumnIndex(FOOD_HOUSEHOLD_UNIT)));
                food.setServingSize(cursor.getInt(cursor.getColumnIndex(FOOD_METRIC_SERVING)));
                food.setFoodMetricServingUnit(cursor.getString(cursor.getColumnIndex(FOOD_METRIC_SERVING_UNIT)));
                food.setFoodManufacturer(cursor.getString(cursor.getColumnIndex(FOOD_MANUFACTURER)));
                food.setSourceDB(cursor.getString(cursor.getColumnIndex("SourceDB")));

                // Adding contact to list
                foodList.add(food);
            } while (cursor.moveToNext());
        }



        //gets the meal ID
        String mealIDQuery = "SELECT " + MEAL_TABLE_NAME + "." + MEAL_ID +
                " FROM " + MEAL_TABLE_NAME +
                " WHERE " + MEAL_TABLE_NAME +"." + MEAL_DATE + " = '" + date + "' AND " + MEAL_NAME + " = '" + mealName + "'";

        cursor = db.rawQuery(mealIDQuery, null);

        Integer id = null;

        if (cursor.moveToFirst()) {
            id = cursor.getInt(cursor.getColumnIndex(MEAL_ID));
        }

        //gets the quantities consumed from the database
        ArrayList<Float> quantityList = null;
        String quantityQuery = "SELECT " + LINKING_TABLE +"." + SERVINGS_NAME +
                " FROM " + MEAL_TABLE_NAME +
                " LEFT JOIN " + LINKING_TABLE +" ON " + MEAL_TABLE_NAME + "." + MEAL_ID +"= " + LINKING_TABLE + "." + MEAL_ID +
                " LEFT JOIN " + FOOD_TABLE_NAME +" ON " + LINKING_TABLE + "." + FOOD_ID +" =  " + FOOD_TABLE_NAME + "." + FOOD_ID +
                " WHERE "+ MEAL_TABLE_NAME +"." + MEAL_DATE + " = " + date + " AND " + MEAL_NAME + " = '" + mealName + "'";

        cursor = db.rawQuery(quantityQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Float quantity = Float.parseFloat(cursor.getString(0));


                // Adding contact to list
                quantityList.add(quantity);
            } while (cursor.moveToNext());
        }
        db.close();

        MealItem meal = new MealItem(id, date,  mealName, foodList, quantityList);
        // return food list
        return meal;
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
        String selectQuery = "SELECT * FROM " + FOOD_TABLE_NAME + " WHERE " + FOOD_NAME + " LIKE '%" + foodname + "%'";

        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                FoodItem food = new FoodItem(1,null,150);
                food.setId(Integer.parseInt(cursor.getString(0)));
                food.setName(cursor.getString(1));
                food.setCalories(Integer.parseInt(cursor.getString(2)));
                food.setFoodHouseholdServing(Integer.parseInt(cursor.getString(3)));
                food.setFoodHouseholdUnit(cursor.getString(4));
                food.setServingSize(Integer.parseInt(cursor.getString(5)));
                food.setFoodMetricServingUnit(cursor.getString(6));
                food.setFoodManufacturer(cursor.getString(7));
                food.setSourceDB(cursor.getString(8));

                // Adding contact to list
                foodList.add(food);
            } while (cursor.moveToNext());
        }
        db.close();
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
        values.put(SERVINGS_NAME, servings); // Needs to become QUANTITY from the FoodItem Page
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


    public Integer getIdFromAPI(FoodItem foodItem){
        SQLiteDatabase db = this.getWritableDatabase();
        //adds the food to the database
        //this.AddFood(foodItem);


        //simplified query to make it run more better.
        String sql = "SELECT * FROM " + FOOD_TABLE_NAME +
                " WHERE " + FOOD_NAME + " = \"" + foodItem.getName() + "\" AND " +
                            FOOD_CALORIES + " = " + foodItem.getCalories();// + " AND " +
//                            FOOD_HOUSEHOLD_SERVING + " = " + foodItem.getFoodHouseholdServing() + " AND " +
//                            FOOD_HOUSEHOLD_UNIT + " = " + foodItem.getFoodHouseholdUnit() + " AND " +
//                            FOOD_METRIC_SERVING + " = " + foodItem.getServingSize() + " AND " +
//                            FOOD_METRIC_SERVING_UNIT + " = " + foodItem.getFoodMetricServingUnit() + " AND " +
//                            FOOD_MANUFACTURER + " = '" + foodItem.getFoodManufacturer() + "' AND " +
//                            "SourceDB" + " = " + foodItem.getSourceDB() + "";

        Cursor cursor = db.rawQuery(sql, null);

        if (cursor !=null)
            cursor.moveToFirst();
//            Integer id = Integer.parseInt(cursor.getString(0));
//        Integer id = Integer.parseInt(String.valueOf(cursor));
        Integer id = cursor.getInt(cursor.getColumnIndex(FOOD_ID));


        Log.d("String", "food id is: " + id.toString());
        db.close();

        return id;
    };

    public Integer getMealID(String date, String name){
        SQLiteDatabase db = this.getWritableDatabase();

        String sql = "SELECT " + MEAL_ID +
                " FROM " + MEAL_TABLE_NAME +
                " WHERE " + MEAL_DATE + " = '" + date + "' AND " + MEAL_NAME + " = '" + name + "'";



        Cursor cursor = db.rawQuery(sql, null);

        if (cursor !=null)
            cursor.moveToFirst();
        Integer id = Integer.parseInt(cursor.getString(0));


        Log.d("String", "meal id is: " + id.toString());
        db.close();
        return id;
    };

    public ArrayList<List> getFoodList(int mealID){
        //
        SQLiteDatabase db = this.getWritableDatabase();

        String sql = "SELECT " + "*" +
                " FROM " + LINKING_TABLE +
                " WHERE " + MEAL_ID + " = '" + mealID + "'";
// SELECT * from "meal_food" where mealID = 58


        Cursor cursor = db.rawQuery(sql, null);


        ArrayList<List> IDQTY = new ArrayList<>();
                if (cursor.moveToFirst()) {
                    do {
                        ArrayList<Integer> list = new ArrayList<>();
                        String id = (cursor.getString(cursor.getColumnIndex(FOOD_ID)));
                        String servings = (cursor.getString(cursor.getColumnIndex(SERVINGS_NAME)));
                        list.add(Integer.parseInt(id));
                        list.add(Integer.parseInt(servings));
                        IDQTY.add(list);
                    } while (cursor.moveToNext());
                }

        db.close();
        return IDQTY;
    };


    public FoodItem getFoodItemById(int foodid){
        //
        SQLiteDatabase db = this.getWritableDatabase();

        String selectQuery = "SELECT * FROM " + FOOD_TABLE_NAME + " WHERE " + FOOD_ID + " = " + foodid ;
// SELECT * from "meal_food" where mealID = 58


        Cursor cursor = db.rawQuery(selectQuery, null);

        FoodItem food = new FoodItem(1,null,150);
        if (cursor.moveToFirst()) {
            do {

                food.setId(cursor.getInt(cursor.getColumnIndex(FOOD_ID)));
//                String name = cursor.getInt(cursor.getColumnIndex("table_column_name"));
                food.setName(cursor.getString(cursor.getColumnIndex(FOOD_NAME)));
                food.setCalories(cursor.getInt(cursor.getColumnIndex(FOOD_CALORIES)));
                food.setFoodHouseholdServing(cursor.getInt(cursor.getColumnIndex(FOOD_HOUSEHOLD_SERVING)));
                food.setFoodHouseholdUnit(cursor.getString(cursor.getColumnIndex(FOOD_HOUSEHOLD_UNIT)));
                food.setServingSize(cursor.getInt(cursor.getColumnIndex(FOOD_METRIC_SERVING)));
                food.setFoodMetricServingUnit(cursor.getString(cursor.getColumnIndex(FOOD_METRIC_SERVING_UNIT)));
                food.setFoodManufacturer(cursor.getString(cursor.getColumnIndex(FOOD_MANUFACTURER)));
                food.setSourceDB(cursor.getString(cursor.getColumnIndex("SourceDB")));

            } while (cursor.moveToNext());
        }

        db.close();
        return food;
    };









}

package com.example.foodfight;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHandler extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "foods.db";
    public static final String TABLE_NAME =  "food_library";
    public static final String COL_1 =  "ID";
    public static final String COL_2 =  "name";
    public static final String COL_3 =  "manufacturer";
    public static final String COL_4 =  "calories";
    public static final String COL_5 =  "hh_serving_size";
    public static final String COL_6 =  "hh_serving_unit";
    public static final String COL_7 =  "serving_size";
    public static final String COL_8 =  "serving_unit";
    public static final String COL_9 =  "upc";
    public static final String COL_10 =  "calories";


    public DatabaseHandler(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
        //when this constructure this database will be created

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (ID INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void GetMeal(){
        //gets information for a meal
    }

    public void DailyCalories(){
        //gets the calories for a day
    }

    public void AddFood(){
        //adds a food to the database
    }

    public void GetFood(){
        //gets a list of foods from the database
    }

    public void AddMeal(){
        // adds a new meal and connects foods to them
    }


}

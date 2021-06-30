package com.example.foodfight;


import java.sql.*;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public void onCreate(SQLiteDatabase db) {
        //adds tables to database

        /* code for the database tables
        CREATE TABLE [meal] ([Date] TEXT,[Meal] INTEGER,[Profile] INTEGER,[meal_id] INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,FOREIGN KEY([meal_id]) REFERENCES [food_meal]([meal_id]) ON DELETE CASCADE)

        CREATE TABLE [food] ([food_id] INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,[Name] TEXT,[Calories] INTEGER,[Serving_hh] FLOAT,[Serving_hh_units] TEXT,[serving_m] FLOAT,[serving_m_unit] TEXT,[SourceDB] TEXT,[picture_link] TEXT,FOREIGN KEY([food_id]) REFERENCES [food_meal]([food_id]) ON DELETE CASCADE)

        CREATE TABLE [meal_food] ([food_meal] INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,[servings_hh] FLOAT NOT NULL,[meal_id] INTEGER NOT NULL,[food_id] INTEGER NOT NULL)
         */

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //Dumps everything and creates a new database
        db.execSQL("DROP TABLE IF EXISTS meal" );
        db.execSQL("DROP TABLE IF EXISTS food" );
        db.execSQL("DROP TABLE IF EXISTS meal_food" );

        onCreate(db);


    }
}

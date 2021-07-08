package com.example.foodfight;

/* The main activity screen shows progress bars for daily and weekly calorie
 * counts against user goals.  Buttons at the bottom of the screen navigate to
 * the Meals, Trend and UserProfile activities
 */

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    // TODO: DatabaseHandler dbNameHere;
    SQLiteDatabase dbFood;
    DatabaseHandler db;
    ApiHandler foodSearcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //TODO: dbNameHere = new DatabaseHandler(this);
        SQLiteDatabase dbFood = openOrCreateDatabase("food", MODE_PRIVATE, null);
        db = new DatabaseHandler(this);
//            // TODO: CREATE / INITIALIZE THE DATABASE
//        // is the database created already
//        boolean db.VerifyExistance()
//            true = toast "food db loaded";
//            false = toast "loading food db";
        // if (Datat)
        //  TODO: VERIFY IF THE DATABASE FILE EXISTS AND IF FOOD TABLE IS POPULATED
        // TODO: IF FOOD TABLE IS NOT POPULATED, LOAD FOOD LIBRARY
        // Pull SharedPreferences and check "name" for default value
        // If default then auto-nav to acUserProfile to get set up
        SharedPreferences sp = getSharedPreferences("profile", Context.MODE_PRIVATE);
        String name = sp.getString("name","Your name");
        if (name.equalsIgnoreCase("Your name")) {
            Intent intent = new Intent(this, acUserProfile.class);
            startActivity(intent);

        }
        // VERIFIES THE DATABASE IS THERE
        // TODO: REMOVE THIS FOR FINAL CODE
        FoodItem apple = new FoodItem("apple",1,150);
        db.AddFood(apple);
        boolean test = doesDatabaseExist(this,"food.db");
        if (!test) {
            Toast.makeText(MainActivity.this,"DB does not Exist", Toast.LENGTH_LONG).show();

        } else {
            Toast.makeText(MainActivity.this,"WAHOOO! DB Exists!", Toast.LENGTH_LONG).show();
        }
//        List<String> foodList = List.newArrayList["Cuban Sandwich", "Jell-o","mofongo", "tacos", "life cereal"];
//        for (int i = 0; i < ; i++) {
//
//        }
//        ArrayList<List> initSearch = foodSearcher.NutriSearch();
        //String jsonFood = JsonReader.getJsonFromAssets(this,"FoodLibrary.json");
//        try {
//            FileReader reader = new FileReader("FoodLibrary.json");
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }

    }


    private static boolean doesDatabaseExist(Context context, String dbName) {
        File dbFile = context.getDatabasePath(dbName);
        return dbFile.exists();
    }



    @Override
    protected void onResume() {
        super.onResume();
        // When MainActivity resumes, pull shared preferences for: name, calorie goal,
        // current daily calorie count, current weekly calorie count
        // Update progress bars
        Log.i("[FoodFight]","Main Activity resumes");
        SharedPreferences sp = getSharedPreferences("profile", Context.MODE_PRIVATE);
        String name = sp.getString("name","Your name");

        int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        String msg;
        if (hour < 6) {
            msg = "Wow, "+name+", you're up early!";
        } else if (hour <12) {
            msg = "Good morning, "+name+".";
        } else if (hour < 18) {
            msg = "Good afternoon, "+name+".";
        } else {
            msg = "Good evening, "+name+".";
        }
        TextView greeting = findViewById(R.id.textGreeting);
        greeting.setText(msg);

        // Set the progress bars and captions
        String dailyGoal = sp.getString("dailyGoal", "2222");
        String dailyCurrent = sp.getString("dailyCurrent", "444");
        String weeklyCurrent = sp.getString("weeklyCurrent", "10000");

        int DG = Integer.parseInt(dailyGoal);
        int WG = DG * 7;
        int DC = Integer.parseInt(dailyCurrent);
        int WC = Integer.parseInt(weeklyCurrent);

        ProgressBar dailyBar = findViewById(R.id.barDaily);
        dailyBar.setMax(DG);
        dailyBar.setProgress(DC);

        ProgressBar weeklyBar = findViewById(R.id.barWeekly);
        weeklyBar.setMax(WG);
        weeklyBar.setProgress(WC);

        TextView dailyCaption = findViewById(R.id.tvDaily);
        String dCap = dailyCurrent + " / " + dailyGoal;
        dailyCaption.setText(dCap);

        TextView weeklyCaption = findViewById(R.id.tvWeekly);
        String wCap = weeklyCurrent + " / " + WG;
        weeklyCaption.setText(wCap);
    }

    // Called when user taps the Meals button
    public void btnMeals(View view) {
        Intent intent = new Intent(this, acMeals.class);
        Log.i("FF_Main","Launching acMeals");
        startActivity(intent);
    }

    // Called when user taps the User Profile button
    public void btnTrend(View view) {
        Intent intent = new Intent(this, acTrend.class);
        startActivity(intent);

        // TODO: Pass current date for default trend
    }

    // Called when user taps the User Profile button
    public void btnProfile(View view) {
        Intent intent = new Intent(this, acUserProfile.class);
        startActivity(intent);
    }



}
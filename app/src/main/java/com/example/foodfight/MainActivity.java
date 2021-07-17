package com.example.foodfight;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * The main activity screen shows progress bars for daily and weekly calorie
 * counts against user goals.  Buttons at the bottom of the screen navigate to
 * the Meals, Trend and UserProfile activities
 */
public class MainActivity extends AppCompatActivity {
    DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* check if the user has used the app for the
        first time by checking the profile set up.*/
        // Pull SharedPreferences and check "name" for default value
        // If default then auto-nav to acUserProfile to get set up
        SharedPreferences sp = getSharedPreferences("profile", Context.MODE_PRIVATE);
        String name = sp.getString("name","Your name");
        if (name.equalsIgnoreCase("Your name")) {
            Intent intent = new Intent(this, acUserProfile.class);
            startActivity(intent);
        }

        // VERIFIES THE DATABASE IS THERE
        // SQLiteDatabase dbFood = openOrCreateDatabase("food", MODE_PRIVATE, null);
        db = new DatabaseHandler(this);
        //CREATE / INITIALIZE THE DATABASE
        //VERIFY IF THE DATABASE FILE EXISTS AND IF FOOD TABLE IS POPULATED
        // TODO: IF FOOD TABLE IS NOT POPULATED, LOAD FOOD LIBRARY
        // TODO: REMOVE THIS FOR FINAL CODE

        FoodItem apple = new FoodItem(1,"apple",150);
        FoodItem apple2 = new FoodItem(569,"1% LOWFAT MILK",88,240,"ml",1,"cup","Target Stores");
        db.AddFood(apple);
        db.AddFood(apple2);

        FoodLibraryCreator foodLib = new FoodLibraryCreator();
        foodLib.libCreator(this);

        File dbFile = this.getDatabasePath("food.db");
        if (dbFile.exists()) {
            Log.i("FF_Main_DBLoad","WAHOO! DB Exists!");
        } else {
            Log.i("FF_Main_DBLoad","NOT GOOD: DB does not exist!");
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onResume() {
        super.onResume();
        // When MainActivity resumes, pull shared preferences for: name, calorie goal,
        // current daily calorie count, current weekly calorie count
        // Update progress bars
        Log.i("[FoodFight]", "Main Activity resumes");
        SharedPreferences sp = getSharedPreferences("profile", Context.MODE_PRIVATE);
        String name = sp.getString("name", "Your name");

        //customize message to fit the time of dame
        int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        String msg;
        if (hour < 6) {
            msg = "Wow, " + name + ", you're up early!";
        } else if (hour < 12) {
            msg = "Good morning, " + name + ".";
        } else if (hour < 18) {
            msg = "Good afternoon, " + name + ".";
        } else {
            msg = "Good evening, " + name + ".";
        }
        TextView greeting = findViewById(R.id.textGreeting);
        greeting.setText(msg);

        //weekly goal stuff

        Calendar dayThing = Calendar.getInstance();
        Date date = dayThing.getTime();
        SimpleDateFormat df = new SimpleDateFormat("MM-dd-yyyy", Locale.getDefault());
        int dayCalories = calsDay(df.format(date));
        int weeklyCalories = weekCaloiries();
        //put data form the user into the shared preferences
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("weeklyCurrent", (Integer.toString(weeklyCalories)));
        editor.putString("dailyCurrent", (Integer.toString(dayCalories)));
        //commit changes
        editor.commit();
        Log.d("weekly", "cals are: " + String.valueOf(weeklyCalories));


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

        //if (dailygoal - 1000  >= current daily total) {tvDailyWords
        //   then display "you're doing great today on your goal!}
        // else if (dailygoal -250 >= current daily total ){ you're getting close} else { " you've exceeded your goal for the day}
        String message = "";
        float currentStatus = Float.parseFloat(dailyCurrent) / Float.parseFloat(dailyGoal);
        if (currentStatus >= 1.0) {
            message = "You're gonna stop now, right?";
        } else if (currentStatus >= 0.9) {
            message = "Slow down. You're getting close!";
        } else if (currentStatus >= 0.8) {
            message = "Starting to feel full now?";
        } else if (currentStatus >= 0.25 && currentStatus < 0.8) {
            message = "You Got This!";
        } else
            message = "You're allowed to eat a little more";

        TextView messageView = findViewById(R.id.tvDailyWords);
        messageView.setText(message);


    }

    // Called when user taps the Meals button
    public void btnMeals(View view) {
        Intent intent = new Intent(this, acMeals.class);
        Log.i("FF_Main","Launching acMeals");
        startActivity(intent);
    }

    // Called when user taps the User Profile button
    // This is a stretch part.
    public void btnTrend(View view) {
        Intent intent = new Intent(this, acTrend.class);
        // TODO: Pass current date for default trend
        startActivity(intent);

    }

    // Called when user taps the User Profile button
    public void btnProfile(View view) {
        Intent intent = new Intent(this, acUserProfile.class);
        startActivity(intent);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public int weekCaloiries(){
        int totalCals = 0;

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0); // ! clear would not reset the hour of day !
        cal.clear(Calendar.MINUTE);
        cal.clear(Calendar.SECOND);
        cal.clear(Calendar.MILLISECOND);

        for (int i = 0; i < 7; i++){
            //get date object
            cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek() + i);
            Date date = cal.getTime();
            SimpleDateFormat df = new SimpleDateFormat("MM-dd-yyyy", Locale.getDefault());
            //get total calories for the day
            //add to week calories
            totalCals += calsDay(df.format(date));
            Log.i("date_acMeals", "");


        }

        //list of meal names


        return totalCals;
    }
    public int calsDay(String date){
        ArrayList<String> mealNames = new ArrayList<>();
        mealNames.add(MealsEnum.Breakfast.toString());
        mealNames.add(MealsEnum.Snack1.toString());
        mealNames.add(MealsEnum.Lunch.toString());
        mealNames.add(MealsEnum.Snack2.toString());
        mealNames.add(MealsEnum.Dinner.toString());
        mealNames.add(MealsEnum.Snack3.toString());
        //list for setting total calories

        int dayCalories = 0;
        //for list of meal names in current date get total calories and display in corresponding text view
        for (int meal_name = 0; meal_name < mealNames.size(); meal_name++) {
            String mealName = mealNames.get(meal_name);

            DatabaseHandler db = new DatabaseHandler(getApplicationContext());
            db.CreateMeal(date, mealName, 0);

            MealItem mealItem = db.GetMeal(date, mealName);

            Log.i("FF_acMeals", mealItem.ID + "|" + mealItem.date + "|" + mealItem.getMealName());

            ArrayList<List> ids = db.getFoodList(mealItem.ID);
            int calories = 0;
            for (int i = 0; i < ids.size(); i++) {
                FoodItem testItem = db.getFoodItemById((int) ids.get(i).get(0));
                Log.d("FF_acMeals:GetFoodItem", testItem.getName());

                //update calories total
                calories += testItem.getCalories() * ((int) ids.get(i).get(1));
            }
            //update calories for the day
            dayCalories += calories;
        }
        return dayCalories;
    }

}
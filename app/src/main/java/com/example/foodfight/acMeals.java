package com.example.foodfight;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;


/**
 * This Activity displays the meals that can be reviewed or edited by the user.
 * The display at the top showing the actual date or the selected date.
 * The select another day button takes opens acCalendar for the user to select a date
 * and will pass it back th new date on returning to this screen.
 */
public class acMeals extends AppCompatActivity {
    CalendarHandler calendarHandler = new CalendarHandler();
    private TextView dateView;
    private String selectedDate;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meals);
        // Set dateView to the system date if the user has not selected a date on the calendar.
        Log.i("FF_Meals","Get Date");
        selectedDate = calendarHandler.getSystemDate();
//        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyy", Locale.getDefault());
//        Calendar rightNow = Calendar.getInstance();

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onResume() {
        super.onResume();
        dateView = findViewById(R.id.tvDate);
        String msg = "Calories for " + selectedDate;
        dateView.setText(msg);
        Log.i("FF_Meals","Set date on screen");

        //TODO: get calories per mealType for display and calc total calories for day

        //list of meal names
        ArrayList<String> mealNames = new ArrayList<>();
        mealNames.add("Breakfast");
        mealNames.add("Snack1");
        mealNames.add("Lunch");
        mealNames.add("Snack2");
        mealNames.add("Dinner");
        mealNames.add("Snack3");
        //list for setting total calories
        ArrayList<TextView> textViews = new ArrayList<>();
        textViews.add(findViewById(R.id.tvBreakfast));
        textViews.add(findViewById(R.id.tvSnack1));
        textViews.add(findViewById(R.id.tvLunch));
        textViews.add(findViewById(R.id.tvSnack2));
        textViews.add(findViewById(R.id.tvDinner));
        textViews.add(findViewById(R.id.tvSnack3));



        int dayCalories = 0;
        //for list of meal names in current date get total calories and display in corresponding text view
        for (int meal_name = 0; meal_name < mealNames.size(); meal_name++) {
            String mealName = mealNames.get(meal_name);

            DatabaseHandler db = new DatabaseHandler(getApplicationContext());
            db.CreateMeal(selectedDate, mealName, 0);

            MealItem mealItem = db.GetMeal(selectedDate, mealName);

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
            //display calories to screen
            TextView mealCals = textViews.get(meal_name);
            mealCals.setText(Integer.toString(calories));
        }

        String CurrentDay = calendarHandler.getSystemDate();
        if (selectedDate.equals(CurrentDay)) {
            //put data form the user into the shared preferences
            SharedPreferences sp = getSharedPreferences("profile", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("dailyCurrent", (Integer.toString(dayCalories)));
            //commit changes
            editor.commit();
        }

        //display calories for the day
        TextView dailyCals = findViewById(R.id.tvTotal);
        dailyCals.setText(Integer.toString(dayCalories));

    }

    // Called when user taps Select Another Day
    public void getAnotherDay(View view) {
        Intent intent = new Intent(this, acCalendar.class);
        startActivityForResult(intent, 1);
    }

    // Set the date to the one passed from the acCalendar
    // RECEIVER OF INTENT from acCalendar
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
                selectedDate = data.getStringExtra("result");
        }
    }

    // Called when user taps the Breakfast button
    public void btnBreakfast(View view) {
        Intent intent = new Intent(this, acFoodList.class);
        intent.putExtra("MealDate",selectedDate);
        intent.putExtra("MealName","Breakfast");
        startActivity(intent);
    }

    // Called when user taps the Snack 1 button
    public void btnSnack1(View view) {
        Intent intent = new Intent(this, acFoodList.class);
        intent.putExtra("MealDate",selectedDate);
        intent.putExtra("MealName","Snack1");
        startActivity(intent);
    }

    // Called when user taps the Lunch button
    public void btnLunch(View view) {
        Intent intent = new Intent(this, acFoodList.class);
        intent.putExtra("MealDate",selectedDate);
        intent.putExtra("MealName","Lunch");
        startActivity(intent);
    }

    // Called when user taps the Snack 2 button
    public void btnSnack2(View view) {
        Intent intent = new Intent(this, acFoodList.class);
        intent.putExtra("MealDate", selectedDate);
        intent.putExtra("MealName","Snack2");
        startActivity(intent);
    }

    // Called when user taps the Dinner button
    public void btnDinner(View view) {
        Intent intent = new Intent(this, acFoodList.class);
        intent.putExtra("MealDate",selectedDate);
        intent.putExtra("MealName","Dinner");
        startActivity(intent);
    }

    // Called when user taps the Snack 3 button
    public void btnSnack3(View view) {
        Intent intent = new Intent(this, acFoodList.class);
        intent.putExtra("MealDate",selectedDate);
        intent.putExtra("MealName","Snack3");
        startActivity(intent);
    }

}
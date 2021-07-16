package com.example.foodfight;

import android.content.Context;

public class FoodLibraryCreator {
    DatabaseHandler db;

    public void libCreator(Context context) {
        db = new DatabaseHandler(context);
        FoodItem a98670  = new FoodItem(98670, "CRISPY CRINKLE CUT FRIES" ,138,84, "g" ,20, "2inch PIECES","Target Stores"); db.AddFood(a98670);
        FoodItem a98660  = new FoodItem(98660, "CRISPY SHOESTRING FRIES POTATOES" ,141,84, "g" ,40, "2inch PIECES","Target Stores"); db.AddFood(a98660);
        FoodItem a98642  = new FoodItem(98642, "CRISPY WAFFLE CUT FRIES FRENCH DRIED POTATOES" ,257,84, "g" ,13, "2inch PIECES","Target Stores"); db.AddFood(a98642);
//
    }
}

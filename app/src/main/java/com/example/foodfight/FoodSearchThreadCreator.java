package com.example.foodfight;

import android.os.StrictMode;
import java.io.IOException;

/**
 * Creates the thread needed to display the list of items into the search results on acAddFood
 */
public class FoodSearchThreadCreator {

    public FoodSearchThreadCreator(acAddFood acAddFood, String foodSearch) throws IOException {
        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
            new ApiHandler(acAddFood, foodSearch).run();
        }
    }
}

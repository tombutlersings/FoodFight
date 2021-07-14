package com.example.foodfight;

import android.os.StrictMode;
import android.widget.Toast;

import java.io.IOException;

public class FoodSearchThreadCreator {


    public FoodSearchThreadCreator(acAddFood acAddFood, String foodSearch) throws IOException {
        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
            //your codes here
            new ApiHandler(acAddFood, foodSearch).run();
            Toast.makeText(acAddFood,"Search Button Clicked", Toast.LENGTH_LONG).show();
        }
    }
    }
//
//    public void onViewCreated(View view, Bundle savedInstanceState) throws IOException {
//        int SDK_INT = android.os.Build.VERSION.SDK_INT;
//        if (SDK_INT > 8)
//        {
//            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
//                    .permitAll().build();
//            StrictMode.setThreadPolicy(policy);
//            //your codes here
//            new ApiHandler(view, savedInstanceState);
//            Toast.makeText(acAddFood.this,"Search Button Clicked", Toast.LENGTH_LONG).show();
//        }
//    }
//}

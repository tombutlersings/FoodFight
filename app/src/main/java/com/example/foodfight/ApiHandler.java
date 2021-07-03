package com.example.foodfight;

// TODO extends Thread or Runnable? -Tom 8-jun-21
// TODO: will receive Strings from AddFoodHandler
// This class may also handle calls to the dictionary?

import android.app.Activity;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApiHandler {
    Activity activityName;
    String foodSearch;

    public static Map<String, Object> jsonToMap(String str) {
        Map<String, Object> map = (Map) (new Gson()).fromJson(str, (new TypeToken<HashMap<String, Object>>() {
        }).getType());
        return map;
    }

    public ApiHandler(Activity activityName, String foodSearch){
        //code goes here
        this.activityName = activityName;
        this.foodSearch = foodSearch;

    }
    public ArrayList<List> NutriSearch(String foodSearch) throws IOException {
        Api nutriApi = new Api();
        String nutriUrl = nutriApi.NutriNameSearch(foodSearch);
        Map foodCall = nutriApi.NutriApiEngine(nutriUrl);
        //System.out.println(foodCall);
        ArrayList apiListOfResults = new ArrayList<ArrayList>();
        ArrayList<LinkedTreeMap> NutritioixBrandedFoodResults = (ArrayList<LinkedTreeMap>) foodCall.get("branded");
        for (int i = 0; i < NutritioixBrandedFoodResults.size(); i++) {
            ArrayList apiSearchResults = new ArrayList<String>();
            System.out.println((i + 1) + ". " + NutritioixBrandedFoodResults.get(i).get("food_name"));
            String food_name = (String) NutritioixBrandedFoodResults.get(i).get("food_name");
            apiSearchResults.add(food_name);
            System.out.println("  By:" + NutritioixBrandedFoodResults.get(0).get("brand_name"));

            String manufacturer = (String) NutritioixBrandedFoodResults.get(0).get("brand_name");
            apiSearchResults.add(manufacturer);

            System.out.println("  Calories:" + NutritioixBrandedFoodResults.get(0).get("nf_calories"));
            int calories = (int) NutritioixBrandedFoodResults.get(0).get("nf_calories");
            apiSearchResults.add(calories);
            apiListOfResults.add(apiSearchResults);
        }
        return apiListOfResults;
    }



    public void apiThreadCreator(){
        //
        // manage the creation of threads and calls at the same time
        // possible extension of runnable


        /**
        This code should work to get the activity that you are calling this from to pass into the ApiHandler.

        private Activity currentActivity = null;
        public Activity getCurrentActivity(){
              return currentActivity;
        }
        public void setCurrentActivity(Activity currentActivity){
              this.currentActivity = currentActivity;
        }
         */
        ApiThread newThread = new ApiThread(activityName);
        new Thread(newThread).start();




    }

}

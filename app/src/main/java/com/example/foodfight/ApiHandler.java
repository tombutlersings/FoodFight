package com.example.foodfight;

// TODO extends Thread or Runnable? -Tom 8-jun-21
// TODO: will receive Strings from AddFoodHandler
// This class may also handle calls to the dictionary?

import android.app.Activity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApiHandler implements Runnable {
    Activity activityName;
    String foodSearch;
    Activity activity;
    ArrayList<List> foodSearchResults;

    public static Map<String, Object> jsonToMap(String str) {
        Map<String, Object> map = (Map) (new Gson()).fromJson(str, (new TypeToken<HashMap<String, Object>>() {
        }).getType());
        return map;
    }

    /**  STEP 2 - uses the foodsearch to get results
     * sets the results list to global foodSearchResults
     * Then run() uses it to open a responding thread
     * @param activityName
     * @param foodSearch
     */
    public ApiHandler(acAddFood activityName, String foodSearch) throws IOException {
        //code goes here
        this.activityName = activityName;
        this.foodSearch = foodSearch;

        ArrayList<List> foodSearchResultsInternal = NutriSearch(foodSearch);
        if (!(foodSearchResultsInternal.size() >= 0)) {
        } else {Toast.makeText(this.activityName,"Step 2 Reached!", Toast.LENGTH_LONG).show();}
        //TODO: Figure out how to get foodSearchResults into a different thread and return the results to the acAddFood Search Results TextView
        //        Thread thread = new Thread();
        //        thread.start();
        foodSearchResults = foodSearchResultsInternal;
//        new FoodResultThreadCreator(foodSearchResults);


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

            // FOOD NAME
            String food_name = (String) NutritioixBrandedFoodResults.get(i).get("food_name");
            apiSearchResults.add(food_name);

            // MANUFACTURER
            String manufacturer = (String) NutritioixBrandedFoodResults.get(0).get("brand_name");
            apiSearchResults.add(manufacturer);

            // CALORIES
            double calories = (double) NutritioixBrandedFoodResults.get(0).get("nf_calories");
            apiSearchResults.add(calories);

            // SERVING UNIT
            String serving_unit = (String) NutritioixBrandedFoodResults.get(0).get("serving");
            apiSearchResults.add(serving_unit);

            // SERVING SIZE
            double serving_size = (double) NutritioixBrandedFoodResults.get(0).get("serving_qty");
            apiSearchResults.add(serving_size);

//            LinkedTreeMap photo = (LinkedTreeMap) NutritioixBrandedFoodResults.get(0).get("photo");
//            String _photo = (String) photo.get("thumb");
//            apiSearchResults.add(_photo);


            apiListOfResults.add(apiSearchResults);

        }
        return apiListOfResults;
    }

    @Override
    public void run() {
        final Activity refActivity = activityName;
        if ( refActivity != null) {
            refActivity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    refActivity.runOnUiThread(new Runnable() {
                        public void run() {
                            //everything we want to change in the user interface goes here
                            // TODO: send apiListOfResults to the screen
                            //                                ArrayList<List> foodlist = NutriSearch(foodSearch);
                            // TextView searchStatus = refActivity.findViewById(R.id.searchStatusTitle);
                            // searchStatus.setText("Search complete!");
                            ArrayList<String> testList = new ArrayList<>();
                            for (int i = 0; i < foodSearchResults.size(); i++) {
                                // todo everything has the same calories right now


                                // TODO: also pass food object info for each line in case they need it to be created into a food object

                                // below is creating string objects of just the 0) food name 1) manufacturer 2) calories
                                List foodResultOne = foodSearchResults.get(i);
                                String foodNameOne = (String) foodResultOne.get(0);
                                String foodManufacturer = (String) foodResultOne.get(1);
                                String foodCalories = ((Double) foodResultOne.get(2)).toString();
                                String foodServingUnit = (String) foodResultOne.get(3);
                                String foodServingSize = ((Double) foodResultOne.get(4)).toString();
                                // TODO: add household serving size
                                // TODO: add household serving unit
                                // TODO: add photo link
                                // TODO: add household serving unit
                                String line = ("Name: " + foodNameOne + "  by: " + foodManufacturer + "   Calories:  " + foodCalories);
                                // TODO: return some sort of list -
                                testList.add(line);
                            }
                            // TODO Make each item a button that refers to an object (creates the object and then can


//                            testList.add("one");ww
//                            testList.add("two");

                            // todo: IMPORTANT
                            // POPULATING THE SEARCH RESULTS
                            ArrayAdapter<String> itemsAdapter = new ArrayAdapter(refActivity, android.R.layout.simple_list_item_1, testList);
                            ListView listView = (ListView) refActivity.findViewById(R.id.searchResults);
                            listView.setAdapter(itemsAdapter);

                            ArrayList<List> newList = new ArrayList<List>();
                            for (int i = 0; i < foodSearchResults.size(); i++) {
                                ArrayList subList = new ArrayList();
                                List foodResultOne = foodSearchResults.get(i);
                                subList.add((String) foodResultOne.get(0));
                                subList.add((String) foodResultOne.get(1));
                                subList.add(((Double) foodResultOne.get(2)).toString());
                                subList.add((String) foodResultOne.get(3));
                                subList.add(((Double) foodResultOne.get(4)).toString());
                                newList.add(subList);
                            }
                            Goals.setFoodSearchList(newList);

                            /**
                             * STEP 3 - The responding thread passes back the results to the acAddFood screen
                             * Populates the list view with
                             */
                            // Changing the status area to show 'completed search'




                        }
                    });
                }
            });
        }
    }

}




//    public void apiThreadCreator(){
//        //
//        // manage the creation of threads and calls at the same time
//        // possible extension of runnable
//
//
//        /**
//        This code should work to get the activity that you are calling this from to pass into the ApiHandler.
//
//        private Activity currentActivity = null;
//         public Activity getCurrentActivity(){
//         return currentActivity;
//         }
//         public void setCurrentActivity(Activity currentActivity){
//         this.currentActivity = currentActivity;
//         }
//         */
//        ApiThread newThread = new ApiThread(activityName);
//        new Thread(newThread).start();
//
//
//
//
//    }


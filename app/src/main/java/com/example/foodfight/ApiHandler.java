package com.example.foodfight;

import android.app.Activity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.internal.LinkedTreeMap;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ApiHandler implements Runnable {
    Activity activityName;
    String foodSearch;
//    Activity activity;
    ArrayList<List> foodSearchResults;

//    public static Map<String, Object> jsonToMap(String str) {
//        Map<String, Object> map = (Map) (new Gson()).fromJson(str, (new TypeToken<HashMap<String, Object>>() {
//        }).getType());
//        return map;
//    }

    public ApiHandler(acAddFood activityName, String foodSearch) throws IOException {

        this.activityName = activityName;
        this.foodSearch = foodSearch;

        ArrayList<List> foodSearchResultsInternal = NutriSearch(foodSearch);
        if (!(foodSearchResultsInternal.size() >= 0)) {
        } else {
            Toast.makeText(this.activityName,"Step 2 Reached!", Toast.LENGTH_LONG).show();
        }
        foodSearchResults = foodSearchResultsInternal;
    }
    public ArrayList<List> NutriSearch(String foodSearch) throws IOException {
        Api nutriApi = new Api();
        String nutriUrl = nutriApi.NutriNameSearch(foodSearch);
        Map foodCall = nutriApi.NutriApiEngine(nutriUrl);

        ArrayList apiListOfResults = new ArrayList<ArrayList>();
        ArrayList<LinkedTreeMap> nutritionixBrandedFoodResults = (ArrayList<LinkedTreeMap>) foodCall.get("branded");
        for (int i = 0; i < nutritionixBrandedFoodResults.size(); i++) {
            ArrayList apiSearchResults = new ArrayList<String>();
            System.out.println((i + 1) + ". " + nutritionixBrandedFoodResults.get(i).get("food_name"));

            // FOOD NAME
            String food_name = (String) nutritionixBrandedFoodResults.get(i).get("food_name");
            apiSearchResults.add(food_name);

            // MANUFACTURER
            String manufacturer = (String) nutritionixBrandedFoodResults.get(i).get("brand_name");
            apiSearchResults.add(manufacturer);

            // CALORIES
            double calories = (double) nutritionixBrandedFoodResults.get(i).get("nf_calories");
            apiSearchResults.add(calories);

            // SERVING UNIT
            String serving_unit = (String) nutritionixBrandedFoodResults.get(i).get("serving_unit");
            apiSearchResults.add(serving_unit);

            // SERVING SIZE
            double serving_size = (double) nutritionixBrandedFoodResults.get(i).get("serving_qty");
            apiSearchResults.add(serving_size);

//            LinkedTreeMap photo = (LinkedTreeMap) nutritionixBrandedFoodResults.get(0).get("photo");
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
                            // sends everything back to user interface
                            ArrayList<String> testList = new ArrayList<>();
                            ArrayAdapter<String> itemsAdapter = new ArrayAdapter(refActivity, android.R.layout.simple_list_item_1, testList);
                            ListView listView = (ListView) refActivity.findViewById(R.id.searchResults);
                            listView.setAdapter(itemsAdapter);
                            ArrayList<List> newList = new ArrayList<List>();
                            ArrayList subList = new ArrayList();

                            for (int i = 0; i < foodSearchResults.size(); i++) {

                                //Parses out food info from list of lists
                                List foodResultOne = foodSearchResults.get(i);
                                String foodNameOne = (String) foodResultOne.get(0); // food name
                                subList.add(foodNameOne);

                                String foodManufacturer = (String) foodResultOne.get(1); // manufacturer
                                subList.add(foodManufacturer);

                                String foodCalories = ((Double) foodResultOne.get(2)).toString(); // calories
                                subList.add(foodCalories);

                                String foodServingUnit = (String) foodResultOne.get(3); // serving unit
                                subList.add(foodServingUnit);

                                String foodServingSize = ((Double) foodResultOne.get(4)).toString(); // serving size
                                subList.add(foodServingSize);

                                String line = (
                                        "Name: " + foodNameOne + "\nBrand: " + foodManufacturer +
                                        "\n" + foodCalories + " calories per " + foodServingSize + "  " + foodServingUnit);

                                testList.add(line);
                                newList.add(subList);


                            }
// TODO: could the above iterator and below iterator be combined into one list or function?

                            Goals.setFoodSearchList(newList);
    }
                    });
                }
            });
        }
    }

}

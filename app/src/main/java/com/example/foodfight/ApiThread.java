//package com.example.foodfight;
//
//import android.app.Activity;
//
//import com.google.gson.internal.LinkedTreeMap;
//
//import java.io.IOException;
//import java.lang.ref.WeakReference;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
//public class ApiThread implements Runnable{
//    private WeakReference<Activity> activity;
//    public ApiThread(Activity activity) {
//        this.activity = new WeakReference<Activity>(activity);;
//    }
//
//    @Override
//
//    public void run() {
//        //whatever you need to do on the thread goes in hear
//        //TODO: Api Call Itself
//
//        }
////        public ArrayList<List> NutriSearch(String foodSearch) throws IOException {
////            Api nutriApi = new Api();
////            String nutriUrl = nutriApi.NutriNameSearch(foodSearch);
////            Map foodCall = nutriApi.NutriApiEngine(nutriUrl);
////            //System.out.println(foodCall);
////            ArrayList apiListOfResults = new ArrayList<ArrayList>();
////            ArrayList<LinkedTreeMap> NutritioixBrandedFoodResults = (ArrayList<LinkedTreeMap>) foodCall.get("branded");
////            for (int i = 0; i < NutritioixBrandedFoodResults.size(); i++) {
////                ArrayList apiSearchResults = new ArrayList<String>();
////                System.out.println((i + 1) + ". " + NutritioixBrandedFoodResults.get(i).get("food_name"));
////                String food_name = (String) NutritioixBrandedFoodResults.get(i).get("food_name");
////                apiSearchResults.add(food_name);
////                System.out.println("  By:" + NutritioixBrandedFoodResults.get(0).get("brand_name"));
////
////                String manufacturer = (String) NutritioixBrandedFoodResults.get(0).get("brand_name");
////                apiSearchResults.add(manufacturer);
////
////                System.out.println("  Calories:" + NutritioixBrandedFoodResults.get(0).get("nf_calories"));
////                int calories = (int) NutritioixBrandedFoodResults.get(0).get("nf_calories");
////                apiSearchResults.add(calories);
////                apiListOfResults.add(apiSearchResults);
////            }
////return apiListOfResults;
////        }
//
//        //this is to prevent errors if the thread gets interrupted.
//        final Activity refActivity = activity.get();
//        if ( refActivity != null) {
//            refActivity.runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    refActivity.runOnUiThread(new Runnable() {
//                        public void run() {
//                            //everything we want to change in the user interface goes here
//                        // TODO: send apiListOfResults to the screen
//
//
//                        }
//                    });
//                }
//            });
//        }
//
//
//
//    }
//}

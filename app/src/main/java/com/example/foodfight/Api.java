package com.example.foodfight;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

public class Api {

    public static Map<String, Object> jsonToMap(String str) {
        Map<String, Object> map = (Map) (new Gson()).fromJson(str, (new TypeToken<HashMap<String, Object>>() {
        }).getType());
        return map;
    }

//    public static int getTime() {
//        Date date = Calendar.getInstance().getTime();
//        DateFormat dateFormat = new SimpleDateFormat("ss");
//        String time = dateFormat.format(date);
//        int time2 = parseInt(time);
//        if (time2 % 2 == 0) {
//            return 0;
//        } else {
//
//            return 1;
//        }
//    }
//
//    public static String getApiKey(){
//        int time = getTime();
//        if (time==0){
//            String USDA_API_KEY = "1N9YQQ18X8tgeqD2qs14gtvo0ZNeFkk3bYUs6V06";
//            System.out.println("Using api key 1");
//            return USDA_API_KEY;
//        }else {
//            String USDA_API_KEY = "oMQt8MccUkatYncHPLhsLbTQldjBukgocgOTA6yl";
//            System.out.println("Using api key 2");
//            return USDA_API_KEY;
//        }
//    }
//
//    public String UsdaApiNameSearch(String foodSearch){
//        String USDA_API_KEY = getApiKey();
//        String NUM_RESULTS = "7";
//        // search by name and get back pages
//        String result = "https://api.nal.usda.gov/fdc/v1/foods/search?query=" + foodSearch + "&pageSize=" + NUM_RESULTS + "&api_key=" + USDA_API_KEY;
//
//        return result;
//    }
//
//    public String UsdaApiIdSearch(String foodSearch){
//        String USDA_API_KEY = "oMQt8MccUkatYncHPLhsLbTQldjBukgocgOTA6yl";
//        // search by ID
//
//        String result = "https://api.nal.usda.gov/fdc/v1/food/" + foodSearch + "?api_key=" + USDA_API_KEY;
//        return result;
//    }

    public String NutriNameSearch(String foodSearch){

        // search by ID
        if(foodSearch.contains(" ")){
            foodSearch = foodSearch.replace(" ", "+");
        }

        String result = "https://trackapi.nutritionix.com/v1/search/instant?query=" + foodSearch;
        return result;
    }
//
//    public static Map UsdaApiEngine(String urlString) throws IOException {
//        StringBuilder apiResults = new StringBuilder();
//        URL url = new URL(urlString);
//        URLConnection conn = url.openConnection();
//        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//        String line;
//        while ((line = rd.readLine()) != null) {
//            apiResults.append(line);
//        }
//        rd.close();
//
//        Gson gson = new Gson();
//        //System.out.println();
//        Map<String, Object> foodCall = jsonToMap(apiResults.toString());
//        return foodCall;
//    }

    public static Map NutriApiEngine(String urlString) throws IOException {
        StringBuilder apiResults = new StringBuilder();
        URL url = new URL(urlString);
        URLConnection conn = url.openConnection();
        conn.setRequestProperty("Content-Type","application/json");
        conn.setRequestProperty("x-app-id","38a0444c");
        conn.setRequestProperty("x-app-key","bba617b06a35637fac59d3a7859d1828");
//        conn.setRequestProperty("x-app-key","78426630616960d84d591193eb42201c");
        conn.setRequestProperty("x-remote-user-id","0");

        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        while ((line = rd.readLine()) != null) {
            apiResults.append(line);
        }
        rd.close();

        Gson gson = new Gson();
        //System.out.println();
        Map<String, Object> foodCall = jsonToMap(apiResults.toString());
        return foodCall;
    }


}

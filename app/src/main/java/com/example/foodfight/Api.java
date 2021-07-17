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

    public String NutriNameSearch(String foodSearch){
        if(foodSearch.contains(" ")){
            foodSearch = foodSearch.replace(" ", "+");
        }

        String result = "https://trackapi.nutritionix.com/v1/search/instant?query=" + foodSearch;
        return result;
    }

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
        Map<String, Object> foodCall = jsonToMap(apiResults.toString());
        return foodCall;
    }
}
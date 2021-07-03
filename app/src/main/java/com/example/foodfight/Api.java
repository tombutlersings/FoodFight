package com.example.foodfight;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static java.lang.Integer.parseInt;

public class Api {
    String apiAddressOne;
    String apiAddressTwo;
    String apiAddressThree;

    public static int getTime() {
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("ss");
        String time = dateFormat.format(date);
        int time2 = parseInt(time);
        if (time2 % 2 == 0) {
            return 0;
        } else {

            return 1;
        }
    }
    public static String getApiKey(){
        int time = getTime();
        if (time==0){
            String USDA_API_KEY = "1N9YQQ18X8tgeqD2qs14gtvo0ZNeFkk3bYUs6V06";
            System.out.println("Using api key 1");
            return USDA_API_KEY;
        }else {
            String USDA_API_KEY = "oMQt8MccUkatYncHPLhsLbTQldjBukgocgOTA6yl";
            System.out.println("Using api key 2");
            return USDA_API_KEY;
        }
    }
    public String UsdaApiNameSearch(String foodSearch){
        String USDA_API_KEY = getApiKey();
        String NUM_RESULTS = "7";
        // search by name and get back pages
        String result = "https://api.nal.usda.gov/fdc/v1/foods/search?query=" + foodSearch + "&pageSize=" + NUM_RESULTS + "&api_key=" + USDA_API_KEY;

        return result;
    }
    public String UsdaApiIdSearch(String foodSearch){
        String USDA_API_KEY = "oMQt8MccUkatYncHPLhsLbTQldjBukgocgOTA6yl";
        // search by ID

        String result = "https://api.nal.usda.gov/fdc/v1/food/" + foodSearch + "?api_key=" + USDA_API_KEY;
        return result;
    }
    public String NutriNameSearch(String foodSearch){

        // search by ID
        if(foodSearch.contains(" ")){
            foodSearch = foodSearch.replace(" ", "+");
        }

        String result = "https://trackapi.nutritionix.com/v1/search/instant?query=" + foodSearch;
        return result;
    }
}

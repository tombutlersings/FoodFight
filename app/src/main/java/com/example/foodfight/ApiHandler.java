package com.example.foodfight;

// TODO extends Thread or Runnable? -Tom 8-jun-21
// TODO: will receive Strings from AddFoodHandler
// This class may also handle calls to the dictionary?

import android.app.Activity;

public class ApiHandler {
    Activity activityName;

    public ApiHandler(Activity activityName){
        //code goes here
        this.activityName = activityName;

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

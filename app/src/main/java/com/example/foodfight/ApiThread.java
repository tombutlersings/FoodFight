package com.example.foodfight;

import android.app.Activity;

import java.lang.ref.WeakReference;

public class ApiThread implements Runnable{
    private WeakReference<Activity> activity;
    public ApiThread(Activity activity) {
        this.activity = new WeakReference<Activity>(activity);;
    }

    @Override

    public void run() {
        //whatever you need to do on the thread goes in hear
        //TODO: Api Call Itself


        //this is to prevent errors if the thread gets interrupted.
        final Activity refActivity = activity.get();
        if ( refActivity != null) {
            refActivity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    refActivity.runOnUiThread(new Runnable() {
                        public void run() {
                            //everything we want to change in the user interface goes here



                        }
                    });
                }
            });
        }



    }
}

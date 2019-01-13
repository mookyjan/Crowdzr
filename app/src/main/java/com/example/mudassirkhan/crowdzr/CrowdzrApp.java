package com.example.mudassirkhan.crowdzr;

import android.app.Application;
import android.content.Context;

/**
 * Created by muhammadrashid on 28/12/2017.
 */

public class CrowdzrApp extends Application {
    /**
     * Keeps a reference of the application context
     */
    private static Context sContext;

    private static boolean activityVisible;
    @Override
    public void onCreate() {
        super.onCreate();
//        AndroidNetworking.initialize(getApplicationContext());

        sContext = getApplicationContext();


      //  TypefaceUtil.overrideFont(getApplicationContext(), "SERIF", "font/HELR45W.ttf"); // font from assets: "assets/fonts/Roboto-Regular.ttf

//        AndroidNetworking.initialize(getApplicationContext());

    }

    public static Context getsContext(){
        return sContext;
    }

    public static boolean isActivityVisible() {
        return activityVisible;
    }

    public static void activityResumed() {
        activityVisible = true;
    }

    public static void activityPaused() {
        activityVisible = false;
    }

//    public static void activityDestroyed(){
//        activityVisible = false;
//    }


}

package com.example.mudassirkhan.crowdzr.api.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.mudassirkhan.crowdzr.CrowdzrApp;
import com.example.mudassirkhan.crowdzr.model.login.LoginResponse;
import com.google.gson.Gson;


/**
 * Created by Salahuddin on 2/1/17.
 */

//store user data
public class UserPrefs {
    private static final String USER_PREF = "user_pref";
    private static final String KEY_USER = "logged_in_user";
    private static final String KEY_USER_ID = "user_id";
    private static UserPrefs mInstance;
    private final SharedPreferences mPrefs;

    private UserPrefs(Context context) {
        if(context == null){
            context = CrowdzrApp.getsContext();
        }
        mPrefs = context.getApplicationContext().getSharedPreferences(USER_PREF, Context
                .MODE_PRIVATE);
    }

    public static UserPrefs get(Context context) {
        if (mInstance == null) {
            mInstance = new UserPrefs(context);
        }
        return mInstance;
    }


    public LoginResponse.User getLoggedInUser(){
        String obj = mPrefs.getString(KEY_USER, ""); //String value at 'key' or "" (empty String) if key not found
        LoginResponse.User user = new Gson().fromJson(obj, LoginResponse.User.class);
        return user;
    }

    public void logoutUser(){
        SharedPreferences.Editor editor = mPrefs.edit();
        editor.putString(KEY_USER,null); //clear user data
        editor.apply();
    }

    public void setLoggedInUser(LoginResponse.User user){
        Gson gson = new Gson();
        SharedPreferences.Editor editor = mPrefs.edit();
        editor.putString(KEY_USER,gson.toJson(user));
        editor.apply();

    }
    public int getUserId(){
        return mPrefs.getInt(KEY_USER_ID,-1);
    }
    public void setUserId(int id){
        SharedPreferences.Editor editor = mPrefs.edit();
        editor.putInt(KEY_USER_ID,id);
        editor.apply();
    }




}

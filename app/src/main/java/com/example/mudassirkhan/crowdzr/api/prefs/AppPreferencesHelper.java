package com.example.mudassirkhan.crowdzr.api.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.mudassirkhan.crowdzr.CrowdzrApp;
import com.example.mudassirkhan.crowdzr.util.AppConstants;

public class AppPreferencesHelper implements PreferencesHelper {

    private static final String PREF_KEY_ACCESS_TOKEN = "PREF_KEY_ACCESS_TOKEN";
    private static final String PREF_KEY_CURRENT_USER_ID = "PREF_KEY_CURRENT_USER_ID";
    private static final String PREF_KEY_CURRENT_USER_NAME = "PREF_KEY_CURRENT_USER_NAME";
    private static final String PREF_KEY_CURRENT_USER_PROFILE_PIC_URL = "PREF_KEY_CURRENT_USER_PROFILE_PIC_URL";

    private static final String PREF_KEY_USER_LOGGED_IN_MODE = "PREF_KEY_USER_LOGGED_IN_MODE";

    private final SharedPreferences mPrefs;
    private static volatile AppPreferencesHelper appPreferencesHelper;

    private AppPreferencesHelper(Context context) {
        mPrefs = context.getSharedPreferences(AppConstants.PREF_NAME, Context.MODE_PRIVATE);
    }

    public static AppPreferencesHelper get() {
        if (appPreferencesHelper == null) {
            synchronized (AccessTokenPrefs.class) {
                if (appPreferencesHelper == null) {
                    appPreferencesHelper = new AppPreferencesHelper(CrowdzrApp.getsContext());
                }
            }
        }
        return appPreferencesHelper;
    }

    @Override
    public String getAccessToken() {
        return mPrefs.getString(PREF_KEY_ACCESS_TOKEN, "");
    }

    @Override
    public void setAccessToken(String accessToken) {
        mPrefs.edit().putString(PREF_KEY_ACCESS_TOKEN, accessToken).apply();
    }

    @Override
    public String  getCurrentUserId() {
      //  long userId = mPrefs.getLong(PREF_KEY_CURRENT_USER_ID, AppConstants.NULL_INDEX);
        //return userId == AppConstants.NULL_INDEX ? null : userId;
        return mPrefs.getString(PREF_KEY_CURRENT_USER_ID, "");
    }

    @Override
    public void setCurrentUserId(String  userId) {
       // long id = userId == null ? AppConstants.NULL_INDEX : userId;
        mPrefs.edit().putString(PREF_KEY_CURRENT_USER_ID, "").apply();
    }


    @Override
    public String getCurrentUserName() {
        return mPrefs.getString(PREF_KEY_CURRENT_USER_NAME, "");
    }

    @Override
    public void setCurrentUserName(String userName) {
        mPrefs.edit().putString(PREF_KEY_CURRENT_USER_NAME, userName).apply();
    }

    @Override
    public String getCurrentUserProfilePicUrl() {
        return mPrefs.getString(PREF_KEY_CURRENT_USER_PROFILE_PIC_URL, "");
    }

    @Override
    public void setCurrentUserProfilePicUrl(String profilePicUrl) {
        mPrefs.edit().putString(PREF_KEY_CURRENT_USER_PROFILE_PIC_URL, profilePicUrl).apply();
    }
}

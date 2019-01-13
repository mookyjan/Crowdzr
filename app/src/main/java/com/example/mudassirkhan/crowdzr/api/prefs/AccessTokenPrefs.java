package com.example.mudassirkhan.crowdzr.api.prefs;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.example.mudassirkhan.crowdzr.CrowdzrApp;


/**
 * Created by muhammadrashid on 28/12/2017.
 */

/**
 * Manage access token preferences.
 */
public class AccessTokenPrefs {
    private static final String ACCESS_TOKEN_PREFS = "bolton_access_token_prefs";
    private static final String KEY_ACCESS_TOKEN = "key_access_token";

    private static volatile AccessTokenPrefs accessTokenPrefs;

    private final SharedPreferences mPrefs;

    public static AccessTokenPrefs get() {
        if (accessTokenPrefs == null) {
            synchronized (AccessTokenPrefs.class) {
                if (accessTokenPrefs == null) {
                    accessTokenPrefs = new AccessTokenPrefs();
                }
            }
        }
        return accessTokenPrefs;
    }

    private AccessTokenPrefs() {
        mPrefs = CrowdzrApp.getsContext().getSharedPreferences(ACCESS_TOKEN_PREFS, Context
                .MODE_PRIVATE);
    }

    public String getAccessToken(){
        return mPrefs.getString(KEY_ACCESS_TOKEN,"");
    }

    public void setAccessToken(String accessToken) {
        if (!TextUtils.isEmpty(accessToken)) {
            mPrefs.edit().putString(KEY_ACCESS_TOKEN, accessToken).apply();
        }

    }
    public void removeAccessToken() {
        if (!TextUtils.isEmpty(AccessTokenPrefs.get().getAccessToken())) {
            mPrefs.edit().putString(KEY_ACCESS_TOKEN, null).apply();
        }

    }
}

package com.example.mudassirkhan.crowdzr.api.prefs;

public interface PreferencesHelper {

    String getAccessToken();

    void setAccessToken(String accessToken);

//    String getCurrentUserEmail();
//
//    void setCurrentUserEmail(String email);

    String  getCurrentUserId();

    void setCurrentUserId(String  userId);

    String getCurrentUserName();

    void setCurrentUserName(String userName);

    String getCurrentUserProfilePicUrl();

    void setCurrentUserProfilePicUrl(String profilePicUrl);
}

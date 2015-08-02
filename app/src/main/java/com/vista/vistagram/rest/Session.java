package com.vista.vistagram.rest;

import android.content.Context;
import android.content.SharedPreferences;

import com.vista.vistagram.App;
import com.vista.vistagram.models.User;

/**
 * Saves user's session and informations to the storage.
 */
public class Session {
    private static final String PREF_NAME = "sessions";

    private static User me;
    private static SharedPreferences prefs;
    private static String accessToken, refreshToken;

    public static void init() {
        prefs = App.getContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        accessToken = prefs.getString("access_token", null);
        refreshToken = prefs.getString("refresh_token", null);

        if (accessToken != null) {
            // load user
            String userJson = prefs.getString("me", null);
            if (userJson != null) me = App.getGson().fromJson(userJson, User.class);
        }
    }

    public static boolean isLoggedIn() {
        return accessToken == null;
    }

    public static void saveSession(User user, String newAccessToken, String newRefreshToken) {
        me = user;
        accessToken = newAccessToken;
        refreshToken = newRefreshToken;

        prefs.edit()
                .putString("access_token", accessToken)
                .putString("refresh_token", refreshToken)
                .putString("me", App.getGson().toJson(me))
                .apply();

    }

    public static void refreshSession(String newAccessToken) {
        accessToken = newAccessToken;

        prefs.edit()
            .putString("access_token", newAccessToken)
            .apply();
    }

    public static void clearSession() {
        me = null;
        accessToken = refreshToken = null;
        prefs.edit().clear().apply();
    }

    public static User getMe() {
        return me;
    }

    public static String getAccessToken() {
        return accessToken;
    }

    public static String getRefreshToken() {
        return refreshToken;
    }
}

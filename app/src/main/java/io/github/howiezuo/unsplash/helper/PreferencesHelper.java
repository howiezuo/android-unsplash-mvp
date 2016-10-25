package io.github.howiezuo.unsplash.helper;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferencesHelper {

    private static final String NAME = "io.github.howiezuo.unsplash.name";
    private static final String TOKEN_KEY = "io.github.howiezuo.unsplash.token";

    private static PreferencesHelper sInstance;
    private final SharedPreferences mSharedPreferences;

    private PreferencesHelper(Context context) {
        mSharedPreferences = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
    }

    public static PreferencesHelper getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new PreferencesHelper(context);
        }
        return sInstance;
    }

    public void saveToken(String token) {
        mSharedPreferences.edit().putString(TOKEN_KEY, token).commit();
    }
    
}

package com.partnersincrime.foxdarkmaster.geekeventsmobileapp.Managers;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by foxdarkmaster on 19-06-2016.
 */
public class SPManager {
    private static final String TAG = "SPManager";

    private static final String KEY_EVENTS = "events";
    private static final String KEY_CATEGORIES = "categories";
    public static final String KEY_LOCATIONS = "locations";

    public static final String KEY_LOCALE = "key_locale";

    public static SharedPreferences getDSP(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static void setValue(Context context, String KEY, Object VALUE) {
        SharedPreferences.Editor editor = getDSP(context).edit();

        if (VALUE instanceof String){
            editor.putString(KEY,(String)VALUE);
        } else if (VALUE instanceof Integer){
            editor.putInt(KEY, (Integer)VALUE);
        } else if (VALUE instanceof Boolean){
            editor.putBoolean(KEY, (Boolean)VALUE);
        }

        editor.apply();
    }

    public static String getCategories(Context context){
        return getDSP(context).getString(KEY_CATEGORIES, null);
    }

    public static String getEvents(Context context){
        return getDSP(context).getString(KEY_EVENTS, null);
    }

    public static String getLocations(Context context){
        return getDSP(context).getString(KEY_LOCATIONS, null);
    }

    public static String getLocale(Context context){
        return getDSP(context).getString(KEY_LOCALE, "en");
    }
}

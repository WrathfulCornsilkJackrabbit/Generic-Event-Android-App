package com.partnersincrime.foxdarkmaster.geekeventsmobileapp.Managers;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by foxdarkmaster on 19-06-2016.
 */
public class SPManager {
    private static final String KEY_ACTIVITIES = "activities";
    private static final String KEY_LOCALE = "locale";

    // GENERAL
    public static SharedPreferences getDefaultSharedPreferences(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    // LANGUAGE
    public static void setLocale(final Context context, final String locale) {
        SharedPreferences.Editor editor = getDefaultSharedPreferences(context).edit();
        editor.putString(KEY_LOCALE, locale).apply();
    }

    public static String getLocale(final Context context) {
        return getDefaultSharedPreferences(context)
                .getString(KEY_LOCALE, null);
    }

    // ACTIVITIES
    public static void setActivities(final Context context, final String activitiesList) {
        SharedPreferences.Editor editor = getDefaultSharedPreferences(context).edit();
        editor.putString(KEY_ACTIVITIES, activitiesList).apply();
    }

    public static String getActivities(final Context context) {
        return getDefaultSharedPreferences(context)
                .getString(KEY_ACTIVITIES, null);
    }
}

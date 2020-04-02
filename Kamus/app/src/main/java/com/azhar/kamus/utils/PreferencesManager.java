package com.azhar.kamus.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Azhar Rivaldi on 31-03-2020.
 */

public class PreferencesManager {

    public static final String NAME = "KAMUS_PREF";
    public static final String FIRST_TIME_KEY = "FIRST_TIME_KEY";

    Context context;
    SharedPreferences prefs;

    public PreferencesManager(Context context){
        this.context = context;
        prefs = context.getSharedPreferences(PreferencesManager.NAME, Context.MODE_PRIVATE);
    }

    public void setFirstTimeLoad(boolean load) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(PreferencesManager.FIRST_TIME_KEY, load);
        editor.apply();
    }

    public Boolean getFirstTimeLoad() {
        return prefs.getBoolean(PreferencesManager.FIRST_TIME_KEY, true);
    }

}

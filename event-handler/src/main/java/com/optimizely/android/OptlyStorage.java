package com.optimizely.android;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

/**
 * Created by jdeffibaugh on 7/26/16 for Optimizely.
 *
 * Wrapper for {@link android.content.SharedPreferences}
 */
public class OptlyStorage {

    static final String PREFS_NAME = "optly";

    @NonNull private Context context;

    public OptlyStorage(@NonNull Context context) {
        this.context = context;
    }

    public void saveLong(String key, long value) {
        getWritablePrefs().putLong(EventIntentService.EXTRA_INTERVAL, value ).apply();
    }

    public long getLong(String key, long defaultValue) {
        return getReadablePrefs().getLong(key, defaultValue);
    }

    private SharedPreferences.Editor getWritablePrefs() {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE).edit();
    }

    private SharedPreferences getReadablePrefs() {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }
}

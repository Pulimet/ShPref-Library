package net.alexandroid.shpref;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.preference.PreferenceManager;

@SuppressLint("CommitPrefEdits")
public class ShPref {

    public static final int APPLY = 0;
    public static final int COMMIT = 1;

    private static int sDefaultWriteMode;

    private static SharedPreferences sShPref;
    @SuppressLint("StaticFieldLeak")
    private static Context sAppContext;

    private static boolean forceApply, forceCommit;

    public static void init(Context context, int defaultWriteMode) {
        sAppContext = context;
        sShPref = PreferenceManager.getDefaultSharedPreferences(context);
        sDefaultWriteMode = defaultWriteMode;
    }

    // Force commit
    public static void putC(int key, Object value) {
        forceCommit = true;
        put(sAppContext.getString(key), value);
    }

    public static void putC(String key, Object value) {
        forceCommit = true;
        put(key, value);
    }

    // Force apply
    public static void putA(int key, Object value) {
        forceApply = true;
        put(sAppContext.getString(key), value);
    }

    public static void putA(String key, Object value) {
        forceApply = true;
        put(key, value);
    }


    // Put
    public static void put(int key, Object value) {
        put(sAppContext.getString(key), value);
    }

    public static void put(String key, Object value) {
        SharedPreferences.Editor editor;

        if (value instanceof String) {
            editor = sShPref.edit().putString(key, (String) value);
        } else if (value instanceof Integer) {
            editor = sShPref.edit().putInt(key, (int) value);
        } else if (value instanceof Boolean) {
            editor = sShPref.edit().putBoolean(key, (boolean) value);
        } else if (value instanceof Float) {
            editor = sShPref.edit().putFloat(key, (float) value);
        } else if (value instanceof Long) {
            editor = sShPref.edit().putLong(key, (long) value);
        } else {
            return;
        }


        if (forceApply || (sDefaultWriteMode == APPLY && !forceCommit)) {
            forceApply = false;
            editor.apply();
        } else {
            forceCommit = false;
            editor.commit();
        }
    }


    // Get methods

    // String
    public static String getString(int key, String defaultValue) {
        return getString(sAppContext.getString(key), defaultValue);
    }

    public static String getString(String key, String defaultValue) {
        try {
            return sShPref.getString(key, defaultValue);
        } catch (Resources.NotFoundException e) {
            return defaultValue;
        }
    }

    // Int
    public static int getInt(int key, int defaultValue) {
        return getInt(sAppContext.getString(key), defaultValue);
    }

    public static int getInt(String key, int defaultValue) {
        try {
            return sShPref.getInt(key, defaultValue);
        } catch (Resources.NotFoundException e) {
            return defaultValue;
        }
    }

    // Boolean
    public static boolean getBoolean(int key, boolean defaultValue) {
        return getBoolean(sAppContext.getString(key), defaultValue);
    }

    public static boolean getBoolean(String key, boolean defaultValue) {
        try {
            return sShPref.getBoolean(key, defaultValue);
        } catch (Resources.NotFoundException e) {
            return defaultValue;
        }
    }


    // Float
    public static float getFloat(int key, float defaultValue) {
        return getFloat(sAppContext.getString(key), defaultValue);
    }

    public static float getFloat(String key, float defaultValue) {
        try {
            return sShPref.getFloat(key, defaultValue);
        } catch (Resources.NotFoundException e) {
            return defaultValue;
        }
    }

    // Long
    public static long getLong(int key, long defaultValue) {
        return getLong(sAppContext.getString(key), defaultValue);
    }

    public static long getLong(String key, long defaultValue) {
        try {
            return sShPref.getLong(key, defaultValue);
        } catch (Resources.NotFoundException e) {
            return defaultValue;
        }
    }


    // Remove force


    public static void removeA(int key) {
        forceApply = true;
        remove(sAppContext.getString(key));
    }

    public static void removeC(int key) {
        forceCommit = true;
        remove(sAppContext.getString(key));
    }

    public static void removeA(String key) {
        forceApply = true;
        remove(key);
    }

    public static void removeC(String key) {
        forceCommit = true;
        remove(key);
    }

    public static void remove(int key) {
        remove(sAppContext.getString(key));
    }

    // Remove

    public static void remove(String key) {
        SharedPreferences.Editor editor = sShPref.edit().remove(key);

        if (forceApply || (sDefaultWriteMode == APPLY && !forceCommit)) {
            forceApply = false;
            editor.apply();
        } else {
            forceCommit = false;
            editor.commit();
        }
    }

}
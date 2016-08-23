package net.alexandroid.shpref;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.preference.PreferenceManager;

@SuppressWarnings({"unused", "WeakerAccess"})
@SuppressLint("CommitPrefEdits")
public class ShPref {

    /**
     * On initialization you can choose the default write method
     * by passing one of following integers as a second argument
     */
    public static final int APPLY = 0;
    public static final int COMMIT = 1;

    /**
     * Default writing method
     */
    private static int sDefaultWriteMode;

    /**
     * SharedPreferences instance assigned on init
     */
    private static SharedPreferences sShPref;

    /**
     * Application context instance assigned on init
     */
    @SuppressLint("StaticFieldLeak")
    private static Context sAppContext;

    /**
     * Boolean bellow used to choose write method, regardless of the default.
     * Used by: putC(), putA(), removeA(), removeC()
     */
    private static boolean forceApply, forceCommit;

    /**
     * @param appContext       - application context
     * @param defaultWriteMode - default write method. Apply or commit.
     */
    public static void init(Context appContext, int defaultWriteMode) {
        sAppContext = appContext;
        sShPref = PreferenceManager.getDefaultSharedPreferences(appContext);
        sDefaultWriteMode = defaultWriteMode;
    }


    /**
     * Put key value to shared preferences
     *
     * @param key   - string resource id as a key. The name of the preference to modify.
     * @param value - value
     */
    public static void put(int key, Object value) {
        put(sAppContext.getString(key), value);
    }

    /**
     * Put key value to shared preferences
     *
     * @param key   - string as a key. The name of the preference to modify.
     * @param value - value
     */
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


    // Force commit

    /**
     * Write value on the same thread regardless of the default writing mode.
     *
     * @param key   - string resource id as a key. The name of the preference to modify.
     * @param value - value
     */
    public static void putC(int key, Object value) {
        forceCommit = true;
        put(sAppContext.getString(key), value);
    }

    /**
     * Write value on the same thread regardless of the default writing mode.
     *
     * @param key   - string as a key. The name of the preference to modify.
     * @param value - value
     */
    public static void putC(String key, Object value) {
        forceCommit = true;
        put(key, value);
    }


    // Force apply
    /**
     * Write value on the background regardless of the default writing mode.
     *
     * @param key   - string resource id as a key. The name of the preference to modify.
     * @param value - value
     */
    public static void putA(int key, Object value) {
        forceApply = true;
        put(sAppContext.getString(key), value);
    }

    /**
     * Write value on the background regardless of the default writing mode.
     *
     * @param key   - string as a key. The name of the preference to modify.
     * @param value - value
     */
    public static void putA(String key, Object value) {
        forceApply = true;
        put(key, value);
    }


    // Get methods

    // String

    /**
     * @param key The name of the preference to retrieve.
     * @param defaultValue Value to return if this preference does not exist.
     * @return Returns the preference value if it exists, or defValue
     */
    public static String getString(int key, String defaultValue) {
        return getString(sAppContext.getString(key), defaultValue);
    }

    /**
     * @param key The name of the preference to retrieve.
     * @param defaultValue Value to return if this preference does not exist.
     * @return Returns the preference value if it exists, or defValue
     */
    public static String getString(String key, String defaultValue) {
        try {
            return sShPref.getString(key, defaultValue);
        } catch (Resources.NotFoundException e) {
            return defaultValue;
        }
    }

    // Int
    /**
     * @param key The name of the preference to retrieve.
     * @param defaultValue Value to return if this preference does not exist.
     * @return Returns the preference value if it exists, or defValue
     */
    public static int getInt(int key, int defaultValue) {
        return getInt(sAppContext.getString(key), defaultValue);
    }

    /**
     * @param key The name of the preference to retrieve.
     * @param defaultValue Value to return if this preference does not exist.
     * @return Returns the preference value if it exists, or defValue
     */
    public static int getInt(String key, int defaultValue) {
        try {
            return sShPref.getInt(key, defaultValue);
        } catch (Resources.NotFoundException e) {
            return defaultValue;
        }
    }

    // Boolean
    /**
     * @param key The name of the preference to retrieve.
     * @param defaultValue Value to return if this preference does not exist.
     * @return Returns the preference value if it exists, or defValue
     */
    public static boolean getBoolean(int key, boolean defaultValue) {
        return getBoolean(sAppContext.getString(key), defaultValue);
    }

    /**
     * @param key The name of the preference to retrieve.
     * @param defaultValue Value to return if this preference does not exist.
     * @return Returns the preference value if it exists, or defValue
     */
    public static boolean getBoolean(String key, boolean defaultValue) {
        try {
            return sShPref.getBoolean(key, defaultValue);
        } catch (Resources.NotFoundException e) {
            return defaultValue;
        }
    }


    // Float
    /**
     * @param key The name of the preference to retrieve.
     * @param defaultValue Value to return if this preference does not exist.
     * @return Returns the preference value if it exists, or defValue
     */
    public static float getFloat(int key, float defaultValue) {
        return getFloat(sAppContext.getString(key), defaultValue);
    }

    /**
     * @param key The name of the preference to retrieve.
     * @param defaultValue Value to return if this preference does not exist.
     * @return Returns the preference value if it exists, or defValue
     */
    public static float getFloat(String key, float defaultValue) {
        try {
            return sShPref.getFloat(key, defaultValue);
        } catch (Resources.NotFoundException e) {
            return defaultValue;
        }
    }

    // Long
    /**
     * @param key The name of the preference to retrieve.
     * @param defaultValue Value to return if this preference does not exist.
     * @return Returns the preference value if it exists, or defValue
     */
    public static long getLong(int key, long defaultValue) {
        return getLong(sAppContext.getString(key), defaultValue);
    }

    /**
     * @param key The name of the preference to retrieve.
     * @param defaultValue Value to return if this preference does not exist.
     * @return Returns the preference value if it exists, or defValue
     */
    public static long getLong(String key, long defaultValue) {
        try {
            return sShPref.getLong(key, defaultValue);
        } catch (Resources.NotFoundException e) {
            return defaultValue;
        }
    }


    // Remove

    /**
     * @param key The name of the preference to remove.
     */
    public static void remove(int key) {
        remove(sAppContext.getString(key));
    }

    /**
     * @param key The name of the preference to remove.
     */
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

    // Remove force

    /**
     * Remove preference on the background regardless of the default writing mode.
     *
     * @param key   - string resource id as a key. The name of the preference to remove.
     */
    public static void removeA(int key) {
        forceApply = true;
        remove(sAppContext.getString(key));
    }

    /**
     * Remove preference on the background regardless of the default writing mode.
     *
     * @param key   - string resource id as a key. The name of the preference to remove.
     */
    public static void removeA(String key) {
        forceApply = true;
        remove(key);
    }

    /**
     * Remove preference on the same thread regardless of the default writing mode.
     *
     * @param key   - string resource id as a key. The name of the preference to remove.
     */
    public static void removeC(int key) {
        forceCommit = true;
        remove(sAppContext.getString(key));
    }

    /**
     * Remove preference on the same thread regardless of the default writing mode.
     *
     * @param key   - string resource id as a key. The name of the preference to remove.
     */
    public static void removeC(String key) {
        forceCommit = true;
        remove(key);
    }

}
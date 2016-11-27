package net.alexandroid.shpref;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

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
     * Boolean bellow used to choose write method, regardless of the default.
     * Used by: putC(), putA(), removeA(), removeC()
     */
    private static boolean forceApply, forceCommit;

    /**
     * @param appContext       - application context
     * @param defaultWriteMode - default write method. Apply or commit.
     */
    public static void init(Context appContext, int defaultWriteMode) {
        Contextor.getInstance().init(appContext);
        sShPref = PreferenceManager.getDefaultSharedPreferences(appContext);
        sDefaultWriteMode = defaultWriteMode;
    }


    // ============= Contains ============

    /**
     * @param key   - string as a key. The name of the preference to check for existence.
     * @return true if exist
     */
    public static boolean contains(@StringRes int key) {
        return contains(Contextor.getInstance().getContext().getString(key));
    }

    private static boolean contains(String key) {
        return sShPref.contains(key);
    }


    // ============= Put ============

    /**
     * Put key value to shared preferences
     *
     * @param key   - string resource id as a key. The name of the preference to modify.
     * @param value - value
     */
    public static void put(@StringRes int key, Object value) {
        put(Contextor.getInstance().getContext().getString(key), value);
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

    public static void putList(String key, List list) {
        if (list == null || list.size() < 1) {
            return;
        }

        for (int i = 0; i < list.size(); i++) {
            put(String.format(Locale.ENGLISH, "listKey_%s_%d", key, i), list.get(i));
        }
    }


    // Force commit

    /**
     * Write value on the same thread regardless of the default writing mode.
     *
     * @param key   - string resource id as a key. The name of the preference to modify.
     * @param value - value
     */
    public static void putC(@StringRes int key, Object value) {
        forceCommit = true;
        put(Contextor.getInstance().getContext().getString(key), value);
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
    public static void putA(@StringRes int key, Object value) {
        forceApply = true;
        put(Contextor.getInstance().getContext().getString(key), value);
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


    // ============= Get ============

    // String

    /**
     * @param key          The name of the preference to retrieve.
     * @param defaultValue Value to return if this preference does not exist.
     * @return Returns the preference value if it exists, or defValue
     */
    public static String getString(@StringRes int key, String defaultValue) {
        return getString(Contextor.getInstance().getContext().getString(key), defaultValue);
    }

    /**
     * @param key          The name of the preference to retrieve.
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

    /**
     * @param key The name of the preference to retrieve.
     * @return Returns the preference value if it exists or null
     */
    @Nullable
    public static String getString(@StringRes int key) {
        return getString(Contextor.getInstance().getContext().getString(key), null);
    }

    /**
     * @param key The name of the preference to retrieve.
     * @return Returns the preference value if it exists or null
     */
    @Nullable
    public static String getString(String key) {
        return getString(key, null);
    }

    // Int

    /**
     * @param key          The name of the preference to retrieve.
     * @param defaultValue Value to return if this preference does not exist.
     * @return Returns the preference value if it exists, or defValue
     */
    public static int getInt(@StringRes int key, int defaultValue) {
        return getInt(Contextor.getInstance().getContext().getString(key), defaultValue);
    }

    /**
     * @param key          The name of the preference to retrieve.
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

    /**
     * @param key The name of the preference to retrieve.
     * @return Returns the preference value if it exists or 0
     */
    public static int getInt(@StringRes int key) {
        return getInt(Contextor.getInstance().getContext().getString(key), 0);
    }

    /**
     * @param key The name of the preference to retrieve.
     * @return Returns the preference value if it exists or 0
     */
    public static int getInt(String key) {
        return getInt(key, 0);
    }

    // Boolean

    /**
     * @param key          The name of the preference to retrieve.
     * @param defaultValue Value to return if this preference does not exist.
     * @return Returns the preference value if it exists, or defValue
     */
    public static boolean getBoolean(@StringRes int key, boolean defaultValue) {
        return getBoolean(Contextor.getInstance().getContext().getString(key), defaultValue);
    }

    /**
     * @param key          The name of the preference to retrieve.
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

    /**
     * @param key The name of the preference to retrieve.
     * @return Returns the preference value if it exists, or defValue
     */
    public static boolean getBoolean(@StringRes int key) {
        return getBoolean(Contextor.getInstance().getContext().getString(key), false);
    }

    /**
     * @param key The name of the preference to retrieve.
     * @return Returns the preference value if it exists, or defValue
     */
    public static boolean getBoolean(String key) {
        return getBoolean(key, false);
    }


    // Float

    /**
     * @param key          The name of the preference to retrieve.
     * @param defaultValue Value to return if this preference does not exist.
     * @return Returns the preference value if it exists, or defValue
     */
    public static float getFloat(@StringRes int key, float defaultValue) {
        return getFloat(Contextor.getInstance().getContext().getString(key), defaultValue);
    }

    /**
     * @param key          The name of the preference to retrieve.
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

    /**
     * @param key The name of the preference to retrieve.
     * @return Returns the preference value if it exists, or 0
     */
    public static float getFloat(@StringRes int key) {
        return getFloat(Contextor.getInstance().getContext().getString(key), 0);
    }

    /**
     * @param key The name of the preference to retrieve.
     * @return Returns the preference value if it exists, or 0
     */
    public static float getFloat(String key) {
        return getFloat(key, 0);
    }

    // Long

    /**
     * @param key          The name of the preference to retrieve.
     * @param defaultValue Value to return if this preference does not exist.
     * @return Returns the preference value if it exists, or defValue
     */
    public static long getLong(@StringRes int key, long defaultValue) {
        return getLong(Contextor.getInstance().getContext().getString(key), defaultValue);
    }

    /**
     * @param key          The name of the preference to retrieve.
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

    /**
     * @param key The name of the preference to retrieve.
     * @return Returns the preference value if it exists, or defValue
     */
    public static long getLong(@StringRes int key) {
        return getLong(Contextor.getInstance().getContext().getString(key), 0);
    }

    /**
     * @param key The name of the preference to retrieve.
     * @return Returns the preference value if it exists, or defValue
     */
    public static long getLong(String key) {
        return getLong(key, 0);
    }


    // =========== Get lists ==============

    public static List<String> getListOfStrings(String key) {
        List<String> list = new ArrayList<>();
        int i = 0;
        String itemKey = String.format(Locale.ENGLISH, "listKey_%s_%d", key, i);
        while (contains(itemKey)) {
            list.add(getString(itemKey));
            i++;
            itemKey = String.format(Locale.ENGLISH, "listKey_%s_%d", key, i);
        }
        return list;
    }

    public static List<Integer> getListOfIntegers(String key) {
        List<Integer> list = new ArrayList<>();
        int i = 0;
        String itemKey = String.format(Locale.ENGLISH, "listKey_%s_%d", key, i);
        while (contains(itemKey)) {
            list.add(getInt(itemKey));
            i++;
            itemKey = String.format(Locale.ENGLISH, "listKey_%s_%d", key, i);
        }
        return list;
    }

    public static List<Boolean> getListOfBooleans(String key) {
        List<Boolean> list = new ArrayList<>();
        int i = 0;
        String itemKey = String.format(Locale.ENGLISH, "listKey_%s_%d", key, i);
        while (contains(itemKey)) {
            list.add(getBoolean(itemKey));
            i++;
            itemKey = String.format(Locale.ENGLISH, "listKey_%s_%d", key, i);
        }
        return list;
    }

    public static List<Float> getListOfFloats(String key) {
        List<Float> list = new ArrayList<>();
        int i = 0;
        String itemKey = String.format(Locale.ENGLISH, "listKey_%s_%d", key, i);
        while (contains(itemKey)) {
            list.add(getFloat(itemKey));
            i++;
            itemKey = String.format(Locale.ENGLISH, "listKey_%s_%d", key, i);
        }
        return list;
    }

    public static List<Long> getListOfLongs(String key) {
        List<Long> list = new ArrayList<>();
        int i = 0;
        String itemKey = String.format(Locale.ENGLISH, "listKey_%s_%d", key, i);
        while (contains(itemKey)) {
            list.add(getLong(itemKey));
            i++;
            itemKey = String.format(Locale.ENGLISH, "listKey_%s_%d", key, i);
        }
        return list;
    }

    public static List getMixedList(String key) {
        List<Object> list = new ArrayList<>();
        int i = 0;
        String itemKey = String.format(Locale.ENGLISH, "listKey_%s_%d", key, i);
        while (contains(itemKey)) {
            Object obj = get(itemKey);
            list.add(obj);
            i++;
            itemKey = String.format(Locale.ENGLISH, "listKey_%s_%d", key, i);
        }
        return list;
    }

    public static Object get(String itemKey) {
        Map<String, ?> all = sShPref.getAll();
        Object object = all.get(itemKey);

        if (object instanceof String) {
            return getString(itemKey);
        } else if (object instanceof Integer) {
            return getInt(itemKey);
        } else if (object instanceof Boolean) {
            return getBoolean(itemKey);
        } else if (object instanceof Float) {
            return getFloat(itemKey);
        } else if (object instanceof Long) {
            return getLong(itemKey);
        } else {
            return null;
        }
    }


    // ============= Remove ============

    /**
     * @param key The name of the preference to remove.
     */
    public static void remove(@StringRes int key) {
        remove(Contextor.getInstance().getContext().getString(key));
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
     * @param key - string resource id as a key. The name of the preference to remove.
     */
    public static void removeA(@StringRes int key) {
        forceApply = true;
        remove(Contextor.getInstance().getContext().getString(key));
    }

    /**
     * Remove preference on the background regardless of the default writing mode.
     *
     * @param key - string resource id as a key. The name of the preference to remove.
     */
    public static void removeA(String key) {
        forceApply = true;
        remove(key);
    }

    /**
     * Remove preference on the same thread regardless of the default writing mode.
     *
     * @param key - string resource id as a key. The name of the preference to remove.
     */
    public static void removeC(@StringRes int key) {
        forceCommit = true;
        remove(Contextor.getInstance().getContext().getString(key));
    }

    /**
     * Remove preference on the same thread regardless of the default writing mode.
     *
     * @param key - string resource id as a key. The name of the preference to remove.
     */
    public static void removeC(String key) {
        forceCommit = true;
        remove(key);
    }

    // ============ Clear =====

    /**
     * Remove all values from the preferences.
     */
    public static void clear() {
        sShPref.edit().clear();
    }

    // Builder class
    public static class Editor {
        private SharedPreferences.Editor editor;

        public Editor() {
            this.editor = sShPref.edit();
        }

        public Editor put(@StringRes int key, Object value) {
            return put(Contextor.getInstance().getContext().getString(key), value);
        }

        public Editor put(String key, Object value) {
            if (value instanceof String) {
                editor.putString(key, (String) value);
            } else if (value instanceof Integer) {
                editor.putInt(key, (int) value);
            } else if (value instanceof Boolean) {
                editor.putBoolean(key, (boolean) value);
            } else if (value instanceof Float) {
                editor.putFloat(key, (float) value);
            } else if (value instanceof Long) {
                editor.putLong(key, (long) value);
            }
            return this;
        }

        public void commit() {
            editor.commit();
        }

        public void apply() {
            editor.apply();
        }
    }


}
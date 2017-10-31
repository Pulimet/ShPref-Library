package net.alexandroid.shpref;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.preference.PreferenceManager;

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

    public static final String SPECIAL_TAG_FOR_DOUBLES = "specialTagForDoubles";

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

    public static void setDefaultWriteMode(int defaultWriteMode) {
        sDefaultWriteMode = defaultWriteMode;
    }

    // ============= Contains ============

    /**
     * @param key - string as a key. The name of the preference to check for existence.
     * @return true if exist
     */
    public static boolean contains(int key) {
        return contains(Contextor.getInstance().getContext().getString(key));
    }

    public static boolean contains(String key) {
        return sShPref.contains(key);
    }


    // ============= Put ============

    /**
     * Put key value to shared preferences
     *
     * @param key   - string resource id as a key. The name of the preference to modify.
     * @param value - the new value for the preference. Passing null for this argument is equivalent to calling remove(key)
     */
    public static void put(int key, Object value) {
        put(Contextor.getInstance().getContext().getString(key), value);
    }

    /**
     * Put key value to shared preferences.
     * If value is null
     *
     * @param key   - string as a key. The name of the preference to modify.
     * @param value - the new value for the preference. Passing null for this argument is equivalent to calling remove(key)
     */
    public static void put(String key, Object value) {
        SharedPreferences.Editor editor = sShPref.edit();

        if (value instanceof String || value == null) {
            editor.putString(key, (String) value);
        } else if (value instanceof Integer) {
            editor.putInt(key, (int) value);
        } else if (value instanceof Boolean) {
            editor.putBoolean(key, (boolean) value);
        } else if (value instanceof Float) {
            editor.putFloat(key, (float) value);
        } else if (value instanceof Double) {
            editor.putLong(key, Double.doubleToRawLongBits((Double) value));
        } else if (value instanceof Long) {
            editor.putLong(key, (long) value);
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

        // To mark the end of list and prevent collisions with lists saved before with the same key
        ShPref.remove(String.format(Locale.ENGLISH, "listKey_%s_%d", key, list.size()));
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
    public static void putA(int key, Object value) {
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
    public static String getString(int key, String defaultValue) {
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
    public static String getString(int key) {
        return getString(Contextor.getInstance().getContext().getString(key), null);
    }

    /**
     * @param key The name of the preference to retrieve.
     * @return Returns the preference value if it exists or null
     */
    public static String getString(String key) {
        return getString(key, null);
    }

    // Int

    /**
     * @param key          The name of the preference to retrieve.
     * @param defaultValue Value to return if this preference does not exist.
     * @return Returns the preference value if it exists, or defValue
     */
    public static int getInt(int key, int defaultValue) {
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
    public static int getInt(int key) {
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
    public static boolean getBoolean(int key, boolean defaultValue) {
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
    public static boolean getBoolean(int key) {
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
    public static float getFloat(int key, float defaultValue) {
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
    public static float getFloat(int key) {
        return getFloat(Contextor.getInstance().getContext().getString(key), 0);
    }

    /**
     * @param key The name of the preference to retrieve.
     * @return Returns the preference value if it exists, or 0
     */
    public static float getFloat(String key) {
        return getFloat(key, 0);
    }

    // Double

    /**
     * @param key          The name of the preference to retrieve.
     * @param defaultValue Value to return if this preference does not exist.
     * @return Returns the preference value if it exists, or defValue
     */
    public static double getDouble(int key, double defaultValue) {
        return getDouble(Contextor.getInstance().getContext().getString(key), defaultValue);
    }

    /**
     * @param key          The name of the preference to retrieve.
     * @param defaultValue Value to return if this preference does not exist.
     * @return Returns the preference value if it exists, or defValue
     */
    public static double getDouble(String key, double defaultValue) {
        try {
            return Double.longBitsToDouble(sShPref.getLong(key, Double.doubleToLongBits(defaultValue)));
        } catch (Resources.NotFoundException e) {
            return defaultValue;
        }
    }

    /**
     * @param key The name of the preference to retrieve.
     * @return Returns the preference value if it exists, or 0
     */
    public static double getDouble(int key) {
        return getDouble(Contextor.getInstance().getContext().getString(key), 0);
    }

    /**
     * @param key The name of the preference to retrieve.
     * @return Returns the preference value if it exists, or 0
     */
    public static double getDouble(String key) {
        return getDouble(key, 0);
    }


    // Long

    /**
     * @param key          The name of the preference to retrieve.
     * @param defaultValue Value to return if this preference does not exist.
     * @return Returns the preference value if it exists, or defValue
     */
    public static long getLong(int key, long defaultValue) {
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
    public static long getLong(int key) {
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

    public static ArrayList<String> getListOfStrings(String key) {
        ArrayList<String> list = new ArrayList<>();
        int i = 0;
        String itemKey = String.format(Locale.ENGLISH, "listKey_%s_%d", key, i);
        while (contains(itemKey)) {
            list.add(getString(itemKey));
            i++;
            itemKey = String.format(Locale.ENGLISH, "listKey_%s_%d", key, i);
        }
        return list;
    }

    public static ArrayList<Integer> getListOfIntegers(String key) {
        ArrayList<Integer> list = new ArrayList<>();
        int i = 0;
        String itemKey = String.format(Locale.ENGLISH, "listKey_%s_%d", key, i);
        while (contains(itemKey)) {
            list.add(getInt(itemKey));
            i++;
            itemKey = String.format(Locale.ENGLISH, "listKey_%s_%d", key, i);
        }
        return list;
    }

    public static ArrayList<Boolean> getListOfBooleans(String key) {
        ArrayList<Boolean> list = new ArrayList<>();
        int i = 0;
        String itemKey = String.format(Locale.ENGLISH, "listKey_%s_%d", key, i);
        while (contains(itemKey)) {
            list.add(getBoolean(itemKey));
            i++;
            itemKey = String.format(Locale.ENGLISH, "listKey_%s_%d", key, i);
        }
        return list;
    }

    public static ArrayList<Float> getListOfFloats(String key) {
        ArrayList<Float> list = new ArrayList<>();
        int i = 0;
        String itemKey = String.format(Locale.ENGLISH, "listKey_%s_%d", key, i);
        while (contains(itemKey)) {
            list.add(getFloat(itemKey));
            i++;
            itemKey = String.format(Locale.ENGLISH, "listKey_%s_%d", key, i);
        }
        return list;
    }

    public static ArrayList<Double> getListOfDoubles(String key) {
        ArrayList<Double> list = new ArrayList<>();
        int i = 0;
        String itemKey = String.format(Locale.ENGLISH, "listKey_%s_%d", key, i);
        while (contains(itemKey)) {
            list.add(getDouble(itemKey));
            i++;
            itemKey = String.format(Locale.ENGLISH, "listKey_%s_%d", key, i);
        }
        return list;
    }

    public static ArrayList<Long> getListOfLongs(String key) {
        ArrayList<Long> list = new ArrayList<>();
        int i = 0;
        String itemKey = String.format(Locale.ENGLISH, "listKey_%s_%d", key, i);
        while (contains(itemKey)) {
            list.add(getLong(itemKey));
            i++;
            itemKey = String.format(Locale.ENGLISH, "listKey_%s_%d", key, i);
        }
        return list;
    }

    /**
     * Can't return type double
     */
    public static ArrayList getMixedList(String key) {
        ArrayList<Object> list = new ArrayList<>();
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

    /**
     * Can't return type double
     */
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
    public static void remove(int key) {
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

    public static void removeList(String key) {
        ShPref.remove(String.format(Locale.ENGLISH, "listKey_%s_%d", key, 0));
    }

    // Remove force

    /**
     * Remove preference on the background regardless of the default writing mode.
     *
     * @param key - string resource id as a key. The name of the preference to remove.
     */
    public static void removeA(int key) {
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
    public static void removeC(int key) {
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
        sShPref.edit().clear().commit();
    }

    // Builder class
    public static class Editor {
        private SharedPreferences.Editor editor;

        public Editor() {
            this.editor = sShPref.edit();
        }

        /**
         * Put key value to shared preferences
         *
         * @param key   - string resource id as a key. The name of the preference to modify.
         * @param value - the new value for the preference. Passing null for this argument is equivalent to calling remove(key)
         */
        public Editor put(int key, Object value) {
            return put(Contextor.getInstance().getContext().getString(key), value);
        }

        /**
         * Put key value to shared preferences.
         *
         * @param key   - string as a key. The name of the preference to modify.
         * @param value - the new value for the preference. Passing null for this argument is equivalent to calling remove(key)
         */
        public Editor put(String key, Object value) {
            if (value instanceof String) {
                editor.putString(key, (String) value);
            } else if (value instanceof Integer) {
                editor.putInt(key, (int) value);
            } else if (value instanceof Boolean) {
                editor.putBoolean(key, (boolean) value);
            } else if (value instanceof Float) {
                editor.putFloat(key, (float) value);
            } else if (value instanceof Double) {
                editor.putLong(key, Double.doubleToRawLongBits((Double) value));
            } else if (value instanceof Long) {
                editor.putLong(key, (long) value);
            }
            return this;
        }

        /**
         * @param key The name of the preference to remove.
         */
        public Editor remove(int key) {
            return remove(Contextor.getInstance().getContext().getString(key));
        }

        /**
         * @param key The name of the preference to remove.
         */
        public Editor remove(String key) {
            editor.remove(key);
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
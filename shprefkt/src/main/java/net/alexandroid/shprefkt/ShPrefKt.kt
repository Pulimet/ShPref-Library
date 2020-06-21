package net.alexandroid.shprefkt

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Resources
import androidx.preference.PreferenceManager
import java.util.*

@Suppress("MemberVisibilityCanBePrivate", "unused")
class ShPrefKt(private val context: Context, private val defaultWriteMode: Int = APPLY) {
    /**
     * On initialization you can choose the default write method
     * by passing one of following integers as a second argument
     */
    companion object {
        const val APPLY = 0
        const val COMMIT = 1
        const val SPECIAL_TAG_FOR_DOUBLES = "specialTagForDoubles"
    }

    /**
     * SharedPreferences instance assigned on init
     */
    private var shPref: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

    /**
     * Boolean bellow used to choose write method, regardless of the default.
     * Used by: putC(), putA(), removeA(), removeC()
     */
    private var forceApply = false
    private var forceCommit = false


    // ============= Contains ============
    /**
     * @param key - string as a key. The name of the preference to check for existence.
     * @return true if exist
     */
    fun contains(key: Int) = contains(context.getString(key))
    fun contains(key: String?) = shPref.contains(key)

    // ============= Put ============
    /**
     * Put key value to shared preferences
     *
     * @param key   - string resource id as a key. The name of the preference to modify.
     * @param value - the new value for the preference. Passing null for this argument is equivalent to calling remove(key)
     */
    fun put(key: Int, value: Any?) {
        put(context.getString(key), value)
    }

    /**
     * Put key value to shared preferences.
     * If value is null
     *
     * @param key   - string as a key. The name of the preference to modify.
     * @param value - the new value for the preference. Passing null for this argument is equivalent to calling remove(key)
     */
    fun put(key: String?, value: Any?) {
        val editor = shPref.edit()
        when (value) {
            null, is String -> editor.putString(key, value as String?)
            is Int -> editor.putInt(key, value)
            is Boolean -> editor.putBoolean(key, value)
            is Float -> editor.putFloat(key, value)
            is Double -> editor.putLong(key, java.lang.Double.doubleToRawLongBits(value))
            is Long -> editor.putLong(key, value)
            else -> return
        }

        if (forceApply || defaultWriteMode == APPLY && !forceCommit) {
            forceApply = false
            editor.apply()
        } else {
            forceCommit = false
            editor.commit()
        }
    }

    fun putList(key: String?, list: List<*>?) {
        if (list.isNullOrEmpty()) return

        for (i in list.indices) {
            put("listKey_$key-$i", list[i])
        }

        // To mark the end of list and prevent collisions with lists saved before with the same key
        remove("listKey_$key-${list.size}")
    }

    // Force commit
    /**
     * Write value on the same thread regardless of the default writing mode.
     *
     * @param key   - string resource id as a key. The name of the preference to modify.
     * @param value - value
     */
    fun putC(key: Int, value: Any?) {
        putC(context.getString(key), value)
    }

    /**
     * Write value on the same thread regardless of the default writing mode.
     *
     * @param key   - string as a key. The name of the preference to modify.
     * @param value - value
     */
    fun putC(key: String?, value: Any?) {
        forceCommit = true
        put(key, value)
    }
    // Force apply
    /**
     * Write value on the background regardless of the default writing mode.
     *
     * @param key   - string resource id as a key. The name of the preference to modify.
     * @param value - value
     */
    fun putA(key: Int, value: Any?) {
        putA(context.getString(key), value)
    }

    /**
     * Write value on the background regardless of the default writing mode.
     *
     * @param key   - string as a key. The name of the preference to modify.
     * @param value - value
     */
    fun putA(key: String?, value: Any?) {
        forceApply = true
        put(key, value)
    }
    // ============= Get ============
    // String
    /**
     * @param key          The name of the preference to retrieve.
     * @param defaultValue Value to return if this preference does not exist.
     * @return Returns the preference value if it exists, or defValue
     */
    fun getString(key: Int, defaultValue: String?) = getString(context.getString(key), defaultValue)

    /**
     * @param key          The name of the preference to retrieve.
     * @param defaultValue Value to return if this preference does not exist.
     * @return Returns the preference value if it exists, or defValue
     */
    fun getString(key: String?, defaultValue: String?): String? {
        return try {
            shPref.getString(key, defaultValue)
        } catch (e: Resources.NotFoundException) {
            defaultValue
        }
    }

    /**
     * @param key The name of the preference to retrieve.
     * @return Returns the preference value if it exists or null
     */
    fun getString(key: Int) = getString(context.getString(key), null)

    /**
     * @param key The name of the preference to retrieve.
     * @return Returns the preference value if it exists or null
     */
    fun getString(key: String?): String? {
        return getString(key, null)
    }
    // Int
    /**
     * @param key          The name of the preference to retrieve.
     * @param defaultValue Value to return if this preference does not exist.
     * @return Returns the preference value if it exists, or defValue
     */
    fun getInt(key: Int, defaultValue: Int) = getInt(context.getString(key), defaultValue)

    /**
     * @param key          The name of the preference to retrieve.
     * @param defaultValue Value to return if this preference does not exist.
     * @return Returns the preference value if it exists, or defValue
     */
    fun getInt(key: String?, defaultValue: Int): Int {
        return try {
            shPref.getInt(key, defaultValue)
        } catch (e: Resources.NotFoundException) {
            defaultValue
        }
    }

    /**
     * @param key The name of the preference to retrieve.
     * @return Returns the preference value if it exists or 0
     */
    fun getInt(key: Int) = getInt(context.getString(key), 0)

    /**
     * @param key The name of the preference to retrieve.
     * @return Returns the preference value if it exists or 0
     */
    fun getInt(key: String?) = getInt(key, 0)

    // Boolean
    /**
     * @param key          The name of the preference to retrieve.
     * @param defaultValue Value to return if this preference does not exist.
     * @return Returns the preference value if it exists, or defValue
     */
    fun getBoolean(key: Int, defaultValue: Boolean) = getBoolean(context.getString(key), defaultValue)

    /**
     * @param key          The name of the preference to retrieve.
     * @param defaultValue Value to return if this preference does not exist.
     * @return Returns the preference value if it exists, or defValue
     */
    fun getBoolean(key: String?, defaultValue: Boolean): Boolean {
        return try {
            shPref.getBoolean(key, defaultValue)
        } catch (e: Resources.NotFoundException) {
            defaultValue
        }
    }

    /**
     * @param key The name of the preference to retrieve.
     * @return Returns the preference value if it exists, or defValue
     */
    fun getBoolean(key: Int) = getBoolean(context.getString(key), false)

    /**
     * @param key The name of the preference to retrieve.
     * @return Returns the preference value if it exists, or defValue
     */
    fun getBoolean(key: String?) = getBoolean(key, false)

    // Float
    /**
     * @param key          The name of the preference to retrieve.
     * @param defaultValue Value to return if this preference does not exist.
     * @return Returns the preference value if it exists, or defValue
     */
    fun getFloat(key: Int, defaultValue: Float) = getFloat(context.getString(key), defaultValue)

    /**
     * @param key          The name of the preference to retrieve.
     * @param defaultValue Value to return if this preference does not exist.
     * @return Returns the preference value if it exists, or defValue
     */
    fun getFloat(key: String?, defaultValue: Float): Float {
        return try {
            shPref.getFloat(key, defaultValue)
        } catch (e: Resources.NotFoundException) {
            defaultValue
        }
    }

    /**
     * @param key The name of the preference to retrieve.
     * @return Returns the preference value if it exists, or 0
     */
    fun getFloat(key: Int) = getFloat(context.getString(key), 0f)

    /**
     * @param key The name of the preference to retrieve.
     * @return Returns the preference value if it exists, or 0
     */
    fun getFloat(key: String?) = getFloat(key, 0f)

    // Double
    /**
     * @param key          The name of the preference to retrieve.
     * @param defaultValue Value to return if this preference does not exist.
     * @return Returns the preference value if it exists, or defValue
     */
    fun getDouble(key: Int, defaultValue: Double) = getDouble(context.getString(key), defaultValue)

    /**
     * @param key          The name of the preference to retrieve.
     * @param defaultValue Value to return if this preference does not exist.
     * @return Returns the preference value if it exists, or defValue
     */
    fun getDouble(key: String?, defaultValue: Double): Double {
        return try {
            java.lang.Double.longBitsToDouble(
                    shPref.getLong(
                            key,
                            java.lang.Double.doubleToLongBits(defaultValue)
                    )
            )
        } catch (e: Resources.NotFoundException) {
            defaultValue
        }
    }

    /**
     * @param key The name of the preference to retrieve.
     * @return Returns the preference value if it exists, or 0
     */
    fun getDouble(key: Int) = getDouble(context.getString(key), 0.0)

    /**
     * @param key The name of the preference to retrieve.
     * @return Returns the preference value if it exists, or 0
     */
    fun getDouble(key: String?) = getDouble(key, 0.0)

    // Long
    /**
     * @param key          The name of the preference to retrieve.
     * @param defaultValue Value to return if this preference does not exist.
     * @return Returns the preference value if it exists, or defValue
     */
    fun getLong(key: Int, defaultValue: Long) = getLong(context.getString(key), defaultValue)

    /**
     * @param key          The name of the preference to retrieve.
     * @param defaultValue Value to return if this preference does not exist.
     * @return Returns the preference value if it exists, or defValue
     */
    fun getLong(key: String?, defaultValue: Long): Long {
        return try {
            shPref.getLong(key, defaultValue)
        } catch (e: Resources.NotFoundException) {
            defaultValue
        }
    }

    /**
     * @param key The name of the preference to retrieve.
     * @return Returns the preference value if it exists, or defValue
     */
    fun getLong(key: Int) = getLong(context.getString(key), 0)

    /**
     * @param key The name of the preference to retrieve.
     * @return Returns the preference value if it exists, or defValue
     */
    fun getLong(key: String?) = getLong(key, 0)

    // =========== Get lists ==============
    fun getListOfStrings(key: String?): ArrayList<String?> {
        val list: ArrayList<String?> = ArrayList()
        var i = 0
        var itemKey = "listKey_$key-$i"
        while (contains(itemKey)) {
            list.add(getString(itemKey))
            i++
            itemKey = "listKey_$key-$i"
        }
        return list
    }

    fun getListOfIntegers(key: String?): ArrayList<Int> {
        val list: ArrayList<Int> = ArrayList()
        var i = 0
        var itemKey = "listKey_$key-$i"
        while (contains(itemKey)) {
            list.add(getInt(itemKey))
            i++
            itemKey = "listKey_$key-$i"
        }
        return list
    }

    fun getListOfBooleans(key: String?): ArrayList<Boolean> {
        val list: ArrayList<Boolean> = ArrayList()
        var i = 0
        var itemKey = "listKey_$key-$i"
        while (contains(itemKey)) {
            list.add(getBoolean(itemKey))
            i++
            itemKey = "listKey_$key-$i"
        }
        return list
    }

    fun getListOfFloats(key: String?): ArrayList<Float> {
        val list: ArrayList<Float> = ArrayList()
        var i = 0
        var itemKey = "listKey_$key-$i"
        while (contains(itemKey)) {
            list.add(getFloat(itemKey))
            i++
            itemKey = "listKey_$key-$i"
        }
        return list
    }

    fun getListOfDoubles(key: String?): ArrayList<Double> {
        val list: ArrayList<Double> = ArrayList()
        var i = 0
        var itemKey = "listKey_$key-$i"
        while (contains(itemKey)) {
            list.add(getDouble(itemKey))
            i++
            itemKey = "listKey_$key-$i"
        }
        return list
    }

    fun getListOfLongs(key: String?): ArrayList<Long> {
        val list: ArrayList<Long> = ArrayList()
        var i = 0
        var itemKey = "listKey_$key-$i"
        while (contains(itemKey)) {
            list.add(getLong(itemKey))
            i++
            itemKey = "listKey_$key-$i"
        }
        return list
    }

    /**
     * Can't return type double
     */
    fun getMixedList(key: String?): ArrayList<Any?> {
        val list: ArrayList<Any?> = ArrayList()
        var i = 0
        var itemKey = "listKey_$key-$i"
        while (contains(itemKey)) {
            val obj = get(itemKey)
            list.add(obj)
            i++
            itemKey = "listKey_$key-$i"
        }
        return list
    }

    /**
     * Can't return type double
     */
    operator fun get(itemKey: String?): Any? = when (shPref.all[itemKey]) {
        is String -> getString(itemKey)
        is Int -> getInt(itemKey)
        is Boolean -> getBoolean(itemKey)
        is Float -> getFloat(itemKey)
        is Long -> getLong(itemKey)
        else -> null

    }
    // ============= Remove ============
    /**
     * @param key The name of the preference to remove.
     */
    fun remove(key: Int) {
        remove(context.getString(key))
    }

    /**
     * @param key The name of the preference to remove.
     */
    fun remove(key: String?) {
        val editor = shPref.edit().remove(key)
        if (forceApply || defaultWriteMode == APPLY && !forceCommit) {
            forceApply = false
            editor.apply()
        } else {
            forceCommit = false
            editor.commit()
        }
    }

    fun removeList(key: String?) {
        remove(java.lang.String.format(Locale.ENGLISH, "listKey_%s_%d", key, 0))
    }
    // Remove force
    /**
     * Remove preference on the background regardless of the default writing mode.
     *
     * @param key - string resource id as a key. The name of the preference to remove.
     */
    fun removeA(key: Int) {
        forceApply = true
        remove(context.getString(key))
    }

    /**
     * Remove preference on the background regardless of the default writing mode.
     *
     * @param key - string resource id as a key. The name of the preference to remove.
     */
    fun removeA(key: String?) {
        forceApply = true
        remove(key)
    }

    /**
     * Remove preference on the same thread regardless of the default writing mode.
     *
     * @param key - string resource id as a key. The name of the preference to remove.
     */
    fun removeC(key: Int) {
        forceCommit = true
        remove(context.getString(key))
    }

    /**
     * Remove preference on the same thread regardless of the default writing mode.
     *
     * @param key - string resource id as a key. The name of the preference to remove.
     */
    fun removeC(key: String?) {
        forceCommit = true
        remove(key)
    }
    // ============ Clear =====
    /**
     * Remove all values from the preferences.
     */
    fun clear() {
        shPref.edit().clear().apply()
    }

    // Builder class
    inner class Editor {
        private val editor: SharedPreferences.Editor = shPref.edit()

        /**
         * Put key value to shared preferences
         *
         * @param key   - string resource id as a key. The name of the preference to modify.
         * @param value - the new value for the preference. Passing null for this argument is equivalent to calling remove(key)
         */
        fun put(key: Int, value: Any?): Editor {
            return put(context.getString(key), value)
        }

        /**
         * Put key value to shared preferences.
         *
         * @param key   - string as a key. The name of the preference to modify.
         * @param value - the new value for the preference. Passing null for this argument is equivalent to calling remove(key)
         */
        fun put(key: String?, value: Any?): Editor {
            when (value) {
                is String -> editor.putString(key, value as String?)
                is Int -> editor.putInt(key, value)
                is Boolean -> editor.putBoolean(key, value)
                is Float -> editor.putFloat(key, value)
                is Double -> {
                    editor.putLong(key, java.lang.Double.doubleToRawLongBits((value as Double?)!!))
                }
                is Long -> editor.putLong(key, value)
            }
            return this
        }

        /**
         * @param key The name of the preference to remove.
         */
        fun remove(key: Int): Editor {
            return remove(context.getString(key))
        }

        /**
         * @param key The name of the preference to remove.
         */
        fun remove(key: String?): Editor {
            editor.remove(key)
            return this
        }

        fun commit() {
            editor.commit()
        }

        fun apply() {
            editor.apply()
        }

    }
}
package net.alexandroid.shprefhelper

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import net.alexandroid.shprefkt.ShPrefKt
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*

@RunWith(AndroidJUnit4::class)
class KotlinKotlinLibraryMethodsTest {
    companion object {

        private const val KEY_1 = "KEY_1"
        private const val KEY_2 = "KEY_2"
        private const val KEY_3 = "KEY_3"
        private const val KEY_4 = "KEY_4"
        private const val KEY_5 = "KEY_5"
        private const val KEY_6 = "KEY_6"

        private const val VALUE_STRING = "Some string"
        private const val DEFAULT_VALUE_STRING = "Some default value"
        private const val VALUE_INTEGER = Int.MAX_VALUE
        private const val DEFAULT_VALUE_INTEGER = 777
        private const val VALUE_BOOLEAN = false
        private const val DEFAULT_VALUE_BOOLEAN = true
        private val VALUE_FLOAT = Float.MAX_VALUE
        private const val DEFAULT_VALUE_FLOAT = 1.2345678f
        private val VALUE_DOUBLE = Double.MAX_VALUE
        private const val DEFAULT_VALUE_DOUBLE = 1.1234567890
        private const val VALUE_LONG = Long.MAX_VALUE
        private const val DEFAULT_VALUE_LONG = 1234567890123456789L
    }

    private lateinit var shPref: ShPrefKt

    @Before
    fun beforeEachTestClearshPref() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        shPref = ShPrefKt(appContext, ShPrefKt.COMMIT)
        shPref.clear()
        println("=== shPref is cleared ===")
    }

    // Clear
    @Test
    fun testClear() {
        shPref.put(KEY_1, VALUE_STRING)
        shPref.clear()
        Assert.assertNull("Clear method doesn't remove all values from Shared Preferences", shPref.getString(KEY_1))
    }

    // Remove
    @Test
    fun removeTest() {
        shPref.put(KEY_1, VALUE_INTEGER)
        shPref.remove(KEY_1)
        Assert.assertEquals("Remove method doesn't remove value", 0, shPref.getInt(KEY_1))
    }

    @Test
    fun removeListTest() {
        val list = ArrayList<Int?>()
        for (i in 0..9) {
            list.add(i)
        }
        shPref.putList(KEY_1, list)
        shPref.removeList(KEY_1)
        Assert.assertEquals("removeList method doesn't work as expected", 0, shPref.getListOfIntegers(KEY_1).size)
    }

    // Contains
    @Test
    fun testContains() {
        shPref.put(KEY_1, VALUE_STRING)
        var isContains = shPref.contains(KEY_1)
        Assert.assertTrue("Contains method return wrong result. 001", isContains)
        shPref.put(R.string.test_key_1, VALUE_STRING)
        isContains = shPref.contains(R.string.test_key_1)
        Assert.assertTrue("Contains method return wrong result. 002", isContains)
    }

    // shPref.put and get String
    @Test
    fun testPutStringNull() {
        shPref.put(KEY_1, null)
        Assert.assertNull("String is not null as should be 001", shPref.getString(KEY_1))
        shPref.put(R.string.test_key_1, null)
        Assert.assertNull("String is not null as should be 002", shPref.getString(R.string.test_key_1))
    }

    @Test
    fun testPutAndGetString() {
        shPref.put(KEY_1, VALUE_STRING)
        Assert.assertEquals("String shPref.put and get error 001", VALUE_STRING, shPref.getString(KEY_1))
        shPref.put(R.string.test_key_1, VALUE_STRING)
        Assert.assertEquals("String put and get error 002", VALUE_STRING, shPref.getString(R.string.test_key_1))
    }

    @Test
    fun testGetStringDefaultValue() {
        Assert.assertEquals("Method getString doesn't return the default value 001",
                DEFAULT_VALUE_STRING, shPref.getString(KEY_1, DEFAULT_VALUE_STRING))
        Assert.assertEquals("Method getString doesn't return the default value 002",
                DEFAULT_VALUE_STRING, shPref.getString(R.string.test_key_1, DEFAULT_VALUE_STRING))
    }

    // Put and get int
    @Test
    fun testPutAndGetInt() {
        shPref.put(KEY_1, VALUE_INTEGER)
        Assert.assertEquals("Int put and get error 001", VALUE_INTEGER, shPref.getInt(KEY_1))
        shPref.put(R.string.test_key_1, VALUE_INTEGER)
        Assert.assertEquals("Int put and get error 002", VALUE_INTEGER, shPref.getInt(R.string.test_key_1))
    }

    @Test
    fun testGetIntDefaultValue() {
        Assert.assertEquals("Method getInt doesn't return the default value 001",
                DEFAULT_VALUE_INTEGER.toLong(), shPref.getInt(KEY_1, DEFAULT_VALUE_INTEGER).toLong())
        Assert.assertEquals("Method getInt doesn't return the default value 002",
                DEFAULT_VALUE_INTEGER, shPref.getInt(R.string.test_key_1, DEFAULT_VALUE_INTEGER))
    }


    // Put and get boolean
    @Test
    fun testPutAndGetBoolean() {
        shPref.put(KEY_1, VALUE_BOOLEAN)
        Assert.assertEquals("Boolean put and get error 001", VALUE_BOOLEAN, shPref.getBoolean(KEY_1))
        shPref.put(R.string.test_key_1, VALUE_BOOLEAN)
        Assert.assertEquals("Boolean put and get error 002", VALUE_BOOLEAN, shPref.getBoolean(R.string.test_key_1))
    }

    @Test
    fun testGetBooleanDefaultValue() {
        Assert.assertEquals("Method getBoolean doesn't return the default value 001",
                DEFAULT_VALUE_BOOLEAN, shPref.getBoolean(KEY_1, DEFAULT_VALUE_BOOLEAN))
        Assert.assertEquals("Method getBoolean doesn't return the default value 002",
                DEFAULT_VALUE_BOOLEAN, shPref.getBoolean(R.string.test_key_1, DEFAULT_VALUE_BOOLEAN))
    }


    // Put and get float
    @Test
    fun testPutAndGetFloat() {
        shPref.put(KEY_1, VALUE_FLOAT)
        Assert.assertEquals("Float put and get error 001", VALUE_FLOAT, shPref.getFloat(KEY_1), 0.0f)
        shPref.put(R.string.test_key_1, VALUE_FLOAT)
        Assert.assertEquals("Float put and get error 002", VALUE_FLOAT, shPref.getFloat(R.string.test_key_1), 0.0f)
    }

    @Test
    fun testGetFloatDefaultValue() {
        Assert.assertEquals("Method getFloat doesn't return the default value 001",
                DEFAULT_VALUE_FLOAT, shPref.getFloat(KEY_1, DEFAULT_VALUE_FLOAT), 0.0f)
        Assert.assertEquals("Method getFloat doesn't return the default value 002",
                DEFAULT_VALUE_FLOAT, shPref.getFloat(R.string.test_key_1, DEFAULT_VALUE_FLOAT), 0.0f)
    }


    // Put and get double
    @Test
    fun testPutAndGetDouble() {
        shPref.put(KEY_1, VALUE_DOUBLE)
        Assert.assertEquals("Double put and get error 001", VALUE_DOUBLE, shPref.getDouble(KEY_1), 0.0)
        shPref.put(R.string.test_key_1, VALUE_DOUBLE)
        Assert.assertEquals("Double put and get error 002", VALUE_DOUBLE, shPref.getDouble(R.string.test_key_1), 0.0)
    }

    @Test
    fun testGetDoubleDefaultValue() {
        Assert.assertEquals("Method getDouble doesn't return the default value 001",
                DEFAULT_VALUE_DOUBLE, shPref.getDouble(KEY_1, DEFAULT_VALUE_DOUBLE), 0.0)
        Assert.assertEquals("Method getDouble doesn't return the default value 002",
                DEFAULT_VALUE_DOUBLE, shPref.getDouble(R.string.test_key_1, DEFAULT_VALUE_DOUBLE), 0.0)
    }


    // Put and get long
    @Test
    fun testPutAndGetLong() {
        shPref.put(KEY_1, VALUE_LONG)
        Assert.assertEquals("Long put and get error 001", VALUE_LONG, shPref.getLong(KEY_1))
        shPref.put(R.string.test_key_1, VALUE_LONG)
        Assert.assertEquals("Long put and get error 002", VALUE_LONG, shPref.getLong(R.string.test_key_1))
    }

    @Test
    fun testGetLongDefaultValue() {
        Assert.assertEquals("Method getLong doesn't return the default value 001",
                DEFAULT_VALUE_LONG, shPref.getLong(KEY_1, DEFAULT_VALUE_LONG))
        Assert.assertEquals("Method getLong doesn't return the default value 002",
                DEFAULT_VALUE_LONG, shPref.getLong(R.string.test_key_1, DEFAULT_VALUE_LONG))
    }


    // List tests
    @Test
    fun putAndGetList() {
        val integerList = ArrayList<Int?>()
        for (i in 0..9) {
            integerList.add(i)
        }
        shPref.putList(KEY_1, integerList)
        Assert.assertEquals("Put and get list error with Integer", integerList, shPref.getListOfIntegers(KEY_1))
        val longList = ArrayList<Long?>()
        for (i in 0..9) {
            longList.add(i.toLong())
        }
        shPref.putList(KEY_1, longList)
        Assert.assertEquals("Put and get list error with Long", longList, shPref.getListOfLongs(KEY_1))
        val booleanList = ArrayList<Boolean?>()
        for (i in 0..9) {
            booleanList.add(true)
        }
        shPref.putList(KEY_1, booleanList)
        Assert.assertEquals("Put and get list error with Boolean", booleanList, shPref.getListOfBooleans(KEY_1))
        val floatList = ArrayList<Float?>()
        for (i in 1..10) {
            floatList.add(i / 2f)
        }
        shPref.putList(KEY_1, floatList)
        Assert.assertEquals("Put and get list error with Float", floatList, shPref.getListOfFloats(KEY_1))
        val stringList = ArrayList<String?>()
        for (i in 0..9) {
            stringList.add(String.format(Locale.ENGLISH, "Test %d", i))
        }
        shPref.putList(KEY_1, stringList)
        Assert.assertEquals("Put and get list error with String", stringList, shPref.getListOfStrings(KEY_1))
        val doubleList = ArrayList<Double?>()
        for (i in 1..10) {
            doubleList.add(i / 2.toDouble())
        }
        shPref.putList(KEY_1, doubleList)
        Assert.assertEquals("Put and get list error with Double", doubleList, shPref.getListOfDoubles(KEY_1))
    }

    @Test
    fun getMixedListTest() {
        val list = ArrayList<Any?>()
        list.add(VALUE_INTEGER)
        list.add(VALUE_LONG)
        list.add(VALUE_BOOLEAN)
        list.add(VALUE_FLOAT)
        list.add(VALUE_STRING)
        //list.add(VALUE_DOUBLE);
        shPref.putList(KEY_1, list)
        val result: ArrayList<*> = shPref.getMixedList(KEY_1)
        Assert.assertEquals("Mixed list getting int value fail", VALUE_INTEGER, result[0])
        Assert.assertEquals("Mixed list getting long value fail", VALUE_LONG, result[1])
        Assert.assertEquals("Mixed list getting boolean value fail", VALUE_BOOLEAN, result[2])
        Assert.assertEquals("Mixed list getting float value fail", VALUE_FLOAT, result[3])
        Assert.assertEquals("Mixed list getting String value fail", VALUE_STRING, result[4])
        //assertEquals("Mixed list getting double value fail", VALUE_DOUBLE, result.get(5));
    }

    // Get method
    @Test
    fun getTest() {
        shPref.put(KEY_1, VALUE_INTEGER)
        Assert.assertEquals("Get method check for integer failed", VALUE_INTEGER, shPref[KEY_1])

        //shPref.put(KEY_1, VALUE_DOUBLE);
        //assertEquals("Get method check for double failed", VALUE_DOUBLE, (double) shPref.get(KEY_1), 0.0);
        shPref.put(KEY_1, VALUE_STRING)
        Assert.assertEquals("Get method check for String failed", VALUE_STRING, shPref[KEY_1])
        shPref.put(KEY_1, VALUE_BOOLEAN)
        Assert.assertEquals("Get method check for boolean failed", VALUE_BOOLEAN, shPref[KEY_1])
        shPref.put(KEY_1, VALUE_FLOAT)
        Assert.assertEquals("Get method check for float failed", VALUE_FLOAT, shPref[KEY_1] as Float, 0.0f)
        shPref.put(KEY_1, VALUE_LONG)
        Assert.assertEquals("Get method check for long failed", VALUE_LONG, shPref[KEY_1] as Long)
    }

    // Editor tests
    @Test
    fun editorTest() {
        shPref.Editor()
                .put(KEY_1, VALUE_LONG)
                .put(KEY_2, VALUE_FLOAT)
                .put(KEY_3, VALUE_BOOLEAN)
                .put(KEY_4, VALUE_STRING)
                .put(KEY_5, VALUE_DOUBLE)
                .put(KEY_6, VALUE_INTEGER)
                .apply()
        Assert.assertEquals("editorTest - get long fail", VALUE_LONG, shPref.getLong(KEY_1))
        Assert.assertEquals("editorTest - get float fail", VALUE_FLOAT.toDouble(), shPref.getFloat(KEY_2).toDouble(), 0.0)
        Assert.assertEquals("editorTest - get boolean fail", VALUE_BOOLEAN, shPref.getBoolean(KEY_3))
        Assert.assertEquals("editorTest - get String fail", VALUE_STRING, shPref.getString(KEY_4))
        Assert.assertEquals("editorTest - get double fail", VALUE_DOUBLE, shPref.getDouble(KEY_5), 0.0)
        Assert.assertEquals("editorTest - get int fail", VALUE_INTEGER, shPref.getInt(KEY_6))
        shPref.clear()
        shPref.Editor()
                .put(R.string.test_key_1, VALUE_LONG)
                .put(R.string.test_key_2, VALUE_FLOAT)
                .put(R.string.test_key_3, VALUE_BOOLEAN)
                .put(R.string.test_key_4, VALUE_STRING)
                .put(R.string.test_key_5, VALUE_DOUBLE)
                .put(R.string.test_key_6, VALUE_INTEGER)
                .commit()
        Assert.assertEquals("editorTest - get long fail", VALUE_LONG, shPref.getLong(R.string.test_key_1))
        Assert.assertEquals("editorTest - get float fail", VALUE_FLOAT.toDouble(), shPref.getFloat(R.string.test_key_2).toDouble(), 0.0)
        Assert.assertEquals("editorTest - get boolean fail", VALUE_BOOLEAN, shPref.getBoolean(R.string.test_key_3))
        Assert.assertEquals("editorTest - get String fail", VALUE_STRING, shPref.getString(R.string.test_key_4))
        Assert.assertEquals("editorTest - get double fail", VALUE_DOUBLE, shPref.getDouble(R.string.test_key_5), 0.0)
        Assert.assertEquals("editorTest - get double fail", VALUE_INTEGER, shPref.getInt(R.string.test_key_6))
        shPref.clear()
        shPref.put(KEY_2, VALUE_STRING)
        shPref.Editor()
                .put(KEY_1, VALUE_LONG)
                .remove(KEY_2)
                .put(KEY_3, VALUE_BOOLEAN)
                .commit()
        Assert.assertEquals("editorTest - get long fail", VALUE_LONG, shPref.getLong(KEY_1))
        Assert.assertFalse("editorTest - remove pref failed", shPref.contains(KEY_2))
        Assert.assertEquals("editorTest - get boolean fail", VALUE_BOOLEAN, shPref.getBoolean(KEY_3))
    }


}
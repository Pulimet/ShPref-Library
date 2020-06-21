package net.alexandroid.shprefhelper;

import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import net.alexandroid.shpref.ShPref;
import net.alexandroid.shprefkt.ShPrefKt;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Locale;

import static net.alexandroid.shpref.ShPref.put;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;


@RunWith(AndroidJUnit4.class)
public class KotlinLibraryMethodsTest {

    private static final String KEY_1 = "KEY_1";
    private static final String KEY_2 = "KEY_2";
    private static final String KEY_3 = "KEY_3";
    private static final String KEY_4 = "KEY_4";
    private static final String KEY_5 = "KEY_5";
    private static final String KEY_6 = "KEY_6";

    private static final String VALUE_STRING = "Some string";
    private static final String DEFAULT_VALUE_STRING = "Some default value";
    private static final int VALUE_INTEGER = Integer.MAX_VALUE;
    private static final int DEFAULT_VALUE_INTEGER = 777;
    private static final boolean VALUE_BOOLEAN = false;
    private static final boolean DEFAULT_VALUE_BOOLEAN = true;
    private static final float VALUE_FLOAT = Float.MAX_VALUE;
    private static final float DEFAULT_VALUE_FLOAT = 1.2345678f;
    private static final double VALUE_DOUBLE = Double.MAX_VALUE;
    private static final double DEFAULT_VALUE_DOUBLE = 1.1234567890;
    private static final long VALUE_LONG = Long.MAX_VALUE;
    private static final long DEFAULT_VALUE_LONG = 1234567890123456789L;

    private static ShPrefKt shPref = null;

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("net.alexandroid.shprefhelper", appContext.getPackageName());
    }

    @Before
    public void beforeEachTestClearShPref() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        shPref = new ShPrefKt(appContext, ShPrefKt.COMMIT);
        shPref.clear();
        System.out.println("=== shPref is cleared ===");
    }

    // Clear

    @Test
    public void testClear() {
        put(KEY_1, VALUE_STRING);
        shPref.clear();
        assertNull("Clear method doesn't remove all values from Shared Preferences", shPref.getString(KEY_1));
    }

    // Remove

    @Test
    public void removeTest() {
        put(KEY_1, VALUE_INTEGER);
        shPref.remove(KEY_1);
        assertEquals("Remove method doesn't remove value", 0, shPref.getInt(KEY_1));
    }

    @Test
    public void removeListTest() {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        shPref.putList(KEY_1, list);
        shPref.removeList(KEY_1);
        assertEquals("removeList method doesn't work as expected", 0, shPref.getListOfIntegers(KEY_1).size());
    }

    // Contains

    @Test
    public void testContains() {
        put(KEY_1, VALUE_STRING);
        boolean isContains = shPref.contains(KEY_1);
        assertTrue("Contains method return wrong result. 001", isContains);

        put(R.string.test_key_1, VALUE_STRING);
        isContains = shPref.contains(R.string.test_key_1);
        assertTrue("Contains method return wrong result. 002", isContains);
    }

    // Put and get String
    @Test
    public void testPutStringNull() {
        put(KEY_1, null);
        assertNull("String is not null as should be 001", shPref.getString(KEY_1));

        put(R.string.test_key_1, null);
        assertNull("String is not null as should be 002", shPref.getString(R.string.test_key_1));
    }

    @Test
    public void testPutAndGetString() {
        put(KEY_1, VALUE_STRING);
        assertEquals("String put and get error 001", VALUE_STRING, shPref.getString(KEY_1));

        put(R.string.test_key_1, VALUE_STRING);
        assertEquals("String put and get error 002", VALUE_STRING, shPref.getString(R.string.test_key_1));
    }

    @Test
    public void testGetStringDefaultValue() {
        assertEquals("Method getString doesn't return the default value 001",
                DEFAULT_VALUE_STRING, shPref.getString(KEY_1, DEFAULT_VALUE_STRING));

        assertEquals("Method getString doesn't return the default value 002",
                DEFAULT_VALUE_STRING, shPref.getString(R.string.test_key_1, DEFAULT_VALUE_STRING));
    }

    // Put and get int

    @Test
    public void testPutAndGetInt() {
        put(KEY_1, VALUE_INTEGER);
        assertEquals("Int put and get error 001", VALUE_INTEGER, shPref.getInt(KEY_1));

        put(R.string.test_key_1, VALUE_INTEGER);
        assertEquals("Int put and get error 002", VALUE_INTEGER, shPref.getInt(R.string.test_key_1));
    }

    @Test
    public void testGetIntDefaultValue() {
        assertEquals("Method getInt doesn't return the default value 001",
                DEFAULT_VALUE_INTEGER, shPref.getInt(KEY_1, DEFAULT_VALUE_INTEGER));

        assertEquals("Method getInt doesn't return the default value 002",
                DEFAULT_VALUE_INTEGER, shPref.getInt(R.string.test_key_1, DEFAULT_VALUE_INTEGER));
    }


    // Put and get boolean

    @Test
    public void testPutAndGetBoolean() {
        put(KEY_1, VALUE_BOOLEAN);
        assertEquals("Boolean put and get error 001", VALUE_BOOLEAN, shPref.getBoolean(KEY_1));

        put(R.string.test_key_1, VALUE_BOOLEAN);
        assertEquals("Boolean put and get error 002", VALUE_BOOLEAN, shPref.getBoolean(R.string.test_key_1));
    }

    @Test
    public void testGetBooleanDefaultValue() {
        assertEquals("Method getBoolean doesn't return the default value 001",
                DEFAULT_VALUE_BOOLEAN, shPref.getBoolean(KEY_1, DEFAULT_VALUE_BOOLEAN));

        assertEquals("Method getBoolean doesn't return the default value 002",
                DEFAULT_VALUE_BOOLEAN, shPref.getBoolean(R.string.test_key_1, DEFAULT_VALUE_BOOLEAN));
    }


    // Put and get float

    @Test
    public void testPutAndGetFloat() {
        put(KEY_1, VALUE_FLOAT);
        assertEquals("Float put and get error 001", VALUE_FLOAT, shPref.getFloat(KEY_1), 0.0f);

        put(R.string.test_key_1, VALUE_FLOAT);
        assertEquals("Float put and get error 002", VALUE_FLOAT, shPref.getFloat(R.string.test_key_1), 0.0f);
    }

    @Test
    public void testGetFloatDefaultValue() {
        assertEquals("Method getFloat doesn't return the default value 001",
                DEFAULT_VALUE_FLOAT, shPref.getFloat(KEY_1, DEFAULT_VALUE_FLOAT), 0.0f);

        assertEquals("Method getFloat doesn't return the default value 002",
                DEFAULT_VALUE_FLOAT, shPref.getFloat(R.string.test_key_1, DEFAULT_VALUE_FLOAT), 0.0f);
    }


    // Put and get double

    @Test
    public void testPutAndGetDouble() {
        put(KEY_1, VALUE_DOUBLE);
        assertEquals("Double put and get error 001", VALUE_DOUBLE, shPref.getDouble(KEY_1), 0.0);

        put(R.string.test_key_1, VALUE_DOUBLE);
        assertEquals("Double put and get error 002", VALUE_DOUBLE, shPref.getDouble(R.string.test_key_1), 0.0);
    }

    @Test
    public void testGetDoubleDefaultValue() {
        assertEquals("Method getDouble doesn't return the default value 001",
                DEFAULT_VALUE_DOUBLE, shPref.getDouble(KEY_1, DEFAULT_VALUE_DOUBLE), 0.0);

        assertEquals("Method getDouble doesn't return the default value 002",
                DEFAULT_VALUE_DOUBLE, shPref.getDouble(R.string.test_key_1, DEFAULT_VALUE_DOUBLE), 0.0);
    }


    // Put and get long

    @Test
    public void testPutAndGetLong() {
        put(KEY_1, VALUE_LONG);
        assertEquals("Long put and get error 001", VALUE_LONG, shPref.getLong(KEY_1));

        put(R.string.test_key_1, VALUE_LONG);
        assertEquals("Long put and get error 002", VALUE_LONG, shPref.getLong(R.string.test_key_1));
    }

    @Test
    public void testGetLongDefaultValue() {
        assertEquals("Method getLong doesn't return the default value 001",
                DEFAULT_VALUE_LONG, shPref.getLong(KEY_1, DEFAULT_VALUE_LONG));

        assertEquals("Method getLong doesn't return the default value 002",
                DEFAULT_VALUE_LONG, shPref.getLong(R.string.test_key_1, DEFAULT_VALUE_LONG));
    }


    // List tests
    @Test
    public void putAndGetList() {
        ArrayList<Integer> integerList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            integerList.add(i);
        }
        shPref.putList(KEY_1, integerList);
        assertEquals("Put and get list error with Integer", integerList, shPref.getListOfIntegers(KEY_1));

        ArrayList<Long> longList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            longList.add((long) i);
        }
        shPref.putList(KEY_1, longList);
        assertEquals("Put and get list error with Long", longList, shPref.getListOfLongs(KEY_1));

        ArrayList<Boolean> booleanList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            booleanList.add(true);
        }
        shPref.putList(KEY_1, booleanList);
        assertEquals("Put and get list error with Boolean", booleanList, shPref.getListOfBooleans(KEY_1));

        ArrayList<Float> floatList = new ArrayList<>();
        for (float i = 1; i < 11; i++) {
            floatList.add(i / 2);
        }
        shPref.putList(KEY_1, floatList);
        assertEquals("Put and get list error with Float", floatList, shPref.getListOfFloats(KEY_1));

        ArrayList<String> stringList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            stringList.add(String.format(Locale.ENGLISH, "Test %d", i));
        }
        shPref.putList(KEY_1, stringList);
        assertEquals("Put and get list error with String", stringList, shPref.getListOfStrings(KEY_1));

        ArrayList<Double> doubleList = new ArrayList<>();
        for (double i = 1; i < 11; i++) {
            doubleList.add(i / 2);
        }
        shPref.putList(KEY_1, doubleList);
        assertEquals("Put and get list error with Double", doubleList, shPref.getListOfDoubles(KEY_1));
    }

    @Test
    public void getMixedListTest() {
        ArrayList<Object> list = new ArrayList<>();
        list.add(VALUE_INTEGER);
        list.add(VALUE_LONG);
        list.add(VALUE_BOOLEAN);
        list.add(VALUE_FLOAT);
        list.add(VALUE_STRING);
        //list.add(VALUE_DOUBLE);

        shPref.putList(KEY_1, list);

        ArrayList result = shPref.getMixedList(KEY_1);
        assertEquals("Mixed list getting int value fail", VALUE_INTEGER, result.get(0));
        assertEquals("Mixed list getting long value fail", VALUE_LONG, result.get(1));
        assertEquals("Mixed list getting boolean value fail", VALUE_BOOLEAN, result.get(2));
        assertEquals("Mixed list getting float value fail", VALUE_FLOAT, result.get(3));
        assertEquals("Mixed list getting String value fail", VALUE_STRING, result.get(4));
        //assertEquals("Mixed list getting double value fail", VALUE_DOUBLE, result.get(5));
    }

    // Get method

    @Test
    public void getTest() {
        put(KEY_1, VALUE_INTEGER);
        assertEquals("Get method check for integer failed", VALUE_INTEGER, (int) shPref.get(KEY_1));

        //shPref.put(KEY_1, VALUE_DOUBLE);
        //assertEquals("Get method check for double failed", VALUE_DOUBLE, (double) shPref.get(KEY_1), 0.0);

        put(KEY_1, VALUE_STRING);
        assertEquals("Get method check for String failed", VALUE_STRING, shPref.get(KEY_1));

        put(KEY_1, VALUE_BOOLEAN);
        assertEquals("Get method check for boolean failed", VALUE_BOOLEAN, shPref.get(KEY_1));

        put(KEY_1, VALUE_FLOAT);
        assertEquals("Get method check for float failed", VALUE_FLOAT, (float) shPref.get(KEY_1), 0.0f);

        put(KEY_1, VALUE_LONG);
        assertEquals("Get method check for long failed", VALUE_LONG, (long) shPref.get(KEY_1));
    }

    // Editor tests
    @Test
    public void editorTest() {
        new ShPref.Editor()
                .put(KEY_1, VALUE_LONG)
                .put(KEY_2, VALUE_FLOAT)
                .put(KEY_3, VALUE_BOOLEAN)
                .put(KEY_4, VALUE_STRING)
                .put(KEY_5, VALUE_DOUBLE)
                .put(KEY_6, VALUE_INTEGER)
                .apply();
        assertEquals("editorTest - get long fail", VALUE_LONG, shPref.getLong(KEY_1));
        assertEquals("editorTest - get float fail", VALUE_FLOAT, shPref.getFloat(KEY_2), 0.0);
        assertEquals("editorTest - get boolean fail", VALUE_BOOLEAN, shPref.getBoolean(KEY_3));
        assertEquals("editorTest - get String fail", VALUE_STRING, shPref.getString(KEY_4));
        assertEquals("editorTest - get double fail", VALUE_DOUBLE, shPref.getDouble(KEY_5), 0.0);
        assertEquals("editorTest - get int fail", VALUE_INTEGER, shPref.getInt(KEY_6));

        shPref.clear();

        new ShPref.Editor()
                .put(R.string.test_key_1, VALUE_LONG)
                .put(R.string.test_key_2, VALUE_FLOAT)
                .put(R.string.test_key_3, VALUE_BOOLEAN)
                .put(R.string.test_key_4, VALUE_STRING)
                .put(R.string.test_key_5, VALUE_DOUBLE)
                .put(R.string.test_key_6, VALUE_INTEGER)
                .commit();

        assertEquals("editorTest - get long fail", VALUE_LONG, shPref.getLong(R.string.test_key_1));
        assertEquals("editorTest - get float fail", VALUE_FLOAT, shPref.getFloat(R.string.test_key_2), 0.0);
        assertEquals("editorTest - get boolean fail", VALUE_BOOLEAN, shPref.getBoolean(R.string.test_key_3));
        assertEquals("editorTest - get String fail", VALUE_STRING, shPref.getString(R.string.test_key_4));
        assertEquals("editorTest - get double fail", VALUE_DOUBLE, shPref.getDouble(R.string.test_key_5), 0.0);
        assertEquals("editorTest - get double fail", VALUE_INTEGER, shPref.getInt(R.string.test_key_6));

        shPref.clear();

        put(KEY_2, VALUE_STRING);

        new ShPref.Editor()
                .put(KEY_1, VALUE_LONG)
                .remove(KEY_2)
                .put(KEY_3, VALUE_BOOLEAN)
                .commit();

        assertEquals("editorTest - get long fail", VALUE_LONG, shPref.getLong(KEY_1));
        assertFalse("editorTest - remove pref failed", shPref.contains(KEY_2));
        assertEquals("editorTest - get boolean fail", VALUE_BOOLEAN, shPref.getBoolean(KEY_3));

    }
}

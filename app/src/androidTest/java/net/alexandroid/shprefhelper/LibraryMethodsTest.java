package net.alexandroid.shprefhelper;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import net.alexandroid.shpref.ShPref;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


@RunWith(AndroidJUnit4.class)
public class LibraryMethodsTest {

    private static final String KEY_1 = "KEY_1";

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

    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        assertEquals("net.alexandroid.shprefhelper", appContext.getPackageName());
        ShPref.init(appContext, ShPref.COMMIT);
    }

    @Before
    public void beforeEachTestClearShPref() {
        ShPref.clear();
        System.out.println("=== ShPref is cleared ===");
    }

    // Clear

    @Test
    public void testClear() {
        ShPref.put(KEY_1, VALUE_STRING);
        ShPref.clear();
        assertNull("Clear method doesn't remove all values from Shared Preferences", ShPref.getString(KEY_1));
    }

    // Remove

    @Test
    public void removeTest() {
        ShPref.put(KEY_1, VALUE_INTEGER);
        ShPref.remove(KEY_1);
        assertEquals("Remove method doesn't remove value", 0, ShPref.getInt(KEY_1));
    }

    // Contains

    @Test
    public void testContains() {
        ShPref.put(KEY_1, VALUE_STRING);
        boolean isContains = ShPref.contains(KEY_1);
        assertEquals("Contains method return wrong result. 001", true, isContains);

        ShPref.put(R.string.test_key_1, VALUE_STRING);
        isContains = ShPref.contains(R.string.test_key_1);
        assertEquals("Contains method return wrong result. 002", true, isContains);
    }

    // Put and get String
    @Test
    public void testPutStringNull() {
        ShPref.put(KEY_1, null);
        assertNull("String is not null as should be 001", ShPref.getString(KEY_1));

        ShPref.put(R.string.test_key_1, null);
        assertNull("String is not null as should be 002", ShPref.getString(R.string.test_key_1));
    }

    @Test
    public void testPutAndGetString() {
        ShPref.put(KEY_1, VALUE_STRING);
        assertEquals("String put and get error 001", VALUE_STRING, ShPref.getString(KEY_1));

        ShPref.put(R.string.test_key_1, VALUE_STRING);
        assertEquals("String put and get error 002", VALUE_STRING, ShPref.getString(R.string.test_key_1));
    }

    @Test
    public void testGetStringDefaultValue() {
        assertEquals("Method getString doesn't return the default value 001",
                DEFAULT_VALUE_STRING, ShPref.getString(KEY_1, DEFAULT_VALUE_STRING));

        assertEquals("Method getString doesn't return the default value 002",
                DEFAULT_VALUE_STRING, ShPref.getString(R.string.test_key_1, DEFAULT_VALUE_STRING));
    }

    // Put and get int

    @Test
    public void testPutAndGetInt() {
        ShPref.put(KEY_1, VALUE_INTEGER);
        assertEquals("Int put and get error 001", VALUE_INTEGER, ShPref.getInt(KEY_1));

        ShPref.put(R.string.test_key_1, VALUE_INTEGER);
        assertEquals("Int put and get error 002", VALUE_INTEGER, ShPref.getInt(R.string.test_key_1));
    }

    @Test
    public void testGetIntDefaultValue() {
        assertEquals("Method getInt doesn't return the default value 001",
                DEFAULT_VALUE_INTEGER, ShPref.getInt(KEY_1, DEFAULT_VALUE_INTEGER));

        assertEquals("Method getInt doesn't return the default value 002",
                DEFAULT_VALUE_INTEGER, ShPref.getInt(R.string.test_key_1, DEFAULT_VALUE_INTEGER));
    }


    // Put and get boolean

    @Test
    public void testPutAndGetBoolean() {
        ShPref.put(KEY_1, VALUE_BOOLEAN);
        assertEquals("Boolean put and get error 001", VALUE_BOOLEAN, ShPref.getBoolean(KEY_1));

        ShPref.put(R.string.test_key_1, VALUE_BOOLEAN);
        assertEquals("Boolean put and get error 002", VALUE_BOOLEAN, ShPref.getBoolean(R.string.test_key_1));
    }

    @Test
    public void testGetBooleanDefaultValue() {
        assertEquals("Method getBoolean doesn't return the default value 001",
                DEFAULT_VALUE_BOOLEAN, ShPref.getBoolean(KEY_1, DEFAULT_VALUE_BOOLEAN));

        assertEquals("Method getBoolean doesn't return the default value 002",
                DEFAULT_VALUE_BOOLEAN, ShPref.getBoolean(R.string.test_key_1, DEFAULT_VALUE_BOOLEAN));
    }



    // Put and get float

    @Test
    public void testPutAndGetFloat() {
        ShPref.put(KEY_1, VALUE_FLOAT);
        assertEquals("Float put and get error 001", VALUE_FLOAT, ShPref.getFloat(KEY_1), 0.0f);

        ShPref.put(R.string.test_key_1, VALUE_FLOAT);
        assertEquals("Float put and get error 002", VALUE_FLOAT, ShPref.getFloat(R.string.test_key_1), 0.0f);
    }

    @Test
    public void testGetFloatDefaultValue() {
        assertEquals("Method getFloat doesn't return the default value 001",
                DEFAULT_VALUE_FLOAT, ShPref.getFloat(KEY_1, DEFAULT_VALUE_FLOAT), 0.0f);

        assertEquals("Method getFloat doesn't return the default value 002",
                DEFAULT_VALUE_FLOAT, ShPref.getFloat(R.string.test_key_1, DEFAULT_VALUE_FLOAT), 0.0f);
    }



    // Put and get double

    @Test
    public void testPutAndGetDouble() {
        ShPref.put(KEY_1, VALUE_DOUBLE);
        assertEquals("Double put and get error 001", VALUE_DOUBLE, ShPref.getDouble(KEY_1), 0.0);

        ShPref.put(R.string.test_key_1, VALUE_DOUBLE);
        assertEquals("Double put and get error 002", VALUE_DOUBLE, ShPref.getDouble(R.string.test_key_1), 0.0);
    }

    @Test
    public void testGetDoubleDefaultValue() {
        assertEquals("Method getDouble doesn't return the default value 001",
                DEFAULT_VALUE_DOUBLE, ShPref.getDouble(KEY_1, DEFAULT_VALUE_DOUBLE), 0.0);

        assertEquals("Method getDouble doesn't return the default value 002",
                DEFAULT_VALUE_DOUBLE, ShPref.getDouble(R.string.test_key_1, DEFAULT_VALUE_DOUBLE), 0.0);
    }


    // Put and get long

    @Test
    public void testPutAndGetLong() {
        ShPref.put(KEY_1, VALUE_LONG);
        assertEquals("Long put and get error 001", VALUE_LONG, ShPref.getLong(KEY_1));

        ShPref.put(R.string.test_key_1, VALUE_LONG);
        assertEquals("Long put and get error 002", VALUE_LONG, ShPref.getLong(R.string.test_key_1));
    }

    @Test
    public void testGetLongDefaultValue() {
        assertEquals("Method getLong doesn't return the default value 001",
                DEFAULT_VALUE_LONG, ShPref.getLong(KEY_1, DEFAULT_VALUE_LONG));

        assertEquals("Method getLong doesn't return the default value 002",
                DEFAULT_VALUE_LONG, ShPref.getLong(R.string.test_key_1, DEFAULT_VALUE_LONG));
    }


    // List tests
    @Test
    public void putAndGetList() {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        ShPref.putList(KEY_1, list);
        assertEquals("Put and get list error", list, ShPref.getListOfIntegers(KEY_1));
    }

    @Test
    public void getMixedListTest() {
        ArrayList<Object> list = new ArrayList<>();
        list.add(VALUE_INTEGER);
        list.add(VALUE_LONG);
        list.add(VALUE_BOOLEAN);
        list.add(VALUE_DOUBLE);
        list.add(VALUE_FLOAT);
        list.add(VALUE_STRING);

        ShPref.putList(KEY_1, list);

        ArrayList result = ShPref.getMixedList(KEY_1);
        assertEquals("Mixed list getting int value fail", VALUE_INTEGER, result.get(0));
        assertEquals("Mixed list getting long value fail", VALUE_LONG, result.get(1));
        assertEquals("Mixed list getting boolean value fail", VALUE_BOOLEAN, result.get(2));
        assertEquals("Mixed list getting double value fail", VALUE_DOUBLE, result.get(3));
        assertEquals("Mixed list getting float value fail", VALUE_FLOAT, result.get(4));
        assertEquals("Mixed list getting String value fail", VALUE_STRING, result.get(5));


    }

    // Get method

    @Test
    public void getTest() {

    }

    // Editor tests
    @Test
    public void editorTest() {

    }
}

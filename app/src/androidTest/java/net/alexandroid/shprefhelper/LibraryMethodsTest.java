package net.alexandroid.shprefhelper;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import net.alexandroid.shpref.ShPref;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

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
    private static final double VALUE_DOUBLE = Double.MAX_VALUE;
    private static final long VALUE_LONG = Long.MAX_VALUE;

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
        assertEquals("String put and get error 001", VALUE_BOOLEAN, ShPref.getBoolean(KEY_1));

        ShPref.put(R.string.test_key_1, VALUE_BOOLEAN);
        assertEquals("String put and get error 002", VALUE_BOOLEAN, ShPref.getBoolean(R.string.test_key_1));
    }

    @Test
    public void testGetBooleanDefaultValue() {
        assertEquals("Method getBoolean doesn't return the default value 001",
                DEFAULT_VALUE_BOOLEAN, ShPref.getBoolean(KEY_1, DEFAULT_VALUE_BOOLEAN));

        assertEquals("Method getBoolean doesn't return the default value 002",
                DEFAULT_VALUE_BOOLEAN, ShPref.getBoolean(R.string.test_key_1, DEFAULT_VALUE_BOOLEAN));
    }




    // TODO float
    // TODO double
    // TODO long
    // TODO putC
    // TODO putA
    // TODO putList
    // TODO getMixed list
    // TODO get
    // TODO remove
    // TODO removeA
    // TODO removeC
    // TODO Editor


}

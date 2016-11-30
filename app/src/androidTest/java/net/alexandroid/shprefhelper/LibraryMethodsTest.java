package net.alexandroid.shprefhelper;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import net.alexandroid.shpref.ShPref;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;


@RunWith(AndroidJUnit4.class)
public class LibraryMethodsTest {

    private static final String KEY_1 = "KEY_1";

    private static final String VALUE_STRING = "Some string";
    private static final String DEFAULT_VALUE_STRING = "Some default value";
    private static final int VALUE_INTEGER = Integer.MAX_VALUE;
    private static final boolean VALUE_BOOLEAN = true;
    private static final float VALUE_FLOAT = Float.MAX_VALUE;
    private static final double VALUE_DOUBLE = Double.MAX_VALUE;
    private static final long VALUE_LONG = Long.MAX_VALUE;
    private static final String VALUE_STRING_NULL = null;

    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        assertEquals("net.alexandroid.shprefhelper", appContext.getPackageName());
    }

    @Before
    public void beforeEachTestClearShPref() {
        ShPref.clear();
        System.out.println("=== ShPref is cleared ===");
    }

    // TODO Test clear method

    @Test
    public void testContains() {
        ShPref.put(KEY_1, VALUE_STRING);
        boolean isContains = ShPref.contains(KEY_1);
        assertEquals("Contains method return wrong result. 001", true, isContains);

        ShPref.put(R.string.test_key_1, VALUE_STRING);
        isContains = ShPref.contains(R.string.test_key_1);
        assertEquals("Contains method return wrong result. 002", true, isContains);
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
        System.out.println("DEFAULT_VALUE_STRING: " + DEFAULT_VALUE_STRING);
        System.out.println("Default value returned: " + ShPref.getString(KEY_1, DEFAULT_VALUE_STRING));
        assertEquals("Method getString doesn't return the write default value 001",
                DEFAULT_VALUE_STRING, ShPref.getString(KEY_1, DEFAULT_VALUE_STRING));

        assertEquals("Method getString doesn't return the write default value 002",
                DEFAULT_VALUE_STRING, ShPref.getString(R.string.test_key_1, DEFAULT_VALUE_STRING));
    }


}

package net.alexandroid.shprefhelper;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import net.alexandroid.shpref.ShPref;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;


@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    private static final String STRING_KEY_1 = "1";
    private static final String STRING_KEY_2 = "2";
    private static final String STRING_KEY_3 = "3";
    private static final String STRING_KEY_4 = "4";
    private static final String STRING_KEY_5 = "5";
    private static final String STRING_KEY_6 = "6";
    private static final String STRING_KEY_7 = "7";

    private static final String VALUE_1 = "VALUE_1";
    private static final String VALUE_2 = "VALUE_2";
    private static final String VALUE_3 = "VALUE_3";
    private static final String VALUE_4 = "VALUE_4";
    private static final String VALUE_5 = "VALUE_5";
    private static final String VALUE_6 = "VALUE_6";
    private static final String VALUE_7 = "VALUE_7";

    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        assertEquals("net.alexandroid.shprefhelper", appContext.getPackageName());
    }

    @Test
    public void testContains() {
        ShPref.put(STRING_KEY_1, VALUE_1);
        boolean isContains = ShPref.contains(STRING_KEY_1);
        assertEquals("Contains method return wrong result. 001", true, isContains);

        ShPref.put(R.string.test_key_1, VALUE_1);
        isContains = ShPref.contains(R.string.test_key_1);
        assertEquals("Contains method return wrong result. 002", true, isContains);
    }
}

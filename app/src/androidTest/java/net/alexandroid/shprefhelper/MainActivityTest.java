package net.alexandroid.shprefhelper;

import android.app.Activity;
import android.support.test.runner.AndroidJUnit4;
import android.widget.TextView;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public MainActivityTestRule<MainActivity> mMainActivityActivityTestRule = new MainActivityTestRule<>(MainActivity.class);

    @Test
    public void testUI() {
        Activity activity = mMainActivityActivityTestRule.getActivity();
        assertNotNull(activity.findViewById(R.id.textView));
        TextView textView = (TextView) activity.findViewById(R.id.textView);
        assertTrue(textView.isShown());
        assertEquals(textView.getText(), activity.getString(R.string.sample_app));

    }

}

package net.alexandroid.shprefhelper;


import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.hasFocus;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;

@RunWith(AndroidJUnit4.class)
public class EspressoTest {

    @Rule
    public MainActivityTestRule<MainActivity> mMainActivityActivityTestRule = new MainActivityTestRule<>(MainActivity.class);

    @Test
    public void testEspresso() {
        ViewInteraction interaction = onView(
                allOf(withId(R.id.textView),
                        withText("Sample app"),
                        hasFocus()));
        interaction.perform(ViewActions.replaceText("Replacing text"));

        ViewInteraction interaction2 = onView(
                allOf(withId(R.id.textView),
                        withText("Replacing text")));
        interaction2.check(ViewAssertions.matches(hasFocus()));
    }

    @Test
    public void testEspressoShorter() {
        onView(allOf(withId(R.id.textView), withText("Sample app"), hasFocus()))
                .perform(ViewActions.replaceText("Replacing text"));

        onView(allOf(withId(R.id.textView), withText("Replacing text")))
                .check(ViewAssertions.matches(hasFocus()));
    }
}

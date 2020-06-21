package net.alexandroid.shprefhelper;


import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class EspressoTest {

    @Rule
    public MainActivityTestRule<MainActivity> mMainActivityActivityTestRule = new MainActivityTestRule<>(MainActivity.class);

    @Test
    public void testEspresso() {
        ViewInteraction interaction = onView(withId(R.id.textView));
//        interaction.perform(ViewActions.replaceText("Replacing text"));
//        interaction.perform(ViewActions.replaceText("Replacing text"));
//
//        ViewInteraction interaction2 = onView(withText("Replacing text"));
//        interaction2.check(ViewAssertions.matches(hasFocus()));

    }

    @Test
    public void testEspressoShorter() {
        ViewInteraction interactionFake = onView(withId(R.id.textView));
//        onView(withId(R.id.textView)).perform(ViewActions.replaceText("Replacing text"));
//        onView(withId(R.id.textView)).check(ViewAssertions.matches(hasFocus()));
    }
}

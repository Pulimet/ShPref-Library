package net.alexandroid.shprefhelper;

import android.app.Activity;
import android.content.Intent;

import androidx.test.rule.ActivityTestRule;


public class TheActivityTestRule<A extends Activity> extends ActivityTestRule<A> {

    public TheActivityTestRule(Class<A> activityClass) {
        super(activityClass);
    }

    @Override
    protected void beforeActivityLaunched() {
        super.beforeActivityLaunched();
    }

    @Override
    protected void afterActivityLaunched() {
        super.afterActivityLaunched();
    }

    @Override
    protected void afterActivityFinished() {
        super.afterActivityFinished();
    }


    @Override
    protected Intent getActivityIntent() {
        return super.getActivityIntent();
    }
}

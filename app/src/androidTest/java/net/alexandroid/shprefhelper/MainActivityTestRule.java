package net.alexandroid.shprefhelper;

import android.app.Activity;
import android.content.Intent;
import android.support.test.rule.ActivityTestRule;


public class MainActivityTestRule<A extends Activity> extends ActivityTestRule<A> {

    public MainActivityTestRule(Class<A> activityClass) {
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

package net.alexandroid.shprefhelper;

import android.app.Activity;
import android.content.Intent;
import android.support.test.rule.ActivityTestRule;

import net.alexandroid.shpref.MyLog;


public class MainActivityTestRule<A extends Activity> extends ActivityTestRule<A> {

    public MainActivityTestRule(Class<A> activityClass) {
        super(activityClass);
    }

    @Override
    protected void beforeActivityLaunched() {
        super.beforeActivityLaunched();
        MyLog.showLogs(true);
        MyLog.d("");
    }

    @Override
    protected void afterActivityLaunched() {
        super.afterActivityLaunched();
        MyLog.d("");
    }

    @Override
    protected void afterActivityFinished() {
        super.afterActivityFinished();
        MyLog.d("");
    }


    @Override
    protected Intent getActivityIntent() {
        MyLog.d("");
        return super.getActivityIntent();
    }
}

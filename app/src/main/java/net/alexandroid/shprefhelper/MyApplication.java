package net.alexandroid.shprefhelper;

import android.app.Application;

import net.alexandroid.shpref.ShPref;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        ShPref.init(this, ShPref.APPLY);
    }
}

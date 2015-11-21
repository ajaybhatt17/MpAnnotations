package com.mp_annotations.sample;

import android.app.Application;

import com.mp_annotations.AnalyticsManager;

/**
 * Created by ajaybhatt on 11/10/15.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AnalyticsManager.getInstance().debugMode(this, true);
        AnalyticsManager.getInstance().init(this, "17c389c2d77a84855587f30ab2621d4e");
    }
}

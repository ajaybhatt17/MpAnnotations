package com.mp_annotations;

import android.content.Context;
import android.util.Log;

import com.mixpanel.android.mpmetrics.MixpanelAPI;
import com.mp_annotations.aspect.TrackEventAspect;

import org.json.JSONObject;

/**
 * Created by ajaybhatt on 11/10/15.
 */
public final class AnalyticsManager {

    private static AnalyticsManager ourInstance = new AnalyticsManager();

    public static AnalyticsManager getInstance() {
        return ourInstance;
    }

    private AnalyticsManager() {
    }

    private String mixpanelKey = null;
    private boolean debugMode = false;
    private Context context = null;
    private boolean enabled = false;

    private static final String TAG = "MP-Annotations";

    public void init(Context context, String mixpanelKey) {
        this.context = context;
        this.mixpanelKey = mixpanelKey;
        enabled = true;
    }

    public void debugMode(Context context, boolean enabled) {
        debugMode = enabled;
        init(context, mixpanelKey != null ? mixpanelKey : "");
        new TrackEventAspect().method();
    }

    public boolean isEnabled() {
        return enabled && mixpanelKey != null && context != null;
    }

    private boolean check(String objectTracked) {
        if (isEnabled())
            return true;
        else if (context != null && mixpanelKey != null)
            Log.w(TAG, "Cannot track " + objectTracked + ". Call 'enable()' before.");
        else
            Log.w(TAG, "Cannot track " + objectTracked + ". Call 'init()' before.");
        return false;
    }

    public void enable() {
        this.enabled = true;
    }

    public void disable() {
        this.enabled = false;
    }

    public void trackEvent(String eventName) {
        if (check("event")){
            if (!debugMode) {
                MixpanelAPI mMixpanelAPI = MixpanelAPI.getInstance(context, mixpanelKey);
                mMixpanelAPI.track(eventName, new JSONObject());
                Log.i(TAG, "Tracked");
            } else {
                Log.i(TAG, "trackEvent(" + eventName + ")");
            }
        }
    }

    public void trackEvent(String eventName, JSONObject params) {
        if(check("event")){
            if (!debugMode) {
                MixpanelAPI mMixpanelAPI = MixpanelAPI.getInstance(context, mixpanelKey);
                mMixpanelAPI.track(eventName, params);
                Log.i(TAG, "Tracked");
            } else {
                Log.i(TAG, "trackEvent(" + eventName + ")");
            }
        }
    }

    public void trackEvent(MixpanelEvent mixpanelEvent) {
        trackEvent(mixpanelEvent.getEventName(), mixpanelEvent.getParams());
    }

    public void recordProfile(String distinctId, JSONObject jsonObject) {
        if (!debugMode) {
            MixpanelAPI mMixpanelAPI = MixpanelAPI.getInstance(context, mixpanelKey);
            mMixpanelAPI.identify(distinctId);
            MixpanelAPI.People mPeople = mMixpanelAPI.getPeople();
            mPeople.identify(distinctId);
            mPeople.set(jsonObject);
        } else {
            Log.i(TAG, "Record Profile(" + distinctId + ")");
        }

    }

    public void recordProfile(JSONObject jsonObject) {
        MixpanelAPI mMixpanelAPI = MixpanelAPI.getInstance(context, mixpanelKey);
        String distinctId = mMixpanelAPI.getDistinctId();
        recordProfile(distinctId, jsonObject);
    }

}

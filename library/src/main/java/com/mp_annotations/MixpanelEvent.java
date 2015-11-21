package com.mp_annotations;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by ajaybhatt on 11/10/15.
 */
public class MixpanelEvent {

    private String eventName;
    private JSONObject params;

    public MixpanelEvent(String eventName, HashMap<String, String> params) {
        this.eventName = eventName;
        for(String key : params.keySet()){
            try {
                this.params.put(key, params.get(key));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public MixpanelEvent(String eventName, String... params) {
        this.eventName = eventName;
        int i = 0;
        for(String param : params){
            try {
                this.params.put("val" + String.valueOf(i), param);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            i++;
        }
    }

    public MixpanelEvent(String eventName, JSONObject params){
        this.eventName = eventName;
        this.params = params;
    }

    public JSONObject getParams(){
        return params;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }
}

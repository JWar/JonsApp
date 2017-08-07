package com.jraw.android.jonsapp.data.model;

import com.jraw.android.jonsapp.MainActivity;

import org.json.JSONObject;

/**
 * Created by JonGaming on 17/07/2017.
 *
 */

public class Conversation extends entity {
    private String COTitle;
    private String COCreatedBy;
    private String CODateCreated;

    public Conversation() {}

    public Conversation(JSONObject aObj) {
        try {
            if (aObj.has("title")) {
                setCOTitle(aObj.getString("title"));
            }
            if (aObj.has("createdby")) {
                setCOCreatedBy(aObj.getString("createdby"));
            }
            if (aObj.has("datecreated")) {
                setCODateCreated(aObj.getString("datecreated"));
            }
        } catch (Exception e) {
            MainActivity.logDebug("Error in Conversation constructor: "+e.getMessage());
        }
    }

    public void setCOTitle(String aStr) {
        COTitle = aStr;
    }
    public void setCOCreatedBy(String aStr) {
        COCreatedBy = aStr;
    }
    public void setCODateCreated(String aStr) {
        CODateCreated = aStr;
    }
    public String getCOTitle() {
        return COTitle;
    }
    public String getCOCreatedBy() {
        return COCreatedBy;
    }
    public String getCODateCreated() {
        return CODateCreated;
    }
}

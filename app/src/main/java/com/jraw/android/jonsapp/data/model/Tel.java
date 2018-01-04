package com.jraw.android.jonsapp.data.model;

import com.jraw.android.jonsapp.utils.Utils;
import org.json.JSONObject;

/**
 * Created by JonGaming on 17/07/2017.
 *
 */

public class Tel extends entity {
    private String TENumber;

    public Tel() {}

    public Tel(JSONObject aObj) {
        try {
            if (aObj.has("tel")) {
                setTENumber(aObj.getString("tel"));
            }
        } catch (Exception e) {
            Utils.logDebug("Error in Tel constructor: "+e.getMessage());
        }
    }

    public void setTENumber(String aStr) {
        TENumber = aStr;
    }
    public String getTENumber() {
        return TENumber;
    }
}

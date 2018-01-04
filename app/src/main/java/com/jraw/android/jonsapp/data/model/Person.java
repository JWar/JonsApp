package com.jraw.android.jonsapp.data.model;

import com.jraw.android.jonsapp.utils.Utils;
import org.json.JSONObject;

/**
 * Created by JonGaming on 17/07/2017.
 *
 */

public class Person extends entity {
    private String PEFname;
    private String PESname;

    public Person() {}

    public Person(JSONObject aObj) {
        try {
            if (aObj.has("fname")) {
                setPEFname(aObj.getString("fname"));
            }
            if (aObj.has("sname")) {
                setPESname(aObj.getString("sname"));
            }
        } catch (Exception e) {
            Utils.logDebug("Error in Person constructor: "+e.getMessage());
        }
    }

    public void setPEFname(String aName) {
        PEFname= aName;
    }
    public void setPESname(String aName) {
        PESname = aName;
    }
    public String getPEFname() {
        return PEFname;
    }
    public String getPESname() {
        return PESname;
    }
}

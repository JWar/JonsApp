package com.jraw.android.jonsapp.data.model;

import com.jraw.android.jonsapp.MainActivity;

import org.json.JSONObject;

/**
 * Created by JonGaming on 17/07/2017.
 *
 */

public class Msg extends entity {
    private int MSCOID = 0;
    private int MSToID = 0;
    private int MSFromID = 0;
    private String MSBody;
    private String MSEventDate;
    private int MSType = 0;
    private String MSData;
    private String MSResult;

    public Msg() {}

    public Msg(JSONObject aObj) {
        try {
            if (aObj.has("coid")) {
                setMSCOID(aObj.getInt("coid"));
            }
            if (aObj.has("toid")) {
                setMSToID(aObj.getInt("toid"));
            }
            if (aObj.has("fromid")) {
                setMSFromID(aObj.getInt("fromid"));
            }
            if (aObj.has("body")) {
                setMSBody(aObj.getString("body"));
            }
            if (aObj.has("eventdate")) {
                setMSEventDate(aObj.getString("eventdate"));
            }
            if (aObj.has("type")) {
                setMSType(aObj.getInt("type"));
            }
            if (aObj.has("data")) {
                setMSData(aObj.getString("data"));
            }
            if (aObj.has("result")) {
                setMSResult(aObj.getString("result"));
            }
        } catch (Exception e) {
            MainActivity.logDebug("Error in Msg constructor: "+e.getMessage());
        }
    }

    public void setMSCOID(int aInt) {
        MSCOID = aInt;
    }
    public void setMSToID(int aInt) {
        MSToID = aInt;
    }
    public void setMSFromID(int aInt) {
        MSFromID = aInt;
    }
    public void setMSBody(String aStr) {
        MSBody = aStr;
    }
    public void setMSEventDate(String aStr) {
        MSEventDate = aStr;
    }
    public void setMSType(int aInt) {
        MSType = aInt;
    }
    public void setMSData(String aStr) {
        MSData = aStr;
    }
    public void setMSResult(String aStr) {
        MSResult = aStr;
    }

    public int getMSCOID() {
        return MSCOID;
    }
    public int getMSToID() {
        return MSToID;
    }
    public int getMSFromID() {
        return MSFromID;
    }
    public String getMSBody() {
        return MSBody;
    }
    public String getMSEventDate() {
        return MSEventDate;
    }
    public int getMSType() {
        return MSType;
    }
    public String getMSData() {
        return MSData;
    }
    public String getMSResult() {
        return MSResult;
    }
}

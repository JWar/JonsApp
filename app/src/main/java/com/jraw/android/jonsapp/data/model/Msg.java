package com.jraw.android.jonsapp.data.model;

import com.jraw.android.jonsapp.utils.Utils;
import org.json.JSONObject;

/**
 * Created by JonGaming on 17/07/2017.
 *
 */

public class Msg extends entity {

    public enum MSG_TYPES {
        TEXT, IMAGE, VIDEO
    }
    //Of course if its sent then its not delivered, if its delivered its not read...
    public enum RESULTS {
        SENT, DELIVERED, READ, FAILED
    }
    //Which conversation this msg is part of, uses public id...
    private int MSCOPublicId = 0;
    //Supposed to be the ID this msg is to but redundant given the way COID handles everything
    private int MSToId = 0;
    //See MSToID above, although knowing who the msg is from is necessary. Remove toId?
    private int MSFromId = 0;
    //Content of Msg
    private String MSBody;
    //Msg date
    private String MSEventDate;
    //Type of msg? Text,image,video?
    private int MSType = 0;
    //Data?? Presumably if this msg has image or video
    private String MSData;
    //Read,sent,delivered etc...
    private int MSResult;

    public Msg() {}

    public Msg(JSONObject aObj) {
        try {
            if (aObj.has("coid")) {
                setMSCOPublicId(aObj.getInt("coid"));
            }
            if (aObj.has("toid")) {
                setMSToId(aObj.getInt("toid"));
            }
            if (aObj.has("fromid")) {
                setMSFromId(aObj.getInt("fromid"));
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
                setMSResult(aObj.getInt("result"));
            }
        } catch (Exception e) {
            Utils.logDebug("Error in Msg constructor: "+e.getMessage());
        }
    }

    public void setMSCOPublicId(int aInt) {
        MSCOPublicId = aInt;
    }
    public void setMSToId(int aInt) {
        MSToId = aInt;
    }
    public void setMSFromId(int aInt) {
        MSFromId = aInt;
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
    public void setMSResult(int aInt) {
        MSResult = aInt;
    }

    public int getMSCOPublicId() {
        return MSCOPublicId;
    }
    public int getMSToId() {
        return MSToId;
    }
    public int getMSFromId() {
        return MSFromId;
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
    public int getMSResult() {
        return MSResult;
    }
}

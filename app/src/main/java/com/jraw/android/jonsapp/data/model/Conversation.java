package com.jraw.android.jonsapp.data.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;

import com.google.gson.annotations.SerializedName;
import com.jraw.android.jonsapp.utils.Utils;

import org.json.JSONObject;

/**
 * Created by JonGaming on 17/07/2017.
 *
 */
@Entity(tableName = "conversation"/*, foreignKeys = @ForeignKey(
        entity = PeCo.class,
        childColumns = "COPublicId",
        parentColumns = "PCCOPublicId",
        onDelete = ForeignKey.CASCADE)*/)
public class Conversation extends entity {
    private String COTitle;
    private int COPublicId;
    private String COCreatedBy;
    private String CODateCreated;

    public Conversation() {}

    //Hmm redundant due to GSON...?
    public Conversation(JSONObject aObj) {
        try {
            if (aObj.has("title")) {
                setCOTitle(aObj.getString("title"));
            }
            if (aObj.has("publicid")) {
                setCOPublicId(aObj.getInt("publicid"));
            }
            if (aObj.has("createdby")) {
                setCOCreatedBy(aObj.getString("createdby"));
            }
            if (aObj.has("datecreated")) {
                setCODateCreated(aObj.getString("datecreated"));
            }
        } catch (Exception e) {
            Utils.logDebug("Error in Conversation constructor: "+e.getMessage());
        }
    }

    public void setCOTitle(String aStr) {
        COTitle = aStr;
    }
    public void setCOPublicId(int aPublicId) {COPublicId=aPublicId;}
    public void setCOCreatedBy(String aStr) {
        COCreatedBy = aStr;
    }
    public void setCODateCreated(String aStr) {
        CODateCreated = aStr;
    }
    public String getCOTitle() {
        return COTitle;
    }
    public int getCOPublicId() {return COPublicId;}
    public String getCOCreatedBy() {
        return COCreatedBy;
    }
    public String getCODateCreated() {
        return CODateCreated;
    }
}

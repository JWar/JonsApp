package com.jraw.android.jonsapp.data.model;

import android.arch.persistence.room.Entity;

/**
 * Created by JonGaming on 29/06/2017.
 *
 */
@Entity(tableName = "txn")
public class Txn extends entity {

    private String TXTelFrom;
    private String TXTo;
    private String TXData;
    private int TXType = -1;
    private String TXTimestamp;
    private int TXSent;
    private int TXDelivered;
    private int TXResult;
    private String TXError;

    public Txn() {}

    public void setTXTelFrom(String aTXTelFrom) {TXTelFrom=aTXTelFrom;}
    public void setTXTo(String aTXTo) {TXTo=aTXTo;}
    public void setTXData(String aStr) {
        TXData = aStr;
    }
    public void setTXType(int aInt) {
        TXType = aInt;
    }
    public void setTXTimestamp(String aStr) {
        TXTimestamp = aStr;
    }
    public void setTXSent(int aTXSent) {TXSent=aTXSent;}
    public void setTXDelivered(int aTXDelivered) {TXDelivered=aTXDelivered;}
    public void setTXResult(int aInt) {
        TXResult = aInt;
    }
    public void setTXError(String aTXError) {TXError=aTXError;}

    public String getTXTelFrom() {
        return TXTelFrom;
    }
    public String getTXTo() {
        return TXTo;
    }
    public String getTXData() {
        try {
            if (TXData.equals("null")) {
                return null;
            }
            return TXData;
        } catch (NullPointerException e) {
            return null;
        }
    }
    public int getTXType() {
        return TXType;
    }
    public String getTXTimestamp() {
        try {
            if (TXTimestamp.equals("null")) {
                return null;
            }
            return TXTimestamp;
        } catch (NullPointerException e) {
            return null;
        }
    }
    public int getTXSent() {
        return TXSent;
    }
    public int getTXDelivered() {
        return TXDelivered;
    }
    public int getTXResult() {
        return TXResult;
    }
    public String getTXError() {
        try {
            if (TXError.equals("null")) {
                return null;
            }
            return TXError;
        } catch (NullPointerException e) {
            return null;
        }
    }
}

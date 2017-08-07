package com.jraw.android.jonsapp.data.model;

/**
 * Created by JonGaming on 29/06/2017.
 *
 */

public class Txn extends entity {

    private String TXData;
    private int TXType = -1;
    private String TXTimestamp;
    private String TXResult;

    public Txn() {
    }

    public void setTXData(String aStr) {
        TXData = aStr;
    }

    public void setTXType(int aInt) {
        TXType = aInt;
    }

    public void setTXTimestamp(String aStr) {
        TXTimestamp = aStr;
    }

    public void setTXResult(String aStr) {
        TXResult = aStr;
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

    public String getTXResult() {
        try {
            if (TXResult.equals("null")) {
                return null;
            }
            return TXResult;
        } catch (NullPointerException e) {
            return null;
        }
    }
}

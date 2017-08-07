package com.jraw.android.jonsapp.data.model.wrappers;

import android.database.Cursor;
import android.database.CursorWrapper;
import com.jraw.android.jonsapp.MainActivity;
import com.jraw.android.jonsapp.data.local.DbSchema.MsgTable;
import com.jraw.android.jonsapp.data.model.Msg;

/**
 * Created by JonGaming on 22/07/2017.
 *
 */

public class MsgCursorWrapper extends CursorWrapper {
    public MsgCursorWrapper(Cursor aCur) {
        super(aCur);
    }

    public Msg getMsg() {
        try {
            Msg msg = new Msg();
            msg.setId(getInt(getColumnIndexOrThrow(MsgTable.Cols.ID)));
            if (getColumnIndex(MsgTable.Cols.COID) > -1) {
                if (!isNull(getColumnIndex(MsgTable.Cols.COID))) {
                    msg.setMSCOID(getInt(getColumnIndex(MsgTable.Cols.COID)));
                }
            }
            if (getColumnIndex(MsgTable.Cols.TOID) > -1) {
                if (!isNull(getColumnIndex(MsgTable.Cols.TOID))) {
                    msg.setMSToID(getInt(getColumnIndex(MsgTable.Cols.TOID)));
                }
            }
            if (getColumnIndex(MsgTable.Cols.FROMID) > -1) {
                if (!isNull(getColumnIndex(MsgTable.Cols.FROMID))) {
                    msg.setMSFromID(getInt(getColumnIndex(MsgTable.Cols.FROMID)));
                }
            }
            if (getColumnIndex(MsgTable.Cols.BODY) > -1) {
                if (!isNull(getColumnIndex(MsgTable.Cols.BODY))) {
                    msg.setMSBody(getString(getColumnIndex(MsgTable.Cols.BODY)));
                }
            }
            if (getColumnIndex(MsgTable.Cols.EVENTDATE) > -1) {
                if (!isNull(getColumnIndex(MsgTable.Cols.EVENTDATE))) {
                    msg.setMSEventDate(getString(getColumnIndex(MsgTable.Cols.EVENTDATE)));
                }
            }
            if (getColumnIndex(MsgTable.Cols.TYPE) > -1) {
                if (!isNull(getColumnIndex(MsgTable.Cols.TYPE))) {
                    msg.setMSType(getInt(getColumnIndex(MsgTable.Cols.TYPE)));
                }
            }
            if (getColumnIndex(MsgTable.Cols.DATA) > -1) {
                if (!isNull(getColumnIndex(MsgTable.Cols.DATA))) {
                    msg.setMSData(getString(getColumnIndex(MsgTable.Cols.DATA)));
                }
            }
            if (getColumnIndex(MsgTable.Cols.RESULT) > -1) {
                if (!isNull(getColumnIndex(MsgTable.Cols.RESULT))) {
                    msg.setMSResult(getString(getColumnIndex(MsgTable.Cols.RESULT)));
                }
            }
            return msg;
        } catch (Exception e) {
            MainActivity.logDebug("Error in MsgCursorWrapper.getMsg: " + e.getMessage());
            return null;
        }
    }
}


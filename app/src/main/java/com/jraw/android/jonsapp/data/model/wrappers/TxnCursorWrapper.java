package com.jraw.android.jonsapp.data.model.wrappers;

import android.database.Cursor;
import android.database.CursorWrapper;
import com.jraw.android.jonsapp.database.DbSchema.TxnTable;
import com.jraw.android.jonsapp.data.model.Txn;
import com.jraw.android.jonsapp.utils.Utils;

/**
 * Created by JonGaming on 22/07/2017.
 *
 */

public class TxnCursorWrapper extends CursorWrapper {
    public TxnCursorWrapper(Cursor aCur) {
        super(aCur);
    }
    public Txn getTxn() {
        try {
            Txn txn = new Txn();
            txn.setId(getInt(getColumnIndexOrThrow(TxnTable.Cols.ID)));
            if (getColumnIndex(TxnTable.Cols.DATA)>-1) {
                if (!isNull(getColumnIndex(TxnTable.Cols.DATA))) {
                    txn.setTXData(getString(getColumnIndex(TxnTable.Cols.DATA)));
                }
            }
            if (getColumnIndex(TxnTable.Cols.TYPE)>-1) {
                if (!isNull(getColumnIndex(TxnTable.Cols.TYPE))) {
                    txn.setTXType(getInt(getColumnIndex(TxnTable.Cols.TYPE)));
                }
            }
            if (getColumnIndex(TxnTable.Cols.TIMESTAMP)>-1) {
                if (!isNull(getColumnIndex(TxnTable.Cols.TIMESTAMP))) {
                    txn.setTXTimestamp(getString(getColumnIndex(TxnTable.Cols.TIMESTAMP)));
                }
            }
            if (getColumnIndex(TxnTable.Cols.RESULT)>-1) {
                if (!isNull(getColumnIndex(TxnTable.Cols.RESULT))) {
                    txn.setTXResult(getInt(getColumnIndex(TxnTable.Cols.RESULT)));
                }
            }
            return txn;
        } catch (Exception e) {
            Utils.logDebug("Error in TxnCursorWrapper.getTxn: "+e.getMessage());return null;}
    }
}
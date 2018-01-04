package com.jraw.android.jonsapp.data.model.wrappers;

import android.database.Cursor;
import android.database.CursorWrapper;
import com.jraw.android.jonsapp.data.source.local.database.DbSchema.TelTable;
import com.jraw.android.jonsapp.data.model.Tel;
import com.jraw.android.jonsapp.utils.Utils;

/**
 * Created by JonGaming on 22/07/2017.
 *
 */

public class TelCursorWrapper extends CursorWrapper {
    public TelCursorWrapper(Cursor aCur) {
        super(aCur);
    }

    public Tel getTel() {
        try {
            Tel tel = new Tel();
            tel.setId(getInt(getColumnIndexOrThrow(TelTable.Cols.ID)));
            if (getColumnIndex(TelTable.Cols.NUMBER) > -1) {
                if (!isNull(getColumnIndex(TelTable.Cols.NUMBER))) {
                    tel.setTENumber(getString(getColumnIndex(TelTable.Cols.NUMBER)));
                }
            }
            return tel;
        } catch (Exception e) {
            Utils.logDebug("Error in TelCursorWrapper.getTel: " + e.getMessage());
            return null;
        }
    }
}


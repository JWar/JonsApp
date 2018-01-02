package com.jraw.android.jonsapp.data.model.wrappers;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.jraw.android.jonsapp.MainActivity;
import com.jraw.android.jonsapp.data.source.local.database.DbSchema.PersonTable;
import com.jraw.android.jonsapp.data.model.Person;

/**
 * Created by JonGaming on 22/07/2017.
 *
 */

public class PersonCursorWrapper extends CursorWrapper {
    public PersonCursorWrapper(Cursor aCur) {
        super(aCur);
    }

    public Person getPerson() {
        try {
            Person per = new Person();
            per.setId(getInt(getColumnIndexOrThrow(PersonTable.Cols.ID)));
            if (getColumnIndex(PersonTable.Cols.FNAME) > -1) {
                if (!isNull(getColumnIndex(PersonTable.Cols.FNAME))) {
                    per.setPEFname(getString(getColumnIndex(PersonTable.Cols.FNAME)));
                }
            }
            if (getColumnIndex(PersonTable.Cols.SNAME) > -1) {
                if (!isNull(getColumnIndex(PersonTable.Cols.SNAME))) {
                    per.setPESname(getString(getColumnIndex(PersonTable.Cols.SNAME)));
                }
            }
            return per;
        } catch (Exception e) {
            MainActivity.logDebug("Error in PersonCursorWrapper.getPerson: " + e.getMessage());
            return null;
        }
    }
}

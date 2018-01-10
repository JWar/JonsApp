package com.jraw.android.jonsapp.data.model.wrappers;

import android.database.Cursor;
import android.database.CursorWrapper;
import com.jraw.android.jonsapp.database.DbSchema.ConversationTable;
import com.jraw.android.jonsapp.data.model.Conversation;
import com.jraw.android.jonsapp.utils.Utils;

/**
 * Created by JonGaming on 22/07/2017.
 *
 */

public class ConversationCursorWrapper extends CursorWrapper {
    public ConversationCursorWrapper(Cursor aCur) {
        super(aCur);
    }

    public Conversation getConversation() {
        try {
            Conversation con = new Conversation();
            con.setId(getInt(getColumnIndexOrThrow(ConversationTable.Cols.ID)));
            if (getColumnIndex(ConversationTable.Cols.TITLE) > -1) {
                if (!isNull(getColumnIndex(ConversationTable.Cols.TITLE))) {
                    con.setCOTitle(getString(getColumnIndex(ConversationTable.Cols.TITLE)));
                }
            }
            if (getColumnIndex(ConversationTable.Cols.CREATEDBY) > -1) {
                if (!isNull(getColumnIndex(ConversationTable.Cols.CREATEDBY))) {
                    con.setCOCreatedBy(getString(getColumnIndex(ConversationTable.Cols.CREATEDBY)));
                }
            }
            if (getColumnIndex(ConversationTable.Cols.DATECREATED) > -1) {
                if (!isNull(getColumnIndex(ConversationTable.Cols.DATECREATED))) {
                    con.setCODateCreated(getString(getColumnIndex(ConversationTable.Cols.DATECREATED)));
                }
            }
            return con;
        } catch (Exception e) {
            Utils.logDebug("Error in ConversationCursorWrapper.getConversation: " + e.getMessage());
            return null;
        }
    }
}


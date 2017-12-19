package com.jraw.android.jonsapp.data.local.repositories;

import android.content.Context;
import android.database.Cursor;

import com.squareup.sqlbrite2.SqlBrite;

import io.reactivex.Observable;

/**
 * Created by JWar on 19/12/2017.
 *
 * Note I store the QuerysCursor and use that in my list. This is to ensure cursor is closed properly.
 * There are options to use List<Conversation> instead of Cursor. But going for quick and dirty way.
 */

public class ConversationRepository {
    private static ConversationRepository sInstance=null;
    private Cursor mQueryCursor;
    public static synchronized ConversationRepository getInstance(Context aContext) {
        if (sInstance==null) {
            sInstance = new ConversationRepository(aContext);
        }
        return sInstance;
    }
    private ConversationRepository(Context aContext) {

    }

    public void setQuery(SqlBrite.Query aQuery) throws Exception {
        if (mQueryCursor!=null&&!mQueryCursor.isClosed()) {
            mQueryCursor.close();
        }
        mQueryCursor=aQuery.run();
    }
    public Cursor getQueryCursor() {
        return mQueryCursor;
    }
    public Observable<SqlBrite.Query> getConversations() throws Exception {
        return null;
    }
    public Observable<SqlBrite.Query> getConversationsViaTitle(String aTitle) throws Exception {
        return null;
    }
}

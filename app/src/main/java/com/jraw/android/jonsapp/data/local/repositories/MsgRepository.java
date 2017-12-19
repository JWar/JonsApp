package com.jraw.android.jonsapp.data.local.repositories;

import android.content.Context;
import android.database.Cursor;
import android.database.Observable;

import com.squareup.sqlbrite2.SqlBrite;

/**
 * Created by JWar on 19/12/2017.
 * Handles Msgs
 */

public class MsgRepository {
    private static MsgRepository sInstance=null;
    private Cursor mQueryCursor;
    public static synchronized MsgRepository getInstance(Context aContext) {
        if (sInstance==null) {
            sInstance = new MsgRepository(aContext);
        }
        return sInstance;
    }
    private MsgRepository(Context aContext) {

    }
    public void setQuery(SqlBrite.Query aQuery) {
        if (mQueryCursor!=null&&!mQueryCursor.isClosed()) {
            mQueryCursor.close();
        }
        mQueryCursor=aQuery.run();
    }
    public Observable<SqlBrite.Query> getMsgs(int aConvId) {
        return null;
    }
}

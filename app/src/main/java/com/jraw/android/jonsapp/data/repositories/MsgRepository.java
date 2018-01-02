package com.jraw.android.jonsapp.data.repositories;

import android.database.Cursor;
import android.database.Observable;
import android.support.annotation.NonNull;

import com.jraw.android.jonsapp.data.source.local.MsgLocalDataSource;
import com.squareup.sqlbrite2.SqlBrite;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by JWar on 19/12/2017.
 * Handles Msgs
 */

public class MsgRepository {

    private static MsgRepository sInstance=null;
    private MsgLocalDataSource mMsgLocalDataSource;
    private Cursor mQueryCursor;

    public static synchronized MsgRepository getInstance(@NonNull MsgLocalDataSource aMsgLocalDataSource) {
        if (sInstance==null) {
            sInstance = new MsgRepository(aMsgLocalDataSource);
        }
        return sInstance;
    }
    private MsgRepository(@NonNull MsgLocalDataSource aMsgLocalDataSource) {
        mMsgLocalDataSource = checkNotNull(aMsgLocalDataSource);
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

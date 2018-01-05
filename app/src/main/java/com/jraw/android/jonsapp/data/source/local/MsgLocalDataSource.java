package com.jraw.android.jonsapp.data.source.local;

import android.support.annotation.NonNull;
import com.jraw.android.jonsapp.data.model.entity;
import com.jraw.android.jonsapp.data.model.wrappers.MsgCursorWrapper;
import com.jraw.android.jonsapp.data.source.MsgDataSource;
import com.jraw.android.jonsapp.data.source.local.database.BriteWrapper;
import com.jraw.android.jonsapp.data.source.local.database.DbSchema.MsgTable;
import com.jraw.android.jonsapp.utils.Utils;
import com.squareup.sqlbrite2.SqlBrite;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by JWar on 21/12/2017.
 * Handles CRUDing Msgs.
 */

public class MsgLocalDataSource implements MsgDataSource {

    private static MsgLocalDataSource sInstance=null;
    private BriteWrapper mBriteWrapper;

    public static synchronized MsgLocalDataSource getInstance(@NonNull BriteWrapper aBriteWrapper) {
        if (sInstance==null) {
            sInstance = new MsgLocalDataSource(aBriteWrapper);
        }
        return sInstance;
    }
    private MsgLocalDataSource(@NonNull BriteWrapper aBriteWrapper) {
        mBriteWrapper = checkNotNull(aBriteWrapper);
    }

    @Override
    public Observable<List<entity>> getMsgs(int aCOId) {
        return mBriteWrapper.createQuery(MsgTable.NAME,
                "SELECT * FROM "+ MsgTable.NAME
                        + " WHERE " + MsgTable.Cols.COID + "=" + aCOId
                        + " ORDER BY " + MsgTable.Cols.EVENTDATE + " DESC")
                .map(new Function<SqlBrite.Query, List<entity>>() {
                    @Override
                    public List<entity> apply(SqlBrite.Query aQuery) throws Exception {
                        List<entity> entList = new ArrayList<>();
                        MsgCursorWrapper cursor = new MsgCursorWrapper(aQuery.run());
                        while (cursor.moveToNext()) {
                            entList.add(cursor.getMsg());
                        }
                        Utils.closeCursor(cursor);
                        return entList;
                    }
                });
    }

    @Override
    public Observable<List<entity>> getMsgsViaBody(int aCOId, String aBody) {
        return mBriteWrapper.createQuery(MsgTable.NAME,
                "SELECT * FROM "+ MsgTable.NAME
                        + " WHERE " + MsgTable.Cols.COID + "=" + aCOId
                        + " AND " + MsgTable.Cols.BODY + " LIKE ?"
                        + " ORDER BY " + MsgTable.Cols.EVENTDATE + " DESC",
                "%"+aBody+"%")
                .map(new Function<SqlBrite.Query, List<entity>>() {
                    @Override
                    public List<entity> apply(SqlBrite.Query aQuery) throws Exception {
                        List<entity> entList = new ArrayList<>();
                        MsgCursorWrapper cursor = new MsgCursorWrapper(aQuery.run());
                        while (cursor.moveToNext()) {
                            entList.add(cursor.getMsg());
                        }
                        Utils.closeCursor(cursor);
                        return entList;
                    }
                });
    }
}

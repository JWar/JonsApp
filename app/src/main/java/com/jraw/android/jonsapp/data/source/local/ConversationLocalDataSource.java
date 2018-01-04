package com.jraw.android.jonsapp.data.source.local;

import android.support.annotation.NonNull;
import com.jraw.android.jonsapp.data.model.entity;
import com.jraw.android.jonsapp.data.source.ConversationDataSource;
import com.jraw.android.jonsapp.data.source.local.database.BriteWrapper;
import com.jraw.android.jonsapp.data.source.local.database.DbSchema.ConversationTable;
import com.squareup.sqlbrite2.SqlBrite;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by JWar on 21/12/2017.
 * Handles CRUDing Conversations.
 */

public class ConversationLocalDataSource implements ConversationDataSource {

    private static ConversationLocalDataSource sInstance=null;
    private BriteWrapper mBriteWrapper;

    public static synchronized ConversationLocalDataSource getInstance(@NonNull BriteWrapper aBriteWrapper) {
        if (sInstance==null) {
            sInstance = new ConversationLocalDataSource(aBriteWrapper);
        }
        return sInstance;
    }
    private ConversationLocalDataSource(@NonNull BriteWrapper aBriteWrapper) {
        mBriteWrapper = checkNotNull(aBriteWrapper);
    }

    @Override
    public Observable<List<entity>> getConversations() {
        return mBriteWrapper.createQuery(ConversationTable.NAME,
                "SELECT * FROM "+ ConversationTable.NAME
                        + " ORDER BY " + ConversationTable.Cols.TITLE + " ASC")
                .map(new Function<SqlBrite.Query, List<entity>>() {
                    @Override
                    public List<entity> apply(SqlBrite.Query aQuery) throws Exception {
                        List<entity> entList = new ArrayList<>();
                        return entList;
                    }
                });
    }
}

package com.jraw.android.jonsapp.data.repositories;

import android.database.Observable;
import android.support.annotation.NonNull;
import com.jraw.android.jonsapp.data.model.Msg;
import com.jraw.android.jonsapp.data.source.local.MsgLocalDataSource;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by JWar on 19/12/2017.
 * Handles Msgs
 */

public class MsgRepository {

    private static MsgRepository sInstance=null;
    private MsgLocalDataSource mMsgLocalDataSource;

    public static synchronized MsgRepository getInstance(@NonNull MsgLocalDataSource aMsgLocalDataSource) {
        if (sInstance==null) {
            sInstance = new MsgRepository(aMsgLocalDataSource);
        }
        return sInstance;
    }
    private MsgRepository(@NonNull MsgLocalDataSource aMsgLocalDataSource) {
        mMsgLocalDataSource = checkNotNull(aMsgLocalDataSource);
    }
    public Observable<List<Msg>> getMsgs(int aConvId) {

        return null;
    }
}

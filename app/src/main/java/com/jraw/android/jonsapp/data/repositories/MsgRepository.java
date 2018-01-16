package com.jraw.android.jonsapp.data.repositories;

import android.support.annotation.NonNull;

import com.jraw.android.jonsapp.data.model.Msg;
import com.jraw.android.jonsapp.data.model.entity;
import com.jraw.android.jonsapp.data.source.MsgDataSource;

import java.util.List;

import io.reactivex.Observable;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by JWar on 19/12/2017.
 * Handles Msgs
 */

public class MsgRepository {

    private static MsgRepository sInstance=null;
    private MsgDataSource mMsgDataSource;

    public static synchronized MsgRepository getInstance(@NonNull MsgDataSource aMsgDataSource) {
        if (sInstance==null) {
            sInstance = new MsgRepository(aMsgDataSource);
        }
        return sInstance;
    }
    private MsgRepository(@NonNull MsgDataSource aMsgDataSource) {
        mMsgDataSource = checkNotNull(aMsgDataSource);
    }
    public void destroyInstance() {
        sInstance=null;
    }
    public Observable<List<entity>> getMsgs(int aCOId) {
        return mMsgDataSource.getMsgs(aCOId);
    }
    public Observable<List<entity>> getMsgsViaBody(int aCOId,
                                                   String aBody) {
        return mMsgDataSource.getMsgsViaBody(aCOId, aBody);
    }
    public long saveMsg(Msg aMsg) {
        return mMsgDataSource.saveMsg(aMsg);
    }
}

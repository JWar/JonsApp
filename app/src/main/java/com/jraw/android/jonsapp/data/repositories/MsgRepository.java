package com.jraw.android.jonsapp.data.repositories;

import android.content.Context;
import android.support.annotation.NonNull;

import com.jraw.android.jonsapp.Injection;
import com.jraw.android.jonsapp.data.model.Msg;
import com.jraw.android.jonsapp.data.model.entity;
import com.jraw.android.jonsapp.data.source.MsgDataSource;
import com.jraw.android.jonsapp.data.source.local.MsgLocalDataSource;
import com.jraw.android.jonsapp.utils.Utils;

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
    //Pretty horrible way of doing this but straight to the point...
    public static synchronized MsgRepository get(Context aContext) {
        try {
            if (sInstance == null) {
                sInstance = new MsgRepository(MsgLocalDataSource.getInstance(Injection.provideBriteWrapper(aContext)));
            }
            return sInstance;
        } catch (Exception e) {Utils.logDebug("Error in MsgRepository.get: "+e.getLocalizedMessage());return null;}
    }
}

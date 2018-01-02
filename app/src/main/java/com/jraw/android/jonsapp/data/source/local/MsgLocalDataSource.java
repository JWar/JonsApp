package com.jraw.android.jonsapp.data.source.local;

import android.support.annotation.NonNull;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by JWar on 21/12/2017.
 * Handles CRUDing Msgs.
 */

public class MsgLocalDataSource {

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
}

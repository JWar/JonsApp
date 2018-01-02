package com.jraw.android.jonsapp.data.source.local;

import android.support.annotation.NonNull;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by JWar on 21/12/2017.
 * Handles CRUDing Conversations.
 */

public class ConversationLocalDataSource {

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


}

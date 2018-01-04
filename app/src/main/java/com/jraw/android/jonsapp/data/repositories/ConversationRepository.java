package com.jraw.android.jonsapp.data.repositories;

import android.support.annotation.NonNull;

import com.jraw.android.jonsapp.data.model.Conversation;
import com.jraw.android.jonsapp.data.model.entity;
import com.jraw.android.jonsapp.data.source.local.ConversationLocalDataSource;

import java.util.List;

import io.reactivex.Observable;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by JWar on 19/12/2017.
 *
 * Note I store the QuerysCursor and use that in my list. This is to ensure cursor is closed properly.
 * There are options to use List<Conversation> instead of Cursor. But going for quick and dirty way.
 * 180104_resorted to List<entity> because its easier to do the entity listing in the datasource and provide
 * ONE way of updating the data in the ListHandler. Could have a method for each entity but this is...
 * silly.
 */

public class ConversationRepository {

    private static ConversationRepository sInstance=null;
    private ConversationLocalDataSource mConversationLocalDataSource;

    public static synchronized ConversationRepository getInstance(@NonNull ConversationLocalDataSource aConversationLocalDataSource) {
        if (sInstance==null) {
            sInstance = new ConversationRepository(aConversationLocalDataSource);
        }
        return sInstance;
    }
    private ConversationRepository(@NonNull ConversationLocalDataSource aConversationLocalDataSource) {
        mConversationLocalDataSource = checkNotNull(aConversationLocalDataSource);
    }

    public Observable<List<entity>> getConversations() {
        return mConversationLocalDataSource.getConversations();
    }
    public Observable<List<entity>> getConversationsViaTitle(String aTitle) {
        return null;
    }
}

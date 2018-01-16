package com.jraw.android.jonsapp.data.repositories;

import android.support.annotation.NonNull;
import com.jraw.android.jonsapp.data.model.entity;
import com.jraw.android.jonsapp.data.source.ConversationDataSource;

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
    private ConversationDataSource mConversationDataSource;

    public static synchronized ConversationRepository getInstance(@NonNull ConversationDataSource aConversationDataSource) {
        if (sInstance==null) {
            sInstance = new ConversationRepository(aConversationDataSource);
        }
        return sInstance;
    }
    private ConversationRepository(@NonNull ConversationDataSource aConversationDataSource) {
        mConversationDataSource = checkNotNull(aConversationDataSource);
    }
    public void destroyInstance() {
        sInstance=null;
    }
    public Observable<List<entity>> getConversations() {
        return mConversationDataSource.getConversations();
    }
    public Observable<List<entity>> getConversationsViaTitle(String aTitle) {
        return mConversationDataSource.getConversationsViaTitle(aTitle);
    }
}

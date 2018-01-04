package com.jraw.android.jonsapp.data.source;

import com.jraw.android.jonsapp.data.model.Conversation;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by JonGaming on 04/01/2018.
 * Used for providing dummy data for mocking/testing/blahing!
 */

public class FakeConversationLocalDataSource implements ConversationDataSource {
    private static FakeConversationLocalDataSource sInstance;

    public static FakeConversationLocalDataSource getInstance() {
        if (sInstance==null) {
            sInstance = new FakeConversationLocalDataSource();
        }
        return sInstance;
    }
    private FakeConversationLocalDataSource() {}

    @Override
    public Observable<List<Conversation>> getConversations() {
        //Will return made up list for testing!
        return null;
    }
}

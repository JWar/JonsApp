package com.jraw.android.jonsapp.data.source.local;

import com.jraw.android.jonsapp.utils.DummyData;
import com.jraw.android.jonsapp.data.model.Conversation;
import com.jraw.android.jonsapp.data.model.entity;
import com.jraw.android.jonsapp.data.source.ConversationDataSource;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

/**
 * Created by JonGaming on 04/01/2018.
 * Used for providing dummy data for mocking/testing/blahing!
 */

public class FakeConversationLocalDataSource implements ConversationDataSource {

    private static FakeConversationLocalDataSource sInstance;

    private List<entity> mFakeData = new ArrayList<>();

    public static FakeConversationLocalDataSource getInstance() {
        if (sInstance==null) {
            sInstance = new FakeConversationLocalDataSource();
        }
        return sInstance;
    }
    private FakeConversationLocalDataSource() {
        mFakeData = DummyData.getConversations();
    }

    @Override
    public Observable<List<entity>> getConversations() {
        return Observable.just(mFakeData);
    }

    @Override
    public Observable<List<entity>> getConversationsViaTitle(String aTitle) {
        List<entity> dataToReturn = new ArrayList<>();
        for (entity ent: mFakeData) {
            Conversation conv = (Conversation) ent;
            if (conv.getCOTitle().contains(aTitle)) {
                dataToReturn.add(ent);
            }
        }
        return Observable.just(dataToReturn);

    }
}

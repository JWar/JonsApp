package com.jraw.android.jonsapp.conversation;

import com.jraw.android.jonsapp.data.model.entity;
import com.jraw.android.jonsapp.data.repositories.ConversationRepository;
import com.jraw.android.jonsapp.data.source.ConversationDataSource;

import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.subscribers.TestSubscriber;

import static org.mockito.Mockito.when;

/**
 * Created by JonGaming on 14/01/2018.
 * In anticipation of extension... cache/saves/blahs
 */

public class ConversationRepositoryTest {
    @Mock
    private ConversationRepository mConversationRepository;

    @Mock
    private ConversationDataSource mConversationLocalDataSource;

    private List<entity> mEntities;

    private TestSubscriber<List<entity>> mTestSubscriber;

    //Tests for failure and success!

    //Hmm should NotAvailable return null or empty list??
    private void setConvNotAvailable(ConversationDataSource aConversationLocalDataSource) {
        List<entity> entities = new ArrayList<>();
        when(aConversationLocalDataSource.getConversations()).thenReturn(Observable.just(entities));
    }
    private void setConvAvailable(ConversationDataSource aConversationLocalDataSource) {
        when(aConversationLocalDataSource.getConversations()).thenReturn(Observable.just(mEntities));
    }


}

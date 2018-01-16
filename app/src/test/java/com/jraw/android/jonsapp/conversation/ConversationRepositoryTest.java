package com.jraw.android.jonsapp.conversation;

import com.jraw.android.jonsapp.data.model.Conversation;
import com.jraw.android.jonsapp.data.model.entity;
import com.jraw.android.jonsapp.data.repositories.ConversationRepository;
import com.jraw.android.jonsapp.data.source.ConversationDataSource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;
import io.reactivex.subscribers.TestSubscriber;

import static org.mockito.Mockito.verify;
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

    private TestObserver<List<entity>> mTestObserver;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mConversationRepository = ConversationRepository.getInstance(mConversationLocalDataSource);
        mEntities = new ArrayList<>();//Dont think need any data in it.. as long as its the same memory address :)
        mTestObserver = new TestObserver<>();
    }
    @After
    public void ripDown() {
        mConversationRepository.destroyInstance();
    }

    @Test
    public void getConversations() {
        setConvAvailable(mConversationLocalDataSource);
        mConversationRepository.getConversations().subscribe(mTestObserver);
        verify(mConversationLocalDataSource).getConversations();
        mTestObserver.assertValue(mEntities);
    }
    @Test
    public void getConversationsViaTitle() {
        String title = "a";
        setConvAvailableViaTitle(mConversationLocalDataSource,title);
        mConversationRepository.getConversationsViaTitle(title).subscribe(mTestObserver);
        verify(mConversationLocalDataSource).getConversationsViaTitle(title);
        mTestObserver.assertValue(mEntities);
    }
    //Tests for failure and success!
    //Hmm should NotAvailable return null or empty list??
    //180115_Think it will be empty list... null is for error...
    private void setConvNotAvailable(ConversationDataSource aConversationDataSource) {
        List<entity> entities = new ArrayList<>();
        when(aConversationDataSource.getConversations()).thenReturn(Observable.just(entities));
    }
    private void setConvAvailable(ConversationDataSource aConversationDataSource) {
        when(aConversationDataSource.getConversations()).thenReturn(Observable.just(mEntities));
    }
    private void setConvAvailableViaTitle(ConversationDataSource aConversationDataSource,
                                          String aTitle) {
        when(aConversationDataSource.getConversationsViaTitle(aTitle)).thenReturn(Observable.just(mEntities));
    }
}

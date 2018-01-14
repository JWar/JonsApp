package com.jraw.android.jonsapp.conversation;


import com.jraw.android.jonsapp.data.model.entity;
import com.jraw.android.jonsapp.data.repositories.ConversationRepository;
import com.jraw.android.jonsapp.ui.conversation.ConversationContract;
import com.jraw.android.jonsapp.ui.conversation.ConversationPresenter;
import com.jraw.android.jonsapp.utils.schedulers.BaseSchedulerProvider;
import com.jraw.android.jonsapp.utils.schedulers.ImmediateSchedulerProvider;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by JonGaming on 13/01/2018.
 * Basic test that runs both Presenter's methods and checks the View's correct methods are subsequently
 * called.
 * I know this may seem trivial but the idea of a testing suite is to build up a comprehensive set of tests
 * that can be run at the click of a button and go through the app automatically ensuring everything works.
 * Instead of having to go back and check things on a device all you need to do is set this simple test up
 * and run it as part of the suite. Once its set up it is set up! Now I know that the Conversations V and P
 * are communicating properly and if I go back later and make changes I just need to run this test to ensure
 * they're still communicating.
 * Funnily enough I forgot to View.setPresenter in the Presenters constructor, these tests found that...
 */

public class ConversationPresenterTest {

    @Mock
    private ConversationRepository mConversationRepository;

    @Mock
    private ConversationContract.ViewConversations mViewConversations;

    private ConversationPresenter mConversationPresenter;

    private BaseSchedulerProvider mSchedulerProvider;

    private List<entity> mEntities;
    private String mSearchQuery;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mSchedulerProvider = new ImmediateSchedulerProvider();
        mEntities =  new ArrayList<>();
        mSearchQuery = "a";
    }
    @Test
    public void setPresenterToView() {
        mConversationPresenter = new ConversationPresenter(mConversationRepository,
                mSchedulerProvider,
                mViewConversations);
        verify(mViewConversations).setPresenter(mConversationPresenter);
    }
    @Test
    public void getConversations() {
        mConversationPresenter = new ConversationPresenter(mConversationRepository,
                mSchedulerProvider,
                mViewConversations);
        setConversationsAvailable();
        mConversationPresenter.getConversations();
        verify(mConversationRepository).getConversations();
        verify(mViewConversations).setConversations(mEntities);
    }
    @Test
    public void getConversationsViaTitle() {
        mConversationPresenter = new ConversationPresenter(mConversationRepository,
                mSchedulerProvider,
                mViewConversations);
        setConversationsViaTitleAvailable();
        mConversationPresenter.getConversationsViaTitle(mSearchQuery);
        verify(mConversationRepository).getConversationsViaTitle(eq(mSearchQuery));
        verify(mViewConversations).setConversations(mEntities);
    }

    private void setConversationsAvailable() {
        when(mConversationRepository.getConversations()).thenReturn(Observable.just(mEntities));
    }
    private void setConversationsViaTitleAvailable() {
        when(mConversationRepository.getConversationsViaTitle(mSearchQuery)).thenReturn(Observable.just(mEntities));
    }
}

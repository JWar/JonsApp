package com.jraw.android.jonsapp.msg;

import com.jraw.android.jonsapp.data.model.entity;
import com.jraw.android.jonsapp.data.repositories.MsgRepository;
import com.jraw.android.jonsapp.ui.msgs.MsgsContract;
import com.jraw.android.jonsapp.ui.msgs.MsgsPresenter;
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
 * Basic test that runs MsgsPresenter's methods and checks View's corresponding methods are called
 */

public class MsgsPresenterTest {
    @Mock
    private MsgRepository mMsgRepository;

    @Mock
    private MsgsContract.ViewMsgs mViewMsgs;

    private MsgsPresenter mMsgsPresenter;

    private BaseSchedulerProvider mSchedulerProvider;

    private List<entity> mEntities;

    private int mConvId;
    private String mSearchQuery;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mSchedulerProvider = new ImmediateSchedulerProvider();
        mEntities =  new ArrayList<>();
        mConvId = 1;
        mSearchQuery = "a";
    }
    @Test
    public void setPresenterToView() {
        mMsgsPresenter = new MsgsPresenter(mMsgRepository,
                mSchedulerProvider,
                mViewMsgs);
        verify(mViewMsgs).setPresenter(mMsgsPresenter);
    }
    @Test
    public void getMsgs() {
        mMsgsPresenter = new MsgsPresenter(mMsgRepository,
                mSchedulerProvider,
                mViewMsgs);
        setMsgsAvailable();
        mMsgsPresenter.getMsgs(mConvId);
        verify(mMsgRepository).getMsgs(mConvId);
        verify(mViewMsgs).setMsgs(mEntities);
    }
    @Test
    public void getMsgsViaBody() {
        mMsgsPresenter = new MsgsPresenter(mMsgRepository,
                mSchedulerProvider,
                mViewMsgs);
        setMsgsViaBodyAvailable();
        mMsgsPresenter.getMsgsViaBody(mConvId,mSearchQuery);
        verify(mMsgRepository).getMsgsViaBody(eq(mConvId),eq(mSearchQuery));
        verify(mViewMsgs).setMsgs(mEntities);
    }

    private void setMsgsAvailable() {
        when(mMsgRepository.getMsgs(mConvId)).thenReturn(Observable.just(mEntities));
    }
    private void setMsgsViaBodyAvailable() {
        when(mMsgRepository.getMsgsViaBody(mConvId,mSearchQuery)).thenReturn(Observable.just(mEntities));
    }
}

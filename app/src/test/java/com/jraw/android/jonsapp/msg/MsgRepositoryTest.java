package com.jraw.android.jonsapp.msg;

import com.jraw.android.jonsapp.data.model.Msg;
import com.jraw.android.jonsapp.data.model.entity;
import com.jraw.android.jonsapp.data.repositories.MsgRepository;
import com.jraw.android.jonsapp.data.source.MsgDataSource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by rawlings_j on 15/01/2018.
 * Need to do save testing too!
 */

public class MsgRepositoryTest {
    @Mock
    private MsgRepository mMsgRepository;

    @Mock
    private MsgDataSource mMsgLocalDataSource;

    private List<entity> mEntities;
    private int mCOId;

    private TestObserver<List<entity>> mTestObserver;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mMsgRepository = MsgRepository.getInstance(mMsgLocalDataSource);
        mEntities = new ArrayList<>();//Dont think need any data in it.. as long as its the same memory address :)
        mCOId = 1;
        mTestObserver = new TestObserver<>();
    }
    @After
    public void ripDown() {
        mMsgRepository.destroyInstance();
    }

    @Test
    public void getMsgs() {
        setMsgsAvailable(mMsgLocalDataSource);
        mMsgRepository.getMsgs(mCOId).subscribe(mTestObserver);
        verify(mMsgLocalDataSource).getMsgs(mCOId);
        mTestObserver.assertValue(mEntities);
    }
    @Test
    public void getMsgsViaBody() {
        String search = "a";
        setMsgsAvailableViaBody(mMsgLocalDataSource,search);
        mMsgRepository.getMsgsViaBody(mCOId,search).subscribe(mTestObserver);
        verify(mMsgLocalDataSource).getMsgsViaBody(mCOId,search);
        mTestObserver.assertValue(mEntities);
    }
    //TDD!
    @Test
    public void saveMsg() {
        Msg msg = new Msg();
        msg.setId(1000);
        msg.setMSBody("Blah blah blah");
        msg.setMSCOPublicId(1);
        msg.setMSFromId(2);
        msg.setMSType(Msg.MSG_TYPES.TEXT.ordinal());
        msg.setMSEventDate("2018/01/15 16:20:00");
        msg.setMSResult(Msg.RESULTS.DELIVERED.ordinal());

        mMsgRepository.saveMsg(msg);
        verify(mMsgLocalDataSource).saveMsg(msg);
    }


    //Tests for failure and success!
    //Hmm should NotAvailable return null or empty list??
    //180115_Think it will be empty list... null is for error...
    private void setMsgsNotAvailable(MsgDataSource aMsgDataSource) {
        List<entity> entities = new ArrayList<>();
        when(aMsgDataSource.getMsgs(mCOId)).thenReturn(Observable.just(entities));
    }
    private void setMsgsAvailable(MsgDataSource aMsgDataSource) {
        when(aMsgDataSource.getMsgs(mCOId)).thenReturn(Observable.just(mEntities));
    }
    private void setMsgsAvailableViaBody(MsgDataSource aMsgDataSource,
                                          String aBody) {
        when(aMsgDataSource.getMsgsViaBody(mCOId,aBody)).thenReturn(Observable.just(mEntities));
    }
}

package com.jraw.android.jonsapp.data.source.local;

import com.jraw.android.jonsapp.DummyData;
import com.jraw.android.jonsapp.data.model.Msg;
import com.jraw.android.jonsapp.data.model.entity;
import com.jraw.android.jonsapp.data.source.MsgDataSource;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

/**
 * Created by rawlings_j on 11/01/2018.
 *
 */

public class FakeMsgLocalDataSource implements MsgDataSource {

    private static FakeMsgLocalDataSource sInstance=null;

    private List<entity> mFakeData = new ArrayList<>();

    public static FakeMsgLocalDataSource getInstance() {
        if (sInstance==null) {
            sInstance = new FakeMsgLocalDataSource();
        }
        return sInstance;
    }
    private FakeMsgLocalDataSource() {
        mFakeData = DummyData.getMsgs();
    }

    @Override
    public Observable<List<entity>> getMsgs(int aCOId) {
        return Observable.just(mFakeData);
    }

    @Override
    public Observable<List<entity>> getMsgsViaBody(int aCOId, String aBody) {
        List<entity> dataToReturn = new ArrayList<>();
        for (entity ent: mFakeData) {
            Msg msg = (Msg) ent;
            if (msg.getMSCOPublicId()==aCOId&&msg.getMSBody().contains(aBody)) {
                dataToReturn.add(ent);
            }
        }
        return Observable.just(dataToReturn);
    }
}

package com.jraw.android.jonsapp.ui.msgs;

import android.support.annotation.NonNull;

import com.jraw.android.jonsapp.data.model.entity;
import com.jraw.android.jonsapp.data.repositories.MsgRepository;
import com.jraw.android.jonsapp.utils.schedulers.BaseSchedulerProvider;
import com.jraw.android.jonsapp.utils.Utils;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by JonGaming on 17/07/2017.
 * Handles retrieval of msgs in a conversation
 */

public class MsgsPresenter implements MsgsContract.PresenterMsgs {

    private final MsgRepository mMsgRepository;

    @NonNull
    private final BaseSchedulerProvider mBaseSchedulerProvider;

    //This ensures the observable is cleared when the presenter isnt in view. OnUnsubcribe is called in
    //onPause of fragment. OnResume 're-gets'.
    private CompositeDisposable mDisposables;

    private MsgsContract.ViewMsgs mViewMsgs;

    public MsgsPresenter(@NonNull MsgRepository aMsgRepository,
                         @NonNull BaseSchedulerProvider aBaseSchedulerProvider,
                         @NonNull MsgsContract.ViewMsgs aViewMsgs) {
        mMsgRepository = checkNotNull(aMsgRepository);
        mBaseSchedulerProvider = checkNotNull(aBaseSchedulerProvider);
        mViewMsgs = checkNotNull(aViewMsgs);
        mViewMsgs.setPresenter(this);
        mDisposables = new CompositeDisposable();
    }

    @Override
    public void getMsgs(int aCOId) {
        Disposable disposable = mMsgRepository.getMsgs(aCOId)
                                    //TODO:180105_<-- do I need to 'subscribeOn'? Isnt Brite automatically using alt thread??
                .observeOn(mBaseSchedulerProvider.ui())//<-- means whatever results we get are processed on ui thread (in the subscribe's consumer)
                .subscribe(new Consumer<List<entity>>() {
                    @Override
                    public void accept(List<entity> aEntities) throws Exception {
                        mViewMsgs.setMsgs(aEntities);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable aThrowable) throws Exception {
                        Utils.logDebug("Error in MsgsPresenter.getMsgs: "+aThrowable.getLocalizedMessage());
                    }
                });
        mDisposables.add(disposable);
    }

    @Override
    public void getMsgsViaBody(int aCOId,
                               String aText) {
        Disposable disposable = mMsgRepository.getMsgsViaBody(aCOId, aText)
                                    //TODO:180105_<-- do I need to 'subscribeOn'? Isnt Brite automatically using alt thread??
                .observeOn(mBaseSchedulerProvider.ui())//<-- means whatever results we get are processed on ui thread (in the subscribe's consumer)
                .subscribe(new Consumer<List<entity>>() {
                    @Override
                    public void accept(List<entity> aEntities) throws Exception {
                        mViewMsgs.setMsgs(aEntities);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable aThrowable) throws Exception {
                        Utils.logDebug("Error in MsgsPresenter.getMsgsViaBody: "+aThrowable.getLocalizedMessage());
                    }
                });
        mDisposables.add(disposable);
    }

    @Override
    public void onUnsubscribe() {mDisposables.clear();}
}

package com.jraw.android.jonsapp.ui.conversation;

import android.support.annotation.NonNull;
import com.jraw.android.jonsapp.data.model.Conversation;
import com.jraw.android.jonsapp.data.model.entity;
import com.jraw.android.jonsapp.data.repositories.ConversationRepository;
import com.jraw.android.jonsapp.utils.Utils;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by JonGaming on 27/06/2017.
 * Handles retrieval of conversations user is part of.
 */

public class ConversationPresenter implements ConversationContract.PresenterConversations {

    private final ConversationRepository mConversationRepository;

    //This ensures the observable is cleared when the presenter isnt in view. OnUnsubcribe is called in
    //onPause of fragment. OnResume 're-gets'.
    private CompositeDisposable mDisposables;

    private ConversationContract.ViewConversations mViewConversations;

    public ConversationPresenter(@NonNull ConversationRepository aConversationRepository) {
        mConversationRepository = checkNotNull(aConversationRepository);
        mDisposables = new CompositeDisposable();
    }

    @Override
    public void getConversations() {
        Disposable disposable = mConversationRepository
                .getConversations()
                .subscribe(new Consumer<List<entity>>() {
                    @Override
                    public void accept(List<entity> aConversations) throws Exception {
                        mViewConversations.setConversations(aConversations);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable aThrowable) throws Exception {
                        Utils.logDebug("Problem in ConversationPresenter.getConversations: " + aThrowable.getLocalizedMessage());
                    }
                });
        mDisposables.add(disposable);
    }

    @Override
    public void getConversationsViaTitle(String aTitle) {
        Disposable disposable = mConversationRepository
                .getConversationsViaTitle(aTitle)
                .subscribe(new Consumer<List<entity>>() {
                    @Override
                    public void accept(List<entity> aConversations) throws Exception {
                        mViewConversations.setConversations(aConversations);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable aThrowable) throws Exception {
                        Utils.logDebug("Problem in ConversationPresenter.getConversationsViaTitle: " + aThrowable.getLocalizedMessage());
                    }
                });
        mDisposables.add(disposable);

    }

    @Override
    public void onUnsubscribe() {
        clear();
    }

    public void clear() {
        mDisposables.clear();
    }
}

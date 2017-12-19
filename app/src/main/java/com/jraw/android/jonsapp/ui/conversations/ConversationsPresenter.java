package com.jraw.android.jonsapp.ui.conversations;

import android.support.annotation.NonNull;

import com.jraw.android.jonsapp.MainActivity;
import com.jraw.android.jonsapp.data.local.repositories.ConversationRepository;
import com.squareup.sqlbrite2.SqlBrite;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by JonGaming on 27/06/2017.
 * Handles retrieval of conversations user is part of.
 */

public class ConversationsPresenter implements ConversationsContract.PresenterConversations {

    private final ConversationRepository mConversationRepository;

    //This ensures the observable is cleared when the presenter isnt in view. OnUnsubcribe is called in
    //onPause of fragment. OnResume 're-gets'.
    private CompositeDisposable mDisposables;

    private ConversationsContract.ViewConversations mViewConversations;

    public ConversationsPresenter(@NonNull ConversationRepository aConversationRepository) {
        mConversationRepository = checkNotNull(aConversationRepository);
        mDisposables = new CompositeDisposable();
    }

    @Override
    public void getConversations() {
        try {
            Disposable disposable = mConversationRepository
                    .getConversations()
                    .subscribe(new Consumer<SqlBrite.Query>() {
                        @Override
                        public void accept(SqlBrite.Query query) throws Exception {
                            mConversationRepository.setQuery(query);
                            mViewConversations.setConversations(mConversationRepository.getQueryCursor());
                        }
                    });
            mDisposables.add(disposable);
        } catch (Exception e) {
            MainActivity.logDebug("Problem in ConversationsPresenter.getConversations: "+e.getLocalizedMessage());
        }
    }

    @Override
    public void getConversationsViaTitle(String aTitle) {
        try {
            Disposable disposable = mConversationRepository
                    .getConversationsViaTitle(aTitle)
                    .subscribe(new Consumer<SqlBrite.Query>() {
                        @Override
                        public void accept(SqlBrite.Query query) throws Exception {
                            mConversationRepository.setQuery(query);
                            mViewConversations.setConversations(mConversationRepository.getQueryCursor());
                        }
                    });
            mDisposables.add(disposable);
        } catch (Exception e) {
            MainActivity.logDebug("Problem in ConversationsPresenter.getCovnersationsViaTitle: "+e.getLocalizedMessage());
        }
    }
    @Override
    public void onUnsubscribe() {
        clear();
    }
    public void clear() {
        try {
            mDisposables.clear();
            mConversationRepository.setQuery(null);//Closes and nulls Cursor.
        } catch (Exception e) {
            MainActivity.logDebug("Problem in ConversationsPresenter.clear: "+e.getLocalizedMessage());}
    }
}

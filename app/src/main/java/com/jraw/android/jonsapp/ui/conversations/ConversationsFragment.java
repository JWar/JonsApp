package com.jraw.android.jonsapp.ui.conversations;


import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jraw.android.jonsapp.R;
import com.jraw.android.jonsapp.data.model.Conversation;

import java.util.List;

/**
 * Handles the View part of Conversations feature. Gets Conversation List from Presenter.
 *
 * Since it doesnt need an id or ref to get the conversations there is no need for savedInstanceState.
 *
 * What about SearchViewQuerying? This will require options menu blah blah then setting the OnQueryHandler
 * as mPresenters getConverationsViaTitle
 */
public class ConversationsFragment extends Fragment implements ConversationsContract.ViewConversations {

    private ConversationsContract.PresenterConversations mPresenter;

    public ConversationsFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_conversations, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Set ListHandler here
    }

    @Override
    public void setConversations(Cursor aCursor) {

    }

    @Override
    public void setPresenter(ConversationsContract.PresenterConversations aPresenter) {
        mPresenter = aPresenter;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.getConversations();
    }
}

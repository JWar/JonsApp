package com.jraw.android.jonsapp.ui.conversation;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.jraw.android.jonsapp.R;
import com.jraw.android.jonsapp.data.model.entity;
import com.jraw.android.jonsapp.ui.list.ListHandler;
import com.jraw.android.jonsapp.ui.list.ListHandlerCallback;
import com.jraw.android.jonsapp.ui.list.ListRecyclerViewAdapter;

import java.util.List;

/**
 * Handles the View part of Conversations feature. Gets Conversation List from Presenter.
 *
 * Since it doesnt need an id or ref to get the conversations there is no need for savedInstanceState.
 *
 * What about SearchViewQuerying? This will require options menu blah blah then setting the OnQueryHandler
 * as mPresenters getConverationsViaTitle
 */
public class ConversationFragment extends Fragment implements ConversationContract.ViewConversations {

    private ConversationContract.PresenterConversations mPresenter;

    private ListHandler mListHandler;

    public ConversationFragment() {}

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_conversations, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Set ListHandler here
        RecyclerView recyclerView = view.findViewById(R.id.fragment_conversations_recycler_view);
        mListHandler = new ListHandler(this,
                recyclerView,
                new ListRecyclerViewAdapter(new ListHandlerCallback() {
                    @Override
                    public void onListClick(int aPosition, String aId) {
                        //This is what is set on every item in the list
                        //TODO: bring up msg list using Conversation Id
                    }

                    @Override
                    public void onListTouch(View aView, MotionEvent aMotionEvent) {
                        //This is what is set on every item in the list
                    }
                }, R.layout.fragment_list_item_convs),
                new LinearLayoutManager(recyclerView.getContext(),LinearLayoutManager.VERTICAL,false));
    }

    @Override
    public void setConversations(List<entity> aList) {
        mListHandler.swapData(null,null,aList,null);
    }

    @Override
    public void setPresenter(ConversationContract.PresenterConversations aPresenter) {
        mPresenter = aPresenter;
    }

    @Override
    public void onResume() {
        super.onResume();
        //Get data
        mPresenter.getConversations();
    }

    @Override
    public void onPause() {
        super.onPause();
        //Ensures the observables arent subscribed when the screen isnt showing.
        mPresenter.onUnsubscribe();
    }
}

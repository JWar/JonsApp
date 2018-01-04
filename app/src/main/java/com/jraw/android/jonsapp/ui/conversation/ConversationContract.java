package com.jraw.android.jonsapp.ui.conversation;

import com.jraw.android.jonsapp.data.model.entity;

import java.util.List;

/**
 * Created by JWar on 19/12/2017.
 *
 * This creates a binding between the parts involved in showing Conversations.
 * Basically this gives a 'top-level' view of how this feature works in the app.
 *
 */

public interface ConversationContract {
    interface ViewConversations {
        //Sets ListHandler to use this list.
        void setConversations(List<entity> aList);
        void setPresenter(PresenterConversations aPresenter);
    }
    interface PresenterConversations {
        //I hope this is fairly self explanatory. Call by View.
        void getConversations();
        //Used in Search Query to filter Conversations with a particular title.
        void getConversationsViaTitle(String aTitle);
        void onUnsubscribe();
    }
}

package com.jraw.android.jonsapp.ui.conversations;

import android.database.Cursor;

/**
 * Created by JWar on 19/12/2017.
 *
 * This creates a binding between the parts involved in showing Conversations.
 * Basically this gives a 'top-level' view of how this feature works in the app.
 *
 */

public interface ConversationsContract {
    interface ViewConversations {
        //Sets ListHandler to use this list.
        void setConversations(Cursor aCursor);
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

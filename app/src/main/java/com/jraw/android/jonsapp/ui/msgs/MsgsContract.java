package com.jraw.android.jonsapp.ui.msgs;

import com.jraw.android.jonsapp.data.model.entity;

import java.util.List;

/**
 * Created by JonGaming on 05/01/2018.
 * Interface to provide the contract for Msgs.
 */

public interface MsgsContract {
    interface ViewMsgs {
        //Sets ListHandler to use this list.
        void setMsgs(List<entity> aList);
        void setPresenter(PresenterMsgs aPresenter);
    }
    interface PresenterMsgs {
        //I hope this is fairly self explanatory. Called by View.
        void getMsgs(int aCOId);
        //Used in Search Query to filter Msgs by their content
        void getMsgsViaBody(int aCOId, String aText);
        void onUnsubscribe();
    }
}

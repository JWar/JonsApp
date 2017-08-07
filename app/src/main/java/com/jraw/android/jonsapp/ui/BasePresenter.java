package com.jraw.android.jonsapp.ui;

/**
 * Created by JonGaming on 08/06/2017.
 * Presenter for getting conversations and msg lists.
 */

public interface BasePresenter {
    //Gets generic data from data id. Upto presenter to understand whats going on.
    void getData(int aId) throws Exception;
    //Gets all msgs in conversations
//    void getMsgs(int aConvID) throws Exception;
//    //Gets all conversations user has
//    void getConvs(int aUserID) throws Exception;
}


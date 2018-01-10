package com.jraw.android.jonsapp.ui.searchbar;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SearchView;
import android.view.View;

import com.jraw.android.jonsapp.R;
import com.jraw.android.jonsapp.utils.Utils;

/**
 * Created by JonGaming on 10/01/2018.
 * Custom bar that appears at top of screen. Used in Msgs feature but made in such a way
 * that its fairly modular.
 * Uses include layout.
 * The idea is to have something that can be fairly easily 'slot' into a parent View. For instance it will
 * appear at the top of the Msgs screen and replace Android's ToolBar/ActionBar/blahbar. Using the ActionBar
 * fine. Just wanted to demonstrate an alternative, I also find this technique quite useful as its almost
 * 'plug and play'. You have this component that you put into the screen and it just does its thing once you've
 * hooked up what needs to be hooked up.
 *
 * SearchBarHost is useful as it provides a way of SearchBar to communicate with its host. At the moment
 * this is not used, but if SearchBar's functionality is extended... plus it imposes structure!
 */

public class SearchBar implements SearchBarContract.SearchBar {

    private View mParentView;
    private SearchView mSearchView;

    public SearchBar(Fragment aSearchBarHost,
                     View aView) {
        if (!(aSearchBarHost instanceof SearchBarContract.SearchBarHost)) {
            throw new RuntimeException("Please init SearchBarContract.SearchBarHost");
        }
        mParentView = aView.findViewById(R.id.search_bar);
        mSearchView = mParentView.findViewById(R.id.search_bar_search_view);
    }

    @Override
    public void setSearchViewQueryListener(SearchView.OnQueryTextListener aSearchViewQueryListener) {
        mSearchView.setOnQueryTextListener(aSearchViewQueryListener);
    }
    //This is necessary to prevent memory leaks... should do the job? Call in fragments onDestroy...
    @Override
    public void clear() {
        mParentView=null;
        mSearchView=null;
    }
}

package com.jraw.android.jonsapp.ui.searchbar;

import android.support.v7.widget.SearchView;

/**
 * Created by JonGaming on 10/01/2018.
 * Assigns methods to search bar AND is used by all views using search bar.
 * Ensures there is structure between search bar and its host.
 * What I like doing is making sure the SearchBar class has a way of checking that its host
 * has been initialised and is a search bar host. A bit like the check done in onAttach when you
 * make a new fragment using Android Studio's wizard and select the Activity interface callback options.
 * It ensures that everything is communicating properly.
 */

public interface SearchBarContract {
    interface SearchBar {
        void setSearchViewQueryListener(SearchView.OnQueryTextListener aSearchViewQueryListener);
        void clear();
    }
    interface SearchBarHost {
        void clear();//Ensures host enforces a clear method. To avoid memory leaks!
    }
}

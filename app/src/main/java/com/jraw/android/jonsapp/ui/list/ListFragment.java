package com.jraw.android.jonsapp.ui.list;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.jraw.android.jonsapp.R;
import com.jraw.android.jonsapp.ui.BaseListView;
import com.jraw.android.jonsapp.ui.BasePresenter;
import com.jraw.android.jonsapp.utils.Utils;

import org.json.JSONArray;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by JonGaming on 06/06/2017.
 * Handles lists
 */
public class ListFragment extends Fragment implements BaseListView<BasePresenter> {
    public static final String LIST_FRAG_TAG = "listFragTag";
    public static final String LIST_TYPE = "entType";
    public static final String ID_TO_SEARCH = "idToSearch";
    private int mListType;
    private String idToSearch;
    RecyclerView mRecyclerView;
    TextView errorTV;
    private ProgressBar mLoadingIndicator;
    public ListRecyclerViewAdapter listRecyclerAdapter;
    private LinearLayoutManager mLayoutManager;

    private BasePresenter mPresenter;

    private OnListFragmentInteractionListener mListener;


    //Urgh, holds ref to recordLists cursor. Cursor from dialogActivitys checkNumber when multiple records found
    //Cant think of a better way...
//    private Cursor recordListCur;
//    private JSONArray recordListArray;

    public ListFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(false);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle args = getArguments();
        if (savedInstanceState != null) {
            mListType = savedInstanceState.getInt(LIST_TYPE);
            idToSearch = savedInstanceState.getString(ID_TO_SEARCH);
        } else if (args != null) {
            mListType = args.getInt(LIST_TYPE);
            idToSearch = args.getString(ID_TO_SEARCH);
        }
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(View view,
                              Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        errorTV = (TextView) view.findViewById(R.id.error_message_display);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.list);
        mLoadingIndicator = (ProgressBar) view.findViewById(R.id.list_loading_indicator);
        Context context = mRecyclerView.getContext();
        mLayoutManager = new LinearLayoutManager(context);
        //Think can just reverse data in repo... rather than faffing about with view
//        if (mListType == R.layout.fragment_list_item_sms_person) {
//            mLayoutManager.setReverseLayout(true);
//        }
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (isLastItemDisplaying(mRecyclerView)) {
                    mListener.setFABDown(true);
                } else {
                    mListener.setFABDown(false);
                }
                if (isFirstItemDisplaying(mRecyclerView)) {
                    mListener.setFABUp(true);
                } else {
                    mListener.setFABUp(false);
                }
            }
        });
        listRecyclerAdapter = new ListRecyclerViewAdapter(mListener, mListType);
        mRecyclerView.setAdapter(listRecyclerAdapter);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //Value passed via intent. If -1 then tells listFragment to load from recordListCursor/Array otherwise call Loader
        //If IdToSearch is -2 then want all records. If -1 then data will be added at a later point. Otherwise,
        //search using id. This is because id can be 0...
        try {
            if (!idToSearch.equals("-1")) {
                showLoading();
//                mPresenter.getList(idToSearch);
//        } else {
//            swapData(recordListArray,recordListCur);
            }
        } catch (Exception e) {
            Utils.logDebug("Error in ListFragment.onActivityCreated: " + e.getMessage());}
    }
    @Override
    public void showLoading() {
        mRecyclerView.setVisibility(View.INVISIBLE);
        mLoadingIndicator.setVisibility(View.VISIBLE);
    }
    @Override
    public void showListView() {
        errorTV.setVisibility(View.GONE);
        mLoadingIndicator.setVisibility(View.INVISIBLE);
        mRecyclerView.setVisibility(View.VISIBLE);
    }
    public void swapData(JSONArray aArray, Cursor aCur) {
        swapData(aArray, aCur, -1);
    }
    @Override
    public void swapData(JSONArray aArray, Cursor aCur, int aScrollPos) {
        try {

            checkNotNull(listRecyclerAdapter);
            if (aArray != null) {
                if (aScrollPos > -1) {
                    if (aScrollPos == 1) {//Scroll down
                        mRecyclerView.scrollToPosition(0);//Forces scroll to top
                    } else if (aScrollPos == 2) {//Scroll up
                        mRecyclerView.scrollToPosition(aArray.length() - 1);//Forces scroll to bottom
                    }
                }
                listRecyclerAdapter.swapArray(aArray);
            } else if (aCur != null) {
                if (aScrollPos > -1) {
                    if (aScrollPos == 1) {//Scroll down
                        mRecyclerView.scrollToPosition(0);//Forces scroll to top
                    } else if (aScrollPos == 2) {//Scroll up
                        mRecyclerView.scrollToPosition(aCur.getCount() - 1);//Forces scroll to bottom
                    }
                }
                listRecyclerAdapter.swapCursor(aCur);
            } else {//No data assigned so just do nothing.
                return;
            }
            handlePostSwap();
        } catch (Exception e) {
            Utils.logDebug("Error in ListFragment.swapData: " + e.getMessage());
        }
    }

    //Shows data if present or error msgs if not
    private void handlePostSwap() throws Exception {
        //Error/record reporting to user
        int count = listRecyclerAdapter.getItemCount();
        if (count < 1) {
            if (errorTV != null) {
                if (count == 0) {
                    errorTV.setText(getString(R.string.error_message_records));
                }
                errorTV.setVisibility(View.VISIBLE);
            }
            mLoadingIndicator.setVisibility(View.INVISIBLE);//Hides progress bar
        } else {
            showListView();
        }
    }

    /**
     * Check whether the last item in RecyclerView is being displayed or not
     *
     * @param recyclerView which you would like to check
     * @return true if last position was Visible and false Otherwise
     */
    private boolean isLastItemDisplaying(RecyclerView recyclerView) {
        if (recyclerView.getAdapter().getItemCount() != 0) {
            int lastVisibleItemPosition = mLayoutManager.findLastCompletelyVisibleItemPosition();
            int count = recyclerView.getAdapter().getItemCount();
            if (lastVisibleItemPosition != RecyclerView.NO_POSITION &&
                    count == 50 &&//Checks to see if list length over 50
                    lastVisibleItemPosition == count - 1)//This can just check if 50th... anything less than 49 and we dont care.
                return true;
        }
        return false;
    }

    private boolean isFirstItemDisplaying(RecyclerView recyclerView) {
        if (recyclerView.getAdapter().getItemCount() != 0) {
            int firstVisibleItemPosition = mLayoutManager.findFirstCompletelyVisibleItemPosition();
            if (firstVisibleItemPosition != RecyclerView.NO_POSITION && firstVisibleItemPosition == 0 &&
                    recyclerView.getAdapter().getItemCount() > 0) //<-- last one checks to see if there are records
                return true;
        }
        return false;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //As field may have changed whilst fragment was running need to save to bundle. SAVE BUTTON? DELETE BUTTON?
        outState.putInt(LIST_TYPE, mListType);
        outState.putString(ID_TO_SEARCH, idToSearch);
    }

    public static ListFragment newInstance(int aListType, String aIdToSearch, BasePresenter aPres) {
        ListFragment fragment = new ListFragment();
        Bundle args = new Bundle();
        args.putInt(LIST_TYPE, aListType);
        args.putString(ID_TO_SEARCH, aIdToSearch);
        fragment.setArguments(args);
        fragment.setPresenter(aPres);
        return fragment;
    }

    public interface OnListFragmentInteractionListener {
        void onListClick(int position, String dataId);

        void getList(String aId);

        void setFABDown(boolean aBool);

        void setFABUp(boolean aBool);
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mRecyclerView != null) {
            mRecyclerView.stopScroll();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (listRecyclerAdapter != null) {
            listRecyclerAdapter.closeCursor();
        }
    }
    @Override
    public void setViews(Cursor aCur) {
        swapData(null, aCur);
    }
    @Override
    public void setViews(JSONArray aArray) {
        swapData(aArray, null);
    }
    @Override
    public void problemFindingData() {
        Toast.makeText(getActivity(), "Problem loading list data", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void setPresenter(@NonNull BasePresenter aPres) {
        mPresenter = checkNotNull(aPres);
    }
}


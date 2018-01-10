package com.jraw.android.jonsapp.ui.msgs;


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
 * A simple {@link Fragment} subclass.
 */
public class MsgsFragment extends Fragment implements MsgsContract.ViewMsgs {

    public static final String TAG = "msgsFragTag";
    private static final String CO_ID = "coId";
    private int mCOId;

    private MsgsContract.PresenterMsgs mPresenterMsgs;

    private ListHandler mListHandler;

    public MsgsFragment() {}

    public static MsgsFragment getInstance(int aCOID) {
        MsgsFragment fragment = new MsgsFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(CO_ID,aCOID);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState!=null) {
            mCOId = savedInstanceState.getInt(CO_ID);
        } else if (getArguments()!=null) {
            mCOId = getArguments().getInt(CO_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_msgs, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Set ListHandler here
        RecyclerView recyclerView = view.findViewById(R.id.fragment_msgs_recycler_view);
        mListHandler = new ListHandler(this,
                recyclerView,
                new ListRecyclerViewAdapter(new ListHandlerCallback() {
                    @Override
                    public void onListClick(int aPosition, String aId) {
                        //This is what is set on every item in the list
                        //TODO: what to do on msg click??
                    }

                    @Override
                    public void onListTouch(View aView, MotionEvent aMotionEvent) {
                        //This is what is set on every item in the list
                    }
                }, R.layout.fragment_list_item_msgs),
                new LinearLayoutManager(recyclerView.getContext(),LinearLayoutManager.VERTICAL,false));
    }

    @Override
    public void setMsgs(List<entity> aList) {
        mListHandler.swapData(null,null,aList,null);
    }

    @Override
    public void setPresenter(MsgsContract.PresenterMsgs aPresenter) {
        mPresenterMsgs=aPresenter;
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenterMsgs.onUnsubscribe();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenterMsgs.getMsgs(mCOId);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(CO_ID,mCOId);
    }
}

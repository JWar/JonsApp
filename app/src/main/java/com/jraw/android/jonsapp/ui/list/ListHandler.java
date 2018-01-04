package com.jraw.android.jonsapp.ui.list;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jraw.android.jonsapp.data.model.Conversation;
import com.jraw.android.jonsapp.data.model.Msg;
import com.jraw.android.jonsapp.data.model.entity;
import com.jraw.android.jonsapp.utils.Utils;

import org.json.JSONArray;

import java.util.List;

import static android.widget.LinearLayout.HORIZONTAL;
import static android.widget.LinearLayout.VERTICAL;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by JonGaming on 09/10/2017.
 * <p>
 * Replacement for ListHandler. Uses recyclerview and Adapter together.
 * Aiming to make this a reusable component that simply needs a RecyclerView element in xml.
 * <p>
 * Passing view to hold references to views?
 * This must have effective cleaning up as views hold reference to activity.
 */

public class ListHandler {
    private RecyclerView mRecyclerView;
    private ListRecyclerViewAdapter mListRecyclerAdapter;
    private LinearLayoutManager mLayoutManager;

    //'Injecting' params as opposed to creating them in ListHandler... Could make it easier to
    //make adapter and manager in init??
    //Testing needs injection...
    public ListHandler(@NonNull Fragment aFragment,
                       @NonNull RecyclerView aRecyclerView,
                       @NonNull ListRecyclerViewAdapter aListRecyclerAdapter,
                       @NonNull LinearLayoutManager aLayoutManager) {
        try {
            checkNotNull(aFragment);
            if (!(aFragment instanceof ListHandlerContract)) {
                throw new RuntimeException(aFragment.toString()
                        + " must implement ListHandlerContract");
            }
            mRecyclerView = checkNotNull(aRecyclerView);
            mListRecyclerAdapter = checkNotNull(aListRecyclerAdapter);
            mLayoutManager = checkNotNull(aLayoutManager);
            mRecyclerView.setLayoutManager(mLayoutManager);
            mRecyclerView.setAdapter(mListRecyclerAdapter);
        } catch (Exception e) {
            Utils.logDebug("Error in ListHandler(constructor): " + e.getLocalizedMessage());
        }
    }
    //Not used. Used for handling changes to ui based upon a scroll.
    public void addOnScrollListener(RecyclerView.OnScrollListener aOnScrollListener) {
        mRecyclerView.addOnScrollListener(aOnScrollListener);
    }

    public void smoothScrollToPosition(RecyclerView aRecyclerView, RecyclerView.State aState, int aPosition) {
        mLayoutManager.smoothScrollToPosition(aRecyclerView, aState, aPosition);
    }

    public void scrollToPosition(int aPosition) {
        mRecyclerView.scrollToPosition(aPosition);
    }

    public int findFirstVisibleItemPosition() {
        return mLayoutManager.findFirstVisibleItemPosition();
    }

    public int getFixScrollPos() {
        if (mLayoutManager.getChildCount() == 0) {
            return 0;
        }

        final View child = mLayoutManager.getChildAt(0);
        final int childPos = mLayoutManager.getPosition(child);

        if (mLayoutManager.getOrientation() == HORIZONTAL
                && Math.abs(child.getLeft()) > child.getMeasuredWidth() / 2) {
            // Scrolled first view more than halfway offscreen
            return childPos + 1;
        } else if (mLayoutManager.getOrientation() == VERTICAL
                && Math.abs(child.getTop()) > child.getMeasuredWidth() / 2) {
            // Scrolled first view more than halfway offscreen
            return childPos + 1;
        }
        return childPos;
    }

    public void showLoading() {
//        showNewConv(false);
//        mRecyclerView.setVisibility(View.INVISIBLE);
//        mLoadingIndicator.setVisibility(View.VISIBLE);
    }
//
//    public void swapConversations(List<Conversation> aList) {
//        mListRecyclerAdapter.swapConversations(aList);
//    }
//    public void swapMsgs(List<Msg> aList) {
//        mListRecyclerAdapter.swapMsgs(aList);
//    }

    public void swapData(JSONArray aArray, Cursor aCur,
                         List<entity> aList, List<List<entity>> aXList) {
        swapData(aArray, aCur, aList, aXList, -1);
    }

    public void swapData(JSONArray aArray, Cursor aCur,
                         List<entity> aList, List<List<entity>> aXList,
                         int aScrollPos) {
        try {
            checkNotNull(mListRecyclerAdapter);
            if (aCur != null) {
                if (aScrollPos > -1) {
                    if (aScrollPos == 1) {//Scroll down
                        mRecyclerView.scrollToPosition(0);//Forces scroll to top
                    } else if (aScrollPos == 2) {//Scroll up
                        mRecyclerView.scrollToPosition(aCur.getCount() - 1);//Forces scroll to bottom
                    }
                }
                mListRecyclerAdapter.swapCursor(aCur);
            } else if (aList != null) {
                if (aScrollPos > -1) {
                    if (aScrollPos == 1) {//Scroll down
                        mRecyclerView.scrollToPosition(0);//Forces scroll to top
                    } else if (aScrollPos == 2) {//Scroll up
                        mRecyclerView.scrollToPosition(aList.size() - 1);//Forces scroll to bottom
                    }
                }
                mListRecyclerAdapter.swapList(aList);
            } else if (aXList != null) {
                if (aScrollPos > -1) {
                    if (aScrollPos == 1) {//Scroll down
                        mRecyclerView.scrollToPosition(0);//Forces scroll to top
                    } else if (aScrollPos == 2) {//Scroll up
                        mRecyclerView.scrollToPosition(aXList.size() - 1);//Forces scroll to bottom
                    }
                }
                mListRecyclerAdapter.swapXList(aXList);
            }//No data assigned so just do nothing. This shouldnt happen though...
        } catch (Exception e) {
            Utils.logDebug("Error in ListHandler.swapData: " + e.getMessage());
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

    public void stopScroll() {
        if (mRecyclerView != null) {
            mRecyclerView.stopScroll();
        }
    }

    //This must be called onDestroy to prevent view leak.
    public void clearListHandler() {
        if (mRecyclerView != null) {
            mRecyclerView = null;
        }
        if (mListRecyclerAdapter != null) {
            mListRecyclerAdapter = null;
        }
        if (mLayoutManager != null) {
            mLayoutManager = null;
        }
    }

    //Create contract here so every fragment that uses RecyclerView must override.
    //The fragment can then use these methods in the onCreate etc... as it sees fit.
    //Sigh this is silly. What about fragments with more than one list?
    public interface ListHandlerContract {
        void clearListHandler();
    }
}

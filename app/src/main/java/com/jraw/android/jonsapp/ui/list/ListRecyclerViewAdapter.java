package com.jraw.android.jonsapp.ui.list;

import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.jraw.android.jonsapp.R;
import com.jraw.android.jonsapp.data.model.Conversation;
import com.jraw.android.jonsapp.data.model.Msg;
import com.jraw.android.jonsapp.data.model.entity;
import com.jraw.android.jonsapp.ui.list.holders.AbstractHolder;
import com.jraw.android.jonsapp.ui.list.holders.ConvsHolder;
import com.jraw.android.jonsapp.ui.list.holders.MsgsHolder;
import com.jraw.android.jonsapp.utils.Utils;

import org.json.JSONArray;

import java.util.List;

/**
 * Created by JonGaming on 06/06/2017.
 *
 * Customized adapter that takes in different viewholders to provide different information in each list item.
 * Not onCreateViewHolder needs to specify which holder to use based upon list type.
 * TODO: 180104_think on a better way of handling different entity types that doesnt involve creating new methods/fields
 */
public class ListRecyclerViewAdapter extends RecyclerView.Adapter<AbstractHolder> {
    private final ListHandlerCallback mListener;
    //Specifies what sort of list wanted (client, user, activity etc...). Will use R.layout.fragment_list_item_...
    private int listItemType;

    private Cursor mCursor;
    //Probably redundant but put in just in case
    private List<entity> mList;
//    private List<Conversation> mConversations;
//    private List<Msg> mMsgs;
//    private List<List<entity>> mXList;

    public ListRecyclerViewAdapter(ListHandlerCallback listener, int type) {
        mListener = listener;
        listItemType = type;
    }

    //This is where the holder type is specified. Must check param list type and change accordingly.
    //User Type to User Holder etc...
    @Override
    public AbstractHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(listItemType, parent, false);
        //Control which viewholder to call based upon list (item) type.
        switch(listItemType) {
            case R.layout.fragment_list_item_msgs:
                return new MsgsHolder(view);
            case R.layout.fragment_list_item_convs:
                return new ConvsHolder(view);
            default:
                Utils.logDebug("Error in ListRecyclerAdapter.onCreateViewHolder: listItemType unrecognised.");
                return null;
        }
    }
    //This is where the data is put into the holder (in bindData).
    @Override
    public void onBindViewHolder(final AbstractHolder holder, final int position) {
        try {
            String dId=null;
            //180104_Changed to using lists, sigh clumsy clumsy
            if (holder instanceof MsgsHolder) {
                MsgsHolder msgsHolder = (MsgsHolder) holder;
                dId = msgsHolder.bindData(mList.get(position),position);
            } else if (holder instanceof ConvsHolder) {
                ConvsHolder convsHolder = (ConvsHolder) holder;
                dId = convsHolder.bindData(mList.get(position),position);
            }
//            //Cursor used here!
//            mCursor.moveToPosition(position);
//            dId = holder.bindData(mCursor,position);

            final String dataId = dId;
            holder.setListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {//This sets up what on click will be. Draws from parent Activity.
                    try {
                        if (null != mListener) {
                            // Notify the active callbacks interface (the activity, if the
                            // fragment is attached to one) that an item has been selected.
                            mListener.onListClick(holder.getAdapterPosition(), dataId);
                        }
                    } catch (Exception e) {
                        Utils.logDebug("Error in onListListener: " + e.getMessage());
                    }
                }
            });
        } catch (Exception e) {
            Utils.logDebug("Error in ListRecyclerAdapter.onBindViewHolder: " + e.getMessage());
        }
    }
    @Override
    public long getItemId(int aPos) {
        try {
            return mList.get(aPos).getId();
        } catch (Exception e) {Utils.logDebug("Error in ListAdapter.getItemId: " + e.getMessage());return -1;}
    }
    @Override
    public int getItemCount() {
        try {
            if (mList!= null) {
                return mList.size();
            } else {
                return -1;
            }
        } catch (Exception e) {
            Utils.logDebug("Error in ListRecyclerAdapter.getItemCount: " + e.getMessage());
            return -1;
        }
    }
    public void swapCursor(Cursor aC) {
        try {
//            closeCursor();//Is this needed as SgLoaderFragment handles all cursor closing...
            mCursor = aC;
            notifyDataSetChanged();
        } catch (Exception e) {Utils.logDebug("Error in ListAdapter.swapCursor: "+e.getMessage());}
    }
    public void swapList(List<entity> aList) {
        mList = aList;
        notifyDataSetChanged();
    }
//    public void swapConversations(List<Conversation> aList) {
//        mConversations = aList;
//        notifyDataSetChanged();
//    }
//    public void swapMsgs(List<Msg> aList) {
//        mMsgs = aList;
//        notifyDataSetChanged();
//    }
    public void swapXList(List<List<entity>> aXList) {

    }
}


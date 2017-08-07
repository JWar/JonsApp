package com.jraw.android.jonsapp.ui.list;

import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.jraw.android.jonsapp.R;
import com.jraw.android.jonsapp.ui.list.holders.AbstractHolder;
import com.jraw.android.jonsapp.utils.Utils;

import org.json.JSONArray;

/**
 * Created by JonGaming on 06/06/2017.
 *
 * {@link RecyclerView.Adapter} that can display a holder and makes a call to the
 * specified {@link ListFragment.OnListFragmentInteractionListener}.
 *
 * Customized adapter that takes in different viewholders to provide different information in each list item.
 * Not onCreateViewHolder needs to specify which holder to use based upon list type.
 */
public class ListRecyclerViewAdapter extends RecyclerView.Adapter<AbstractHolder> {

    private JSONArray mValues;//Holds 'rows' data received from server. DOES THIS NEED TO BE FINAL?
    //Not final due to desire to have one adapter object, but dont know if thats silly...

    private final ListFragment.OnListFragmentInteractionListener mListener;
    //Specifies what sort of list wanted (client, user, activity etc...). Will use R.layout.fragment_list_item_...
    private int listItemType;

    Cursor cursor;

    public ListRecyclerViewAdapter(ListFragment.OnListFragmentInteractionListener listener, int type) {
        mListener = listener;
        listItemType = type;
//        mValuesCopy= items;
    }

    //This is where the holder type is specified. Must check param list type and change accordingly.
    //User Type to User Holder etc...
    @Override
    public AbstractHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(listItemType, parent, false);
        return null;
        //Control which viewholder to call based upon list (item) type.
//        switch(listItemType) {
//            case R.layout.fragment_list_item_user:
//                return new UserHolder(view);
//            case R.layout.fragment_list_item_person:
//            case R.layout.fragment_list_item_staff:
//                return new PersonHolder(view);
//            case R.layout.fragment_list_item_project:
//                return new ProjectHolder(view);
//            case R.layout.fragment_list_item_act:
//                return new ActHolder(view);
//            case R.layout.fragment_list_item_project_dialog:
//                return new PDialogProjHolder(view);
//            case R.layout.fragment_list_item_project_edit:
//                return new ProjectEditHolder(view);
//            case R.layout.fragment_list_item_log:
//                return new LogHolder(view);
//            case R.layout.fragment_list_item_person_call_sms:
//                return new PersonCallSMSHolder(view);
//            case R.layout.fragment_list_item_sms_person:
//                return new SMSPersonHolder(view);
//            case R.layout.fragment_list_item_switch_user:
//                return new SwitchUserHolder(view);
//            default:
//                Utils.logDebug("Error in ListRecyclerAdapter.onCreateViewHolder: listItemType unrecognised.");
//                return null;
//        }
    }
    //This is where the data is put into the holder (in bindData).
    @Override
    public void onBindViewHolder(final AbstractHolder holder, final int position) {
        try {
            String dId;
            if (mValues!=null) {//Handles whether list is using jsonArray or cursor!
                dId=holder.bindData(mValues.getJSONObject(position),position);
            } else {
                //Cursor used here!
                cursor.moveToPosition(position);
                dId = holder.bindData(cursor,position);
            }
            final String dataId = dId;
            holder.setListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {//This sets up what on click will be. Draws from parent Activity.
                    try {
                        if (null != mListener) {
                            // Notify the active callbacks interface (the activity, if the
                            // fragment is attached to one) that an item has been selected.
                            mListener.onListClick(position, dataId);
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
            if (mValues!=null) {
                return mValues.getJSONObject(aPos).getInt("id");
            } else {
                cursor.moveToPosition(aPos);
                return cursor.getLong(cursor.getInt(0));
            }
        } catch (Exception e) {Utils.logDebug("Error in ListAdapter.getItemId: " + e.getMessage());return -1;}
    }
    @Override
    public int getItemCount() {
        try {
            if (mValues!=null) {
                return mValues.length();
            } else {
                if (cursor != null) {
                    return cursor.getCount();
                } else {
                    return -1;
                }
            }
        } catch (Exception e) {
            Utils.logDebug("Error in ListRecyclerAdapter.getItemCount: " + e.getMessage());
            return -1;
        }
    }
    public void swapArray(JSONArray aArray) {
        try {
            mValues = aArray;
            notifyDataSetChanged();
        } catch (Exception e) {Utils.logDebug("Error in ListAdapter.swapArray: "+e.getMessage());}
    }
    public void swapCursor(Cursor aC) {
        try {
//            closeCursor();//Is this needed as SgLoaderFragment handles all cursor closing...
            cursor = aC;
            notifyDataSetChanged();
        } catch (Exception e) {Utils.logDebug("Error in ListAdapter.swapCursor: "+e.getMessage());}
    }
    public void closeCursor() {
        if (cursor!=null&&!cursor.isClosed()) {
            cursor.close();
            cursor=null;
        }
    }
}


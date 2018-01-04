package com.jraw.android.jonsapp.ui.list.holders;

import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.json.JSONObject;

/**
 * Created by JonGaming on 27/06/2017.
 * TODO: Hmm not sure this is needed... can cast holders based upon listItemType. So why does abstract holder need all methods?
 */

public abstract class AbstractHolder extends RecyclerView.ViewHolder {
    public AbstractHolder(View itemView) {
        super(itemView);
    }
    public abstract void setListener(View.OnClickListener aListener);
}
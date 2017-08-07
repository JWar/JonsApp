package com.jraw.android.jonsapp.ui.list.holders;

import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.json.JSONObject;

/**
 * Created by JonGaming on 27/06/2017.
 */

public abstract class AbstractHolder extends RecyclerView.ViewHolder {
    public AbstractHolder(View itemView) {
        super(itemView);
    }

    public abstract String bindData(Cursor aCursor, int aPos);
    public abstract String bindData(JSONObject aData, int aPos);
    public abstract void setListener(View.OnClickListener aListener);
}
package com.jraw.android.jonsapp.ui.list.holders;

import android.database.Cursor;
import android.view.View;
import android.widget.TextView;

import com.jraw.android.jonsapp.R;
import com.jraw.android.jonsapp.data.model.Conversation;
import com.jraw.android.jonsapp.data.model.entity;
import com.jraw.android.jonsapp.data.model.wrappers.ConversationCursorWrapper;

/**
 * Created by JWar on 21/12/2017.
 * Conversation item code
 */

public class ConvsHolder extends AbstractHolder {
    private View mView;
    private TextView mTitleTV;

    public ConvsHolder(View view) {
        super(view);
        mView = view;
        mTitleTV = view.findViewById(R.id.list_item_convs_title);
    }
    public String bindData(entity aEnt, int aPos) {
        return setViews((Conversation) aEnt,aPos);
    }
    private String setViews(Conversation aConv, int aPos) {
        mTitleTV.setText(aConv.getCOTitle());
        return aConv.getId()+"";
    }

    @Override
    public void setListener(View.OnClickListener aListener) {
        mView.setOnClickListener(aListener);
    }
    @Override
    public String toString() {
        return super.toString() + " '" + mTitleTV.getText() + " '";
    }
}

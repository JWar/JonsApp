package com.jraw.android.jonsapp.ui.list.holders;

import android.app.Activity;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jraw.android.jonsapp.MainActivity;
import com.jraw.android.jonsapp.R;

import org.json.JSONObject;

/**
 * Created by JonGaming on 27/06/2017.
 *
 */

public class MsgsHolder extends AbstractHolder {

        public View mView;
        public RelativeLayout mBodyRL;
        public RelativeLayout mTimeRL;
        public TextView mDateTV;//datetime text view
        public ImageView mTypeIV;
        public TextView mNameTV;
        public TextView mBodyTV;//body text view
        public ImageView mTickIV;
        public JSONObject mItem;

    public MsgsHolder(View view) {
            super(view);
            mView = view;
            mBodyRL = (RelativeLayout) view.findViewById(R.id.msgs_body_rl);
            mTimeRL = (RelativeLayout) view.findViewById(R.id.msgs_time_rl);
            mDateTV = (TextView) view.findViewById(R.id.msgs_time);
            mTypeIV = (ImageView) view.findViewById(R.id.msgs_image_view);
            mNameTV = (TextView) view.findViewById(R.id.msgs_name);
            mBodyTV = (TextView) view.findViewById(R.id.msgs_text_view);
        }

    private void setTimeToEnd() {
        mNameTV.setPaddingRelative(24,4,72,4);
        mBodyTV.setPaddingRelative(24,4,72,4);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        lp.addRule(RelativeLayout.ALIGN_PARENT_END);
        lp.setMargins(4, 24, 2, 4);
        mTimeRL.setLayoutParams(lp);
        lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(2,24,2,4);
        lp.addRule(RelativeLayout.START_OF,R.id.msgs_time_rl);
        mBodyRL.setLayoutParams(lp);
    }
    private void setTimeToStart() {
        mNameTV.setPaddingRelative(76,4,20,4);
        mBodyTV.setPaddingRelative(76,4,20,4);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        if (Build.VERSION.SDK_INT<17) {
            lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        } else {
            lp.addRule(RelativeLayout.ALIGN_PARENT_START);
        }
        lp.setMargins(4, 24, 2, 4);
        mTimeRL.setLayoutParams(lp);
        lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(2, 24, 2, 4);
        if (Build.VERSION.SDK_INT<17) {
            lp.addRule(RelativeLayout.RIGHT_OF,R.id.msgs_time_rl);
        } else {
            lp.addRule(RelativeLayout.END_OF, R.id.msgs_time_rl);
        }
        mBodyRL.setLayoutParams(lp);
//        mNameTV.setX(60);
//        mBodyTV.setX(60);
    }
    private String setViews(Activity aAct,int aPos) {
//        String toDisplay=aAct.getACEventDate();
//        mDateTV.setText(toDisplay);
//        switch (aAct.getACType()) {
//
//            case SgTxnEngine.ACTIVITY_TYPE_TEL_OUT:
//
//                if (Build.VERSION.SDK_INT<Build.VERSION_CODES.LOLLIPOP) {
//                    mBodyRL.setBackground(mView.getResources().getDrawable(
//                            R.drawable.bg_outgoing_bubble
//                    ));
//                } else {
//                    mBodyRL.setBackground(mView.getResources().getDrawable(
//                            R.drawable.bg_outgoing_bubble,null
//                    ));
//                }
//                setTimeToEnd();
//                break;
//            case SgTxnEngine.ACTIVITY_TYPE_TEL:
//
//                if (Build.VERSION.SDK_INT<Build.VERSION_CODES.LOLLIPOP) {
//                    mBodyRL.setBackground(mView.getResources().getDrawable(
//                            R.drawable.bg_incoming_bubble
//                    ));
//                } else {
//                    mBodyRL.setBackground(mView.getResources().getDrawable(
//                            R.drawable.bg_incoming_bubble,null
//                    ));
//                }
//                setTimeToStart();
//                break;
//        }
//        mBodyTV.setText(aAct.getACBody());
//
//        return aAct.getId()+"";
        return null;
    }

    @Override
    public String bindData(JSONObject aData, int aPos) {
        try {
            mItem = aData;
//            Activity act = new Activity(aData);
//            return setViews(act,aPos);
            return null;
        } catch (Exception e) {
            MainActivity.logDebug("Error in SMSPersonHolder.bindData(JSON): " + e.getMessage());return null;}
    }

    @Override
    public String bindData(Cursor aCursor, int aPos) {
        try {
//            Activity act = new ActivityCursorWrapper(aCursor).getActivity();
//            return setViews(act,aPos);
            return null;
        } catch (Exception e) {
            MainActivity.logDebug("Error in SMSPersonHolder.bindData(Cur): " + e.getMessage());return null;}
    }
    @Override
    public void setListener(View.OnClickListener aViewListener) {
        mView.setOnClickListener(aViewListener);
    }

    @Override
    public String toString() {
        return super.toString() + " '" + mDateTV.getText() + " '" + mBodyTV.getText() + "'" + mItem.toString();
    }
}

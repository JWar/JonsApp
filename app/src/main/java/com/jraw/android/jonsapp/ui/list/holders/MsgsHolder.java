package com.jraw.android.jonsapp.ui.list.holders;

import android.database.Cursor;
import android.os.Build;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jraw.android.jonsapp.MainActivity;
import com.jraw.android.jonsapp.R;
import com.jraw.android.jonsapp.data.model.Msg;
import com.jraw.android.jonsapp.data.model.wrappers.MsgCursorWrapper;

import org.json.JSONObject;

/**
 * Created by JonGaming on 27/06/2017.
 * Links xml with code, sets data etc... in views
 *
 * The setTimeToEnd/Start switches the direction of the views depending on if the msg is sent/received
 */

public class MsgsHolder extends AbstractHolder {

    private View mView;
    private RelativeLayout mBodyRL;
    private RelativeLayout mTimeRL;
    private TextView mDateTV;//datetime text view
    private TextView mNameTV;
    private TextView mBodyTV;//body text view
    private ImageView mTickIV;
    private JSONObject mItem;

    public MsgsHolder(View view) {
        super(view);
        mView = view;
        mBodyRL = view.findViewById(R.id.list_item_msgs_body_rl);
        mTimeRL = view.findViewById(R.id.list_item_msgs_time_rl);
        mDateTV = view.findViewById(R.id.list_item_msgs_time);
        mNameTV = view.findViewById(R.id.list_item_msgs_name);
        mBodyTV = view.findViewById(R.id.list_item_msgs_text_view);
    }

    private void setTimeToEnd() {
        mNameTV.setPaddingRelative(24, 4, 72, 4);
        mBodyTV.setPaddingRelative(24, 4, 72, 4);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        lp.addRule(RelativeLayout.ALIGN_PARENT_END);
        lp.setMargins(4, 24, 2, 4);
        mTimeRL.setLayoutParams(lp);
        lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(2, 24, 2, 4);
        lp.addRule(RelativeLayout.START_OF, R.id.msgs_time_rl);
        mBodyRL.setLayoutParams(lp);
    }

    private void setTimeToStart() {
        mNameTV.setPaddingRelative(76, 4, 20, 4);
        mBodyTV.setPaddingRelative(76, 4, 20, 4);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        if (Build.VERSION.SDK_INT < 17) {
            lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        } else {
            lp.addRule(RelativeLayout.ALIGN_PARENT_START);
        }
        lp.setMargins(4, 24, 2, 4);
        mTimeRL.setLayoutParams(lp);
        lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(2, 24, 2, 4);
        if (Build.VERSION.SDK_INT < 17) {
            lp.addRule(RelativeLayout.RIGHT_OF, R.id.msgs_time_rl);
        } else {
            lp.addRule(RelativeLayout.END_OF, R.id.msgs_time_rl);
        }
        mBodyRL.setLayoutParams(lp);
//        mNameTV.setX(60);
//        mBodyTV.setX(60);
    }

    private String setViews(Msg aMsg, int aPos) {
        mBodyTV.setText(aMsg.getMSBody());
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
        return aMsg.getId()+"";
    }

    @Override
    public String bindData(Cursor aCursor, int aPos) {
        try {
            Msg msg = new MsgCursorWrapper(aCursor).getMsg();
            return setViews(msg,aPos);
        } catch (Exception e) {
            MainActivity.logDebug("Error in MsgsHolder.bindData(Cur): " + e.getMessage());
            return null;
        }
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

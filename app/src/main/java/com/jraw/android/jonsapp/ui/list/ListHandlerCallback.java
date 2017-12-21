package com.jraw.android.jonsapp.ui.list;

import android.view.MotionEvent;
import android.view.View;

/**
 * Created by JWar on 21/12/2017.
 * Provides way of adding OnClick/OnTouch handlers on the list items.
 * Done in such a way so as to make it flexible and allow whatever method is assigned to the handler.
 */

public interface ListHandlerCallback {
    void onListClick(int aPosition, String aId);
    void onListTouch(View aView, MotionEvent aMotionEvent);
}

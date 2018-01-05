package com.jraw.android.jonsapp.utils;

import android.database.Cursor;
import android.util.Log;

/*
 * Created by JonGaming on 06/06/2017.
 * Class that holds utility functions... usually static methods
 */
import static com.jraw.android.jonsapp.MainActivity.LOG_TAG;
import static com.jraw.android.jonsapp.MainActivity.LOG_DEBUG;

public class Utils {
    //Clumsy way of logging to console. Basically using this as catch all for any exceptions/errors/blah.
    //Of course this will require something more rigorous... presumably involving Crashalytics or the like.
    //A singular system that takes the errors and handles them gracefully so the dev can be alerted..
    public static void logDebug(String aLog) {if (LOG_DEBUG) {
        Log.i(LOG_TAG, aLog);}
    }
    public static void closeCursor(Cursor aCursor) {
        if (aCursor!=null&&!aCursor.isClosed()) {
            aCursor.close();
        }
    }
}

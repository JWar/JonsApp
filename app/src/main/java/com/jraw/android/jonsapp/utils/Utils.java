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

    public static final String CHANNEL_ID = "newmsg";
    //Quick and dirty way of assigning ids for different users. Mostly used in DummyData...
    //Will use shared preferences for this user!
    public static final String USER_ID = "userId";
    //This will be inited from SharedPreferences in real versions
    public static final int THIS_USER_ID=1;

    public static final int USER_A=1;
    public static final int USER_B=2;
    public static final int USER_C=3;
    public static final int USER_D=4;
    public static final int USER_E=5;
    public static final int USER_F=6;

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

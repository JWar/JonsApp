package com.jraw.android.jonsapp.utils;

import android.util.Log;

/**
 * Created by JonGaming on 06/06/2017.
 * Class that holds utility functions... usually static methods
 */
import static com.jraw.android.jonsapp.MainActivity.LOG_TAG;
import static com.jraw.android.jonsapp.MainActivity.LOG_DEBUG;

public class Utils {

    public static void logDebug(String aError) {if (LOG_DEBUG) {
        Log.i(LOG_TAG, aError);}}
}

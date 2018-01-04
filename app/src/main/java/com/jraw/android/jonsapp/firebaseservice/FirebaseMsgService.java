package com.jraw.android.jonsapp.firebaseservice;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.jraw.android.jonsapp.utils.Utils;

import org.json.JSONObject;

/**
 * Created by JonGaming on 27/06/2017.
 *
 */

public class FirebaseMsgService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Utils.logDebug("From: " + remoteMessage.getFrom());
        Utils.logDebug("Type: " + remoteMessage.getMessageType());
        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Intent intent = new Intent("noti");
            Utils.logDebug("Message data payload: " + remoteMessage.getData());
            intent.putExtra("data", new JSONObject(remoteMessage.getData()).toString());
            LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(intent);
        }
        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Intent intent = new Intent("noti");
            intent.putExtra("body",remoteMessage.getNotification().getBody());
            LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(intent);
            Utils.logDebug("Message Notification Body: " + remoteMessage.getNotification().getBody());
        }

        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.

    }
}

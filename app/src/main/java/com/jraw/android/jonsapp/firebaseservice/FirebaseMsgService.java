package com.jraw.android.jonsapp.firebaseservice;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.Gson;
import com.jraw.android.jonsapp.data.model.Msg;
import com.jraw.android.jonsapp.data.repositories.MsgRepository;
import com.jraw.android.jonsapp.utils.Utils;

/**
 * Created by JonGaming on 27/06/2017.
 *
 */

public class FirebaseMsgService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        //180117_Cant rememeber where the new Msg is put... I think in data but going to check for both that and
        //notification.
        //Not sure if going to run into problems with simply saving the msg to database but meh.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Utils.logDebug("From: " + remoteMessage.getFrom());
        Utils.logDebug("Type: " + remoteMessage.getMessageType());
        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
//            Intent intent = new Intent("noti");
            Utils.logDebug("Message data payload: " + remoteMessage.getData());
            //Cant remember what the format of the Firebase msg is... but it will get the string in json format and msgify it.
            try {
                Gson gson = new Gson();
                Msg msg = gson.fromJson(remoteMessage.getData().toString(), Msg.class);
                if (MsgRepository.get(this).saveMsg(msg)==0) {
                    throw new Exception("Problem saving Msg");
                }
            } catch (Exception e) {
                Utils.logDebug("Error in FirebaseMsgService.onMessageReceived: "+e.getLocalizedMessage());
            }
//            intent.putExtra("data", new JSONObject(remoteMessage.getData()).toString());
//            LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(intent);
        }
        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            //Will need to get Msg from notification but cant rem format...
            try {
                Gson gson = new Gson();
                Msg msg = gson.fromJson(remoteMessage.getNotification().toString(), Msg.class);
                if (MsgRepository.get(this).saveMsg(msg) == 0) {
                    throw new Exception("Problem saving Msg");
                }
            } catch (Exception e) {
                Utils.logDebug("Error in FirebaseMsgService.onMessageReceived Notifi: "+e.getLocalizedMessage());
            }
//            Intent intent = new Intent("noti");
//            intent.putExtra("body",remoteMessage.getNotification().getBody());
//            LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(intent);
            Utils.logDebug("Message Notification Body: " + remoteMessage.getNotification().getBody());
        }
    }
}

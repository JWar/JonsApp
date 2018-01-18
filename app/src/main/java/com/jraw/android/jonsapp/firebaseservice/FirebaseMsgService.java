package com.jraw.android.jonsapp.firebaseservice;

import android.app.Notification;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.Gson;
import com.jraw.android.jonsapp.R;
import com.jraw.android.jonsapp.data.model.Msg;
import com.jraw.android.jonsapp.data.repositories.MsgRepository;
import com.jraw.android.jonsapp.utils.Utils;

/**
 * Created by JonGaming on 27/06/2017.
 * Hmm design question: I reckon best thing is to send Msg to an intent service? or to put it another way:
 * is there a problem doing it with MsgRepository directly?
 */

public class FirebaseMsgService extends FirebaseMessagingService {
    //Kept in memory for app lifetime...
//    private static MsgRepository sMsgRepository;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        //180117_Cant remember where the new Msg is put... I think in data but going to check for both that and
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
                Msg msg = gson.fromJson(remoteMessage.getData().get("msg"), Msg.class);
                if (MsgRepository.get(this).saveMsg(msg)==0) {
                    throw new Exception("Problem saving Msg");
                }

                Notification notification = new NotificationCompat.Builder(this,Utils.CHANNEL_ID)
                        .setContentTitle(getString(R.string.notification_new_msg_received))
                        .setContentText(msg.getMSBody())
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .build();
                NotificationManagerCompat manager = NotificationManagerCompat.from(getApplicationContext());
                manager.notify(123, notification);
            } catch (Exception e) {
                Utils.logDebug("Error in FirebaseMsgService.onMessageReceived: "+e.getLocalizedMessage());
            }
//            intent.putExtra("data", new JSONObject(remoteMessage.getData()).toString());
//            LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(intent);
        }
    }
}

package com.jraw.android.jonsapp;

import com.jraw.android.jonsapp.data.repositories.ConversationRepository;
import com.jraw.android.jonsapp.data.repositories.MsgRepository;
import com.jraw.android.jonsapp.data.source.ConversationDataSource;
import com.jraw.android.jonsapp.data.source.MsgDataSource;
import com.jraw.android.jonsapp.utils.BaseSchedulerProvider;

/**
 * Created by JonGaming on 03/01/2018.
 * Used to get dependencies. In mock's case this will probably provide dummy data to be used in testing.
 */

public class Injection {
    public static ConversationRepository provideConversationRepository() {
        return null;
    }
    public static MsgRepository provideMsgRepository() {
        return null;
    }
    public static ConversationDataSource provideConversationDataSource() {
        return null;
    }
    public static MsgDataSource provideMsgDataSource() {
        return null;
    }
    public static BaseSchedulerProvider provideBaseSchedulerProvider() {
        return null;
    }
}

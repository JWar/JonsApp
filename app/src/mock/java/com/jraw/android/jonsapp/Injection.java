package com.jraw.android.jonsapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.jraw.android.jonsapp.data.repositories.ConversationRepository;
import com.jraw.android.jonsapp.data.repositories.MsgRepository;
import com.jraw.android.jonsapp.data.source.ConversationDataSource;
import com.jraw.android.jonsapp.data.source.MsgDataSource;
import com.jraw.android.jonsapp.data.source.local.FakeConversationLocalDataSource;
import com.jraw.android.jonsapp.data.source.local.FakeMsgLocalDataSource;
import com.jraw.android.jonsapp.utils.BaseSchedulerProvider;
import com.jraw.android.jonsapp.utils.SchedulerProvider;

/**
 * Created by JonGaming on 03/01/2018.
 * Used to get dependencies. In mock's case this will probably provide dummy data to be used in testing.
 */

public class Injection {
    //TODO:180111_Hmm how to avoid the unnecessary context param? Its needed to ensure switching build variants matches up...
    public static ConversationRepository provideConversationRepository(@Nullable Context aContext) {
        return ConversationRepository.getInstance(FakeConversationLocalDataSource.getInstance());
    }
    public static MsgRepository provideMsgRepository(@Nullable Context aContext) {
        return MsgRepository.getInstance(FakeMsgLocalDataSource.getInstance());
    }
    public static BaseSchedulerProvider provideBaseSchedulerProvider() {
        return SchedulerProvider.getInstance();
    }
}

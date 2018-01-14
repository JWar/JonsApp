package com.jraw.android.jonsapp;

import android.content.Context;
import android.support.annotation.Nullable;

import com.jraw.android.jonsapp.data.repositories.ConversationRepository;
import com.jraw.android.jonsapp.data.repositories.MsgRepository;
import com.jraw.android.jonsapp.data.source.local.FakeConversationLocalDataSource;
import com.jraw.android.jonsapp.data.source.local.FakeMsgLocalDataSource;
import com.jraw.android.jonsapp.utils.schedulers.BaseSchedulerProvider;
import com.jraw.android.jonsapp.utils.schedulers.SchedulerProvider;

/**
 * Created by JonGaming on 03/01/2018.
 * Used to get dependencies. In mock's case this will probably provide dummy data to be used in testing.
 */

public class Injection {
    //TODO:180111_Hmm how to avoid the unnecessary context param? Its needed to ensure switching build variants matches up...
    //180112_Seems its difficult to do this and keep the method signatures the same between mock/prod.
    //Context is of course needed for Prod. As I have drawn inspiration from googlesamples they have no
    //problem with context because of the use of local data source... Think ill just ignore it and
    //use the dummy data I have made.
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

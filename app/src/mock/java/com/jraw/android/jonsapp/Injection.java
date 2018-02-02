package com.jraw.android.jonsapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.jraw.android.jonsapp.data.repositories.ConversationRepository;
import com.jraw.android.jonsapp.data.repositories.MsgRepository;
import com.jraw.android.jonsapp.data.source.ConversationDataSource;
import com.jraw.android.jonsapp.data.source.MsgDataSource;
import com.jraw.android.jonsapp.data.source.local.ConversationLocalDataSource;
import com.jraw.android.jonsapp.data.source.local.FakeConversationLocalDataSource;
import com.jraw.android.jonsapp.data.source.local.FakeMsgLocalDataSource;
import com.jraw.android.jonsapp.data.source.local.MsgLocalDataSource;
import com.jraw.android.jonsapp.database.BriteWrapper;
import com.jraw.android.jonsapp.database.DbHelper;
import com.jraw.android.jonsapp.database.JonsAppDatabase;
import com.jraw.android.jonsapp.utils.schedulers.BaseSchedulerProvider;
import com.jraw.android.jonsapp.utils.schedulers.SchedulerProvider;
import com.squareup.sqlbrite2.SqlBrite;

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
    public static BaseSchedulerProvider provideSchedulerProvider() {
        return SchedulerProvider.getInstance();
    }
    public static BriteWrapper provideBriteWrapper(@NonNull Context aContext) throws Exception {
        return BriteWrapper.getInstance(
                new SqlBrite.Builder().build().wrapDatabaseHelper(
                        new DbHelper(aContext.getApplicationContext(),DbHelper.DATABASE_NAME,null,DbHelper.VERSION),
                        Injection.provideSchedulerProvider().io()));
    }
    public static ConversationDataSource provideConversationDataSource(@NonNull Context aContext) throws Exception {
        return ConversationLocalDataSource.getInstance(provideBriteWrapper(aContext));
    }
    public static MsgDataSource provideMsgDataSource(@NonNull Context aContext) throws Exception {
        return MsgLocalDataSource.getInstance(provideBriteWrapper(aContext));
    }
    public static JonsAppDatabase provideJonsAppDatabase(@NonNull Context aContext) throws Exception {
        return JonsAppDatabase.getInstance(aContext);
    }
}

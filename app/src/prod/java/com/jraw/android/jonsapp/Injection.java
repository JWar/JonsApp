package com.jraw.android.jonsapp;

import android.content.Context;
import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jraw.android.jonsapp.data.repositories.ConversationRepository;
import com.jraw.android.jonsapp.data.repositories.MsgRepository;
import com.jraw.android.jonsapp.data.source.ConversationDataSource;
import com.jraw.android.jonsapp.data.source.MsgDataSource;
import com.jraw.android.jonsapp.data.source.local.ConversationLocalDataSource;
import com.jraw.android.jonsapp.data.source.local.MsgLocalDataSource;
import com.jraw.android.jonsapp.database.BriteWrapper;
import com.jraw.android.jonsapp.database.DbHelper;
import com.jraw.android.jonsapp.data.source.remote.JonsAppApi;
import com.jraw.android.jonsapp.utils.BaseSchedulerProvider;
import com.jraw.android.jonsapp.utils.DoubleTypeAdapter;
import com.jraw.android.jonsapp.utils.SchedulerProvider;
import com.squareup.sqlbrite2.SqlBrite;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by JonGaming on 03/01/2018.
 * Used to provide various dependencies basically static methods that return the object needed.
 */

public class Injection {

    public static ConversationRepository provideConversationRepository(@NonNull Context aContext) throws Exception {
        return ConversationRepository.getInstance(provideConversationDataSource(aContext));
    }
    public static MsgRepository provideMsgRepository(@NonNull Context aContext) throws Exception {
        return MsgRepository.getInstance(provideMsgDataSource(aContext));
    }
    public static ConversationDataSource provideConversationDataSource(@NonNull Context aContext) throws Exception {
        return ConversationLocalDataSource.getInstance(provideBriteWrapper(aContext));
    }
    public static MsgDataSource provideMsgDataSource(@NonNull Context aContext) throws Exception {
        return MsgLocalDataSource.getInstance(provideBriteWrapper(aContext));
    }
    public static BaseSchedulerProvider provideBaseSchedulerProvider() throws Exception {
        return SchedulerProvider.getInstance();
    }
    public static BriteWrapper provideBriteWrapper(@NonNull Context aContext) throws Exception {
        return BriteWrapper.getInstance(
                new SqlBrite.Builder().build().wrapDatabaseHelper(
                        new DbHelper(aContext,DbHelper.DATABASE_NAME,null,DbHelper.VERSION),
                        Injection.provideSchedulerProvider().io()));
    }
    public static SchedulerProvider provideSchedulerProvider() throws Exception {
        return SchedulerProvider.getInstance();
    }
    public static JonsAppApi provideJonsAppApi() throws Exception {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(JonsAppApi.END_POINT)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(provideGson()))
                .build();
        return retrofit.create(JonsAppApi.class);
    }
    public static Gson provideGson() {
        return new GsonBuilder().registerTypeAdapter(Double.class,
                new DoubleTypeAdapter()).create();
    }
}

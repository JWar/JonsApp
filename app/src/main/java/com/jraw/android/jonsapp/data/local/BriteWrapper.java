package com.jraw.android.jonsapp.data.local;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.squareup.sqlbrite2.BriteDatabase;
import com.squareup.sqlbrite2.SqlBrite;
import com.squareup.sqlbrite2.SqlBrite.Query;

import io.reactivex.Observable;


/**
 * Created by JWar on 21/12/2017.
 * Provides singleton access to Brite.
 * This is so everything to do with the database goes through one point, meaning
 * content observers that Brite automatically sets up are always triggered correctly.
 * If you have multiple ways of CRUDing then you run the risk of missing any updates.
 * Thats the interesting thing about Rx and Observables they will detect changes to
 * the table you're using and respond accordingly. E.g. if a new msg comes in whilst you have
 * the msg list up then it will automatically refresh with that new message.
 *
 * This just has basic CRUD methods for interacting with BriteDatabase.
 * The LocalDataSources are where the actual SQL is stored...
 */

public class BriteWrapper {
    private static BriteWrapper sInstance = null;
    private BriteDatabase mBriteDatabase;

    public static synchronized BriteWrapper getInstance(BriteDatabase aBriteDatabase) {
        if (sInstance == null) {
            sInstance = new BriteWrapper(aBriteDatabase);
        }
        return sInstance;
    }

    private BriteWrapper(BriteDatabase aBriteDatabase) {
        mBriteDatabase = aBriteDatabase;
    }

    public long insert(@NonNull String table, @NonNull ContentValues contentValues) {
        return mBriteDatabase.insert(table,contentValues);
    }
    public Cursor query(String sql, String... args) {
        return mBriteDatabase.query(sql, args);
    }
    public Observable<Query> createQuery(@NonNull String table, @NonNull String sql, @NonNull String... args) {
        return mBriteDatabase.createQuery(table,sql,args);
    }
    public Observable<Query> createQuery(@NonNull Iterable<String> tables, @NonNull String sql, @NonNull String... args) {
        return mBriteDatabase.createQuery(tables,sql,args);
    }
    public int update(@NonNull String table, @NonNull ContentValues contentValues,
                      @Nullable String whereClause, @Nullable String... whereArgs) {
        return mBriteDatabase.update(table,contentValues,whereClause,whereArgs);
    }
    public int delete(@NonNull String table, @Nullable String whereClause, @Nullable String... whereArgs) {
        return mBriteDatabase.delete(table,whereClause,whereArgs);
    }
}

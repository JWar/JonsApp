package com.jraw.android.jonsapp.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

import com.jraw.android.jonsapp.data.model.Conversation;
import com.jraw.android.jonsapp.data.model.Msg;
import com.jraw.android.jonsapp.data.model.PeCo;
import com.jraw.android.jonsapp.data.model.PeTel;
import com.jraw.android.jonsapp.data.model.Person;
import com.jraw.android.jonsapp.data.model.Tel;
import com.jraw.android.jonsapp.data.model.Txn;
import com.jraw.android.jonsapp.database.dao.ConversationDao;

/**
 * Created by JWar on 02/02/2018.
 *
 */
@Database(entities = {Conversation.class, Msg.class, PeCo.class, Person.class, PeTel.class, Tel.class, Txn.class},
        version = 1)
public abstract class JonsAppDatabase extends RoomDatabase {
    public static final String DATABASE_NAME = "JonsAppData";

    private static JonsAppDatabase sInstance;

    public static synchronized JonsAppDatabase getInstance(@NonNull Context aContext) {
        if (sInstance == null) {
            sInstance = Room.databaseBuilder(aContext.getApplicationContext(),
                    JonsAppDatabase.class, JonsAppDatabase.DATABASE_NAME).build();
        }
        return sInstance;
    }
    public static void destroyInstance() {
        sInstance=null;
    }

    public abstract ConversationDao conversationDao();
}

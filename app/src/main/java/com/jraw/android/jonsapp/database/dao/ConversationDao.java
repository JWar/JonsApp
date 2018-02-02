package com.jraw.android.jonsapp.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.jraw.android.jonsapp.data.model.Conversation;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by JWar on 02/02/2018.
 *
 */
@Dao
public interface ConversationDao {

    @Query("SELECT * FROM conversation")
    Flowable<List<Conversation>> getAllConversations();

    @Insert
    long insertConversation(Conversation aConversation);
    @Insert
    List<Long> insertConversations(List<Conversation> aConversations);

    @Update
    int updateConversation(Conversation aConversation);
    @Update
    int updateConversations(Conversation... aConversations);

    @Delete
    int deleteConversation(Conversation aConversation);
    @Delete
    int deleteConversations(Conversation... aConversations);


}

package com.jraw.android.jonsapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.jraw.android.jonsapp.database.DbSchema.*;
import com.jraw.android.jonsapp.utils.Utils;

/**
 * Created by JonGaming on 02/01/2018.
 * Quick and dirty data base creation.
 */

public class DbHelper extends SQLiteOpenHelper {
    public static final int VERSION = 1;
    public static final String DATABASE_NAME = "JonsAppData";

    public DbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Utils.logDebug("DbHelper.onCreate");
        db.execSQL(DATABASE_CREATE_PERSON);
        db.execSQL(DATABASE_CREATE_CONVERSATION);
        db.execSQL(DATABASE_CREATE_MSG);
        db.execSQL(DATABASE_CREATE_TEL);
        db.execSQL(DATABASE_CREATE_PETEL);
        db.execSQL(DATABASE_CREATE_PECO);
        db.execSQL(DATABASE_CREATE_TXN);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            // Simplest implementation is to drop all old tables and recreate them
            //toExec needed because of lint error with string concatenation
            String toExec = "DROP TABLE IF EXISTS " + DATABASE_CREATE_PERSON;
            db.execSQL(toExec);
            toExec = "DROP TABLE IF EXISTS " + DATABASE_CREATE_MSG;
            db.execSQL(toExec);
            toExec = "DROP TABLE IF EXISTS " + DATABASE_CREATE_CONVERSATION;
            db.execSQL(toExec);
            toExec = "DROP TABLE IF EXISTS " + DATABASE_CREATE_PECO;
            db.execSQL(toExec);
            toExec = "DROP TABLE IF EXISTS " + DATABASE_CREATE_TEL;
            db.execSQL(toExec);
            toExec = "DROP TABLE IF EXISTS " + DATABASE_CREATE_PETEL;
            db.execSQL(toExec);
            toExec = "DROP TABLE IF EXISTS " + DATABASE_CREATE_TXN;
            db.execSQL(toExec);
            onCreate(db);
        }
    }
    private static final String DATABASE_CREATE_PERSON =
            "CREATE TABLE " + PersonTable.NAME + " (" +
                    PersonTable.Cols.ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    PersonTable.Cols.FIRSTNAME + " VARCHAR(45) DEFAULT NULL, " +
                    PersonTable.Cols.SURNAME + " VARCHAR(45) DEFAULT NULL);";
    private static final String DATABASE_CREATE_MSG =
            "CREATE TABLE " + MsgTable.NAME + " (" +
                    MsgTable.Cols.ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    MsgTable.Cols.COPUBLICID + " INTEGER(11) DEFAULT 0, " +
                    MsgTable.Cols.TOID + " INTEGER(11) DEFAULT 0, " +
                    MsgTable.Cols.FROMID + " INTEGER(11) DEFAULT 0, " +
                    MsgTable.Cols.BODY + " VARCHAR DEFAULT NULL, " +
                    MsgTable.Cols.EVENTDATE + " VARCHAR DEFAULT NULL, " +
                    //Type of msg - text, img, video
                    MsgTable.Cols.TYPE + " INTEGER(3) DEFAULT 0, " +
                    //Cant remember what Data is? Is it blob? I.e. for images?
                    MsgTable.Cols.DATA + " VARCHAR DEFAULT NULL, " +
                    //This is the result code for the msg txn?
                    MsgTable.Cols.RESULT + " INTEGER(3) DEFAULT 0);";
    private static final String DATABASE_CREATE_CONVERSATION =
            "CREATE TABLE " + ConversationTable.NAME + " (" +
                    ConversationTable.Cols.ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    ConversationTable.Cols.TITLE + " VARCHAR DEFAULT NULL, " +
                    ConversationTable.Cols.PUBLICID + " INTEGER DEFAULT 0, " +
                    ConversationTable.Cols.CREATEDBY + " VARCHAR, " +
                    ConversationTable.Cols.DATECREATED + " VARCHAR DEFAULT NULL);";
    private static final String DATABASE_CREATE_PECO =
            "CREATE TABLE " + PeCoTable.NAME + " (" +
                    PeCoTable.Cols.ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    PeCoTable.Cols.PEID + " INTEGER(11) DEFAULT 0, " +
                    PeCoTable.Cols.COPUBLICID + " INTEGER DEFAULT 0);";
    private static final String DATABASE_CREATE_TEL =
            "CREATE TABLE " + TelTable.NAME + " (" +
                    TelTable.Cols.ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    TelTable.Cols.NUMBER + " VARCHAR DEFAULT NULL);";
    private static final String DATABASE_CREATE_PETEL =
            "CREATE TABLE " + PeTelTable.NAME + " (" +
                    PeTelTable.Cols.ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    PeTelTable.Cols.PEID + " INTEGER(11) DEFAULT 0, " +
                    PeTelTable.Cols.TELID+ " INTEGER DEFAULT 0);";
    private static final String DATABASE_CREATE_TXN =
            "CREATE TABLE " + TxnTable.NAME + " (" +
                    TxnTable.Cols.ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    TxnTable.Cols.TELFROM + " VARCHAR, " +
                    TxnTable.Cols.TELTO + " VARCHAR, " +
                    TxnTable.Cols.DATA + " VARCHAR DEFAULT NULL, " +
                    TxnTable.Cols.TYPE + " INTEGER(3) DEFAULT 0, " +
                    TxnTable.Cols.TIMESTAMP + " VARCHAR DEFAULT NULL, " +
                    TxnTable.Cols.SENT + " INTEGER(1) DEFAULT 0, " +
                    TxnTable.Cols.DELIVERED + " INTEGER(1) DEFAULT 0, " +
                    TxnTable.Cols.RESULT + " INTEGER(3) DEFAULT 0, " +
                    TxnTable.Cols.ERROR+ " VARCHAR DEFAULT NULL);";
}

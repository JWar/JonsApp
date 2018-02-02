package com.jraw.android.jonsapp.data.model;

/**
 * Created by JWar on 02/02/2018.
 *
 */

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;

@Entity(tableName = "petel", foreignKeys = @ForeignKey(
        entity = Person.class,
        childColumns = "PTPEId",
        parentColumns = "id",
        onDelete = ForeignKey.CASCADE), indices = {@Index(value = {"PTPEId", "PTTelId"},
        unique = true)})
public class PeTel extends entity {
    private int PTPEId;
    private int PTTelId;

    public int getPTPEId() {
        return PTPEId;
    }
    public int getPTTelId() {
        return PTTelId;
    }
    public void setPTPEId(int aPTPEId) {
        PTPEId=aPTPEId;
    }
    public void setPTTelId(int aPTTelId) {
        PTTelId=aPTTelId;
    }
}

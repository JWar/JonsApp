package com.jraw.android.jonsapp.data.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;

/**
 * Created by JWar on 02/02/2018.
 *
 */
@Entity(tableName = "peco"/*, foreignKeys = @ForeignKey(
        entity = Person.class,
        childColumns = "PCPEId",
        parentColumns = "id",
        onDelete = ForeignKey.CASCADE), indices = {@Index(value = {"PCPEId", "PCCOPublicId"},
        unique = true)}*/)
public class PeCo extends entity {
    private int PCPEId;
    private int PCCOPublicId;

    public int getPCPEId() {
        return PCPEId;
    }
    public int getPCCOPublicId() {
        return PCCOPublicId;
    }
    public void setPCPEId(int aPCPEId) {
        PCPEId=aPCPEId;
    }
    public void setPCCOPublicId(int aPCCOPublicId) {
        PCCOPublicId=aPCCOPublicId;
    }
}

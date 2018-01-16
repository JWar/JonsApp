package com.jraw.android.jonsapp.data.source;

import com.jraw.android.jonsapp.data.model.Msg;
import com.jraw.android.jonsapp.data.model.entity;
import java.util.List;
import io.reactivex.Observable;

/**
 * Created by JonGaming on 04/01/2018.
 * Contract for data sources.
 * This is to provide consistency across data sources (fake and real).
 * It is useful in providing structure.
 */

public interface MsgDataSource {
    Observable<List<entity>> getMsgs(int aCOId);
    Observable<List<entity>> getMsgsViaBody(int aCOId, String aBody);
    //Hmm will this need a return id? Put it in anyway just in case
    long saveMsg(Msg aMsg);
}

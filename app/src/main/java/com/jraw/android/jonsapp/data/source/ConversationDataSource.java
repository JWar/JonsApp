package com.jraw.android.jonsapp.data.source;

import com.jraw.android.jonsapp.data.model.entity;
import java.util.List;
import io.reactivex.Observable;

/**
 * Created by JonGaming on 04/01/2018.
 * Contract for data sources.
 * This is to provide consistency across data sources (fake and real).
 * It is useful in providing structure.
 */

public interface ConversationDataSource {
    Observable<List<entity>> getConversations();
    Observable<List<entity>> getConversationsViaTitle(String aTitle);
}

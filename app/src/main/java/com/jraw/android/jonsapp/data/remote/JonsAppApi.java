package com.jraw.android.jonsapp.data.remote;

import org.json.JSONObject;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Created by JWar on 21/12/2017.
 *
 */

public interface JonsAppApi {
    enum SEARCH_TYPES {
        ALL, SINGLE, OWNER, ACT_LOGTIME, PRNEWESTEVENT, ALLPRSNEWESTEVENT, SIX,
        TEL, STAFF, STAFF_LIST, CLIENT_LIST, PRLASTPES, KNOWNPES
    }

    String END_POINT = "http://51.7.163.110:10000/jrclient_1.0.2/";

    @GET("listperson.aj")
    Observable<ResponseJonsApp> getPersons(@Query("crit") String aCrit,
                                           @Query("st") int aSearchType,
                                           @Query("userid") String aUserId,
                                           @Query("ownerid") String aOwnerId);

    @POST
    Observable<JSONObject> sendTxns(@Url String aUrl,
                                    @Body String aTxns);
}

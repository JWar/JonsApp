package com.jraw.android.jonsapp.data.remote;

import com.google.gson.annotations.SerializedName;

/**
 * Created by JWar on 21/12/2017.
 *
 * Used in Retrofit to get reponse from server. Not sure how this will be used re: the system design -
 * basically is this needed? Cant firebase send all msgs using push? Is there ever any need to contact server
 * apart from on installation?
 */

public class ResponseJonsApp {
    @SerializedName("action")
    public String mAction;
    @SerializedName("rows")
    public String mRows;
    @SerializedName("error1")
    public String mError1;
}

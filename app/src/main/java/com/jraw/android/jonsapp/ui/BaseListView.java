package com.jraw.android.jonsapp.ui;

import android.database.Cursor;

import org.json.JSONArray;

/**
 * Created by JonGaming on 06/06/2017.
 * Base List that all lists have in common...
 */
public interface BaseListView<T> {
    void setPresenter(T presenter);
    void problemFindingData();
    void setViews(Cursor aCur);
    void setViews(JSONArray aArray);
    void showLoading();
    void showListView();
    void swapData(JSONArray aArray, Cursor aCur, int aScrollPos);
}

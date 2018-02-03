package com.jraw.android.jonsapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.jraw.android.jonsapp.utils.Utils;

public class MainActivity extends AppCompatActivity {

    public static final String LOG_TAG = "JonsApp";
    public static boolean LOG_DEBUG = true;

    private BroadcastReceiver mFirebaseReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            //Get all your data from intent and do what you want
            if (intent.hasExtra("data")) {
                Utils.logDebug("data: "+intent.getStringExtra("data"));
            }
            if (intent.hasExtra("body")) {
                Utils.logDebug("body: "+intent.getStringExtra("body"));
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(mFirebaseReceiver,
                new IntentFilter("noti"));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mFirebaseReceiver != null) {
            LocalBroadcastManager.getInstance(getApplicationContext()).unregisterReceiver(mFirebaseReceiver);
        }
    }
}

package com.jraw.android.jonsapp.ui.conversations;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.jraw.android.jonsapp.R;

public class ConversationsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversations);
        Toolbar toolbar = (Toolbar) findViewById(R.id.conversations_toolbar);
        setSupportActionBar(toolbar);

    }
}

package com.jraw.android.jonsapp.ui.msgs;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import com.jraw.android.jonsapp.R;

public class MsgsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msgs);
        Toolbar toolbar = (Toolbar) findViewById(R.id.msgs_toolbar);
        setSupportActionBar(toolbar);


    }

}

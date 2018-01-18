package com.jraw.android.jonsapp.ui.msgs;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.jraw.android.jonsapp.Injection;
import com.jraw.android.jonsapp.R;
import com.jraw.android.jonsapp.utils.Utils;

public class MsgsActivity extends AppCompatActivity {

    private static final String CO_ID = "coId";
    private int mCOId;

    private MsgsPresenter mMsgsPresenter;

    public static void start(Context aContext, int aCOId) {
        Intent intent = new Intent(aContext,MsgsActivity.class);
        intent.putExtra(CO_ID,aCOId);
        aContext.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msgs);
        if (savedInstanceState!=null) {
            mCOId = savedInstanceState.getInt(CO_ID);
        } else if (getIntent()!=null) {
            mCOId = getIntent().getIntExtra(CO_ID,-1);
        }
        try {
            MsgsFragment fragment = (MsgsFragment) getSupportFragmentManager().findFragmentByTag(MsgsFragment.TAG);
            if (fragment==null) {
                fragment = MsgsFragment.getInstance(mCOId);
                getSupportFragmentManager()
                        .beginTransaction()
                        .addToBackStack(MsgsFragment.TAG)
                        .add(R.id.msgs_list_fragment_container,fragment,MsgsFragment.TAG)
                        .commit();
            }
            mMsgsPresenter = new MsgsPresenter(Injection.provideMsgRepository(this),
                    Injection.provideBaseSchedulerProvider(),
                    fragment);
        } catch (Exception e) {
            Utils.logDebug("Error in MsgsActivity.onCreate: "+e.getLocalizedMessage());}
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(CO_ID,mCOId);
    }
}

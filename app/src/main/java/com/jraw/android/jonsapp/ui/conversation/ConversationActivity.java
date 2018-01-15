package com.jraw.android.jonsapp.ui.conversation;

import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.jraw.android.jonsapp.Injection;
import com.jraw.android.jonsapp.R;
import com.jraw.android.jonsapp.utils.Utils;

import static com.jraw.android.jonsapp.utils.Utils.USER_ID;

public class ConversationActivity extends AppCompatActivity {

    private ConversationPresenter mConversationPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //First things first is check for installation! I.e is this the first time JonsApp has been run?
        //Basic way is check Shared Preferences for a user id...
        SharedPreferences sharedPreferences = getPreferences(0);
        if (sharedPreferences.getInt(USER_ID,0)==0) {//If its default value then installation NOT happened.
            //Run installation routine!
        }

        setContentView(R.layout.activity_conversations);
        Toolbar toolbar = findViewById(R.id.conversations_toolbar);
        setSupportActionBar(toolbar);
        try {
            ConversationFragment fragment = (ConversationFragment) getSupportFragmentManager().findFragmentByTag(ConversationFragment.TAG);
            if (fragment==null) {
                fragment = new ConversationFragment();
                getSupportFragmentManager()
                        .beginTransaction()
                        .addToBackStack(ConversationFragment.TAG)
                        .add(R.id.conversations_list_fragment_container,fragment,ConversationFragment.TAG)
                        .commit();
            }
            //Hmm this is where the database gets init... the Presenter inits the Repo inits LocalDataSource which inits DB.
            mConversationPresenter = new ConversationPresenter(Injection.provideConversationRepository(this),
                    Injection.provideBaseSchedulerProvider(),
                    fragment);
        } catch (Exception e) {
            Utils.logDebug("Error in ConversationActivity.onCreate: "+e.getLocalizedMessage());}
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
}

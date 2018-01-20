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
import com.jraw.android.jonsapp.ui.install.InstallFragment;
import com.jraw.android.jonsapp.ui.install.InstallPresenter;
import com.jraw.android.jonsapp.utils.Utils;

import static com.jraw.android.jonsapp.utils.Utils.USER_ID;

public class ConversationActivity extends AppCompatActivity {
    //Needed because of future usage with fragment switching etc...
    private ConversationPresenter mConversationPresenter;
    private InstallPresenter mInstallPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.activity_conversations);
            Toolbar toolbar = findViewById(R.id.conversations_toolbar);
            setSupportActionBar(toolbar);

            //First things first is check for installation! I.e is this the first time JonsApp has been run?
            //Basic way is check Shared Preferences for a user id...
//            SharedPreferences sharedPreferences = getPreferences(0);
//            if (sharedPreferences.getInt(USER_ID,0)==0) {//If its default value then installation needed.
//                //Run installation routine!
//                InstallFragment installFragment = new InstallFragment();
//                getSupportFragmentManager()
//                        .beginTransaction()
//                        .addToBackStack(InstallFragment.TAG)
//                        .add(R.id.conversations_fragment_container,installFragment,InstallFragment.TAG)
//                        .commit();
//                mInstallPresenter = new InstallPresenter(installFragment);
//                return;
//            }

            //Need to extend this to handle onInstall...
            //Need to think about fragments interaction. Think its simple enough at the moment
            //that its either install or conversation fragment. But it needs to handle the switch from
            //install to conversations.
            Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.conversations_fragment_container);
            if (fragment==null) {//Assuming if null then its a Conversation.
                ConversationFragment conversationFragment = new ConversationFragment();
                getSupportFragmentManager()
                        .beginTransaction()
                        .addToBackStack(ConversationFragment.TAG)
                        .add(R.id.conversations_fragment_container,conversationFragment,ConversationFragment.TAG)
                        .commit();
                //Hmm this is where the database gets init... the Presenter inits the Repo inits LocalDataSource which inits DB.
                mConversationPresenter = new ConversationPresenter(Injection.provideConversationRepository(this),
                        Injection.provideSchedulerProvider(),
                        conversationFragment);
            } else if (fragment instanceof ConversationFragment) {
                mConversationPresenter = new ConversationPresenter(Injection.provideConversationRepository(this),
                        Injection.provideSchedulerProvider(),
                        (ConversationFragment) fragment);
            } else if (fragment instanceof InstallFragment) {
                mInstallPresenter = new InstallPresenter((InstallFragment)fragment);
            }
        } catch (Exception e) {
            Utils.logDebug("Error in ConversationActivity.onCreate: "+e.getLocalizedMessage());}
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (getSupportFragmentManager().getBackStackEntryCount()<2){
            finish();
        } else {
            super.onBackPressed();
        }
    }
}

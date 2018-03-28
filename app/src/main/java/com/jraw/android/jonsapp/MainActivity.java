package com.jraw.android.jonsapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.jraw.android.jonsapp.utils.Utils;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public static final String LOG_TAG = "JonsApp";
    public static boolean LOG_DEBUG = true;

    private LinearLayout mTelRowsLL;
    private int mTelPositionId;

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
        mTelPositionId=1;
        mTelRowsLL = findViewById(R.id.main_activity_row_ll);
    }

    public void onAddNewRow(View aView) {
        onAddTelRow("Dummy tel",-1);
    }
    private void onAddTelRow(@Nullable String aTelNum, int aTelType) {
        try {
            mTelPositionId += 1;
            int buttonsId = View.generateViewId();
            int spinnerId = View.generateViewId();
            int etId = View.generateViewId();
            RelativeLayout newRL = new RelativeLayout(this);
            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            lp.setMargins(Utils.pixelsToDp(this,2f),
                    Utils.pixelsToDp(this,2f),
                    Utils.pixelsToDp(this,2f),
                    Utils.pixelsToDp(this,12f));
            newRL.setLayoutParams(lp);

            //ImageButton: first because building from End
            ImageButton delIB = new ImageButton(this);
            delIB.setId(buttonsId);
            delIB.setTag(mTelPositionId);
//            delIB.setBackground(getResources().getDrawable(R.drawable.white_ripple,null));
            delIB.setImageResource(android.R.drawable.ic_delete);

            if (mTelRowsLL.getChildCount()==0) {//This is the first tel!
                //Issue new Id to delIB as going to need ET to align with linear layout.
                delIB.setId(View.generateViewId());
                delIB.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View aView) {
//                        onDelTel1();
                    }
                });

                ImageButton addIB = new ImageButton(this);
                addIB.setId(View.generateViewId());
                addIB.setTag(mTelPositionId);
//                addIB.setBackground(getResources().getDrawable(R.drawable.white_ripple,null));
                addIB.setImageResource(android.R.drawable.ic_input_add);
                addIB.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View aView) {
                        onAddTelRow(null,-1);
                    }
                });
                //Add rules/params for layout
                lp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                lp.setMargins(0,0,0,0);
                addIB.setLayoutParams(lp);
                delIB.setLayoutParams(lp);
                LinearLayout buttonsLL = new LinearLayout(this);
                buttonsLL.setId(buttonsId);
                RelativeLayout.LayoutParams llp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                llp.setMargins(Utils.pixelsToDp(this,4f),
                        0,0,0);
                llp.addRule(RelativeLayout.ALIGN_PARENT_END);
                buttonsLL.setLayoutParams(llp);
                buttonsLL.setOrientation(LinearLayout.VERTICAL);
                buttonsLL.addView(addIB);
                buttonsLL.addView(delIB);
                newRL.addView(buttonsLL,llp);
            } else {
                delIB.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View aView) {
//                        onRemoveTelRow((int) aView.getTag());
                    }
                });
                //Add rules/params for layout
                lp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                lp.setMargins(Utils.pixelsToDp(this,4f),
                        0,0,0);
                lp.addRule(RelativeLayout.ALIGN_PARENT_END);
                lp.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
                newRL.addView(delIB, lp);
            }

            //Spinner
            Spinner spinner = new Spinner(this);
            spinner.setId(spinnerId);
            spinner.setTag(mTelPositionId);
            spinner.setPadding(0,0,0,0);
            spinner.setPaddingRelative(0,0,0,0);

            setSpinner(spinner, aTelType);
//            //Add rules/params for layout
            lp = new RelativeLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
//                    (int)getResources().getDimension(R.dimen.spinner_width),//This forces spinner width
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            lp.addRule(RelativeLayout.ALIGN_PARENT_START);
            lp.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
            lp.setMargins(Utils.pixelsToDp(this,12f),
                    Utils.pixelsToDp(this,8f),
                    0,0);
            spinner.setLayoutParams(lp);

            newRL.addView(spinner);

            //ET
            EditText telET = new EditText(this);
            telET.setId(etId);
            telET.setTag(mTelPositionId);
//            telET.setBackground(getResources().getDrawable(R.drawable.rounded_corner,null));
            telET.setInputType(InputType.TYPE_CLASS_PHONE);
            telET.setImeOptions(EditorInfo.IME_ACTION_DONE);
            telET.setMaxLines(1);

            telET.setMinHeight(Utils.pixelsToDp(this,54f));
            telET.setPadding(Utils.pixelsToDp(this,2f),
                    Utils.pixelsToDp(this,2f),
                    0,
                    Utils.pixelsToDp(this,2f));
            telET.setHorizontallyScrolling(false);
            if (aTelNum != null) {
                //Add tel to TelET.
                telET.setText(aTelNum);
            }
            //This sets the ET to between the IB and Spinner
            lp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);

//            lp.setMarginStart(-108);//Seems the ET isnt shifted enough to the left. match parent failing?
            lp.addRule(RelativeLayout.START_OF, buttonsId);
            lp.addRule(RelativeLayout.END_OF, spinnerId);
            lp.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
            lp.setMargins(Utils.pixelsToDp(this,4f),
                    Utils.pixelsToDp(this,16f),
                    0,
                    Utils.pixelsToDp(this,20f));
            newRL.addView(telET, lp);
            mTelRowsLL.addView(newRL);

            //If got to here then we can happily add them all to list...
//            mTelRows.add(Pair.create(mTelPositionId, newRL));//Add to RL list
//            mSpinners.add(Pair.create(mTelPositionId, spinner));//Add to spinners list
//            mTelETs.add(Pair.create(mTelPositionId, telET));//Add to TelET list
        } catch (Exception e) {
            //Mostly for debugging purposes..
            Utils.logDebug("ClientDetailFragment.onAddTelRow: " + e.getLocalizedMessage());
        }
    }

    private void setSpinner(Spinner aSpinner, int aTelType) {
        String[] telTypes;
        switch (aTelType) {
            case 0:
                telTypes = new String[]{
                        getString(R.string.client_detail_tel_type_mobile),
                        getString(R.string.client_detail_tel_type_work),
                        getString(R.string.client_detail_tel_type_home)
                };
                break;
            case 1:
                telTypes = new String[]{
                        getString(R.string.client_detail_tel_type_work),
                        getString(R.string.client_detail_tel_type_mobile),
                        getString(R.string.client_detail_tel_type_home)
                };
                break;
            case 2:
                telTypes = new String[]{
                        getString(R.string.client_detail_tel_type_home),
                        getString(R.string.client_detail_tel_type_mobile),
                        getString(R.string.client_detail_tel_type_work)
                };
                break;
            default:
                telTypes = new String[]{
                        getString(R.string.client_detail_tel_type_mobile),
                        getString(R.string.client_detail_tel_type_work),
                        getString(R.string.client_detail_tel_type_home)
                };
        }
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(this,
                R.layout.simple_spinner_item,
                telTypes);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the mSpinner
        aSpinner.setAdapter(adapter);
        aSpinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> aAdapterView, View aView, int aI, long aL) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> aAdapterView) {

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

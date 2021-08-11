package mindthehead.iclean.work;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.MifareClassic;
import android.nfc.tech.MifareUltralight;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.UnsupportedEncodingException;

import mindthehead.iclean.R;
import mindthehead.iclean.util.NFCManager;
import mindthehead.iclean.util.dialog.NFCDialogListener;
import mindthehead.iclean.work.settings.SettingsFragment;
import mindthehead.iclean.work.shedules.SchedulesFragment;
import mindthehead.iclean.work.shedules.SchedulesListener;
import mindthehead.iclean.work.sync.SyncFragment;
import mindthehead.iclean.work.sync.SyncListener;
import mindthehead.iclean.work.task.TaskFragment;
import mindthehead.iclean.work.task.TaskListener;
import mindthehead.iclean.work.times.TimesFragment;
import mindthehead.iclean.work.times.TimesListener;
import mindthehead.iclean.work.home.HomeFragment;
import mindthehead.iclean.work.home.HomeListener;

public class WorkActivity extends AppCompatActivity implements HomeListener, TimesListener, TaskListener, SchedulesListener, SyncListener, ImageView.OnClickListener {


    static final int TYPE_HOME = 0;
    static final int TYPE_TIMES = 1;
    static final int TYPE_TASK = 2;
    static final int TYPE_SCHEDULES = 3;
    static final int TYPE_SYNC = 4;
    static final int TYPE_SETTINGS = 5;

    private WorkActivityListener listener;
    private FragmentManager mainFragmentManager;

    private HomeFragment homeFragment;
    private TimesFragment timesFragment;
    private TaskFragment taskFragment;
    private SchedulesFragment schedulesFragment;
    private SyncFragment syncFragment;
    private SettingsFragment settingsFragment;

    private ImageView iv_app_logo, iv_back, iv_label_logo, iv_user;

    private LinearLayout ll_label;
    private TextView tv_label, tv_description;

    private int currentStatus;

    //Intialize attributes
    NfcAdapter nfcAdapter;
    PendingIntent pendingIntent;
    final static String TAG = "nfc_test";

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work);

        ll_label = findViewById(R.id.ll_work_label);

        iv_app_logo = findViewById(R.id.iv_work_logo);
        iv_back = findViewById(R.id.iv_work_back);

        iv_label_logo = findViewById(R.id.iv_work_current_logo);
        iv_user = findViewById(R.id.iv_work_bar_user);
        iv_user.setOnClickListener(this);
        tv_label = findViewById(R.id.tv_work_current_label);
        tv_description = findViewById(R.id.tv_work_current_description);

        showHomeFragment();

        //Initialise NfcAdapter
        nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        //If no NfcAdapter, display that the device has no NFC
        if (nfcAdapter == null){
            finish();
        }
        //PendingIntent.getActivity(Context,requestcode(identifier for intent),intent,int)
        pendingIntent = PendingIntent.getActivity(this,0,new Intent(this,this.getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP),0);


    }//onCreate


    protected void onResume() {

        super.onResume();
        assert nfcAdapter != null;
        //nfcAdapter.enableForegroundDispatch(context,pendingIntent, intentFilterArray, techListsArray)
        nfcAdapter.enableForegroundDispatch(this,pendingIntent,null,null);

    }//onResume


    protected void onPause() {

        super.onPause();
        if (nfcAdapter != null) nfcAdapter.disableForegroundDispatch(this);

    }//onPause


    protected void onNewIntent(Intent intent) {

        super.onNewIntent(intent);
        setIntent(intent);
        if (listener != null) listener.onNFCTagFound(NFCManager.obtainTagFromIntent(intent));

    }//onNewIntent


    public void setListener(WorkActivityListener _listener){

        listener = _listener;


    }//setListener

    private void insideFragment(int type) {

        if (type == TYPE_HOME) {

            showHomeBar();

        } else  if(type == TYPE_TIMES){

            showFragmentBar();
            tv_label.setText("Timbratura");
            tv_description.setText("Timbra l'entrata o l'uscita");
            iv_label_logo.setImageResource(R.drawable.icon_times);

        } else  if(type == TYPE_TASK){

            showFragmentBar();
            tv_label.setText("Task");
            tv_description.setText("Timbra l'entrata o l'uscita nella \n stazione di lavoro");
            iv_label_logo.setImageResource(R.drawable.icon_task);

        } else  if(type == TYPE_SCHEDULES){

            showFragmentBar();
            tv_label.setText("Turni di lavoro");
            tv_description.setText("Visualizza i turni di lavoro, \n i piani e la mappa delle stazioni");
            iv_label_logo.setImageResource(R.drawable.icon_schedules);

        } else  if(type == TYPE_SYNC){

            showFragmentBar();
            tv_label.setText("Sincronizza");
            tv_description.setText("Sincronicca i dati inseriti");
            iv_label_logo.setImageResource(R.drawable.icon_sync);

        } else  if(type == TYPE_SETTINGS){

            showFragmentBar();
            tv_label.setText("IMPOSTAZIONI");
            tv_description.setText("");
            iv_label_logo.setImageResource(R.drawable.ic_user);

        }

    }//insideFragment


    public void onBackPressed() {

        if(currentStatus != TYPE_HOME) showHomeFragment();

    }//onBackPressed

    private void showHomeBar(){

        iv_app_logo.setVisibility(View.VISIBLE);

        iv_back.setOnClickListener(null);
        iv_back.setVisibility(View.GONE);

        ll_label.setVisibility(View.GONE);

    }//showHomeBar

    private void showFragmentBar(){

        iv_app_logo.setVisibility(View.GONE);


        iv_back.setVisibility(View.VISIBLE);
        iv_back.setOnClickListener(this);

        ll_label.setVisibility(View.VISIBLE);

    }//showFragmentBar


    public void showHomeFragment() {

        currentStatus = TYPE_HOME;
        insideFragment(TYPE_HOME);

        homeFragment = new HomeFragment();
        homeFragment.setListener(this);
        mainFragmentManager = getSupportFragmentManager();

        FragmentTransaction fragmentTransaction = mainFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fl_work, homeFragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.commit();

        homeFragment.setListener(this);

    }//showHomeFragment


    public void showTimesFragment() {

        currentStatus = TYPE_TIMES;
        insideFragment(TYPE_TIMES);

        timesFragment = new TimesFragment();
        timesFragment.setListener(this);
        mainFragmentManager = getSupportFragmentManager();

        FragmentTransaction fragmentTransaction = mainFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fl_work, timesFragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.commit();

        timesFragment.setListener(this);

    }//showTimesFragment


    public void showTaskFragment() {

        currentStatus = TYPE_TASK;
        insideFragment(TYPE_TASK);

        taskFragment = new TaskFragment();
        taskFragment.setListener(this);
        mainFragmentManager = getSupportFragmentManager();

        FragmentTransaction fragmentTransaction = mainFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fl_work, taskFragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.commit();

        taskFragment.setListener(this);

    }//showTaskFragment


    public void showSchedulesFragment() {

        currentStatus = TYPE_SCHEDULES;
        insideFragment(TYPE_SCHEDULES);

        schedulesFragment = new SchedulesFragment();
        schedulesFragment.setListener(this);
        mainFragmentManager = getSupportFragmentManager();

        FragmentTransaction fragmentTransaction = mainFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fl_work, schedulesFragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.commit();

        schedulesFragment.setListener(this);

    }//showSchedulesFragment


    public void showSyncFragment() {

        currentStatus = TYPE_SYNC;
        insideFragment(TYPE_SYNC);

        syncFragment = new SyncFragment();
        syncFragment.setListener(this);
        mainFragmentManager = getSupportFragmentManager();

        FragmentTransaction fragmentTransaction = mainFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fl_work, syncFragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.commit();

        syncFragment.setListener(this);

    }//showSyncFragment

    public void showSettingsFragment() {

        currentStatus = TYPE_SETTINGS;
        insideFragment(TYPE_SETTINGS);
        settingsFragment = new SettingsFragment();
        mainFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = mainFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fl_work, settingsFragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.commit();

    }//showSettingsFragment



    public void onTimesChosen() {

        showTimesFragment();

    }//onTimesChosen


    public void onTaskChosen() {

        showTaskFragment();

    }//onTaskChosen


    public void onSchedulesChosen() {

        showSchedulesFragment();

    }//onSchedulesChosen


    public void onSyncChosen() {

        showSyncFragment();

    }//onSyncChosen


    public void onClick(View v) {

        if (v == iv_back && mainFragmentManager != null){

            showHomeFragment();

        }else if (v == iv_user && mainFragmentManager != null){

            //showSettingsFragment();

        }

    }

}//WorkActivity
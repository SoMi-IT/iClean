package mindthehead.iclean.work;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
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
import mindthehead.iclean.work.sync.SyncFragment;
import mindthehead.iclean.work.task.TaskFragment;
import mindthehead.iclean.work.times.TimesFragment;
import mindthehead.iclean.work.home.HomeFragment;
import mindthehead.iclean.work.home.HomeListener;

public class WorkActivity extends AppCompatActivity implements HomeListener, ImageView.OnClickListener {


    static final int TYPE_HOME = 0;
    static final int TYPE_TIMES = 1;
    static final int TYPE_TASK = 2;
    static final int TYPE_SCHEDULES = 3;
    static final int TYPE_SYNC = 4;
    static final int TYPE_SETTINGS = 5;

    private WorkActivityListener listener;
    private FragmentManager mainFragmentManager;

    private ImageView iv_app_logo, iv_back, iv_label_logo, iv_user;

    private LinearLayout ll_label;
    private TextView tv_label, tv_description;

    private int currentStatus;

    private NfcAdapter nfcAdapter;
    private PendingIntent pendingIntent;


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

        showFragment(TYPE_HOME);

        nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        if (nfcAdapter == null){
            finish();
        }

        pendingIntent = PendingIntent.getActivity(this,0,new Intent(this,this.getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP),0);


    }//onCreate


    protected void onResume() {

        super.onResume();
        assert nfcAdapter != null;
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

    private void updateTopBar(String label, String description, int resource) {

        tv_label.setText(label);
        tv_description.setText(description);
        iv_label_logo.setImageResource(resource);

    }//updateTopBar


    private void insideFragment(int type) {

        if (type == TYPE_HOME) {

            toggleFragmentBar(false);
            return;

        } else if(type == TYPE_TIMES){
            updateTopBar("Timbratura", "Timbra l'entrata o l'uscita", R.drawable.icon_times);

        } else if(type == TYPE_TASK){
            updateTopBar("Task", "Timbra l'entrata o l'uscita nella \n stazione di lavoro", R.drawable.icon_task);

        } else if(type == TYPE_SCHEDULES){
            updateTopBar("Turni di lavoro", "Visualizza i turni di lavoro, \n i piani e la mappa delle stazioni", R.drawable.icon_schedules);

        } else if(type == TYPE_SYNC){
            updateTopBar("Fine turno", "Carica i dati inseriti", R.drawable.icon_sync);

        } else  if(type == TYPE_SETTINGS){
            updateTopBar("IMPOSTAZIONI", "", R.drawable.ic_user);
        }

        toggleFragmentBar(true);

    }//insideFragment


    public void onBackPressed() {
        if(currentStatus != TYPE_HOME) showFragment(TYPE_HOME);
    }//onBackPressed


    private void toggleFragmentBar(boolean insideFragment){

        if (insideFragment) {
            iv_app_logo.setVisibility(View.GONE);
            iv_back.setVisibility(View.VISIBLE);
            iv_back.setOnClickListener(this);
            ll_label.setVisibility(View.VISIBLE);

        } else {
            iv_app_logo.setVisibility(View.VISIBLE);
            iv_back.setOnClickListener(null);
            iv_back.setVisibility(View.GONE);
            ll_label.setVisibility(View.GONE);
        }

    }//showFragmentBar


    public void showFragment(int newType) {

        currentStatus = newType;
        insideFragment(newType);
        Fragment currentFragment = null;
        mainFragmentManager = getSupportFragmentManager();

        if(currentStatus == TYPE_HOME) {

            HomeFragment homeFragment = new HomeFragment();
            homeFragment.setListener(this);
            currentFragment = homeFragment;

        }else if(currentStatus == TYPE_TIMES) {
            currentFragment = new TimesFragment();

        }else if(currentStatus == TYPE_TASK) {
            currentFragment = new TaskFragment();

        }else if(currentStatus == TYPE_SCHEDULES) {
            currentFragment = new SchedulesFragment();

        }else if(currentStatus == TYPE_SYNC) {
            currentFragment = new SyncFragment();
        }

        FragmentTransaction fragmentTransaction = mainFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fl_work, currentFragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.commit();

    }//showHomeFragment


    public void onTimesChosen() {
        showFragment(TYPE_TIMES);
    }//onTimesChosen


    public void onTaskChosen() {
        showFragment(TYPE_TASK);
    }//onTaskChosen


    public void onSchedulesChosen() {
        showFragment(TYPE_SCHEDULES);
    }//onSchedulesChosen


    public void onSyncChosen() {
        showFragment(TYPE_SYNC);
    }//onSyncChosen


    public void onClick(View v) {

        if (v == iv_back && mainFragmentManager != null){
            showFragment(TYPE_HOME);
        }else if (v == iv_user && mainFragmentManager != null){
            //showFragment();
        }

    }

}//WorkActivity
package mindthehead.iclean.work.times;


import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.core.graphics.drawable.DrawableCompat;
import androidx.fragment.app.Fragment;

import mindthehead.iclean.R;
import mindthehead.iclean.util.DateManager;
import mindthehead.iclean.util.SharedPreferencesManager;
import mindthehead.iclean.util.dialog.ManualDialogListener;
import mindthehead.iclean.util.dialog.ManualTimeDialog;
import mindthehead.iclean.util.dialog.NFCDialog;
import mindthehead.iclean.util.dialog.NFCDialogListener;
import mindthehead.iclean.work.WorkActivity;
import mindthehead.iclean.work.settings.UserDataManager;


public class TimesFragment extends Fragment implements View.OnClickListener, NFCDialogListener, ManualDialogListener {


    private WorkActivity activity;

    private TimesListener listener;

    private NFCDialog nfcDialog;

    private TextView tv_date, tv_time, tv_start, tv_end;

    private Button b_start, b_end;

    private View v_start, v_end;

    private Thread thread;

    private int currentChoice;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        activity = (WorkActivity) getActivity();

        View rootView = inflater.inflate(R.layout.fragment_times, container, false);

        tv_date = rootView.findViewById(R.id.tv_times_date);
        tv_time = rootView.findViewById(R.id.tv_times_time);
        tv_start = rootView.findViewById(R.id.tv_times_start);
        tv_end = rootView.findViewById(R.id.tv_times_end);

        tv_date.setText(DateManager.getCurrentDate());
        tv_time.setText(DateManager.getCurrentTime());

        b_start = rootView.findViewById(R.id.b_times_item_start);
        b_end = rootView.findViewById(R.id.b_times_item_end);
        b_start.setOnClickListener(this);
        b_end.setOnClickListener(this);

        v_start = rootView.findViewById(R.id.v_times_start);
        v_end = rootView.findViewById(R.id.v_times_end);

        startClock();

        updateStatus();

        return rootView;

    }//onCreateView


    public void onDestroy() {

        if (thread != null)thread.interrupt();
        super.onDestroy();

    }//onDestroy


    private void updateStatus() {

        UserDataManager userDataManager = new UserDataManager(activity);

        if(userDataManager.getDateIn().length() == 0  && userDataManager.getDateOut().length() == 0) {

            v_start.setBackgroundResource(R.drawable.times_round_view_disabled);
            v_end.setBackgroundResource(R.drawable.times_round_view_disabled);

            tv_start.setText("Non hai ancora timbrato l'ingresso");
            tv_end.setText("Non hai ancora timbrato l'uscita");

            toggleButtonState(b_start, true);
            toggleButtonState(b_end, false);

        } else if(userDataManager.getDateIn().length() != 0  && userDataManager.getDateOut().length() == 0) {

            v_start.setBackgroundResource(R.drawable.times_round_view_start);
            v_end.setBackgroundResource(R.drawable.times_round_view_disabled);

            tv_start.setText("Hai timbrato l'ingresso il: " + userDataManager.getDateIn() + "\n alle ore: " + userDataManager.getTimeIn());
            tv_end.setText("Non hai ancora timbrato l'uscita");

            toggleButtonState(b_start, false);
            toggleButtonState(b_end, true);

        }  else if(userDataManager.getDateIn().length() != 0  && userDataManager.getDateOut().length() != 0) {

            v_start.setBackgroundResource(R.drawable.times_round_view_start);
            v_end.setBackgroundResource(R.drawable.times_round_view_end);

            tv_start.setText("Hai timbrato l'ingresso il: " + userDataManager.getDateIn() + "\n alle ore: " + userDataManager.getTimeIn());
            tv_end.setText("Hai timbrato l'uscita il: " + userDataManager.getDateOut() + "\n alle ore: " + userDataManager.getTimeOut());

            toggleButtonState(b_start, false);
            toggleButtonState(b_end, false);

        }

    }


    private void toggleButtonState(Button b, boolean isEnable) {

        Drawable buttonDrawable = b.getBackground();
        buttonDrawable = DrawableCompat.wrap(buttonDrawable);

        if (isEnable) {

            DrawableCompat.setTint(buttonDrawable, activity.getColor(R.color.white_100));
            b.setBackground(buttonDrawable);
            b.setClickable(true);

        } else {

            DrawableCompat.setTint(buttonDrawable, activity.getColor(R.color.light_transparent));
            b.setBackground(buttonDrawable);
            b.setClickable(false);

        }

    }//toggleButtonState


    private void startClock() {

        thread = new Thread() {

            public void run() {
                try {
                    while (!isInterrupted()) {

                        Thread.sleep(1000);

                        activity.runOnUiThread(new Runnable() {

                            public void run() {
                                tv_date.setText(DateManager.getCurrentDate());
                                tv_time.setText(DateManager.getCurrentTime());
                            }

                        });

                    }

                } catch (InterruptedException e) {
                }

            }

        };

        thread.start();

    }//startClock


    public void setListener(TimesListener _listener){

        listener = _listener;

    }//setListener


    public void onClick(View view) {

        if(view == b_start) {

            currentChoice = ManualTimeDialog.MANUAL_TYPE_IN;
            nfcDialog = new NFCDialog(activity);
            nfcDialog.setListener(this);
            nfcDialog.show();

        } else if (view == b_end) {

            currentChoice = ManualTimeDialog.MANUAL_TYPE_OUT;
            nfcDialog = new NFCDialog(activity);
            nfcDialog.setListener(this);
            nfcDialog.show();

        }

    }//onClick


    public void onNFCFind() {

        if(nfcDialog != null)nfcDialog.dismiss();
        ManualTimeDialog manualTimeDialog = new ManualTimeDialog(activity, currentChoice);
        manualTimeDialog.setListener(this);
        manualTimeDialog.show();

    }//onNFCFind

    public void onManual() {

        if(nfcDialog != null)nfcDialog.dismiss();
        ManualTimeDialog manualTimeDialog = new ManualTimeDialog(activity, currentChoice);
        manualTimeDialog.setListener(this);
        manualTimeDialog.show();

    }//onManual


    public void onManualPick(String date, String time) {

        if(currentChoice == ManualTimeDialog.MANUAL_TYPE_IN) {

            UserDataManager.saveWorkShiftData(activity, date, null, time, null);

        } else if(currentChoice == ManualTimeDialog.MANUAL_TYPE_OUT) {

            UserDataManager.saveWorkShiftData(activity, null, date, null, time);

        }

        updateStatus();

    }//onTimeChosen

}//TimesFragment


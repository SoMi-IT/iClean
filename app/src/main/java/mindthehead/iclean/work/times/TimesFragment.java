package mindthehead.iclean.work.times;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.fragment.app.Fragment;
import mindthehead.iclean.R;
import mindthehead.iclean.data.DataManager;
import mindthehead.iclean.util.DateManager;
import mindthehead.iclean.util.dialog.ManualDialogListener;
import mindthehead.iclean.util.dialog.ManualTimeDialog;
import mindthehead.iclean.util.dialog.NFCDialog;
import mindthehead.iclean.util.dialog.NFCDialogListener;
import mindthehead.iclean.work.WorkActivity;


public class TimesFragment extends Fragment implements View.OnClickListener, NFCDialogListener, ManualDialogListener {


    private WorkActivity activity;

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
        tv_date.setText(DateManager.getCurrentDate());
        tv_time.setText(DateManager.getCurrentTime());
        tv_start = rootView.findViewById(R.id.tv_times_start);
        tv_end = rootView.findViewById(R.id.tv_times_end);

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

        if(DataManager.getCheckIn(activity).length() == 0  && DataManager.getCheckOut(activity).length() == 0) {

            v_start.setBackgroundResource(R.drawable.times_round_view_disabled);
            v_end.setBackgroundResource(R.drawable.times_round_view_disabled);

            tv_start.setText("Non hai ancora timbrato l'ingresso");
            tv_end.setText("Non hai ancora timbrato l'uscita");

            toggleButtonState(b_start, true);
            toggleButtonState(b_end, false);

        } else if(DataManager.getCheckIn(activity).length() != 0  && DataManager.getCheckOut(activity).length() == 0) {

            v_start.setBackgroundResource(R.drawable.times_round_view_start);
            v_end.setBackgroundResource(R.drawable.times_round_view_disabled);

            tv_start.setText("Hai timbrato l'ingresso il: " + "\n" + DataManager.getCheckIn(activity));
            tv_end.setText("Non hai ancora timbrato l'uscita");

            toggleButtonState(b_start, false);
            toggleButtonState(b_end, true);

        }  else if(DataManager.getCheckIn(activity).length() != 0  && DataManager.getCheckOut(activity).length() != 0) {

            v_start.setBackgroundResource(R.drawable.times_round_view_start);
            v_end.setBackgroundResource(R.drawable.times_round_view_end);

            tv_start.setText("Hai timbrato l'ingresso il: " + "\n" + DataManager.getCheckIn(activity));
            tv_end.setText("Hai timbrato l'uscita il: " + "\n" + DataManager.getCheckOut(activity));

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


    public void onClick(View view) {

        if(view == b_start) {

            currentChoice = ManualTimeDialog.MANUAL_TYPE_IN;

        } else if (view == b_end) {

            currentChoice = ManualTimeDialog.MANUAL_TYPE_OUT;

        }

        NFCDialog nfcDialog = new NFCDialog(activity);
        nfcDialog.setListener(this);
        nfcDialog.show();

    }//onClick


    public void onNFCFind(String id) {

        if(currentChoice == ManualTimeDialog.MANUAL_TYPE_IN) {

            DataManager.saveWorkShiftData(activity, DateManager.getCurrentDate(), null);

        } else if(currentChoice == ManualTimeDialog.MANUAL_TYPE_OUT) {

            DataManager.saveWorkShiftData(activity, null, DateManager.getCurrentMoment());

        }

        updateStatus();

    }//onNFCFind

    public void onManual() {

        ManualTimeDialog manualTimeDialog = new ManualTimeDialog(activity, currentChoice);
        manualTimeDialog.setListener(this);
        manualTimeDialog.show();

    }//onManual


    public void onManualPick(String time) {

        if(currentChoice == ManualTimeDialog.MANUAL_TYPE_IN) {

            DataManager.saveWorkShiftData(activity, time, null);

        } else if(currentChoice == ManualTimeDialog.MANUAL_TYPE_OUT) {

            DataManager.saveWorkShiftData(activity, null, time);

        }

        updateStatus();

    }//onTimeChosen

}//TimesFragment


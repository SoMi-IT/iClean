package mindthehead.iclean.work.times;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import mindthehead.iclean.R;
import mindthehead.iclean.util.DateManager;
import mindthehead.iclean.work.WorkActivity;


public class TimesFragment extends Fragment implements View.OnClickListener {


    private WorkActivity activity;

    private TimesListener listener;

    private TextView tv_date, tv_time, tv_start, tv_end;

    private Button b_start, b_end;

    private View v_start, v_end;

    private Thread thread;


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

        return rootView;

    }//onCreateView


    public void onDestroy() {

        if (thread != null)thread.interrupt();
        super.onDestroy();

    }//onDestroy

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


    }//onClick


}//TimesFragment


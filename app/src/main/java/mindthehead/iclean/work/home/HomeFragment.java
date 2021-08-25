package mindthehead.iclean.work.home;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import mindthehead.iclean.R;
import mindthehead.iclean.work.WorkActivity;


public class HomeFragment extends Fragment implements View.OnClickListener {


    private HomeListener listener;

    private Button b_times, b_task, b_schedules, b_sync;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        b_times = rootView.findViewById(R.id.b_home_times);
        b_task = rootView.findViewById(R.id.b_home_task);
        b_schedules = rootView.findViewById(R.id.b_home_schedules);
        b_sync = rootView.findViewById(R.id.b_home_sync);

        b_times.setOnClickListener(this);
        b_task.setOnClickListener(this);
        b_schedules.setOnClickListener(this);
        b_sync.setOnClickListener(this);

        return rootView;

    }//onCreateView


    public void setListener(HomeListener _listener){

        listener = _listener;

    }//setListener


    public void onClick(View view) {

        if (view == b_times) {

            listener.onTimesChosen();

        } else if (view == b_task) {

            listener.onTaskChosen();

        } else if (view == b_schedules) {

            listener.onSchedulesChosen();

        } else if (view == b_sync) {

            listener.onSyncChosen();

        }

    }//onClick


}//HomeFragment


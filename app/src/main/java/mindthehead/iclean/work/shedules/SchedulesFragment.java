package mindthehead.iclean.work.shedules;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import mindthehead.iclean.R;
import mindthehead.iclean.work.WorkActivity;


public class SchedulesFragment extends Fragment implements  SchedulesListListener {


    private WorkActivity activity;

    private SchedulesListener listener;

    private RecyclerView rv_schedules;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        activity = (WorkActivity) getActivity();

        View rootView = inflater.inflate(R.layout.fragment_schedules, container, false);

        ScheduleDataManager scheduleDataManager= new ScheduleDataManager();

        rv_schedules = rootView.findViewById(R.id.rv_schedules);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(activity);
        rv_schedules.setLayoutManager(layoutManager);

        SchedulesListAdapter schedulesListAdapter = new SchedulesListAdapter(activity, scheduleDataManager.getFakeData());
        schedulesListAdapter.setListener(this);
        rv_schedules.setAdapter(schedulesListAdapter);

        return rootView;

    }//onCreateView


    public void setListener(SchedulesListener _listener){

        listener = _listener;

    }//setListener

    public void onItemInfoClicked(Schedule schedule) {

        ScheduleItemInfoDialog scheduleItemInfoDialog = new ScheduleItemInfoDialog(activity, schedule.getSite(), schedule.getFloor(), schedule.getDepartment());
        scheduleItemInfoDialog.show();

    }//onItemInfoClicked

}//SchedulesFragment


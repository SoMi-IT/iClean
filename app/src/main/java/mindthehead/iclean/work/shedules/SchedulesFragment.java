package mindthehead.iclean.work.shedules;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import mindthehead.iclean.R;
import mindthehead.iclean.util.dialog.InfoDialog;
import mindthehead.iclean.work.WorkActivity;
import mindthehead.iclean.work.shedules.adapter.SchedulesListAdapter;
import mindthehead.iclean.work.shedules.adapter.SchedulesListAdapterListener;
import mindthehead.iclean.work.shedules.data.Schedule;
import mindthehead.iclean.work.shedules.data.ScheduleDataManager;


public class SchedulesFragment extends Fragment implements SchedulesListAdapterListener {

    WorkActivity activity;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        activity = (WorkActivity) getActivity();

        View rootView = inflater.inflate(R.layout.fragment_schedules, container, false);

        ScheduleDataManager scheduleDataManager= new ScheduleDataManager();

        RecyclerView rv_schedules = rootView.findViewById(R.id.rv_schedules);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(activity);
        rv_schedules.setLayoutManager(layoutManager);

        scheduleDataManager = new ScheduleDataManager();
        SchedulesListAdapter schedulesListAdapter = new SchedulesListAdapter(activity, scheduleDataManager.getStoredSchedules(activity));
        schedulesListAdapter.setListener(this);
        rv_schedules.setAdapter(schedulesListAdapter);;

        return rootView;

    }//onCreateView


    public void onItemInfoClicked(Schedule schedule) {

        InfoDialog infoDialog = new InfoDialog(activity, "", schedule.getSite(), "", "");
        infoDialog.show();

    }//onItemInfoClicked

}//SchedulesFragment


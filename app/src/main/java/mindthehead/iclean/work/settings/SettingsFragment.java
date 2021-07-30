package mindthehead.iclean.work.settings;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.fragment.app.Fragment;
import mindthehead.iclean.R;
import mindthehead.iclean.work.WorkActivity;
import mindthehead.iclean.work.shedules.data.ScheduleDataManager;
import mindthehead.iclean.work.task.data.TaskDataManager;


public class SettingsFragment extends Fragment implements View.OnClickListener {


    private WorkActivity activity;

    private Button b_settings;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        activity = (WorkActivity) getActivity();

        View rootView = inflater.inflate(R.layout.fragment_settings, container, false);

        b_settings = rootView.findViewById(R.id.b_settings);
        b_settings.setOnClickListener(this);

        return rootView;

    }//onCreateView


    public void onClick(View view) {

        if (view == b_settings) {

            new Thread(() -> {

                TaskDataManager.saveTasks(activity, TaskDataManager.getFakeData());
                ScheduleDataManager.saveSchedules(activity, ScheduleDataManager.getFakeData());

            }).start();

        }

    }//onClick


}//SettingsFragment


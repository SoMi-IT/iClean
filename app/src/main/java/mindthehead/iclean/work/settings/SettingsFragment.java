package mindthehead.iclean.work.settings;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import mindthehead.iclean.R;
import mindthehead.iclean.work.WorkActivity;
import mindthehead.iclean.work.sync.SyncListener;
import mindthehead.iclean.work.task.TaskDataManager;


public class SettingsFragment extends Fragment implements View.OnClickListener {


    private WorkActivity activity;

    private SyncListener listener;

    private Button b_settings;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        activity = (WorkActivity) getActivity();

        View rootView = inflater.inflate(R.layout.fragment_settings, container, false);

        b_settings = rootView.findViewById(R.id.b_settings);
        b_settings.setOnClickListener(this);

        return rootView;

    }//onCreateView


    public void setListener(SyncListener _listener){

        listener = _listener;

    }//setListener


    public void onClick(View view) {

        if (view == b_settings) {

            new Thread(() -> {

                TaskDataManager.saveTasks(activity, TaskDataManager.getFakeData());

            }).start();

        }

    }//onClick


}//SettingsFragment


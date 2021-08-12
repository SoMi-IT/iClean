package mindthehead.iclean.work.sync;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import mindthehead.iclean.R;
import mindthehead.iclean.util.SharedPreferencesManager;
import mindthehead.iclean.work.WorkActivity;


public class SyncFragment extends Fragment implements View.OnClickListener {


    private WorkActivity activity;

    private SyncListener listener;

    private TextView tv_state;

    private Button b_sync;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        activity = (WorkActivity) getActivity();

        View rootView = inflater.inflate(R.layout.fragment_sync, container, false);

        tv_state = rootView.findViewById(R.id.tv_sync_state);
        b_sync = rootView.findViewById(R.id.b_sync);
        b_sync.setOnClickListener(this);

        updateStatus();

        return rootView;

    }//onCreateView


    public void setListener(SyncListener _listener){

        listener = _listener;

    }//setListener


    private void updateStatus() {

        if(SharedPreferencesManager.readInt(activity, R.string.synced) == 1) tv_state.setText("IClean è sincronizzato");
        else tv_state.setText("IClean non è sincronizzato");



    }//updateStatus

    public void onClick(View view) {

        if (view == b_sync) {

            SharedPreferencesManager.writeString(activity, R.string.times_check_in, "");
            SharedPreferencesManager.writeString(activity, R.string.times_check_out, "");
            SharedPreferencesManager.writeString(activity, R.string.schedules, "");
            SharedPreferencesManager.writeString(activity, R.string.tasks, "");
            SharedPreferencesManager.writeInt(activity, R.string.synced, 1);

            updateStatus();

        }

    }//onClick


}//SyncFragment


package mindthehead.iclean.work.sync;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import mindthehead.iclean.R;
import mindthehead.iclean.auth.AuthActivity;
import mindthehead.iclean.data.DataManager;
import mindthehead.iclean.util.SharedPreferencesManager;
import mindthehead.iclean.util.dialog.WarningDialog;
import mindthehead.iclean.work.WorkActivity;
import mindthehead.iclean.work.sync.data.JsonSyncDataManager;
import mindthehead.iclean.work.sync.data.SyncManager;


public class SyncFragment extends Fragment implements View.OnClickListener, SyncManagerListener {


    private WorkActivity activity;

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


    private void updateStatus() {

        if(DataManager.areDataSynced(activity)) tv_state.setText(R.string.sync_fragment_ok);
        else tv_state.setText(R.string.sync_fragment_no);

    }//updateStatus

    public void onClick(View view) {

        if (view == b_sync) {

            if(DataManager.isUserWorkShiftEnd(activity)) {

                String finalSync = JsonSyncDataManager.getDataForSync(activity);

                SyncManager syncManager = new SyncManager();
                syncManager.setListener(this);
                syncManager.startSync(finalSync);

            } else Toast.makeText(activity, "Non hai chiuso il turno di lavoro!", Toast.LENGTH_LONG).show();


        }

    }//onClick


    public void onSyncSuccessful(String message) {

        activity.runOnUiThread(() -> {

            Toast.makeText(activity, R.string.sync_fragment_done, Toast.LENGTH_LONG).show();

        });


        DataManager.saveData(activity, "", "", "", "", "", "", 1);
        startActivity(new Intent(activity, AuthActivity.class));

    }//onSyncSuccessful

    public void onSyncError(String error) {

        activity.runOnUiThread(() -> {

            WarningDialog warningDialog = new WarningDialog(activity, "Error: " + error);
            warningDialog.show();

        });

    }//onSyncError

}//SyncFragment


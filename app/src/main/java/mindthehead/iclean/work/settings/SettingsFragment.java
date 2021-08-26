package mindthehead.iclean.work.settings;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.fragment.app.Fragment;
import mindthehead.iclean.R;
import mindthehead.iclean.work.WorkActivity;


public class SettingsFragment extends Fragment implements View.OnClickListener {


    private Button b_settings;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_settings, container, false);

        b_settings = rootView.findViewById(R.id.b_settings);
        b_settings.setOnClickListener(this);

        return rootView;

    }//onCreateView


    public void onClick(View view) {

        if (view == b_settings) {

            new Thread(() -> {


            }).start();

        }

    }//onClick


}//SettingsFragment


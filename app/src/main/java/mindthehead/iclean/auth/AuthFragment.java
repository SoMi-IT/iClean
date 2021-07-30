package mindthehead.iclean.auth;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import androidx.fragment.app.Fragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import mindthehead.iclean.R;


public class AuthFragment extends Fragment implements View.OnClickListener {


    private AuthActivity activity;

    private AuthListener listener;

    private EditText et_username;
    private EditText et_password;

    private FloatingActionButton b_confirm;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        activity = (AuthActivity) getActivity();

        View rootView = inflater.inflate(R.layout.fragment_auth, container, false);


        et_username = rootView.findViewById(R.id.et_auth_username);
        et_password = rootView.findViewById(R.id.et_auth_password);

        b_confirm = rootView.findViewById(R.id.b_auth_confirm_label);
        b_confirm.setOnClickListener(this);


        return rootView;

    }//onCreateView


    private void saveData(){

        String emailString = et_username.getText().toString();
        String pswString = et_password.getText().toString();

        if(emailString.equals("")){

            et_username.setError("write your e-mail!");

        } else if(pswString.equals("")){

            et_password.setError("write your password!");

        } else {

            et_username.setError(null);
            et_password.setError(null);

            disableAuthButton();
            listener.onAuthStarted(emailString, pswString);

        }

    }//saveData


    private void disableAuthButton() {

        if (b_confirm != null)b_confirm.setClickable(false);

    }//disableAuthButton

    public void enableAuthButton() {

        if (b_confirm != null)b_confirm.setClickable(true);

    }//enableAuthButton

    public void setListener(AuthListener _listener){

        listener = _listener;

    }//setListener


    public void onClick(View view) {

        if (view == b_confirm) {
            saveData();
        }

    }//onClick


}//AuthFragment


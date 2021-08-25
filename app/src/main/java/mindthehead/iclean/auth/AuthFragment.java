package mindthehead.iclean.auth;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import androidx.fragment.app.Fragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import mindthehead.iclean.R;
import mindthehead.iclean.data.DataManager;
import mindthehead.iclean.util.dialog.OptionDialog;
import mindthehead.iclean.util.dialog.OptionDialogListener;


public class AuthFragment extends Fragment implements View.OnClickListener, OptionDialogListener {


    private AuthFragmentListener listener;

    private EditText et_username;
    private EditText et_password;

    private String username, psw;

    private FloatingActionButton b_confirm;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_auth, container, false);

        et_username = rootView.findViewById(R.id.et_auth_username);
        et_password = rootView.findViewById(R.id.et_auth_password);

        b_confirm = rootView.findViewById(R.id.b_auth_confirm_label);
        b_confirm.setOnClickListener(this);

        return rootView;

    }//onCreateView


    private void initAuth(){

        String emailString = et_username.getText().toString();
        String pswString = et_password.getText().toString();

        if(emailString.equals("")){

            et_username.setError("write your e-mail!");

        } else if(pswString.equals("")){

            et_password.setError("write your password!");

        } else {

            et_username.setError(null);
            et_password.setError(null);

            username = emailString;
            psw = pswString;

            if (!DataManager.areDataSynced(getActivity())) {

                OptionDialog optionDialog = new OptionDialog(getActivity(), "Esistono già dei dati salvati ma non ancora sincronizzati. \n Vuoi eliminarli e scaricarne di nuovi o continuare con i dati esistenti?", "Sovrascrivi", "Mantieni");
                optionDialog.setListener(this);
                optionDialog.show();

            }else {
                toggleAuthButton(false);
                listener.onAuthStartRequested(true, username, psw);
            }


        }

    }//saveData


    public void toggleAuthButton(boolean active) {

        if (b_confirm != null && active)b_confirm.setClickable(true);
        else if (b_confirm != null)b_confirm.setClickable(false);

    }//toggleAuthButton


    public void setListener(AuthFragmentListener _listener){
        listener = _listener;
    }//setListener


    public void onClick(View view) {
        if (view == b_confirm) initAuth();
    }//onClick


    public void onYesChoice() {
        toggleAuthButton(false);
        listener.onAuthStartRequested(true, username, psw);
    }//onYesChoice


    public void onNotChoice() {
        toggleAuthButton(false);
        listener.onAuthStartRequested(false, username, psw);
    }//onNotChoice


}//AuthFragment


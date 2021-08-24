package mindthehead.iclean.auth;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ProgressBar;

import mindthehead.iclean.R;
import mindthehead.iclean.util.SharedPreferencesManager;
import mindthehead.iclean.util.dialog.WarningDialog;
import mindthehead.iclean.work.WorkActivity;
import mindthehead.iclean.work.task.data.JsonTaskDataManager;
import mindthehead.iclean.work.task.data.TaskDataManager;


public class AuthActivity extends AppCompatActivity implements AuthFragmentListener, AuthenticationManagerListener {


    private AuthFragment authFragment;


    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        toggleLoader(false);

        showAuthFragment();

    }//onCreate


    public void toggleLoader(boolean visible){

        View v_authLoader = findViewById(R.id.v_auth);
        ProgressBar pb_authLoader = findViewById(R.id.pb_auth);

        if(visible) {
            v_authLoader.setVisibility(View.VISIBLE);
            pb_authLoader.setVisibility(View.VISIBLE);
        }else {
            v_authLoader.setVisibility(View.GONE);
            pb_authLoader.setVisibility(View.GONE);
        }

    }//toggleLoader


    public void showAuthFragment() {

        authFragment = new AuthFragment();
        authFragment.setListener(this);

        FragmentManager mainFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = mainFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fl_auth, authFragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.commit();

        authFragment.setListener(this);

    }//showAuthFragment


    public void onAuthStarted(String email, String psw) {

        toggleLoader(true);

        InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
        if(getWindow().getCurrentFocus() != null)inputMethodManager.hideSoftInputFromWindow(getWindow().getCurrentFocus().getApplicationWindowToken(),0);

        AuthenticationManager authenticationManager = new AuthenticationManager();
        authenticationManager.setListener(this);
        authenticationManager.startAuth(this, email, psw);

    }//onAuthStarted


    public void onLoginSuccessful(String username, String schedules, String tasks) {

        if(username != null && schedules != null && tasks != null) {

            SharedPreferencesManager.writeString(this, R.string.username, username);
            SharedPreferencesManager.writeString(this, R.string.schedules, schedules);
            SharedPreferencesManager.writeString(this, R.string.tasks, tasks);
            SharedPreferencesManager.writeInt(this, R.string.synced, 0);

        }

        startActivity(new Intent(this, WorkActivity.class));

    }//onLoginSuccessful


    public void onLoginError(String error) {

        Activity activity = this;

        runOnUiThread(() -> {

            if (authFragment != null) authFragment.toggleAuthButton(true);

            toggleLoader(false);

            WarningDialog warningDialog = new WarningDialog(activity, "Error: " + error);
            warningDialog.show();

        });

    }//onLoginError

}//WelcomeActivity
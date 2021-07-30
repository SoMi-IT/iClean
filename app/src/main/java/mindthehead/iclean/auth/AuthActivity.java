package mindthehead.iclean.auth;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import mindthehead.iclean.R;
import mindthehead.iclean.work.WorkActivity;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class AuthActivity extends AppCompatActivity implements AuthListener, AuthenticationManagerListener {


    private FragmentManager mainFragmentManager;

    private AuthFragment authFragment;

    private View v_authLoader;
    private ProgressBar pb_authLoader;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        v_authLoader = findViewById(R.id.v_auth);
        pb_authLoader = findViewById(R.id.pb_auth);

        dismissLoader();

        showAuthFragment();

        //startActivity(new Intent(this, WorkActivity.class));

    }//onCreate


    public void dismissLoader(){

        v_authLoader.setVisibility(View.GONE);
        pb_authLoader.setVisibility(View.GONE);

    }//dismissLoader


    public void showLoader(){

        v_authLoader.setVisibility(View.VISIBLE);
        pb_authLoader.setVisibility(View.VISIBLE);

    }//showLoader


    public void showAuthFragment() {

        authFragment = new AuthFragment();
        authFragment.setListener(this);
        mainFragmentManager = getSupportFragmentManager();

        FragmentTransaction fragmentTransaction = mainFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fl_auth, authFragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.commit();

        authFragment.setListener(this);

    }//showAuthFragment


    public void onAuthStarted(String email, String psw) {

        showLoader();
        InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(getWindow().getCurrentFocus().getApplicationWindowToken(),0);
        AuthenticationManager authenticationManager = new AuthenticationManager();
        authenticationManager.setListener(this);
        authenticationManager.startAuth("", "");

    }//onAuthStarted


    public void onLoginSuccessful() {

        startActivity(new Intent(this, WorkActivity.class));

    }//onLoginSuccessful

    public void onLoginError(String error) {

        Log.d("XXX", error +"");

        if (authFragment != null) authFragment.enableAuthButton();

        dismissLoader();

    }//onLoginError

}//WelcomeActivity
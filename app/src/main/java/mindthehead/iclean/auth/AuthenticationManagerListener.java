package mindthehead.iclean.auth;

import org.json.JSONArray;

public interface AuthenticationManagerListener {

    void onLoginSuccessful(String username, String schedules, String tasks);
    void onLoginError(String error);

}//AuthenticationManagerListener

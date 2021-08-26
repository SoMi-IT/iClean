package mindthehead.iclean.auth;


public interface AuthenticationManagerListener {

    void onLoginSuccessful(String userid, String username, String schedules, String tasks);
    void onLoginError(String error);

}//AuthenticationManagerListener

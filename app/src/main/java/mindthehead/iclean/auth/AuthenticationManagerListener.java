package mindthehead.iclean.auth;

public interface AuthenticationManagerListener {

    void onLoginSuccessful();
    void onLoginError(String error);

}//AuthenticationManagerListener

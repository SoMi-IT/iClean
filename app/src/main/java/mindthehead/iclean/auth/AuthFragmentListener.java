package mindthehead.iclean.auth;

public interface AuthFragmentListener {

    void onAuthStartRequested(boolean downloadNeeded, String email, String psw);

}//AuthListener

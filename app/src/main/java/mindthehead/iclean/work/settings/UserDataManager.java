package mindthehead.iclean.work.settings;

import android.app.Activity;

import mindthehead.iclean.R;
import mindthehead.iclean.util.SharedPreferencesManager;
import mindthehead.iclean.util.dialog.WarningDialog;

public class UserDataManager {

    private Activity activity;
    private String dateIn;
    private String dateOut;
    private String timeIn;
    private String timeOut;


    private boolean isUserLogged()
    {

        return true;

    }//isUserLogged

    public  boolean isUserWorkShiftStarted() {

        return dateIn.length() != 0;

    }//isUserWorkShiftStarted


    public boolean isUserWorkShiftEnd() {

        return dateOut.length() != 0;

    }//isUserWorkShiftEnd


    public UserDataManager (Activity _activity) {

        activity = _activity;
        dateIn = SharedPreferencesManager.readString(activity, R.string.times_date_in);
        dateOut = SharedPreferencesManager.readString(activity, R.string.times_date_out);
        timeIn = SharedPreferencesManager.readString(activity, R.string.times_time_in);
        timeOut = SharedPreferencesManager.readString(activity, R.string.times_time_out);

    }

    public static void saveData(Activity activity, String _dateIn, String _dateOut, String _timeIn, String _timeOut) {

        if (_dateIn != null) {

            SharedPreferencesManager.writeString(activity, R.string.times_date_in, _dateIn);
            SharedPreferencesManager.writeString(activity, R.string.times_time_in, _timeIn);

        } else if (_dateOut != null) {

            SharedPreferencesManager.writeString(activity, R.string.times_date_out, _dateOut);
            SharedPreferencesManager.writeString(activity, R.string.times_time_out, _timeOut);

        }

    }//saveData



    public String getDateIn() {

        return dateIn;

    }//getDateIn

    public String getDateOut() {

        return dateOut;

    }//getDateOut

    public  String getTimeIn() {

        return timeIn;

    }//getTimeIn

    public  String getTimeOut() {

        return timeOut;

    }//getTimeOut



}//UserDataManager

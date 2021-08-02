package mindthehead.iclean.work.settings;

import android.app.Activity;

import java.util.ArrayList;

import mindthehead.iclean.R;
import mindthehead.iclean.util.SharedPreferencesManager;
import mindthehead.iclean.util.dialog.WarningDialog;
import mindthehead.iclean.work.shedules.data.Schedule;
import mindthehead.iclean.work.task.data.Task;

public class UserDataManager {

    private Activity activity;

    private String userId;

    private String UserName;
    private String UserLastName;


    public UserDataManager (Activity _activity) {

        activity = _activity;

    }//Constructor

    public  boolean isUserWorkShiftStarted() {

        return SharedPreferencesManager.readString(activity, R.string.times_date_in).length() != 0;

    }//isUserWorkShiftStarted


    public boolean isUserWorkShiftEnd() {

        return SharedPreferencesManager.readString(activity, R.string.times_date_out).length() != 0;

    }//isUserWorkShiftEnd


    public static void saveWorkShiftData(Activity activity, String _dateIn, String _dateOut, String _timeIn, String _timeOut) {

        if (_dateIn != null) {

            SharedPreferencesManager.writeString(activity, R.string.times_date_in, _dateIn);
            SharedPreferencesManager.writeString(activity, R.string.times_time_in, _timeIn);

        } else if (_dateOut != null) {

            SharedPreferencesManager.writeString(activity, R.string.times_date_out, _dateOut);
            SharedPreferencesManager.writeString(activity, R.string.times_time_out, _timeOut);

        }

    }//saveData



    public String getDateIn() {

        return SharedPreferencesManager.readString(activity, R.string.times_date_in);

    }//getDateIn

    public String getDateOut() {

        return SharedPreferencesManager.readString(activity, R.string.times_date_out);

    }//getDateOut

    public  String getTimeIn() {

        return SharedPreferencesManager.readString(activity, R.string.times_time_in);

    }//getTimeIn

    public  String getTimeOut() {

        return SharedPreferencesManager.readString(activity, R.string.times_time_out);

    }//getTimeOut



}//UserDataManager

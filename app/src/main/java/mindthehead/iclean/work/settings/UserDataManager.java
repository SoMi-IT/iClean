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

        return SharedPreferencesManager.readString(activity, R.string.times_check_in).length() != 0;

    }//isUserWorkShiftStarted


    public boolean isUserWorkShiftEnd() {

        return SharedPreferencesManager.readString(activity, R.string.times_check_out).length() != 0;

    }//isUserWorkShiftEnd


    public static void saveWorkShiftData(Activity activity, String checkIn, String checkOut) {

        if (checkIn != null) {

            SharedPreferencesManager.writeString(activity, R.string.times_check_in, checkIn);

        } else if (checkOut != null) {

            SharedPreferencesManager.writeString(activity, R.string.times_check_out, checkOut);

        }

    }//saveData


    public  String getCheckIn() {

        return SharedPreferencesManager.readString(activity, R.string.times_check_in);

    }//getTimeIn

    public  String getCheckOut() {

        return SharedPreferencesManager.readString(activity, R.string.times_check_out);

    }//getTimeOut



}//UserDataManager

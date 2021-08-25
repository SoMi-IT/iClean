package mindthehead.iclean.data;

import android.app.Activity;

import mindthehead.iclean.R;
import mindthehead.iclean.util.SharedPreferencesManager;

public class DataManager {

    private Activity activity;

    private String userId;

    private String UserName;
    private String UserLastName;


    public DataManager (Activity _activity) {

        activity = _activity;

    }//Constructor


    public static void saveData(Activity activity, String username, String checkIn, String checkOut, String schedules, String tasks, int synced) {

        SharedPreferencesManager.writeString(activity, R.string.username, username);
        SharedPreferencesManager.writeString(activity, R.string.times_check_in, checkIn);
        SharedPreferencesManager.writeString(activity, R.string.times_check_out, checkOut);
        SharedPreferencesManager.writeString(activity, R.string.schedules, schedules);
        SharedPreferencesManager.writeString(activity, R.string.tasks, tasks);
        SharedPreferencesManager.writeInt(activity, R.string.synced, synced);

    }//saveData

    public static boolean areDataSynced(Activity activity) {

        return SharedPreferencesManager.readInt(activity, R.string.synced) == 1;

    }//areDataSynced


    public static boolean hasTasks(Activity activity) {

        return SharedPreferencesManager.readString(activity, R.string.tasks).length() != 0;

    }//hasTasks


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

}

package mindthehead.iclean.data;

import android.app.Activity;

import mindthehead.iclean.R;
import mindthehead.iclean.util.SharedPreferencesManager;

public class DataManager {


    public static void saveData(Activity activity, String userid, String username, String checkIn, String checkOut, String schedules, String tasks, int synced) {

        SharedPreferencesManager.writeString(activity, R.string.userid, userid);
        SharedPreferencesManager.writeString(activity, R.string.username, username);
        SharedPreferencesManager.writeString(activity, R.string.times_check_in, checkIn);
        SharedPreferencesManager.writeString(activity, R.string.times_check_out, checkOut);
        SharedPreferencesManager.writeString(activity, R.string.schedules, schedules);
        SharedPreferencesManager.writeString(activity, R.string.tasks, tasks);
        SharedPreferencesManager.writeInt(activity, R.string.synced, synced);

    }//saveData

    public static boolean areDataSynced(Activity activity) {

        return (SharedPreferencesManager.readInt(activity, R.string.synced) == 1 || SharedPreferencesManager.readString(activity, R.string.tasks).equals(""));

    }//areDataSynced


    public  static boolean isUserWorkShiftStarted(Activity activity) {

        return SharedPreferencesManager.readString(activity, R.string.times_check_in).length() != 0;

    }//isUserWorkShiftStarted


    public static boolean isUserWorkShiftEnd(Activity activity) {

        return SharedPreferencesManager.readString(activity, R.string.times_check_out).length() != 0;

    }//isUserWorkShiftEnd


    public static void saveWorkShiftData(Activity activity, String checkIn, String checkOut) {

        if (checkIn != null) {

            SharedPreferencesManager.writeString(activity, R.string.times_check_in, checkIn);

        } else if (checkOut != null) {

            SharedPreferencesManager.writeString(activity, R.string.times_check_out, checkOut);

        }

    }//saveData


    public static String getCheckIn(Activity activity) {

        return SharedPreferencesManager.readString(activity, R.string.times_check_in);

    }//getTimeIn

    public  static String getCheckOut(Activity activity) {

        return SharedPreferencesManager.readString(activity, R.string.times_check_out);

    }//getTimeOut

    public static String getUserId(Activity _activity) {

        return SharedPreferencesManager.readString(_activity, R.string.userid);

    }//getUserId

    public static String getUserName(Activity _activity) {

        return SharedPreferencesManager.readString(_activity, R.string.username);

    }//getUserName

}

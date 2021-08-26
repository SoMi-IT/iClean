package mindthehead.iclean.work.sync.data;


import android.app.Activity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import mindthehead.iclean.R;
import mindthehead.iclean.data.DataManager;
import mindthehead.iclean.util.SharedPreferencesManager;


public class JsonSyncDataManager {


    private static final String SYNC_USERID = "userid";
    private static final String SYNC_CHECK_IN = "checkin";
    private static final String SYNC_CHECK_OUT = "checkout";
    private static final String SYNC_TASKS = "tasks";

    public static String getDataForSync(Activity activity) {

        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put(SYNC_USERID, Integer.parseInt(DataManager.getUserId(activity)));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            jsonObject.put(SYNC_CHECK_IN, DataManager.getCheckIn(activity));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            jsonObject.put(SYNC_CHECK_OUT, DataManager.getCheckOut(activity));
        } catch (JSONException e) {
            e.printStackTrace();
        }


        JSONArray tasksDone;

        try {

            tasksDone = new JSONArray(SharedPreferencesManager.readString(activity, R.string.tasks));
            jsonObject.put(SYNC_TASKS, tasksDone);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonObject.toString();

    }//getDataForSync


}//JsonDataManager



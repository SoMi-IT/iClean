package mindthehead.iclean.work.shedules.data;


import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;


public class JsonScheduleDataManager {



    public static ArrayList<Schedule> getSchedulesFromString(String string) {

        ArrayList<Schedule> schedules = new ArrayList<Schedule>();

        try {


            JSONArray jsonArray = new JSONArray(string);

            for(int i = 0; i<jsonArray.length(); i++) {

                Schedule schedule = new Schedule();
                JSONObject currentJsonObject = new JSONObject();
                currentJsonObject = jsonArray.getJSONObject(i);
                Log.d("XXX", "--Schedule: " + jsonArray.getJSONObject(i).toString());
                schedule = getScheduleFromJson(currentJsonObject);

                if(schedule != null) schedules.add(schedule);

            }

        } catch (JSONException e) {
            e.printStackTrace();
            return schedules;
        }

        return schedules;

    }//getTasksFromString


    public static Schedule getScheduleFromJson(JSONObject jsonObject){

        Schedule schedule = null;
        ArrayList<Schedule> schedules = new ArrayList<Schedule>();

        try {

            String date = jsonObject.getString(Schedule.SCHEDULES_DATE);
            String timeStart = jsonObject.getString(Schedule.SCHEDULES_TIME_START);
            String timeEnd = jsonObject.getString(Schedule.SCHEDULES_TIME_END);

            schedule = new Schedule(date, timeStart, timeEnd);

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

        return schedule;

    }//getScheduleFromJson


}//JsonDataManager



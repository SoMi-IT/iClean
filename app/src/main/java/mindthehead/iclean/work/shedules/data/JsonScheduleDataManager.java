package mindthehead.iclean.work.shedules.data;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import mindthehead.iclean.work.task.data.Task;


public class JsonScheduleDataManager {


    public static ArrayList<Schedule> getSchedulesFromString(String string) {

        ArrayList<Schedule> schedules = new ArrayList<Schedule>();

        try {

            JSONObject jsonObject = new JSONObject(string);

            JSONArray jsonArray = new JSONArray();
            jsonArray = jsonObject.getJSONArray("schedules");

            for(int i = 0; i<jsonArray.length(); i++) {

                Schedule schedule = new Schedule();
                JSONObject currentJsonObject = new JSONObject();
                currentJsonObject = jsonArray.getJSONObject(i);
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

            String id = jsonObject.getString("id");
            String date = jsonObject.getString("date");
            String timeStart = jsonObject.getString("timeStart");
            String timeEnd = jsonObject.getString("timeEnd");
            String site = jsonObject.getString("site");
            String floor = jsonObject.getString("floor");
            String department = jsonObject.getString("department");

            schedule = new Schedule(id, date, timeStart, timeEnd, site, floor, department);

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

        return schedule;

    }//getScheduleFromJson


    public static String getStringFromSchedules(ArrayList<Schedule> schedules){

        JSONObject jsonObject = new JSONObject();

        try {

            JSONArray jsonArray = new JSONArray();

            for(int i = 0; i<schedules.size(); i++) {

                if(getJsonObjectFromSchedule(schedules.get(i)) != null) jsonArray.put(getJsonObjectFromSchedule(schedules.get(i)));

            }

            jsonObject.put("schedules", jsonArray);

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }


        return jsonObject.toString();

    }//getStringFromSchedules


    public static JSONObject getJsonObjectFromSchedule(Schedule schedule){

        JSONObject jsonObject = new JSONObject();

        try {

            jsonObject.put("id", schedule.getId());
            jsonObject.put("date", schedule.getDate());
            jsonObject.put("timeStart", schedule.getTimeStart());
            jsonObject.put("timeEnd", schedule.getTimeEnd());
            jsonObject.put("site", schedule.getSite());
            jsonObject.put("floor", schedule.getFloor());
            jsonObject.put("department", schedule.getDepartment());

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

        return jsonObject;

    }//getJsonObjectFromSchedule


}//JsonDataManager



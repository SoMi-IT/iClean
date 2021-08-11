package mindthehead.iclean.work.task.data;


import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class JsonTaskDataManager {


    public static String getConvertedJsonTasks(String string) {

        JSONArray newJsonArray = new JSONArray();

        try {

            JSONArray jsonArray = new JSONArray(string);

            for(int i = 0; i<jsonArray.length(); i++) {

                JSONObject currentJsonObject = new JSONObject();
                currentJsonObject = jsonArray.getJSONObject(i);
                Log.d("XXX", "--Old Task: " + jsonArray.getJSONObject(i).toString());
                JSONObject newJsonObject = new JSONObject();
                newJsonObject = getConvertedJsonTask(currentJsonObject, i);
                if (newJsonObject != null)
                {
                    newJsonArray.put(newJsonObject);
                    Log.d("XXX", "--New Task: " + newJsonObject.toString());
                }


            }

        } catch (JSONException e) {
            e.printStackTrace();
            return "";
        }

        return newJsonArray.toString();

    }//getTasksFromString


    public static JSONObject getConvertedJsonTask(JSONObject jsonObject, int index){

        JSONObject newJsonObject = new JSONObject();

        try {

            int status;
            if (index == 0) status = Task.STATUS_CURRENT_NOT_STARTED;
            else status = Task.STATUS_TODO;

            newJsonObject.put("status", status);
            newJsonObject.put(Task.TASK_ID, jsonObject.getString(Task.TASK_ID));
            newJsonObject.put(Task.TASK_DATE, jsonObject.getString(Task.TASK_DATE));
            newJsonObject.put("dateStartDone", "");
            newJsonObject.put("dateEndDone", "");
            newJsonObject.put(Task.TASK_TIME_START, jsonObject.getString(Task.TASK_TIME_START));
            newJsonObject.put("timeStartDone", "");
            newJsonObject.put(Task.TASK_TIME_END, jsonObject.getString(Task.TASK_TIME_END));
            newJsonObject.put("timeEndDone", "");
            newJsonObject.put(Task.TASK_PLACE, jsonObject.getString(Task.TASK_PLACE));
            newJsonObject.put(Task.TASK_PLACE_NAME, jsonObject.getString(Task.TASK_PLACE_NAME));
            newJsonObject.put("floor", "");
            newJsonObject.put("department", "");
            newJsonObject.put("info", "");


        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

        return newJsonObject;

    }//getConvertedJsonTask


    public static ArrayList<Task> getTasksFromString(String string) {

        ArrayList<Task> tasks = new ArrayList<Task>();

        try {

            JSONArray jsonArray = new JSONArray(string);

            for(int i = 0; i<jsonArray.length(); i++) {

                Task task = new Task();
                JSONObject currentJsonObject = new JSONObject();
                currentJsonObject = jsonArray.getJSONObject(i);
                Log.d("XXX", "--Stored Task: " + jsonArray.getJSONObject(i).toString());
                task = getTaskFromJson(currentJsonObject);

                if(task != null) tasks.add(task);

            }

        } catch (JSONException e) {
            e.printStackTrace();
            return tasks;
        }

        return tasks;

    }//getTasksFromString


    public static Task getTaskFromJson(JSONObject jsonObject){

        Task task = null;

        try {

            int status = jsonObject.getInt("status");
            String id = jsonObject.getString(Task.TASK_ID);
            String date = jsonObject.getString(Task.TASK_DATE);
            String dateStartDone = jsonObject.getString("dateStartDone");
            String dateEndDone = jsonObject.getString("dateEndDone");
            String timeStart = jsonObject.getString(Task.TASK_TIME_START);
            String timeStartDone = jsonObject.getString("timeStartDone");
            String timeEnd = jsonObject.getString(Task.TASK_TIME_END);
            String timeEndDone = jsonObject.getString("timeEndDone");
            String NFC = jsonObject.getString(Task.TASK_PLACE);
            String site = jsonObject.getString(Task.TASK_PLACE_NAME);
            String floor = jsonObject.getString("floor");
            String department = jsonObject.getString("department");
            String info = jsonObject.getString("info");

            task = new Task(status, id,  date, dateStartDone, dateEndDone, timeStart, timeStartDone, timeEnd, timeEndDone, NFC,  site, floor, department, info);
            task.setStatus(status);

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

        return task;

    }//getSTaskFromJson


    public static String getStringFromTasks(ArrayList<Task> tasks){

        JSONArray jsonArray = new JSONArray();

        for(int i = 0; i<tasks.size(); i++) {

            if(getJsonObjectFromTask(tasks.get(i)) != null) jsonArray.put(getJsonObjectFromTask(tasks.get(i)));

        }

        return jsonArray.toString();

    }//getStringFromTasks


    public static JSONObject getJsonObjectFromTask(Task task){

        JSONObject jsonObject = new JSONObject();

        try {

            jsonObject.put("status", task.getStatus());
            jsonObject.put(Task.TASK_ID, task.getId());
            jsonObject.put(Task.TASK_DATE, task.getDate());
            jsonObject.put("dateStartDone", task.getDateStartDone());
            jsonObject.put("dateEndDone", task.getDateEndDone());
            jsonObject.put(Task.TASK_TIME_START, task.getTimeStart());
            jsonObject.put("timeStartDone", task.getTimeStartDone());
            jsonObject.put(Task.TASK_TIME_END, task.getTimeEnd());
            jsonObject.put("timeEndDone", task.getTimeEndDone());
            jsonObject.put(Task.TASK_PLACE, task.getNFC());
            jsonObject.put(Task.TASK_PLACE_NAME, task.getSite());
            jsonObject.put("floor", task.getFloor());
            jsonObject.put("department", task.getDepartment());
            jsonObject.put("info", task.getInfo());

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

        return jsonObject;

    }//getJsonObjectFromTask


}//JsonDataManager



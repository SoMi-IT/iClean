package mindthehead.iclean.work.task.data;


import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;


public class JsonTaskDataManager {


    public static ArrayList<Task> getTasksFromString(String string) {

        ArrayList<Task> tasks = new ArrayList<>();

        try {

            JSONArray jsonArray = new JSONArray(string);

            for(int i = 0; i<jsonArray.length(); i++) {

                Task task;
                JSONObject currentJsonObject;
                currentJsonObject = jsonArray.getJSONObject(i);
                Log.d("XXX", "--Stored Task: " + jsonArray.getJSONObject(i).toString());
                task = getTaskFromJson(currentJsonObject);

                if(task != null) tasks.add(task);

            }

            Log.d("XXX", " -------------- ");
        } catch (JSONException e) {
            e.printStackTrace();
            return tasks;
        }

        return tasks;

    }//getTasksFromString


    public static Task getTaskFromJson(JSONObject jsonObject){

        Task task;

        try {


            int id = jsonObject.getInt(Task.TASK_ID);
            int status = jsonObject.getInt(Task.TASK_STATUS);
            String date = jsonObject.getString(Task.TASK_DATE);
            String timeStart = jsonObject.getString(Task.TASK_TIME_START);
            String checkIn = jsonObject.getString(Task.TASK_CHECK_IN);
            String timeEnd = jsonObject.getString(Task.TASK_TIME_END);
            String checkOut = jsonObject.getString(Task.TASK_CHECK_OUT);
            String code = jsonObject.getString(Task.TASK_CODE);
            String site = jsonObject.getString(Task.TASK_SITE);
            String floor = jsonObject.getString(Task.TASK_FLOOR);
            String department = jsonObject.getString(Task.TASK_DEPARTMENT);
            String description = jsonObject.getString(Task.TASK_DESCRIPTION);

            task = new Task(id, status, date, timeStart, checkIn, timeEnd, checkOut,  code,  site, floor, department, description);
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

            jsonObject.put(Task.TASK_ID, task.getId());
            jsonObject.put(Task.TASK_STATUS, task.getStatus());
            jsonObject.put(Task.TASK_DATE, task.getDate());
            jsonObject.put(Task.TASK_TIME_START, task.getTimeStart());
            jsonObject.put(Task.TASK_CHECK_IN, task.getCheckIn());
            jsonObject.put(Task.TASK_TIME_END, task.getTimeEnd());
            jsonObject.put(Task.TASK_CHECK_OUT, task.getCheckOut());
            jsonObject.put(Task.TASK_CODE, task.getCode());
            jsonObject.put(Task.TASK_SITE, task.getSite());
            jsonObject.put(Task.TASK_FLOOR, task.getFloor());
            jsonObject.put(Task.TASK_DEPARTMENT, task.getDepartment());
            jsonObject.put(Task.TASK_DESCRIPTION, task.getDescription());


        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

        return jsonObject;

    }//getJsonObjectFromTask


}//JsonDataManager



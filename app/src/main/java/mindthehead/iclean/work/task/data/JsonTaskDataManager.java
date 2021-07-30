package mindthehead.iclean.work.task.data;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class JsonTaskDataManager {


    public static ArrayList<Task> getTasksFromString(String string) {

        ArrayList<Task> tasks = new ArrayList<Task>();

        try {

            JSONObject jsonObject = new JSONObject(string);

            JSONArray jsonArray = new JSONArray();
            jsonArray = jsonObject.getJSONArray("tasks");

            for(int i = 0; i<jsonArray.length(); i++) {

                Task task = new Task();
                JSONObject currentJsonObject = new JSONObject();
                currentJsonObject = jsonArray.getJSONObject(i);
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
        ArrayList<Task> tasks = new ArrayList<Task>();

        try {

            int status = jsonObject.getInt("status");
            String id = jsonObject.getString("id");
            int priority = jsonObject.getInt("priority");
            String date = jsonObject.getString("date");
            String timeStart = jsonObject.getString("timeStart");
            String timeEnd = jsonObject.getString("timeEnd");
            String site = jsonObject.getString("site");
            String floor = jsonObject.getString("floor");
            String department = jsonObject.getString("department");
            String info = jsonObject.getString("info");

            task = new Task(id, priority,date, timeStart, timeEnd, site, floor, department, info);
            task.setStatus(status);

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

        return task;

    }//getSTaskFromJson


    public static String getStringFromTasks(ArrayList<Task> tasks){

        JSONObject jsonObject = new JSONObject();

        try {

            JSONArray jsonArray = new JSONArray();

            for(int i = 0; i<tasks.size(); i++) {

                if(getJsonObjectFromTask(tasks.get(i)) != null) jsonArray.put(getJsonObjectFromTask(tasks.get(i)));

            }

            jsonObject.put("tasks", jsonArray);

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }


        return jsonObject.toString();

    }//getStringFromTasks


    public static JSONObject getJsonObjectFromTask(Task task){

        JSONObject jsonObject = new JSONObject();

        try {

            jsonObject.put("status", task.getStatus());
            jsonObject.put("id", task.getId());
            jsonObject.put("priority", task.getPriority());
            jsonObject.put("date", task.getDate());
            jsonObject.put("timeStart", task.getTimeStart());
            jsonObject.put("timeEnd", task.getTimeEnd());
            jsonObject.put("site", task.getSite());
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



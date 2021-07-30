package mindthehead.iclean.example.datamanage;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import mindthehead.iclean.work.task.data.Task;


public class JsonDataManager {


    public static ArrayList<Task> getTasksFromString(String string) {

        ArrayList<Task> surveys = new ArrayList<Task>();

        try {

            JSONObject jsonObject = new JSONObject(string);

            JSONArray jsonArray = new JSONArray();
            jsonArray = jsonObject.getJSONArray("tasks");

            for(int i = 0; i<jsonArray.length(); i++) {

                Task task = new Task();
                JSONObject currentJsonObject = new JSONObject();
                currentJsonObject = jsonArray.getJSONObject(i);
                task = getTaskFromJson(currentJsonObject);

                if(task != null) surveys.add(task);

            }

        } catch (JSONException e) {
            e.printStackTrace();
            return surveys;
        }

        return surveys;

    }//getTasksFromString


    public static Task getTaskFromJson(JSONObject jsonObject){

        Task task = null;
        ArrayList<Task> tasks = new ArrayList<Task>();

        try {

            String id = jsonObject.getString("id");
            String date = jsonObject.getString("id");
            String timeStart = jsonObject.getString("id");
            String timeEnd = jsonObject.getString("id");
            String site = jsonObject.getString("id");
            String floor = jsonObject.getString("id");
            String department = jsonObject.getString("id");
            String info = jsonObject.getString("id");

            //task = new Task(id, date, timeStart, timeEnd, site, floor, department, info);;

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

                JSONObject currentJsonObject = new JSONObject(getStringFromTask(tasks.get(i)));
                jsonArray.put(currentJsonObject);

            }

            jsonObject.put("surveys", jsonArray);

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }


        return jsonObject.toString();

    }//getStringFromTasks


    public static String getStringFromTask(Task task){


        JSONObject jsonObject = new JSONObject();

        try {

            jsonObject.put("id", task.getId());
            jsonObject.put("id", task.getDate());
            jsonObject.put("id", task.getTimeStart());
            jsonObject.put("id", task.getTimeEnd());
            jsonObject.put("id", task.getSite());
            jsonObject.put("id", task.getFloor());
            jsonObject.put("id", task.getDepartment());
            jsonObject.put("id", task.getInfo());

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

        return jsonObject.toString();

    }//getStringFromTask


}//JsonDataManager



package mindthehead.iclean.work.sync.data;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import mindthehead.iclean.work.task.data.Task;


public class JsonTaskDoneDataManager {


    public static String getTasksDoneFromString(String string) {

        JSONArray tasksDone  = new JSONArray();

        try {

            JSONArray jsonArray = new JSONArray(string);

            for(int i = 0; i<jsonArray.length(); i++) {

                JSONObject currentJsonObject;
                JSONObject taskDone;

                currentJsonObject = jsonArray.getJSONObject(i);
                taskDone = getTaskDoneFromJson(currentJsonObject);

                if(taskDone != null) tasksDone.put(taskDone);

            }

        } catch (JSONException e) {
            e.printStackTrace();
            return tasksDone.toString();
        }

        return tasksDone.toString();

    }//getTasksDoneFromString


    public static JSONObject getTaskDoneFromJson(JSONObject jsonObject){

        JSONObject taskDone = new JSONObject();

        try {

            int status = jsonObject.getInt(Task.TASK_STATUS);
            if (status != Task.STATUS_DONE) return null;

            int id = jsonObject.getInt(Task.TASK_ID);
            taskDone.put(Task.TASK_ID, id);


            String checkIn = jsonObject.getString(Task.TASK_CHECK_IN).replace(":", "-");
            taskDone.put(Task.TASK_CHECK_IN, checkIn);

            String checkOut = jsonObject.getString(Task.TASK_CHECK_OUT).replace(":", "-");
            taskDone.put(Task.TASK_CHECK_OUT, checkOut);


        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

        return taskDone;

    }//getTaskDoneFromJson



}//JsonDataManager



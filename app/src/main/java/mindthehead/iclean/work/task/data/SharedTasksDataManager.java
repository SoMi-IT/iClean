package mindthehead.iclean.work.task.data;


import android.content.Context;
import java.util.ArrayList;
import mindthehead.iclean.R;
import mindthehead.iclean.util.SharedPreferencesManager;


public class SharedTasksDataManager {


    private ArrayList<Task> tasks;


    public static ArrayList<Task> obtainStoredTasks(Context context) {

        ArrayList<Task> storedTasks = new ArrayList<Task>();

        String storedTasksString = SharedPreferencesManager.readString(context, R.string.task);

        storedTasks = JsonTaskDataManager.getTasksFromString(storedTasksString);


        return storedTasks;

    }//obtainStoredTasks


    public static Task obtainTaskFromId(Context context, String taskId) {

        ArrayList<Task> storedTasks = obtainStoredTasks(context);

        for(int i=0; i<storedTasks.size(); i++) {

            if(taskId.equals(storedTasks.get(i).getId())) {

                return storedTasks.get(i);

            }

        }

        return null;

    }//obtainTaskFromId





    public static void updateTask(Context context, Task task, String newTaskId) {

        ArrayList<Task> storedTasks = obtainStoredTasks(context);

        for(int i = 0; i<storedTasks.size(); i++) {

            if(task.getId().equals(storedTasks.get(i).getId())) {

                storedTasks.get(i).setId(newTaskId);

            }

        }

        String newStoredTaskString = JsonTaskDataManager.getStringFromTasks(storedTasks);

        SharedPreferencesManager.writeString(context, R.string.task, newStoredTaskString);

    }//updateTask


    public static boolean isTaskAlreadyExist(Context context, String taskId) {


        ArrayList<Task> storedTasks = obtainStoredTasks(context);

        for(int i=0; i<storedTasks.size(); i++) {

            if(taskId.equals(storedTasks.get(i).getId())) {

                return true;

            }

        }

        return false;

    }//isTaskAlreadyExist


    public static void addNewTask(Context context, String taskId) {


        ArrayList<Task> storedTasks = obtainStoredTasks(context);

        Task newTask = new Task();
        newTask.setId(taskId);

        storedTasks.add(newTask);

        String newStoredSurveysString = JsonTaskDataManager.getStringFromTasks(storedTasks);

        SharedPreferencesManager.writeString(context, R.string.task, newStoredSurveysString);

    }//addNewTask


    public static void deleteSurvey(Context context, String taskId) {


        ArrayList<Task> storedTasks = obtainStoredTasks(context);

        for(int i=0; i<storedTasks.size(); i++) {

            if(taskId.equals(storedTasks.get(i).getId())) {

                storedTasks.remove(storedTasks.get(i));

            }

        }


        String newStoredSurveysString = JsonTaskDataManager.getStringFromTasks(storedTasks);

        SharedPreferencesManager.writeString(context, R.string.task, newStoredSurveysString);

    }//deleteSurvey


}//SharedTasksDataManager

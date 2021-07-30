package mindthehead.iclean.work.task;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

import mindthehead.iclean.R;
import mindthehead.iclean.util.SharedPreferencesManager;
import mindthehead.iclean.work.shedules.Schedule;

public class TaskDataManager {

    private TaskDataManagerListener listener;

    public TaskDataManager(){


    }//Constructor

    public void setListener(TaskDataManagerListener _listener){

        listener = _listener;

    }//setListener


    public static ArrayList<Task> getFakeData(){

        ArrayList<Task> tasks = new ArrayList<>();

        for (int i = 0; i < 5; i++) {

            Task task = new Task();
            task.setExpanded(false);
            task.setStatus(Task.STATUS_TODO);
            task.setId("000" + i);
            task.setPriority(3-i);
            task.setDate("01/02/2021");
            task.setTimeStart("1" + i +":00");
            task.setTimeEnd("1" + (i+5) +":00");
            task.setSite("ST789" + i);
            task.setFloor("Secondo");
            task.setDepartment("Riabilitazione");
            task.setInfo("Svuotare la spazzatura, \n Spazzare \n Lavare pavimento \n Sanificazione");

            tasks.add(task);

        }

        return tasks;

    }//getFakeData

    public void obtainStoredTasks(Activity context) {

        String storedTasksString = SharedPreferencesManager.readString(context, R.string.task);
        ArrayList<Task> tasks = JsonTaskDataManager.getTasksFromString(storedTasksString);
        listener.dataCreated(tasks);

    }//obtainStoredTasks


    public static void saveTasks(Context context, ArrayList<Task> tasks) {

        Collections.sort(tasks, (obj1, obj2) -> {
            // ## Ascending order
            //return obj1.firstName.compareToIgnoreCase(obj2.firstName); // To compare string values
            //return Integer.compare(obj1.getPriority(), obj2.getPriority()); // To compare integer values
            // ## Descending order
            // return obj2.firstName.compareToIgnoreCase(obj1.firstName); // To compare string values
            return Integer.compare(obj2.getPriority(), obj1.getPriority()); // To compare integer values

        });

        tasks.get(0).setStatus(Task.STATUS_CURRENT);
        String newStoredTaskString = JsonTaskDataManager.getStringFromTasks(tasks);
        Log.d("XXX", newStoredTaskString);
        SharedPreferencesManager.writeString(context, R.string.task, newStoredTaskString);

    }//saveTasks


    public void updateTask(Activity context, Task task) {


        String storedTasksString = SharedPreferencesManager.readString(context, R.string.task);
        ArrayList<Task> tasks = JsonTaskDataManager.getTasksFromString(storedTasksString);

        for(int i = 0; i<tasks.size(); i++) {

            if(tasks.get(i).getId().equals(task.getId())) {

                tasks.get(i).setStatus(Task.STATUS_DONE);
                if (i < tasks.size()-1) tasks.get(i+1).setStatus(Task.STATUS_CURRENT);
            }

        }

        String newStoredTaskString = JsonTaskDataManager.getStringFromTasks(tasks);
        SharedPreferencesManager.writeString(context, R.string.task, newStoredTaskString);

        listener.dataUpdated(tasks);

    }//updateTask


}//taskDataManager

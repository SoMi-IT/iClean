package mindthehead.iclean.work.task.data;

import android.app.Activity;
import android.content.Context;

import java.util.ArrayList;
import java.util.Collections;

import mindthehead.iclean.R;
import mindthehead.iclean.util.SharedPreferencesManager;

public class TaskDataManager {

    private TaskDataManagerListener listener;

    public TaskDataManager(){


    }//Constructor

    public void setListener(TaskDataManagerListener _listener){

        listener = _listener;

    }//setListener


    public ArrayList<Task> getStoredTasks(Activity context) {

        String storedTasksString = SharedPreferencesManager.readString(context, R.string.tasks);
        ArrayList<Task> tasks = JsonTaskDataManager.getTasksFromString(storedTasksString);
        return tasks;

    }//obtainStoredTasks


    public static void saveTasks(Context context, ArrayList<Task> tasks) {

        /*Collections.sort(tasks, (obj1, obj2) -> {
            // ## Ascending order
            //return obj1.firstName.compareToIgnoreCase(obj2.firstName); // To compare string values
            //return Integer.compare(obj1.getPriority(), obj2.getPriority()); // To compare integer values
            // ## Descending order
            // return obj2.firstName.compareToIgnoreCase(obj1.firstName); // To compare string values
            return Integer.compare(obj2.getPriority(), obj1.getPriority()); // To compare integer values

        });*/

        tasks.get(0).setStatus(Task.STATUS_CURRENT_NOT_STARTED);
        String newStoredTaskString = JsonTaskDataManager.getStringFromTasks(tasks);
        SharedPreferencesManager.writeString(context, R.string.tasks, newStoredTaskString);

    }//saveTasks


    public void updateTask(Activity context, Task task) {

        int donePosition = 0;
        int currentPosition = 0;
        int newPosition = 0;

        String storedTasksString = SharedPreferencesManager.readString(context, R.string.tasks);
        ArrayList<Task> tasks = JsonTaskDataManager.getTasksFromString(storedTasksString);

        for(int i = 0; i<tasks.size(); i++) {

            if(tasks.get(i).getId() == task.getId()) {

                tasks.set(i, task);

                if (tasks.get(i).getStatus() == Task.STATUS_CURRENT_NOT_STARTED) {

                    tasks.get(i).setStatus(Task.STATUS_CURRENT_STARTED);

                    currentPosition = i;

                } else if (tasks.get(i).getStatus() == Task.STATUS_CURRENT_STARTED) {

                    tasks.get(i).setStatus(Task.STATUS_DONE);

                    donePosition = i;

                    if (i < tasks.size()-1){

                        tasks.get(i+1).setStatus(Task.STATUS_CURRENT_NOT_STARTED);
                        newPosition = i + 1;

                    }

                }

            }

        }
        String newStoredTaskString = JsonTaskDataManager.getStringFromTasks(tasks);
        SharedPreferencesManager.writeString(context, R.string.tasks, newStoredTaskString);

        listener.dataUpdated(tasks, donePosition, currentPosition, newPosition);

    }//updateTask


}//taskDataManager

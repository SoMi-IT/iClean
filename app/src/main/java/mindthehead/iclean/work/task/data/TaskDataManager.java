package mindthehead.iclean.work.task.data;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;

import mindthehead.iclean.R;
import mindthehead.iclean.util.SharedPreferencesManager;
import mindthehead.iclean.util.dialog.ManualTimeDialog;
import mindthehead.iclean.work.task.TaskFragment;

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
            task.setPriority(5-i);
            task.setDate("0" + i +"/02/2021");
            task.setDateStartDone("");
            task.setDateEndDone("");
            task.setTimeStart("1" + (i) +":00");
            task.setTimeStartDone("");
            task.setTimeEnd("1" + (i+1) +":00");
            task.setTimeEndDone("");
            task.setSite("ST789" + i);
            task.setFloor("Secondo");
            task.setDepartment("Riabilitazione");
            task.setInfo("Svuotare la spazzatura, \n Spazzare \n Lavare pavimento \n Sanificazione");

            tasks.add(task);

        }

        return tasks;

    }//getFakeData

    public ArrayList<Task> getStoredTasks(Activity context) {

        String storedTasksString = SharedPreferencesManager.readString(context, R.string.task);
        ArrayList<Task> tasks = JsonTaskDataManager.getTasksFromString(storedTasksString);
        return tasks;

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

        tasks.get(0).setStatus(Task.STATUS_CURRENT_NOT_STARTED);
        String newStoredTaskString = JsonTaskDataManager.getStringFromTasks(tasks);
        SharedPreferencesManager.writeString(context, R.string.task, newStoredTaskString);

    }//saveTasks


    public void updateTask(Activity context, Task task) {

        int donePosition = 0;
        int currentPosition = 0;
        int newPosition = 0;

        String storedTasksString = SharedPreferencesManager.readString(context, R.string.task);
        ArrayList<Task> tasks = JsonTaskDataManager.getTasksFromString(storedTasksString);

        for(int i = 0; i<tasks.size(); i++) {

            if(tasks.get(i).getId().equals(task.getId())) {

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

            Log.d("XXX" , "tmestartdone: " + tasks.get(i).getTimeStartDone());
            Log.d("XXX" , "tmeenddone: " + tasks.get(i).getDateEndDone());
            Log.d("XXX" , " ------------ ");
        }

        String newStoredTaskString = JsonTaskDataManager.getStringFromTasks(tasks);
        SharedPreferencesManager.writeString(context, R.string.task, newStoredTaskString);

        listener.dataUpdated(tasks, donePosition, currentPosition, newPosition);

    }//updateTask


}//taskDataManager

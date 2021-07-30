package mindthehead.iclean.work.task;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import mindthehead.iclean.R;
import mindthehead.iclean.util.DateManager;
import mindthehead.iclean.work.WorkActivity;
import mindthehead.iclean.work.task.dialog.TaskItemEndDialog;
import mindthehead.iclean.work.task.dialog.TaskItemEndDialogListener;
import mindthehead.iclean.work.task.dialog.TaskItemInfoDialog;
import mindthehead.iclean.work.task.dialog.TaskItemLocationDialog;
import mindthehead.iclean.work.task.dialog.TaskItemStartDialog;
import mindthehead.iclean.work.task.dialog.TaskItemStartDialogListener;


public class TaskFragment extends Fragment implements TasksListAdapterListener, TaskDataManagerListener, TaskItemStartDialogListener, TaskItemEndDialogListener {


    private WorkActivity activity;
    private TaskDataManager taskDataManager;
    private TasksListAdapter tasksListAdapter;
    private TaskListener listener;
    private Task currentTask;

    private RecyclerView rv_tasks;

    private TextView tv_date, tv_time;

    private Thread thread;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        activity = (WorkActivity) getActivity();

        View rootView = inflater.inflate(R.layout.fragment_task, container, false);

        rv_tasks = rootView.findViewById(R.id.rv_tasks);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(activity);
        rv_tasks.setLayoutManager(layoutManager);

        taskDataManager = new TaskDataManager();
        taskDataManager.setListener(this);
        taskDataManager.obtainStoredTasks(activity);

        tv_date = rootView.findViewById(R.id.tv_task_date);
        tv_time = rootView.findViewById(R.id.tv_task_time);

        tv_date.setText(DateManager.getCurrentDate());
        tv_time.setText(DateManager.getCurrentTime());

        startClock();

        return rootView;

    }//onCreateView

    public void onDestroy() {

        if (thread != null)thread.interrupt();
        super.onDestroy();

    }//onDestroy


    private void startClock() {

        thread = new Thread() {

            public void run() {
                try {
                    while (!isInterrupted()) {

                        Thread.sleep(1000);

                        activity.runOnUiThread(new Runnable() {

                            public void run() {
                                tv_date.setText(DateManager.getCurrentDate());
                                tv_time.setText(DateManager.getCurrentTime());
                            }

                        });

                    }

                } catch (InterruptedException e) {
                }

            }

        };

        thread.start();

    }//startClock


    public void setListener(TaskListener _listener){

        listener = _listener;

    }//setListener


    public void onItemInfoClicked(Task task) {

        TaskItemInfoDialog taskItemInfoDialog = new TaskItemInfoDialog(activity, task.getInfo());
        taskItemInfoDialog.show();

    }//onItemInfoClicked


    public void onItemLocationClicked(Task task) {

        TaskItemLocationDialog taskItemLocationDialog = new TaskItemLocationDialog(activity, task.getSite(), task.getFloor(), task.getDepartment());
        taskItemLocationDialog.show();

    }//onItemLocationClicked


    public void onItemStartClicked(Task task) {

        currentTask = task;
        TaskItemStartDialog taskItemStartDialog = new TaskItemStartDialog(activity);
        taskItemStartDialog.setListener(this);
        taskItemStartDialog.show();

    }//onItemStartClicked


    public void onItemEndClicked(Task task) {

        currentTask = task;
        TaskItemEndDialog taskItemEndDialog = new TaskItemEndDialog(activity);
        taskItemEndDialog.setListener(this);
        taskItemEndDialog.show();

    }//onItemEndClicked


    public void dataCreated(ArrayList<Task> tasks) {

        tasksListAdapter = new TasksListAdapter(activity, tasks);
        tasksListAdapter.setListener(this);
        rv_tasks.setAdapter(tasksListAdapter);

    }//dataCreated

    public void dataUpdated(ArrayList<Task> tasks) {

        tasksListAdapter.updateTasks(tasks);

    }//dataUpdated

    public void onStartDid() {

        taskDataManager.updateTask(activity, currentTask);

    }//onStartDid

    public void onEndDid() {


    }//onEndDid

}//TaskFragment


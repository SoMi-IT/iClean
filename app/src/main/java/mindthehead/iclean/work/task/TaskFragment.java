package mindthehead.iclean.work.task;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import mindthehead.iclean.R;
import mindthehead.iclean.data.DataManager;
import mindthehead.iclean.util.DateManager;
import mindthehead.iclean.util.dialog.InfoDialog;
import mindthehead.iclean.util.dialog.ManualDialogListener;
import mindthehead.iclean.util.dialog.ManualTimeDialog;
import mindthehead.iclean.util.dialog.NFCDialog;
import mindthehead.iclean.util.dialog.NFCDialogListener;
import mindthehead.iclean.util.dialog.WarningDialog;
import mindthehead.iclean.work.WorkActivity;
import mindthehead.iclean.work.task.adapter.TasksListAdapter;
import mindthehead.iclean.work.task.adapter.TasksListAdapterListener;
import mindthehead.iclean.work.task.data.Task;
import mindthehead.iclean.work.task.data.TaskDataManager;
import mindthehead.iclean.work.task.data.TaskDataManagerListener;


public class TaskFragment extends Fragment implements TasksListAdapterListener, TaskDataManagerListener, NFCDialogListener, ManualDialogListener {


    private WorkActivity activity;
    private TaskDataManager taskDataManager;
    private TasksListAdapter tasksListAdapter;
    private Task currentTask;
    private NFCDialog nfcDialog;

    private TextView tv_date, tv_time;

    private Thread thread;

    private int currentChoice;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        activity = (WorkActivity) getActivity();

        View rootView = inflater.inflate(R.layout.fragment_task, container, false);

        RecyclerView rv_tasks = rootView.findViewById(R.id.rv_tasks);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(activity);
        rv_tasks.setLayoutManager(layoutManager);

        taskDataManager = new TaskDataManager();
        taskDataManager.setListener(this);
        tasksListAdapter = new TasksListAdapter(activity, taskDataManager.getStoredTasks(activity));
        tasksListAdapter.setListener(this);
        rv_tasks.setAdapter(tasksListAdapter);

        tv_date = rootView.findViewById(R.id.tv_task_date);
        tv_time = rootView.findViewById(R.id.tv_task_time);
        tv_date.setText(DateManager.getCurrentItalianDate());
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

                        activity.runOnUiThread(() -> {
                            tv_date.setText(DateManager.getCurrentItalianDate());
                            tv_time.setText(DateManager.getCurrentTime());


                        });

                    }

                } catch (InterruptedException e) {
                }

            }

        };

        thread.start();

    }//startClock


    public void onItemInfoClicked(Task task) {

        InfoDialog infoDialog = new InfoDialog(activity, task.getDescription(),"","","");
        infoDialog.show();

    }//onItemInfoClicked


    public void onItemLocationClicked(Task task) {

        InfoDialog infoDialog = new InfoDialog(activity, "", task.getSite(), task.getFloor(), task.getDepartment());
        infoDialog.show();

    }//onItemLocationClicked


    public void onItemStartClicked(Task task) {

        if (DataManager.getCheckIn(activity).length() == 0 ) {

            WarningDialog warningDialog = new WarningDialog(activity, "Non hai ancora timbrato l'ingresso!");
            warningDialog.show();
            return;

        }
        if (DataManager.getCheckOut(activity).length() != 0) {

            WarningDialog warningDialog = new WarningDialog(activity, "Hai già chiuso il turno!");
            warningDialog.show();
            return;

        }
        currentChoice = ManualTimeDialog.MANUAL_TYPE_IN;
        currentTask = task;
        nfcDialog = new NFCDialog(activity);
        nfcDialog.setListener(this);
        nfcDialog.show();

    }//onItemStartClicked


    public void onItemEndClicked(Task task) {

        if (!DataManager.isUserWorkShiftStarted(activity)) {

            WarningDialog warningDialog = new WarningDialog(activity, "Non hai ancora timbrato l'ingresso!");
            warningDialog.show();
            return;

        }
        if (DataManager.isUserWorkShiftEnd(activity)) {

            WarningDialog warningDialog = new WarningDialog(activity, "Hai già chiuso il turno!");
            warningDialog.show();
            return;

        }

        currentChoice = ManualTimeDialog.MANUAL_TYPE_OUT;
        currentTask = task;
        nfcDialog = new NFCDialog(activity);
        nfcDialog.setListener(this);
        nfcDialog.show();

    }//onItemEndClicked


    public void dataUpdated(ArrayList<Task> tasks, int donePosition, int currentPosition, int newPosition) {

        tasksListAdapter.updateTasks(tasks, donePosition, currentPosition, newPosition);

    }//dataUpdated


    public void onNFCFind(String id) {

        if (currentTask.getCode().equals(id)) {

            if(currentChoice == ManualTimeDialog.MANUAL_TYPE_IN) {

                currentTask.setCheckIn(DateManager.getCurrentMoment());
                taskDataManager.updateTask(activity, currentTask);

            } else if(currentChoice == ManualTimeDialog.MANUAL_TYPE_OUT) {

                currentTask.setCheckOut(DateManager.getCurrentMoment());
                taskDataManager.updateTask(activity, currentTask);

            }

        } else {

            WarningDialog warningDialog = new WarningDialog(activity, "Il TAG Scansionato non corrisponde a quello del Task");
            warningDialog.show();
        }



    }//onNFCFind

    public void onManual() {

        ManualTimeDialog manualTimeDialog = new ManualTimeDialog(activity, currentChoice);
        manualTimeDialog.setListener(this);
        manualTimeDialog.show();

    }//onManual


    public void onManualPick(String time) {

        if(currentChoice == ManualTimeDialog.MANUAL_TYPE_IN) {

            currentTask.setCheckIn(time);
            taskDataManager.updateTask(activity, currentTask);

        } else if(currentChoice == ManualTimeDialog.MANUAL_TYPE_OUT) {

            currentTask.setCheckOut(time);
            taskDataManager.updateTask(activity, currentTask);

        }

    }//onManualPick

}//TaskFragment


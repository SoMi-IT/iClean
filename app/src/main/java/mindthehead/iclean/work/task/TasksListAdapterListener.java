package mindthehead.iclean.work.task;

import mindthehead.iclean.work.shedules.Schedule;

public interface TasksListAdapterListener {

    void onItemInfoClicked(Task task);
    void onItemLocationClicked(Task task);
    void onItemStartClicked(Task task);
    void onItemEndClicked(Task task);

}//TasksListAdapterListener

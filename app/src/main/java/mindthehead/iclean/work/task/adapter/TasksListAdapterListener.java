package mindthehead.iclean.work.task.adapter;

import mindthehead.iclean.work.task.data.Task;

public interface TasksListAdapterListener {

    void onItemInfoClicked(Task task);
    void onItemLocationClicked(Task task);
    void onItemStartClicked(Task task);
    void onItemEndClicked(Task task);

}//TasksListAdapterListener

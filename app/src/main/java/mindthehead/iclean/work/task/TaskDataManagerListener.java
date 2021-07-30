package mindthehead.iclean.work.task;

import java.util.ArrayList;

public interface TaskDataManagerListener {

    void dataCreated(ArrayList<Task> tasks);
    void dataUpdated(ArrayList<Task> tasks);

}//TaskDataManagerListener

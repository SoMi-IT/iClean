package mindthehead.iclean.work.task.data;

import java.util.ArrayList;

public interface TaskDataManagerListener {

    void dataUpdated(ArrayList<Task> tasks, int donePosition, int currentPosition);

}//TaskDataManagerListener

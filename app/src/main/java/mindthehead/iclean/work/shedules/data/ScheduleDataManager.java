package mindthehead.iclean.work.shedules.data;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;

import mindthehead.iclean.R;
import mindthehead.iclean.util.SharedPreferencesManager;
import mindthehead.iclean.work.task.data.JsonTaskDataManager;
import mindthehead.iclean.work.task.data.Task;

public class ScheduleDataManager {

    public ScheduleDataManager(){


    }//Constructor


    public ArrayList<Schedule> getStoredSchedules(Activity context) {

        String storedSchedulesString = SharedPreferencesManager.readString(context, R.string.schedules);
        return JsonScheduleDataManager.getSchedulesFromString(storedSchedulesString);

    }//getStoredSchedules

}//ScheduleDataManager

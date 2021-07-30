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


    public static ArrayList<Schedule> getFakeData(){

        ArrayList<Schedule> schedules = new ArrayList<>();

        for (int i = 0; i < 8; i++) {

            Schedule schedule = new Schedule();
            schedule.setId("000" + i);
            schedule.setDate("0" + i +"/02/2021");
            schedule.setTimeStart("10:00");
            schedule.setTimeEnd("18:00");
            schedule.setSite("ST789" + i);
            schedule.setFloor("Secondo");
            schedule.setDepartment("Riabilitazione");

            schedules.add(schedule);

        }
        return schedules;

    }//getFakeData


    public static void saveSchedules(Context context, ArrayList<Schedule> schedules) {

        String newStoredScheduleString = JsonScheduleDataManager.getStringFromSchedules(schedules);
        SharedPreferencesManager.writeString(context, R.string.schedules, newStoredScheduleString);

    }//saveTasks

    public ArrayList<Schedule> getStoredSchedules(Activity context) {

        String storedSchedulesString = SharedPreferencesManager.readString(context, R.string.schedules);
        return JsonScheduleDataManager.getSchedulesFromString(storedSchedulesString);

    }//getStoredSchedules

}//ScheduleDataManager

package mindthehead.iclean.work.shedules.data;

import android.app.Activity;
import java.util.ArrayList;
import mindthehead.iclean.R;
import mindthehead.iclean.util.SharedPreferencesManager;

public class ScheduleDataManager {

    public ScheduleDataManager(){


    }//Constructor


    public ArrayList<Schedule> getStoredSchedules(Activity context) {

        String storedSchedulesString = SharedPreferencesManager.readString(context, R.string.schedules);
        return JsonScheduleDataManager.getSchedulesFromString(storedSchedulesString);

    }//getStoredSchedules

}//ScheduleDataManager

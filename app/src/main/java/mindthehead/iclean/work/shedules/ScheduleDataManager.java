package mindthehead.iclean.work.shedules;

import android.util.Log;

import java.util.ArrayList;

public class ScheduleDataManager {

    public ScheduleDataManager(){


    }//Constructor


    public ArrayList<Schedule> getFakeData(){

        ArrayList<Schedule> schedules = new ArrayList<>();

        for (int i = 0; i < 9; i++) {

            Schedule schedule = new Schedule();
            schedule.setId("000" + i);
            schedule.setDate("01/02/2021");
            schedule.setTimeStart("10:00");
            schedule.setTimeEnd("18:00");
            schedule.setSite("ST7899");
            schedule.setFloor("Secondo");
            schedule.setDepartment("Riabilitazione");

            schedules.add(schedule);

        }
        return schedules;

    }//getFakeData

}//ScheduleDataManager

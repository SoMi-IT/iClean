package mindthehead.iclean.work.times;

import java.util.ArrayList;

import mindthehead.iclean.work.task.data.Task;

public class TimesDataManager {

    public TimesDataManager(){


    }//Constructor


    public ArrayList<Task> getFakeData(){

        ArrayList<Task> tasks = new ArrayList<>();

        for (int i = 0; i < 9; i++) {

            Task task = new Task();
            task.setExpanded(false);
            task.setId("000" + i);
            task.setDate("01/02/2021");
            task.setTimeStart("10:00");
            task.setTimeEnd("18:00");
            task.setSite("ST7899");
            task.setFloor("Secondo");
            task.setDepartment("Riabilitazione");
            task.setInfo("Svuotare la spazzatura, \n Spazzare \n Lavare pavimento \n Sanificazione");

            tasks.add(task);

        }
        return tasks;



    }//getFakeData

}//taskDataManager

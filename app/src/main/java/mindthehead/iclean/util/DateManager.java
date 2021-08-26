package mindthehead.iclean.util;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class DateManager {


    /*public static String getFormattedStringFromDate(int year, int month, int day) {


        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        return df.format(calendar.getTime());

    }//getFormattedStringFromDate


    public static String getDayNameFromDate(int year, int month, int day) {


        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        return calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault());

    }//getFormattedStringFromDate*/


    public static String getCurrentDate(){

       return new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

    }//getCurrentDate

    public static String getCurrentTime(){

        return new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date());

    }//getCurrentTime

    public static String getCurrentMoment(){

        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date());

    }//getCurrentMoment

}//DateManager

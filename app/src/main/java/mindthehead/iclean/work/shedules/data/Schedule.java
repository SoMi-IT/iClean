package mindthehead.iclean.work.shedules.data;

public class Schedule {


    public final static String SCHEDULES_DATE = "date";
    public final static String SCHEDULES_TIME_START = "timestart";
    public final static String SCHEDULES_TIME_END = "timeend";
    public final static String SCHEDULES_SITE = "site";

    private String id;
    private final String date;
    private final String timeStart;
    private final String timeEnd;
    private final String site;


    public Schedule(String _date, String _timeStart, String _timeEnd, String _site) {

        date = _date;
        timeStart = _timeStart;
        timeEnd = _timeEnd;
        site = _site;

    }//Constructor


    public String getId() { return id; }//getId

    public String getDate() { return date; }//getDate

    public String getTimeStart() { return timeStart; }//getTimeStart

    public String getTimeEnd() { return timeEnd; }//getTimeEnd

    public String getSite() { return site; }//getSite


    public void setId(String _id) { id = _id; }//setDate

    //public void setDate(String _date) { date = _date; }//setDate

    //public void setTimeStart(String _timeStart) { timeStart = _timeStart; }//setTimeStart

    //public void setTimeEnd(String _timeEnd) { timeEnd = _timeEnd; }//setTimeEnd

    //public void setSite(String _site) { site = _site; }//setSite


}//Schedule

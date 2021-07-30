package mindthehead.iclean.work.shedules;

public class Schedule {


    private String id;
    private String date;
    private String timeStart;
    private String timeEnd;
    private String site;
    private String floor;
    private String department;


    public Schedule() { }//Constructor


    public Schedule(String _id, String _date, String _timeEnd, String _timeStart, String _site, String _floor, String _department) {

        id = _id;
        date = _date;
        timeStart = _timeStart;
        timeEnd = _timeEnd;
        site = _site;
        floor = _floor;
        department = _department;

    }//Constructor

    public String getId() { return id; }//getId

    public String getDate() { return date; }//getDate

    public String getTimeStart() { return timeStart; }//getTimeStart

    public String getTimeEnd() { return timeEnd; }//getTimeEnd

    public String getSite() { return site; }//getSite

    public String getFloor() { return floor; }//getFloor

    public String getDepartment() { return department; }//getDepartment


    public void setId(String _id) { id = _id; }//setDate

    public void setDate(String _date) { date = _date; }//setDate

    public void setTimeStart(String _timeStart) { timeStart = _timeStart; }//setTimeStart

    public void setTimeEnd(String _timeEnd) { timeEnd = _timeEnd; }//setTimeEnd

    public void setSite(String _site) { site = _site; }//setSite

    public void setFloor(String _floor) { floor = _floor; }//setFloor

    public void setDepartment(String _department) { department = _department; }//setDepartment


}//Schedule

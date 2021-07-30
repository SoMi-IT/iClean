package mindthehead.iclean.work.task;

public class Task {

    public static final int STATUS_DONE = 0;
    public static final int STATUS_CURRENT = 1;
    public static final int STATUS_TODO = 2;

    private boolean isExpanded;
    private int status;
    private String id;
    private int priority;
    private String date;
    private String timeStart;
    private String timeEnd;
    private String site;
    private String floor;
    private String department;
    private String info;


    public Task() { }//Constructor


    public Task(String _id, int _priority, String _date, String _timeEnd, String _timeStart, String _site, String _floor, String _department, String _info) {

        isExpanded = false;
        id = _id;
        priority = _priority;
        date = _date;
        timeStart = _timeStart;
        timeEnd = _timeEnd;
        site = _site;
        floor = _floor;
        department = _department;
        info = _info;

    }//Constructor

    public boolean isExpanded() { return isExpanded; }//isExpanded

    public int getStatus() { return status; }//getStatus

    public String getId() { return id; }//getId

    public int getPriority() { return priority; }//getPriority

    public String getDate() { return date; }//getDate

    public String getTimeStart() { return timeStart; }//getTimeStart

    public String getTimeEnd() { return timeEnd; }//getTimeEnd

    public String getSite() { return site; }//getSite

    public String getFloor() { return floor; }//getFloor

    public String getDepartment() { return department; }//getDepartment

    public String getInfo() { return info; }//getInfo


    public void setExpanded(boolean _isExpanded) { isExpanded = _isExpanded; }//setExpanded

    public void setStatus(int _status) { status = _status; }//setStatus

    public void setId(String _id) { id = _id; }//setId

    public void setPriority(int _priority) { priority = _priority; }//setPriority

    public void setDate(String _date) { date = _date; }//setDate

    public void setTimeStart(String _timeStart) { timeStart = _timeStart; }//setTimeStart

    public void setTimeEnd(String _timeEnd) { timeEnd = _timeEnd; }//setTimeEnd

    public void setSite(String _site) { site = _site; }//setSite

    public void setFloor(String _floor) { floor = _floor; }//setFloor

    public void setDepartment(String _department) { department = _department; }//setDepartment

    public void setInfo(String _info) { info = _info; }//setInfo


}//Task

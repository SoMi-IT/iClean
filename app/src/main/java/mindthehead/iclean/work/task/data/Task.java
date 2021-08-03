package mindthehead.iclean.work.task.data;

public class Task {

    public static final int STATUS_DONE = 0;
    public static final int STATUS_CURRENT_NOT_STARTED = 1;
    public static final int STATUS_CURRENT_STARTED = 2;
    public static final int STATUS_TODO = 3;

    private boolean isExpanded;
    private int status;
    private String id;
    private int priority;
    private String date;
    private String dateStartDone;
    private String dateEndDone;
    private String timeStart;
    private String timeStartDone;
    private String timeEnd;
    private String timeEndDone;
    private String site;
    private String floor;
    private String department;
    private String info;


    public Task() { }//Constructor

    public Task(String _id, int _priority, String _date, String _dateStartDone, String _dateEndDone, String _timeStart, String _timeStartDone, String _timeEnd, String _timeEndDone, String _site, String _floor, String _department, String _info) {
        id = _id;
        priority = _priority;
        date = _date;
        dateStartDone = _dateStartDone;
        dateEndDone = _dateEndDone;
        timeStart = _timeStart;
        timeStartDone = _timeStartDone;
        timeEnd = _timeEnd;
        timeEndDone = _timeEndDone;
        site = _site;
        floor = _floor;
        department = _department;
        info = _info;
    }

    //GET---------------
    public boolean isExpanded() { return isExpanded; }//isExpanded

    public int getStatus() { return status; }//getStatus

    public String getId() { return id; }//getId

    public int getPriority() { return priority; }//getPriority

    public String getDate() { return date; }//getDate

    public String getDateStartDone() { return dateStartDone; }//getDateStartDone

    public String getDateEndDone() { return dateEndDone; }//getDateEndDone

    public String getTimeStart() { return timeStart; }//getTimeStart

    public String getTimeStartDone() { return timeStartDone; }//getTimeStartDone

    public String getTimeEnd() { return timeEnd; }//getTimeEnd

    public String getTimeEndDone() { return timeEndDone; }//getTimeEndDone

    public String getSite() { return site; }//getSite

    public String getFloor() { return floor; }//getFloor

    public String getDepartment() { return department; }//getDepartment

    public String getInfo() { return info; }//getInfo

    //SET---------------

    public void setExpanded(boolean _isExpanded) { isExpanded = _isExpanded; }//setExpanded

    public void setStatus(int _status) { status = _status; }//setStatus

    public void setId(String _id) { id = _id; }//setId

    public void setPriority(int _priority) { priority = _priority; }//setPriority


    public void setDate(String _date) { date = _date; }//setDate

    public void setDateStartDone(String _dateStartDone) { dateStartDone = _dateStartDone; }//setDateStartDone

    public void setDateEndDone(String _dateEndDone) { dateEndDone = _dateEndDone; }//setDateEndDone


    public void setTimeStart(String _timeStart) { timeStart = _timeStart; }//setTimeStart

    public void setTimeStartDone(String _timeStartDone) { timeStartDone = _timeStartDone; }//setTimeStartDone

    public void setTimeEnd(String _timeEnd) { timeEnd = _timeEnd; }//setTimeEnd

    public void setTimeEndDone(String _timeEndDone) { timeEndDone = _timeEndDone; }//setTimeEndDone


    public void setSite(String _site) { site = _site; }//setSite

    public void setFloor(String _floor) { floor = _floor; }//setFloor

    public void setDepartment(String _department) { department = _department; }//setDepartment

    public void setInfo(String _info) { info = _info; }//setInfo


}//Task

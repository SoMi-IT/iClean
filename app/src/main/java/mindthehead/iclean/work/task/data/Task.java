package mindthehead.iclean.work.task.data;

public class Task {

    public final static String TASK_ID = "id";
    public final static String TASK_STATUS = "status";
    public final static String TASK_DATE = "date";
    public final static String TASK_TIME_START = "timestart";
    public final static String TASK_CHECK_IN = "checkin";
    public final static String TASK_TIME_END = "timeend";
    public final static String TASK_CHECK_OUT = "checkout";
    public final static String TASK_CODE = "code";
    public final static String TASK_SITE = "site";
    public final static String TASK_FLOOR = "floor";
    public final static String TASK_DEPARTMENT = "department";
    public final static String TASK_DESCRIPTION= "description";

    public static final int STATUS_DONE = 0;
    public static final int STATUS_CURRENT_NOT_STARTED = 1;
    public static final int STATUS_CURRENT_STARTED = 2;
    public static final int STATUS_TODO = 3;

    private int id;
    private int status;
    private String date;
    private String timeStart;
    private String checkIn;
    private String timeEnd;
    private String checkOut;
    private String code;
    private String site;
    private String floor;
    private String department;
    private String description;


    public Task() { }//Constructor

    public Task( int _id, int _status, String _date, String _timeStart, String _checkIn, String _timeEnd, String _checkOut, String _code, String _site, String _floor, String _department, String _description) {

        id = _id;
        status = _status;
        date = _date;
        timeStart = _timeStart;
        checkIn = _checkIn;
        timeEnd = _timeEnd;
        checkOut = _checkOut;
        code = _code;
        site = _site;
        floor = _floor;
        department = _department;
        description = _description;
    }

    //GET---------------

    public int getId() { return id; }//getId

    public int getStatus() { return status; }//getStatus

    public String getDate() { return date; }//getDate

    public String getTimeStart() { return timeStart; }//getTimeStart

    public String getCheckIn() { return checkIn; }//getCheckIn

    public String getTimeEnd() { return timeEnd; }//getTimeEnd

    public String getCheckOut() { return checkOut; }//getCheckOut

    public String getCode() { return code; }//getCode

    public String getSite() { return site; }//getSite

    public String getFloor() { return floor; }//getFloor

    public String getDepartment() { return department; }//getDepartment

    public String getDescription() { return description; }//getDescription

    //SET---------------

    public void setId(int _id) { id = _id; }//setId

    public void setStatus(int _status) { status = _status; }//setStatus

    //public void setDate(String _date) { date = _date; }//setDate

    //public void setTimeStart(String _timeStart) { timeStart = _timeStart; }//setTimeStart

    public void setCheckIn(String _checkIn) { checkIn = _checkIn; }//setCheckIn

   // public void setTimeEnd(String _timeEnd) { timeEnd = _timeEnd; }//setTimeEnd

    public void setCheckOut(String _checkOut) { checkOut = _checkOut; }//setCheckOut

    /*public void setCode(String _code) { code = _code; }//setCode

    public void setSite(String _site) { site = _site; }//setSite

    public void setFloor(String _floor) { floor = _floor; }//setFloor

    public void setDepartment(String _department) { department = _department; }//setDepartment

    public void setDescription(String _description) { description = _description; }//setDescription*/


}//Task

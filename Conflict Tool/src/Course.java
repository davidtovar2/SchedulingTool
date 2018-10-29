import java.sql.Time;

public class Course {

	private int rowID;
	private String subject;
	private Time startTime;
	private Time endTime;
	private String days;
	private String instructor;
	private String location;
	
	Course(int rowNumber,String subject,Time start, Time end, String days,String instructor,String location){
		this.rowID = rowNumber;
		this.subject = subject;
		this.startTime = start;
		this.endTime = end;
		this.days = days;
		this.instructor = instructor;
		this.location = location;
		
	}
	
	public int getRowID() {
		return rowID;
	}

	public void setRowID(int rowID) {
		this.rowID = rowID;
	}
	
	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public Time getStartTime() {
		return startTime;
	}

	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}

	public Time getEndTime() {
		return endTime;
	}

	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}

	public String getDays() {
		return days;
	}

	public void setDays(String days) {
		this.days = days;
	}

	public String getInstructor() {
		return instructor;
	}

	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	public boolean conflict(Course checkCourse) {
		// Needs work, just checks professor against time start time and days
		// proof of concept, full Conflict checking WIP
		if (this.instructor.equals(checkCourse.getInstructor()) && this.startTime.equals(checkCourse.getStartTime()) && this.days.equals(checkCourse.getDays()) && this.rowID != checkCourse.getRowID()) {
			return true;
		}
		return false;
	}
	public String toString(){
		return ( rowID + " " + subject + " -- " + instructor + " -- " + location + " -- " + startTime +" to "+ endTime + " on " + days + "\n");
	}
}

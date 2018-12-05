
import java.sql.Time;

import javax.swing.JFileChooser;

/**
 * @author Jake Olsen, David Tovar
 *
 */
public class Course {

	private int rowID;
	private int nbr;
	private String subjectAbbre;
	private String subject;
	private Time startTime;
	private Time endTime;
	private String dates;
	private String days;
	private int[] dayArray;
	private String instructor;
	private String location;
	
	/**
	 * @param rowNumber
	 * @param subjectAbbre
	 * @param nbr
	 * @param subject
	 * @param start
	 * @param end
	 * @param dates
	 * @param days
	 * @param dayArray
	 * @param instructor
	 * @param location
	 */
	Course(int rowNumber,String subjectAbbre, Double nbr,String subject,Time start, Time end,String dates, String days,int[] dayArray,String instructor,String location){
		this.rowID = rowNumber;
		this.subjectAbbre = subjectAbbre;
		this.nbr = (int) Math.round(nbr);
		this.subject = subject;
		this.startTime = start;
		this.endTime = end;
		this.dates = dates;
		this.days = days;
		this.dayArray = dayArray;
		this.instructor = instructor;
		this.location = location;
		
	}
	
	/**
	 * @return
	 */
	public int getRowID() {
		return rowID;
	}

	/**
	 * @param rowID
	 */
	public void setRowID(int rowID) {
		this.rowID = rowID;
	}
	
	/**
	 * @return
	 */
	public int getNbr() {
		return nbr;
	}

	/**
	 * @param nbr
	 */
	public void setNbr(int nbr) {
		this.nbr = nbr;
	}

	/**
	 * @return
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * @param subject
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	/**
	 * @return
	 */
	public Time getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime
	 */
	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return
	 */
	public Time getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime
	 */
	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}

	/**
	 * @return
	 */
	public String getDates() {
		return dates;
	}

	/**
	 * @param dates
	 */
	public void setDates(String dates) {
		this.dates = dates;
	}

	/**
	 * @return
	 */
	public String getDays() {
		return days;
	}

	/**
	 * @param days
	 */
	public void setDays(String days) {
		this.days = days;
	}

	/**
	 * @return
	 */
	public int[] getDayArray() {
		return dayArray;
	}

	/**
	 * @param dayArray
	 */
	public void setDayArray(int[] dayArray) {
		this.dayArray = dayArray;
	}

	/**
	 * @return
	 */
	public String getInstructor() {
		return instructor;
	}

	/**
	 * @param instructor
	 */
	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}

	/**
	 * @return
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @param location
	 */
	public void setLocation(String location) {
		this.location = location;
	}
	/**
	 * @param checkCourse
	 * @return
	 */
	public boolean conflict(Course checkCourse) {
		// Needs work, just checks professor against time start time and days
		// proof of concept, full Conflict checking WIP
		if (this.getInstructor().equals(checkCourse.getInstructor()) && this.getRowID() != checkCourse.getRowID() && !this.getInstructor().equals("STAFF")) {
			if (timeCheck(checkCourse)) {
				return true;	
			}
		}
		if (this.getLocation().equals(checkCourse.getLocation()) && this.getRowID() != checkCourse.getRowID() && this.getLocation().length() > 2) {
			if (timeCheck(checkCourse)) {
				return true;	
			}
		}
		return false;
	}
	/**
	 * @param checkCourse
	 * @return
	 */
	public boolean timeCheck(Course checkCourse) {
		if (this.getStartTime().equals(checkCourse.getStartTime()) && this.sameDay(checkCourse) && this.dates.equals(checkCourse.getDates()) ||
			this.getEndTime().equals(checkCourse.getEndTime()) && this.sameDay(checkCourse) && this.dates.equals(checkCourse.getDates()) ||
			this.getStartTime().before(checkCourse.getStartTime()) && this.getEndTime().after(checkCourse.getEndTime())  && this.sameDay(checkCourse) && this.dates.equals(checkCourse.getDates()) ||
			this.getEndTime().after(checkCourse.getStartTime()) && this.getStartTime().before(checkCourse.getEndTime())  && this.sameDay(checkCourse) && this.dates.equals(checkCourse.getDates())) {
			return true;
		}
		return false;
	}
	/**
	 * @param checkCourse
	 * @return
	 */
	public boolean sameDay(Course checkCourse) {
		for (int i=0;i<4;i++) {
			if(this.dayArray[i] == checkCourse.getDayArray()[i] && this.dayArray[i] == 1) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * @param checkCourse
	 * @return
	 */
	public boolean graduateLevel(Course checkCourse) {
		if (this.getNbr() % 100 == checkCourse.getNbr() % 100 && (this.getNbr() > 500 || checkCourse.getNbr() > 500)) {
			return true;
		}
		return false;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		return ( rowID + " " + subjectAbbre + nbr + " - " + subject + " -- " + instructor + " -- " + location + " -- " + startTime +" to "+ endTime + " on " + days +  " - " + dates +"\n");
	}
}
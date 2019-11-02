import java.util.*;
import java.lang.*;
import java.io.*;
public class Courses implements Comparable<Courses>,Serializable{
	//all the protected variables that are used 
	protected String coursename;
	protected String courseID; 
	protected int maxnum;
	protected int currentreg;
	protected ArrayList<String> listofnames;
	protected String instructor;
	protected int sectionnum;
	protected String location;
	
	
	//course contructor to store all the course details when making an object
	public Courses(String incoursename, String incourseID, int inmaxnum, int incurrentreg, //csv import constructor
			String inlistofnames, String Ininstructor, int insectionnum, String inlocation){ 
	this.coursename = incoursename;
	this.courseID = incourseID;
	this.maxnum = inmaxnum;
	this.currentreg = incurrentreg;
	this.listofnames = new ArrayList<String>();
	this.instructor = Ininstructor;
	this.sectionnum = insectionnum;
	this.location = inlocation;
		
	}
	
	//getter for the course ID
	public String getCourseID() {
		return courseID;
	}
	
	//setter for the course ID
	public void setCourseID(String inID) {
		courseID = inID;
	}
	
	//getter for the section number
	public int getSectionnum() {
		return sectionnum;
	}
	
	//setter for the section number
	public void setSectionnum(int sectNum) {
		sectionnum = sectNum;
	}
	
	//method to increase the current students registered number by 1
	public void incStudents() {
		this.currentreg = this.currentreg + 1;
	}
	
	//method to decrease the current students registered number by 1
	public void decStudents() {
		this.currentreg = this.currentreg - 1;
	}
	
	//setter for the instructor
	public void setInstruct(String instruct) {
		instructor = instruct;
	}
	
	//getter for the instructor
	public String getInstruct() {
		return instructor;
	}
	
	//setter for the location
	public void setLocation(String inlocation) {
		location = inlocation;
	}
	
	//getter for the location
	public String getLocation() {
		return location;
	}
	
	//setter for the maximum number of students in a course
	public void setMaxNum(int inmaxnum) {
		maxnum = inmaxnum;
	}
	
	//getter for the maximum number of students in a course
	public int getMaxNum() {
		return maxnum;
	}
	
	//setter for the number of current students in a course
	public void setCurrentStud(int incurrent) {
		currentreg = incurrent;
	}
	
	//getter for the number of current students in a course
	public int getCurrentStud() {
		return currentreg;
	}
	
	//setter for the list of names that are in a course
	public void setListofNames(ArrayList<String> innames) {
		listofnames = innames;
	}
	
	//setter for the list of names that are in a course
	public ArrayList<String> getListofNames() {
		return listofnames;
	}
	
	//method that adds the 
	//public void addtoListofNames(String newstudent) {
	//	listofnames.add(newstudent);
	//}
	
	//setter for the course names
	public void setCourseName(String inname) {
		coursename = inname;
	}
	
	//getter for the course name
	public String getCourseName() {
		return coursename;
	}
	
	//the compareTo method that will be used in sorting the arraylist for the courses.
	public int compareTo(Courses c1) {
		if(c1.getCurrentStud() > this.getCurrentStud()) {
			return -1;
		}
		else if(c1.getCurrentStud()< this.getCurrentStud()) {
			return 1;
		}
		else {
		return 0;
		}
	}
	
	

	


	
}

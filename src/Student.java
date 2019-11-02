import java.util.*;
import java.io.*;
public class Student extends User implements StudentSignature, Serializable{
	//since student is extending user and it also needs an ID, it is a protected variable in this class
	protected String studID;
	
	//student constructor to get student objects that include the usernames, passwords, names, and IDs
	public Student(String inuser, String inpass, String infname, String inlname, String instudID) {
		super(inuser,inpass,infname,inlname);
		this.studID = instudID;
	}
	
	//setter for the ID
	public void setID(String inID) {
		studID = inID;
	}
	//getter for the ID
	public String getID() {
		return studID;
	}

	//overrides the User
	//where students register for a class
	public void register() { 
		Scanner enroll = new Scanner(System.in);
		Scanner snum = new Scanner(System.in);
		Scanner addmore = new Scanner(System.in);
		Scanner inputID = new Scanner(System.in);
		Student s=null;
		
		
		//since the student was able to sign it, it is the student ID that is connected with their username and password
		String whatID = this.getID();
		while (true) {
			//finding out what course they want to be enrolled in
			System.out.println("What is the course ID of the class you want to be enrolled in?: ");
			String regist = enroll.nextLine();
			
			System.out.println("What is the section number of the class?: ");
			int sNUM = snum.nextInt();
			
			//if the admin registered them, we look for their name
			for(int i = 0; i < CRSapplication.studList.size(); i++) {
				if(CRSapplication.studList.get(i).getID().equals(whatID)) {
					s = CRSapplication.studList.get(i);
				}
			}
			
			//when we find their name, we look for the course they want to be enrolled in and if it is not full, we enroll them
			for(int j = 0; j < CRSapplication.courseinfo.size(); j++) {
				if(CRSapplication.courseinfo.get(j).getCourseID().equals(regist)) {
					//check if the class is already full
					if(CRSapplication.courseinfo.get(j).getSectionnum() == sNUM){
						if(CRSapplication.courseinfo.get(j).getCurrentStud() < CRSapplication.courseinfo.get(j).getMaxNum()) {
							//checks if you are already registered in the course
							if(CRSapplication.courseinfo.get(j).getListofNames().contains(this.getID())) {
								System.out.println("You are already registered in this course!");
							}
							else {
							CRSapplication.courseinfo.get(j).getListofNames().add(this.getID());
							CRSapplication.courseinfo.get(j).incStudents();
							System.out.println("You have been registered!");
							}
						}
						else {
							//if it is full, we let them know it is full
							System.out.println("Sorry that class is full");
						}
					}
				}
			}
			
			//see if the student wants to register in more classes
			System.out.println("Do you want to register for another class? ");
			String anotherclass = addmore.nextLine();
			if(anotherclass.equals("no")) {
				this.exit();
				break;
			}
		}
	}
	
	//method to withdraw from a course
	public void withdraw() {
		//scanners for user input
		Scanner dropmore = new Scanner(System.in);
		Scanner dropsect = new Scanner(System.in);
		System.out.println("Sorry to see you go! ");
		
		
		while(true) {
		//find out what class they want to drop
		Scanner dropclass = new Scanner(System.in);
		System.out.println("What is the course ID of the class you want to drop? ");
		String dropClassName = dropclass.nextLine();
		
		System.out.println("What is the section number? ");
		int dropSectionNum = dropsect.nextInt();
		
		//find the class they want to drop using the for loop and then if they are in the class remove them. If they are not in that class, no error will be thrown
		for(int j = 0; j < CRSapplication.courseinfo.size(); j ++) {
			if(CRSapplication.courseinfo.get(j).getCourseID().equals(dropClassName)) {
				if(CRSapplication.courseinfo.get(j).getSectionnum() == dropSectionNum) {
				for(int i = 0; i < CRSapplication.courseinfo.get(j).getListofNames().size(); i++) {
					//checks if you were even in that course to begin with
					if(CRSapplication.courseinfo.get(j).getListofNames().contains(this.getID())){
						CRSapplication.courseinfo.get(j).getListofNames().remove(this.getID());
						CRSapplication.courseinfo.get(j).decStudents();
						break;
					}
					else {
						System.out.println("You were not registered in that course!");
					}
			}
			}
		}
		}
		
		//see if the student wants to drop another class
		System.out.println("Do you want to drop another class? ");
		String yesAgain = dropmore.nextLine();
		if(yesAgain.equals("no")) {
			break;
		}
		}
		System.out.println("You have withdrawn from the courses you wanted to leave that you were originally in");
		this.exit();
	}

	
	//exit the program
	public void exit() {
		
		System.out.println("Bye!");
	
		//serilizating the students list with the student information when the program ends
		try {
			FileOutputStream savedinfo = new FileOutputStream("MyUniversityCourses.ser");
			ObjectOutputStream PutSavedInfo = new ObjectOutputStream(savedinfo);
			PutSavedInfo.writeObject(CRSapplication.courseinfo);
			
			FileOutputStream studentInfo = new FileOutputStream("MyUniversityStudents.ser");
			ObjectOutputStream PutSavedStudInfo = new ObjectOutputStream(studentInfo);
			PutSavedStudInfo.writeObject(CRSapplication.studList);
		}
		catch(Exception e9) {
			System.out.println("Did not save on exit");
		}
		
	}
	
	//method to show which courses a student is registered for
	public void registeredCourses() {
		String studentID = this.getID();
		System.out.println("Here are the classes you are registered in: ");
		
		//the for loop goes through the courses and sees if the student is enrolled and if they are, it prints the details of the class
		for(int j = 0; j < CRSapplication.courseinfo.size(); j++) {
			if(CRSapplication.courseinfo.get(j).getListofNames().contains(studentID)){
				System.out.println(CRSapplication.courseinfo.get(j).getCourseName() + " taught by " +
									CRSapplication.courseinfo.get(j).getInstruct() + " in section number " + CRSapplication.courseinfo.get(j).getSectionnum());
			}
		}
		this.exit();
		
	}
	
	//method that shows all the courses
	public void allCourses() {
		
		//for look that goes through the courseinfo list and prints out all the courses.
		for(int j = 0; j < CRSapplication.courseinfo.size();j++) {
			System.out.println(CRSapplication.courseinfo.get(j).getCourseName() + " taught by " + CRSapplication.courseinfo.get(j).getInstruct() +
					": Section #: " + CRSapplication.courseinfo.get(j).getSectionnum() + " held at " + CRSapplication.courseinfo.get(j).getLocation());
		}
		this.exit();
	}
	
	//method that shows the courses that are not full
	public void NoneFullCourses() {
		//goes through the courses and if the max number of students and the current number of students is not equal, it prints the details of the course.
		for(int j = 0; j < CRSapplication.courseinfo.size();j++) {
			if(CRSapplication.courseinfo.get(j).getCurrentStud() < CRSapplication.courseinfo.get(j).getMaxNum()) {
				System.out.println(CRSapplication.courseinfo.get(j).getCourseName() + " taught by " + CRSapplication.courseinfo.get(j).getInstruct() +
						": Section #: " + CRSapplication.courseinfo.get(j).getSectionnum() + " held at " + CRSapplication.courseinfo.get(j).getLocation());
			}
		}
		this.exit();
	}
}

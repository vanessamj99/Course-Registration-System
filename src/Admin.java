import java.util.*;
import java.io.*;
public class Admin extends User implements AdminSignatures,Serializable{
	//All the scanners needed to receive the user input
	Scanner information = new Scanner(System.in);
	Scanner section = new Scanner(System.in);
	Scanner courseid = new Scanner(System.in);
	
	Scanner nameOFCOURSE = new Scanner(System.in);
	Scanner sectionnum = new Scanner(System.in);
	Scanner IDOFCOURSE = new Scanner(System.in);
	Scanner MAXNUM = new Scanner(System.in);
	Scanner CURRENTNUM = new Scanner(System.in);
	Scanner instruct = new Scanner(System.in);
	Scanner locate = new Scanner(System.in);
	Scanner options = new Scanner(System.in);
	
	Scanner changer = new Scanner(System.in);
	
	//All the variables: Strings and ints to assign values or get user input connected with the scanners above.
	String userinformationfname;
	String userinformationlname;
	String userID;
	String passID;
	int sect;
	String id;
	
	String nameofcourse;
	String IDofCourse;
	int maxNumofStudents;
	int numOfCurrent;
	String studsInClass;
	String instructor;
	int sectionNum;
	String location;
	
	
	//Admin Contructor with the username, password, and since it extends the user, it has empty strings to fill in for the first name and last name
	public Admin(String inuser, String inpass) {
		super(inuser,inpass,"","");
	}
	
	//method to delete a course
	public void delete() {
		System.out.println("What course would you like to delete? ");
		System.out.println("Enter the Course ID: ");
		id = courseid.nextLine();
		System.out.println("Enter the section number: ");
		sect = section.nextInt();
		
		//for loop to find the course that needs to be deleted, it will notify you if you course was deleted, if not that means the course and that section number combo did not exist
		for(int i = 0; i < CRSapplication.courseinfo.size(); i++) {
		
			if(CRSapplication.courseinfo.get(i).getCourseID().equals(id)) {
				if(CRSapplication.courseinfo.get(i).getSectionnum() == sect) {
					CRSapplication.courseinfo.remove(i);
					System.out.println("The course has been deleted!");
				}
			}
		}
	}
	
	//method to create a new course
	public void create() {
		System.out.println("What is the Course name?: ");
		nameofcourse = nameOFCOURSE.nextLine();
		
		System.out.println("What is the Course ID?: ");
		IDofCourse = IDOFCOURSE.nextLine();
		
		System.out.println("What the max number of students for this course?: ");
		maxNumofStudents = MAXNUM.nextInt();
		
		numOfCurrent = 0;
		studsInClass = null;
		
		System.out.println("Who is the instructor?: ");
		instructor = instruct.nextLine();
		
		System.out.println("What is the section number?: ");
		sect = sectionnum.nextInt();
		
		
		System.out.println("What is the location of this course?: ");
		location = locate.nextLine();
		
		//creating the new course with all inputed data
		Courses newCourse = new Courses(nameofcourse,IDofCourse,maxNumofStudents,numOfCurrent,studsInClass,instructor,sect,location);
		
		//checks if a course with that id and sect number already exists
		for(int y = 0; y < CRSapplication.courseinfo.size(); y++) {
			if((CRSapplication.courseinfo.get(y).getCourseID().equals(IDofCourse)) && (CRSapplication.courseinfo.get(y).getSectionnum() == sect)) {
				System.out.println("A Course already exists with that course ID and section number! Sorry!");
				break;
			}
			else {
				CRSapplication.courseinfo.add(newCourse);
				System.out.println("Registered!");
				break;
			}
		}
		
		
	}
	

	//method to edit a course
	public void edit() {
		System.out.println("What course would you like to edit? ");
		System.out.println("Enter the course ID: ");
		
		Scanner studentsaddorRemove = new Scanner(System.in);
		Scanner nameOfCourseID = new Scanner(System.in);
		Scanner checkID = new Scanner(System.in);
		
		IDofCourse = IDOFCOURSE.nextLine();
		System.out.println("Enter the section number: ");
		sect = sectionnum.nextInt();
		
		//for loop to find the course you want to edit
		for(int j = 0; j < CRSapplication.courseinfo.size(); j ++) {
			if(CRSapplication.courseinfo.get(j).getCourseID().equals(IDofCourse)) {
				if(CRSapplication.courseinfo.get(j).getSectionnum() == sect) {
					//edit anything besides the course id and name
					
					//the course that you want to edit
					Courses edited = CRSapplication.courseinfo.get(j);
					
					//all the options you can change within a course
					System.out.println("What would you like to edit? Your options are: ");
					System.out.println("1.) Section number ");
					System.out.println("2.) Instructor ");
					System.out.println("3.) Location ");
					System.out.println("4.) Max Number of Students");
					System.out.println("5.) The students in the course ");
					int chose = options.nextInt();
					
					//switch statements in order to run what the admin wants to edit within the course
					switch(chose) {
					case 1:
						//changing section numbers
						System.out.println("What do you want to change it to?: ");
						int changeSect = changer.nextInt();
						edited.setSectionnum(changeSect);
						break;
					case 2:
						//changing instructor names
						System.out.println("What do you want to change it to?: ");
						String changeInstruct = changer.nextLine();
						edited.setInstruct(changeInstruct);
						break;
					case 3: 
						//changing location
						System.out.println("What do you want to change it to?: ");
						String changeLocate = changer.nextLine();
						edited.setLocation(changeLocate);
						break;
					case 4:
						//changing max number of students
						System.out.println("What do you want to change it to?: ");
						int changeMaxNum = changer.nextInt();
						edited.setMaxNum(changeMaxNum);
						break;
					case 5:
						//this edits the students in the course which also increases or decreasing the number of current students in the course
						System.out.println("1.) Add Students");
						System.out.println("2.) Remove Students");
						int changenames = changer.nextInt();
						
						//adds a student to a course and increases the current number of students
						if(changenames == 1) {
				
							System.out.println("What is the student ID? ");
							String findstudent = checkID.nextLine();
							
							for(int u = 0; u < CRSapplication.studList.size(); u++) {
								if(CRSapplication.studList.get(u).getID().contains(findstudent)) {
									
									for(int p = 0; p < CRSapplication.courseinfo.size(); p++) {
										
										if(CRSapplication.courseinfo.get(p).getCourseID().equals(IDofCourse)) {
											if(CRSapplication.courseinfo.get(p).getSectionnum() == sect) {
												CRSapplication.courseinfo.get(p).getListofNames().add(findstudent);
												CRSapplication.courseinfo.get(j).incStudents();
											}
										}
										}
								}
							}
						}
						//removes a student from a course and decreases the current number of students
						else if(changenames == 2) {
							
							System.out.println("What is the student ID? ");
							String findstud = studentsaddorRemove.nextLine();
							
							for(int u = 0; u < CRSapplication.studList.size(); u++) {
								if(CRSapplication.studList.get(u).getID().contains(findstud)) {
									
									for(int p = 0; p < CRSapplication.courseinfo.size(); p++) {
										
										if(CRSapplication.courseinfo.get(p).getCourseID().equals(IDofCourse)) {
											if(CRSapplication.courseinfo.get(p).getSectionnum() == sect) {
												CRSapplication.courseinfo.get(p).getListofNames().remove(findstud);
												CRSapplication.courseinfo.get(j).decStudents();
											}
										}
										}
								}
							}
							
						}
						
						break;
					
					}
				}
			}
	
		}
		System.out.println("The course has been edited!");
		
	}
	
	//method to display info of a given course
	public void display() {
		String c = "";
		System.out.println("What course would you like to display? ");
		System.out.println("Enter the Course ID: ");
		id = courseid.nextLine();
		System.out.println("Enter the section number: ");
		sect = section.nextInt();
		
		for(int j = 0; j < CRSapplication.courseinfo.size(); j ++) {
			if(CRSapplication.courseinfo.get(j).getCourseID().equals(id)) {
				if(CRSapplication.courseinfo.get(j).getSectionnum() == sect) {
					c = CRSapplication.courseinfo.get(j).getCourseName() + " taught by " + CRSapplication.courseinfo.get(j).getInstruct() +
							": Section #: " + CRSapplication.courseinfo.get(j).getSectionnum() + " held at " + CRSapplication.courseinfo.get(j).getLocation() + 
							" with these students " + CRSapplication.courseinfo.get(j).getListofNames();
					
				}
			}
	
		}
		System.out.println(c); 

	}
	
	//overrides from User
	//method to register a student
	public void register() {
		System.out.println("What is the first name of the student? ");
		userinformationfname = information.nextLine();
		
		System.out.println("What is the last name of the student? ");
		userinformationlname = information.nextLine();
		
		System.out.println("What is the username for the student? ");
		userID = information.nextLine();
		
		System.out.println("What is the password for the student? ");
		passID = information.nextLine();
		
		System.out.println("What is the ID of the student? ");
		id = information.nextLine();
		Student mate = new Student(userID,passID,userinformationfname,userinformationlname,id);
		CRSapplication.studList.add(mate);
		System.out.println("The Student was registered! ");
	}
	
	//when the program ends, it is serialized.
	public void exit() {
		System.out.println("Thank you, bye Admin!");
		try {
			FileOutputStream savedinfo = new FileOutputStream("MyUniversityCourses.ser");
			ObjectOutputStream PutSavedInfo = new ObjectOutputStream(savedinfo);
			PutSavedInfo.writeObject(CRSapplication.courseinfo);
			PutSavedInfo.close();
			savedinfo.close();
			
			FileOutputStream studentInfo = new FileOutputStream("MyUniversityStudents.ser");
			ObjectOutputStream PutSavedStudInfo = new ObjectOutputStream(studentInfo);
			PutSavedStudInfo.writeObject(CRSapplication.studList);
			PutSavedStudInfo.close();
			studentInfo.close();
		}
		catch(Exception e9) {
			e9.printStackTrace();
			System.out.println("Did not save on exit");
		}
	}
	//method that displays all the courses that are full
	public void FullCourses() {
		//for loop goes through the courses and see which ones' max number = their current number of students which will indicate that the course is full
			for(int j = 0; j < CRSapplication.courseinfo.size();j++) {
				if(CRSapplication.courseinfo.get(j).getCurrentStud() >= CRSapplication.courseinfo.get(j).getMaxNum()) {
					System.out.println(CRSapplication.courseinfo.get(j).getCourseName() + " taught by " + CRSapplication.courseinfo.get(j).getInstruct() +
							": Section #: " + CRSapplication.courseinfo.get(j).getSectionnum() + " held at " + CRSapplication.courseinfo.get(j).getLocation() + 
							" is full");
				}
			}	
	}
	//method that prints out all the courses
	public void AllCourses() {
		//for loop goes through all the courses and prints out the information
		for(int j = 0; j < CRSapplication.courseinfo.size();j++) {
			System.out.println(CRSapplication.courseinfo.get(j).getCourseName() + " taught by " + CRSapplication.courseinfo.get(j).getInstruct() +
					": Section #: " + CRSapplication.courseinfo.get(j).getSectionnum() + " held at " + CRSapplication.courseinfo.get(j).getLocation() +
					" has these students enrolled " + CRSapplication.courseinfo.get(j).getListofNames() + ". The current amount of students is " +
					CRSapplication.courseinfo.get(j).getCurrentStud() + " and this course can have up to " + CRSapplication.courseinfo.get(j).getMaxNum() +
					" students.");
			System.out.println();
		}
	}
	
	//method that shows which students are registered in a course
	public void registeredStudentsinCourse() {
		ArrayList<String> StudinC = null;
		Scanner courID = new Scanner(System.in);
		Scanner sectNumber = new Scanner(System.in);
		System.out.println("What is the course ID? ");
		String cID = courID.nextLine();
		System.out.println("What is the section number? ");
		int snumber = sectNumber.nextInt();
		//for loop that does through the courses and finds the course you want to see who is registered in it.
		for(int k = 0; k < CRSapplication.courseinfo.size(); k++) {
			if(CRSapplication.courseinfo.get(k).getCourseID().equals(cID)) {
				if(CRSapplication.courseinfo.get(k).getSectionnum() == snumber) {
					StudinC = CRSapplication.courseinfo.get(k).getListofNames();
				}
			}
		}
		System.out.println("The students in this course are: ");
		System.out.println(StudinC);
	}
	
	//method that shows which courses a student is enrolled in
	public void StudentsCourses() {
		System.out.println("What is the Student ID? ");
		Scanner inStudentID = new Scanner(System.in);
		String studentID = inStudentID.nextLine();
		
		System.out.println("Here are the classes the student is registered in: ");
		//for loop that goes through the courses and see if the student you are looking for is in that course
		for(int j = 0; j < CRSapplication.courseinfo.size(); j++) {
			if(CRSapplication.courseinfo.get(j).getListofNames().contains(studentID)) {
				String h = CRSapplication.courseinfo.get(j).getCourseName() + " taught by " + CRSapplication.courseinfo.get(j).getInstruct() + " held at " +
			CRSapplication.courseinfo.get(j).getLocation() + " with the section number of: " + CRSapplication.courseinfo.get(j).getSectionnum();
				System.out.println(h);
			}
		}
	}
	
	//method to sort the course arraylist -> named courseinfo
	public void Sort() {
		
		Collections.sort(CRSapplication.courseinfo);
		this.exit();
		
	}
	//writing the full courses to a file
	public void writetoFile() {
		String p;
		try {
			//creates file
			File ToFile = new File("FullCourses.txt");
			//check if file exists
			if (!ToFile.exists()) {
				//if it does not exist, create file
				ToFile.createNewFile();
			}
			
			//create fileoutputstream object
			FileOutputStream fileinput = new FileOutputStream(ToFile);
			
			//convert string to array of bytes
			byte[] pByte; 
			//for loop that finds out if a course is full
			for(int j = 0; j < CRSapplication.courseinfo.size();j++) {
				if(CRSapplication.courseinfo.get(j).getCurrentStud() == CRSapplication.courseinfo.get(j).getMaxNum()) {
					p = CRSapplication.courseinfo.get(j).getCourseName() + ": Section #:" + CRSapplication.courseinfo.get(j).getSectionnum() + "\n";
					pByte = p.getBytes();
					//writes to the file
					fileinput.write(pByte); 
				}
			}	
			fileinput.close();
			this.exit();

		}
		catch(Exception e) {
			System.out.println("Something went wrong!");
			e.printStackTrace();
		}
	}

	
	
}

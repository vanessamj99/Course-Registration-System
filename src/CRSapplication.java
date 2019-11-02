import java.util.*;
import java.io.*;

public class CRSapplication implements Serializable{
	
	//static lists
	static ArrayList<Student> studList = new ArrayList<Student>();
	static ArrayList<Courses> courseinfo = new ArrayList<Courses>();
	
	
	public static void main(String[] args) throws FileNotFoundException {
		

		//scanners for user input
		Scanner input = new Scanner(System.in);

		Scanner reportsOrManage = new Scanner(System.in);
		
		//Admin object
		Admin ad = new Admin(null, null);
		
		
	
	//finds out if this is their first time reading the file
		try{
		//first it tries to deserialize the file and it it exists, we know this is not the first time the program is ran
		//if this is the first time this is being run, an exception will be thrown and caught by the catch block where serialization and file reading occur.
		FileInputStream savedinfo = new FileInputStream("MyUniversityCourses.ser");
		ObjectInputStream ReadSavedInfo = new ObjectInputStream(savedinfo);
		CRSapplication info = new CRSapplication();
		info.courseinfo = (ArrayList<Courses>) ReadSavedInfo.readObject();
		ReadSavedInfo.close();
		savedinfo.close();
		
		FileInputStream savedStudentInfo = new FileInputStream("MyUniversityStudents.ser");
		ObjectInputStream readSavedStudent = new ObjectInputStream(savedStudentInfo);
		
		info.studList = (ArrayList<Student>) readSavedStudent.readObject();
		readSavedStudent.close();
		savedStudentInfo.close();
		
		}
		catch(Exception e) {
			//reads the .csv file and catches the exception if this is the first time the program is ran because the .ser would not exist yet.
			File info = new File("MyUniversityCourses.csv");
			Scanner reading = new Scanner(info);
			String s = reading.nextLine();
			
			
			String[] arr;
			String[] classes;
			
			//reading the file
			while(reading.hasNextLine()) {
				s = reading.nextLine();
				arr = s.split(",");
				

				Courses classinfo = new Courses(arr[0],arr[1],Integer.parseInt(arr[2]),Integer.parseInt(arr[3]),arr[4],arr[5],Integer.parseInt(arr[6]),arr[7]);

				
				courseinfo.add(classinfo);
	
		}

		}
		

		
		System.out.println("Welcome to the course registration system! ");
		System.out.println("1.) Admin");
		System.out.println("2.) Student");
		
		
		
		
	//finds out if they are an admin or a student
	while(true) {
		System.out.print("Enter the number that corresponds with your choice: ");
		
		int adorstud = input.nextInt();
		
		if (adorstud == 1) {
			while(true) {
				//make sure the admin is the right admin by asking for the username and password
				System.out.println("Enter the username: ");
				String USER = input.next();
				if(USER.equals("Admin")) {
					System.out.println("Enter the password: ");
					String PASS = input.next();
					if(PASS.equals("Admin001")) {
						break;
					}
				}
				
			}
			
			
			
			
			
		//welcome the admin and give them their different choices 
			System.out.println("Welcome Admin!");
			System.out.println();
			while(true) {
				//displaying all the options
				System.out.println("Would you like to do Course Management or See Reports? ");
				System.out.println("1.) Course Management ");
				System.out.println("2.) See Reports ");
				System.out.println("3.) Exit ");
				String ReportsOrManagement = reportsOrManage.nextLine();
				if(ReportsOrManagement.equals("1")) {
					System.out.println("What would you like to do today? Your options are: (Enter the corresponding number) ");
					System.out.println("1.) Create a new Course");
					System.out.println("2.) Delete a Course");
					System.out.println("3.) Edit a Course");
					System.out.println("4.) Display course information");
					System.out.println("5.) Register a Student");
					System.out.println("6.) Exit ");
					int actions = input.nextInt();
					//switch statement to get the admin tasks done
					switch(actions) {
					case 1: 
						ad.create();
						break;
					case 2:
						ad.delete();
						break;
					case 3:
						ad.edit();
						break;
					case 4:
						ad.display();
						break;
					case 5:
						ad.register();
						break;
					case 6:
						ad.exit();
						break;
					}
					if(actions == 6) {
					break;
					}
				}
				//some more of the admin choices to choose from
				if(ReportsOrManagement.equals("2")) {
					System.out.println("What would you like to see today? ");
					System.out.println("1.) View all courses ");
					System.out.println("2.) View all courses that are Full ");
					System.out.println("3.) Write to a file the list of courses that are full ");
					System.out.println("4.) View the names of students registered in a course ");
					System.out.println("5.) View the list of courses a student is registered in");
					System.out.println("6.) Sort");
					System.out.println("7.) Exit ");
					int choicess = input.nextInt();
					switch(choicess) {
					case 1:
						ad.AllCourses();
						break;
					case 2:
						ad.FullCourses();
						break;
					case 3:
						ad.writetoFile();
						break;
					case 4:
						ad.registeredStudentsinCourse();
						break;
					case 5:
						ad.StudentsCourses();
						break;
					case 6:
						ad.Sort();
						break;
					case 7:
						ad.exit();
						break;
					
					}
				}
				else if(ReportsOrManagement.equals("3")) {
					ad.exit();
				}
				else {
					continue;
				}
				break;
			}
			break;
			
		}
		//If the person is a student
		else if (adorstud == 2) {
			//student object
			Student st = null;
			//checks if the student has been registered by the admin
			while(true) {
				
				System.out.println("Enter the username: ");
				String USER = input.next();
				for(int j = 0; j < CRSapplication.studList.size();j++) {
					if(CRSapplication.studList.get(j).getUsername().equals(USER)){
						st = CRSapplication.studList.get(j);
					}
				}
				if(st == null) {
					System.out.println("You are not registered, contact admin");
					continue;
				}
				
					System.out.println("Enter the password: ");
					String PASS = input.next();
					if(PASS.equals(st.getPassword())) {
						break;
					}
				
			}
			
			//welcoming the student and giving them their options to choose from
			System.out.println("Welcome Student! ");
			System.out.println("What would you like to do today? Your options are: (Enter the corresponding number) ");
			System.out.println("1.) View all courses");
			System.out.println("2.) View all courses that are still open");
			System.out.println("3.) Register in a course");
			System.out.println("4.) Withdraw from a course");
			System.out.println("5.) View all courses that you are registered in");
			System.out.println("6.) Exit ");
			int actions = input.nextInt();
			//the different task that the admin will do.
			switch(actions) {
			case 1:
				st.allCourses();
				break;
			case 2:
				st.NoneFullCourses();
				break;
			case 3:
				st.register();
				break;
			case 4:
				st.withdraw();
				break;
			case 5:
				st.registeredCourses();
				break;
			case 6:
				st.exit();
				break;
			}
			break;
		}

		else {
			System.out.println("Invalid choice");
			System.out.println();
		}
	}
			
		
		
	}



}

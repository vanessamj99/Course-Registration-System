
public interface AdminSignatures {
	
	//method to delete a course
		public void delete();
		
		//method to create a new course
		public void create();
		
		//method to edit a course
		public void edit();
		
		//method to display info of a given course
		public void display();
		
		
		//method to register a student
		public void register();
		
		//may not need this method
		public void exit();
		
		//method to display all courses
		public void AllCourses();
		
		//method that displays the students registered in a course
		public void registeredStudentsinCourse();
		
		//method that displays all the courses a specific student is enrolled in
		public void StudentsCourses();
		
		
		//method that sorts the arraylist of courses by number of current students enrolled
		public void Sort();
		
		//method that shows all the courses that are full
		public void FullCourses();
		
		public void writetoFile();
		
}

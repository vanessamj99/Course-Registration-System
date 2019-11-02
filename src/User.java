import java.util.*;
import java.io.*;
public class User implements Serializable{

	//variables that will be used for students and the admin
	protected String username;
	protected String password;
	protected String fname;
	protected String lname;
	
	//constructor for the user class
	public User(String username,String password,String fname,String lname) {
		this.username = username;
		this.password = password;
		this.fname = fname;
		this.lname = lname;
	}
	
	//method to get the username
	public String getUsername() {
		return username;
	}
	
	//method to set the username
	public void setUsername(String inuser) {
		username = inuser;
	}
	
	//method to get the password
	public String getPassword() {
		return password;
	}
	
	//method to set the password
	public void setPassword(String inpass) {
		password = inpass;
	}
	
	//method to get the first name
	public String getFname() {
		return fname;
	}
	
	//method to set the first name
	public void setFname(String infname) {
		fname = infname;
	}
	
	//method to get the last name
	public String getLname() {
		return lname;
	}
	
	//method to set the last name
	public void setLname(String inlname) {
		lname = inlname;
	}
	
	//method to register in a course
	public void register() {
		
	}
	
	//method to exit the system
	public void exit() {
		
	}
}

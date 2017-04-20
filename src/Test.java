import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;
public class Test { //Student(String username, String password, String firstName, String lastName) throws FileNotFoundException {
	public static Scanner input = new Scanner(System.in);
public static School NYU = new School();
public static void main(String[] args) throws IOException{ 
	System.out.println("Hello. Are you a Student or an Admin. Be honest. Enter 'Student' or 'Admin' " );
	String choice = input.nextLine(); //this is just a test case to see who's using it
	
if(choice.equals("Student")){
	File f = new File("Student.ser");
	if(f.exists() && !f.isDirectory()) { 
	    System.out.println("Welcome back matty!");
	   deserializeStudent();
	}
	else{
StudentInterface matt = new Student("md3460", "hellobuddy", "Matthew", "Davis"); studentMenu(matt);} }
//these if statements better be closed now





 if(choice.equals("Admin")){
	File k = new File("Admin.ser"); //this deserializes the admin IF the .ser file exists. it's the same as up top 
	if(k.exists() && k.isDirectory()){
	 deserializeAdmin();}
	else{
AdminInterface nikky = new Admin("Nikhil","Dhawan"); adminMenu(nikky);} }  //this will just make a new object if the .ser file doesn't exist 


}



public static void menu(StudentInterface matt, AdminInterface nikky) throws IOException{ //i didn't get to use this method because the serialization wasn't cooperating
	System.out.println("Hello. Are you a Student or an Admin. Be honest. Enter 'Student' or 'Admin' " );
	String choice = input.nextLine();
	if(choice.equals("Admin")){
		adminMenu(nikky);
	}
	if(choice.equals("Student")){
		studentMenu(matt);
	}
}
public static void adminMenu(AdminInterface nikky) throws IOException{ //the admin menu, which throws the io exception
	System.out.println("Hello. Please enter your password");
	String guess = input.nextLine(); //the scanner interprets what the user enters and does that method
	if(guess.equals(nikky.getPassword())){
		while(true){
		System.out.println("Welcome admin!. Please make a selection"
				+ "\n 1. Create a new course. \n 2. Delete a course. \n 3. Edit a course."
				+ "\n 4. Display informatrion for a course \n 5. Register a student \n 6. View all courses"
				+ "\n 7. View full courses \n 8. Write to file full courses \n 9. View names of student by course \n 10. View courses by student \n 11. Sort courses \n 12. Exit");
		
		String choice = input.next();
		if(choice.equals("1")){
			nikky.createCourse();
		}
		if(choice.equals("2")){
			nikky.deleteCourse();
		}
		if(choice.equals("3")){
			nikky.editCourse();
		}
		if(choice.equals("4")){
			nikky.viewCourseInfo();
		}
		if(choice.equals("5")){ 
			nikky.registerStudent();
		}
		if(choice.equals("6")){
			nikky.viewCourses();
		}
		if(choice.equals("7")){
			nikky.viewFullCourses();
		}
		if(choice.equals("8")){
			nikky.writeFullCourses();
			}
		if(choice.equals("9")){
			nikky.viewStudentNames();
		}
		if(choice.equals("10")){
			nikky.viewStudentCourses();
		}
		if(choice.equals("11")){
			System.out.println("Courses sorted.");
			nikky.sortCourses();
		}
		if(choice.equals("12")){System.out.println("Goodbye!"); //when you exit, it serializes the SCHOOL object AND the Admin object. it works the same for the user.
		try {
			//FileOutput Stream writes data to a file
			FileOutputStream fos = new FileOutputStream("Admin.ser");
			FileOutputStream school = new FileOutputStream("School.ser");

			//ObjectOutputStream writes objects to a stream (A sequence of data)
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			ObjectOutputStream schoolOOS = new ObjectOutputStream(school);

			//Writes the specific object to the OOS
			oos.writeObject(nikky);
			schoolOOS.writeObject(nikky.getNYU());
			//Close both streams
			oos.close();
			schoolOOS.close();
			school.close();
			fos.close();
			System.out.println("Serialization complete");
		} 
		catch (IOException ioe) {
			ioe.printStackTrace();
		}
		//matt.serializeSchool();
		return ;
	}
}}}
public static void studentMenu(StudentInterface matt){ //this works the same as the Admin menu
	System.out.println("Hello! Please enter your password. //For the sake of testing the password is 'hellobuddy'" );
	if(input.nextLine().equals("hellobuddy")){
	while(true){
	System.out.println("Hello sweetie. Please make a selection \n 1. View all courses. \n 2. View all courses that aren't full. \n 3. Register for a course. \n 4. Withdraw from a course. \n 5. View your courses \n 6. Exit. ");
	String choice = input.nextLine();
	if(choice.equals("1")){
		matt.viewAllCourses();
	}
	if(choice.equals("2")){
		matt.viewAvailableCourses();
	}
	if(choice.equals("3")){
		matt.register();
	}
	if(choice.equals("4")){
		matt.withdraw();
	}
	if(choice.equals("5")){
		matt.viewRegisteredCourses();
	}
	if(choice.equals("6")){
		System.out.println("Goodbye!");
		try {
			//FileOutput Stream writes data to a file
			FileOutputStream fos = new FileOutputStream("Student.ser");
			FileOutputStream school = new FileOutputStream("School.ser");

			//ObjectOutputStream writes objects to a stream (A sequence of data)
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			ObjectOutputStream schoolOOS = new ObjectOutputStream(school);

			//Writes the specific object to the OOS
			oos.writeObject(matt);
			schoolOOS.writeObject(matt.getNYU());
			//Close both streams
			oos.close();
			schoolOOS.close();
			school.close();
			fos.close();
			System.out.println("Serialization complete");
		} 
		catch (IOException ioe) {
			ioe.printStackTrace();
		}
		//matt.serializeSchool();
		return ;
	}
	}

}
}

public static void deserializeStudent(){
	StudentInterface matt = null;
	 try{
		  //FileInputSystem recieves bytes from a file
	      FileInputStream fis = new FileInputStream("Student.ser");
	      
	      //ObjectInputStream does the deserialization-- it reconstructs the data into an object
	      ObjectInputStream ois = new ObjectInputStream(fis);
	      
	      //Cast as Employee. readObject will take the object from ObjectInputStream
	      matt = (StudentInterface)ois.readObject();
	      ois.close();
	      fis.close();
	    }
	    catch(IOException ioe) {
	       ioe.printStackTrace();
	       return;
	    }
	 catch(ClassNotFoundException cnfe) {
	       cnfe.printStackTrace();
	       return;
	     } 
	 School temp = null;
	 try{
		  //FileInputSystem recieves bytes from a file
	      FileInputStream fis = new FileInputStream("School.ser");
	      
	      //ObjectInputStream does the deserialization-- it reconstructs the data into an object
	      ObjectInputStream ois = new ObjectInputStream(fis);
	      
	      //Cast as Employee. readObject will take the object from ObjectInputStream
	      temp = (School)ois.readObject();
	      matt.setNYU(temp);
	      ois.close();
	      fis.close();
	    }
	    catch(IOException ioe) {
	       ioe.printStackTrace();
	       return;
	    }
	 catch(ClassNotFoundException cnfe) {
	       cnfe.printStackTrace();
	       return;
	     } 
	studentMenu(matt);
}
public static void deserializeAdmin() throws IOException{ //the deserialization files needed when the program is first run
	 System.out.println("Welcome back admin");
	AdminInterface nikky = null;
	 try{
		  //FileInputSystem recieves bytes from a file
	      FileInputStream fis = new FileInputStream("Admin.ser");
	      
	      //ObjectInputStream does the deserialization-- it reconstructs the data into an object
	      ObjectInputStream ois = new ObjectInputStream(fis);
	      
	      //Cast as Student. readObject will take the object from ObjectInputStream
	      nikky = (AdminInterface)ois.readObject();
	      ois.close();
	      fis.close();
	    }
	    catch(IOException ioe) {
	       ioe.printStackTrace();
	       System.out.println("something went wrong");
	       return;
	    }
	 catch(ClassNotFoundException cnfe) {
	       System.out.println("something went wrong");
	       cnfe.printStackTrace();
	       return;
	     } 
	 School temp = null;
	 try{
		  //FileInputSystem recieves bytes from a file
	      FileInputStream fis = new FileInputStream("School.ser");
	      
	      //ObjectInputStream does the deserialization-- it reconstructs the data into an object
	      ObjectInputStream ois = new ObjectInputStream(fis);
	      
	      //Cast as Employee. readObject will take the object from ObjectInputStream
	      temp = (School)ois.readObject();
	      nikky.setNYU(temp);
	      ois.close();
	      fis.close();
	    }
	    catch(IOException ioe) {
	       ioe.printStackTrace();
	       return;
	    }
	 catch(ClassNotFoundException cnfe) {
	       cnfe.printStackTrace();
	       return;
	     } 
	 adminMenu(nikky);
}
}
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Collections;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
public class Admin extends User implements AdminInterface, java.io.Serializable {
public static Scanner input = new Scanner(System.in);
private String username;
private String password;
private String path; 
private boolean append_to_file = false; //this is used to write the full courses to a text file
	Admin(String firstName, String lastName) throws FileNotFoundException { //the constructor works like the User 
		super("Admin", "Admin001", firstName, lastName);
		this.username ="Admin";
		this.password = "Admin001";
		//NYU.loadList();
	}
/* (non-Javadoc)
 * @see AdminInterface#viewCourses()
 */
@Override
public void viewCourses(){ //this loops through the course list and prints every course name and ID and section number
	System.out.println("All courses: ");
	for(Course x : NYU.getCourseList()){
		System.out.println(x.getCourseName() + " " + x.getCourseID() + " " + x.getSectionNumber()); //we don't need all of the information do we?
	}
}
/* (non-Javadoc)
 * @see AdminInterface#viewFullCourses()
 */

public void writeFullCourses() throws IOException{ //this writes to a text file the full courses
	String fileName = "fullCourses.txt";
	Scanner scan = new Scanner(System.in);
	
	try{
		FileWriter fileWriter = new FileWriter(fileName);
		BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
		String text = scan.nextLine();
		bufferedWriter.write(text);
		for(Course x : NYU.getCourseList()){
			if(x.getCurrentStudents() == x.getMaxStudents()){
			bufferedWriter.write(x.getCourseName() + " " + x.getCourseID());
			bufferedWriter.newLine();}}
		

		
//Always close writer
		bufferedWriter.close();
	}

	//Always close files

	catch (IOException exk) {
		System.out.println( "Error writing file '" + fileName + "'");
		exk.printStackTrace();
	}
}
public void viewFullCourses(){
	System.out.println("Full courses: "); //this prints out the name and ID of a full course. also the section number
	for(Course x : NYU.getCourseList()){
		if(x.getCurrentStudents() == x.getMaxStudents()){
			System.out.println(x.getCourseName() + " " + x.getCourseID() + " " + x.getSectionNumber()); //we don't need all of the information do we?
		}
	}
}
/* (non-Javadoc)
 * @see AdminInterface#viewStudentNames()
 */
@Override
public void viewStudentNames(){
	System.out.println("Enter the ID for the course you want to see the roster of: "); //this gives the names of every student in a given course
	String tempCourse = input.nextLine();
	for(Course x : NYU.getCourseList()){
		if(x.getCourseID().equals(tempCourse)){
			for(Student y : x.getRegisteredStudents()){
				System.out.println(y.getFirstName() + " " + y.getLastName());
			}
		}
	}
}
/* (non-Javadoc)
 * @see AdminInterface#viewCourseInfo()
 */
@Override
public void serialize(){ //this was supposed to be the serialization method but i didn't end up using it
	try {
		//FileOutput Stream writes data to a file
		FileOutputStream fos = new FileOutputStream("Admin.ser");
		//ObjectOutputStream writes objects to a stream (A sequence of data)
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		
		//Writes the specific object to the OOS
		oos.writeObject(this);
		
		//Close both streams
		oos.close();
		fos.close();
		System.out.println("Serialization complete");
	} 
	catch (IOException ioe) {
		ioe.printStackTrace();
	} }
@Override
public void viewCourseInfo(){ //this prints out everything about a course
	System.out.println("Enter the ID of the course you want to view: ");
	String newID = input.nextLine();
	System.out.println("Enter the section number: ");
	String newSectionNum = input.nextLine();
	for(Course x : NYU.getCourseList()){
		if(x.getCourseID().equals(newID) && x.getSectionNumber().equals(newSectionNum)){
		System.out.println(x.getCourseName() + " " + x.getCourseID() + " Instructor: " +x.getProfessorName() + " \n Maximum students: " +x.getMaxStudents() + " Current Students: " +x.getCurrentStudents() + " \n Location: " + x.getCourseLocation()) ; //we don't need all of the information do we?
		return ;
		}
	}
	System.out.println("Course not found. Try again.");
}
/* (non-Javadoc)
 * @see AdminInterface#viewStudentCourses()
 */
@Override
public void viewStudentCourses(){ //this goes through the list of students and prints out the courses for the entered student
	System.out.println("Enter the student's first name: ");
	String firstTemp = input.nextLine();
	System.out.println("Enter the Student's last name: ");
	String lastTemp = input.nextLine();
	for(Student x : NYU.getStudentList()){
		if(firstTemp.equals(x.getFirstName()) && lastTemp.equals(x.getLastName())){
			System.out.println(x.firstName + " " + x.getLastName() + "'s courses: ");
			x.viewRegisteredCourses();
			return ;
		}
	}
	System.out.println("Student name could not be found. Try again.");
}
/* (non-Javadoc)
 * @see AdminInterface#sortCourses()
 */
@Override
public void sortCourses(){ //this uses a Collections comparator to sort the courses
	Collections.sort(
            NYU.getCourseList(),
            (course1, course2) -> course1.getCurrentStudents()
                    - course2.getCurrentStudents()); //sort
}
/* (non-Javadoc)
 * @see AdminInterface#createCourse()
 */
@Override
public void createCourse(){ 
	Course newCourse = new Course(); //first a new Course is created using the empty constructor
	System.out.println("Enter the ID of the course you would like to create: "); //then the setters are used to define attributes about the course
	newCourse.setCourseID(input.nextLine()); //the empty constructor creates a list of students for the course, the setters do the rest
		System.out.println("Enter the new name of the course: "); 
			newCourse.setCourseName(input.nextLine());
			System.out.println("Enter the new instructor for the course: ");
			newCourse.setProfessorName(input.nextLine());
			System.out.println("Enter the new location for the course: ");
			newCourse.setCourseLocation(input.nextLine());
			System.out.println("Enter the new section number: ");
			newCourse.setSectionNumber(input.nextLine());
			System.out.println("Enter the new max students: ");
			newCourse.setMaxStudents(input.nextInt());
			NYU.getCourseList().add(newCourse);
			System.out.println("Changes saved.");
		}
/* (non-Javadoc)
 * @see AdminInterface#editCourse()
 */
@Override
public void editCourse(){ //this finds the course you entered by name 
	System.out.println("Enter the name of the course you would like to edit: ");
	String tempCourse = input.nextLine();
	for(Course x : NYU.getCourseList()){
		if(tempCourse.equals(x.getCourseName())){
			System.out.println("Enter the new name of the course: "); //i'm not letting them change the ID of the course, I feel like that shouldn't change
			x.setCourseName(input.nextLine());
			System.out.println("Enter the new instructor for the course: ");
			x.setProfessorName(input.nextLine());
			System.out.println("Enter the new location for the course: ");
			x.setCourseLocation(input.nextLine());
			System.out.println("Enter the new section number: ");
			x.setSectionNumber(input.nextLine());
			System.out.println("Enter the new max students: ");
			x.setMaxStudents(input.nextInt());
			System.out.println("Changes saved.");
			return ;
		}
	}
	System.out.println("Course not found. Try again.");
}
/* (non-Javadoc)
 * @see AdminInterface#registerStudent()
 */
@Override
public void registerStudent(){ //this creates a new student object and lets the admin define his/her attributes, then adds it to the student list
	Student newStudent = new Student();
	System.out.println("Enter the new student's first name: ");
	newStudent.setFirstName(input.nextLine());
	System.out.println("Enter the new student's last name: ");
	newStudent.setLastName(input.nextLine());
	System.out.println("Enter new username: ");
	newStudent.setUsername(input.nextLine());
	System.out.println("Enter new password: ");
	newStudent.setPassword(input.nextLine());
	NYU.addStudent(newStudent);
	System.out.println("Student added successfully.");
}
/* (non-Javadoc)
 * @see AdminInterface#deleteCourse()
 */
@Override
public void deleteCourse(){ //this finds a course by ID and section number and deletes it
	System.out.println("Enter the ID of the course you would like to delete. ");
	String courseID = input.nextLine();
	System.out.println("Enter the section number of the course you would like to delete: ");
	String tempSectionNumber = input.nextLine();
	//first i'm going to remove it from every student and then i'm going to remove it from the courseList that nyu has
	for(StudentInterface x : NYU.getStudentList()){
		for(Course y : x.getStudentCourses()){
			if(y.getCourseID().equals(courseID)){
				x.getStudentCourses().remove(y); //the course is now removed from the STUDENT's SCHEDULE
			}
		}
	}
	for(Course x: NYU.getCourseList()){
		if(x.getCourseID().equals(courseID) && x.getSectionNumber().equals(tempSectionNumber)){
			NYU.getCourseList().remove(x); //removes it from x
			System.out.println("Course deleted.");
			return ;
		}
	}
	System.out.println("Something went wrong.");
}
	/* (non-Javadoc)
	 * @see AdminInterface#getUsername()
	 */
	@Override
	public String getUsername() { //getters and setters down here
		return username;
	}
	/* (non-Javadoc)
	 * @see AdminInterface#setUsername(java.lang.String)
	 */
	@Override
	public void setUsername(String username) {
		this.username = username;
	}
	/* (non-Javadoc)
	 * @see AdminInterface#getPassword()
	 */
	@Override
	public String getPassword() {
		return password;
	}
	/* (non-Javadoc)
	 * @see AdminInterface#setPassword(java.lang.String)
	 */
	@Override
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public void serializeSchool(){
		super.serializeSchool();
	}
}

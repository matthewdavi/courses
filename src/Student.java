import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.Serializable;
public class Student extends User implements StudentInterface, java.io.Serializable{
public static Scanner input = new Scanner(System.in);
private ArrayList<Course> studentCourses = new ArrayList<Course>();
Student(){
super(); //the empty constructor is just the empty course constructor
}
Student(String username, String password, String firstName, String lastName) throws FileNotFoundException {
		super(username, password, firstName, lastName);
		//NYU.loadList();
		NYU.getStudentList().add(this); //this adds the new student to the School's ArrayList of students
	}
public void serialize(){
	try {
		//FileOutput Stream writes data to a file
		FileOutputStream fos = new FileOutputStream("Student.ser");
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
/* (non-Javadoc)
 * @see StudentInterface#getStudentCourses()
 */
@Override
public ArrayList<Course> getStudentCourses() {
	return this.studentCourses;
}
/* (non-Javadoc)
 * @see StudentInterface#setStudentCourses(java.util.ArrayList)
 */
@Override
public void setStudentCourses(ArrayList<Course> studentCourses) {
	this.studentCourses = studentCourses;
}
/* (non-Javadoc)
 * @see StudentInterface#register()
 */
@Override
public void register(){
	System.out.println("Enter the course ID of the course you would like to add to your schedule" );
	String tempCourse = input.nextLine();
	System.out.println("Enter the section number: ");
	String sectionNum = input.nextLine();
	for(Course x : NYU.getCourseList()){
		if(tempCourse.equals(x.getCourseID()) && x.getSectionNumber().equals(sectionNum)){
			studentCourses.add(x);
			System.out.println("Course added.");
			x.addStudent(this);
			return;
		}
	}
	System.out.println("Course not found. Try again");
}
/* (non-Javadoc)
 * @see StudentInterface#viewAllCourses()
 */
@Override
public void viewAllCourses(){
	System.out.println("All courses: ");
	for(Course x: this.getNYU().getCourseList()){
		System.out.println(x.getCourseName() + " " + x.getCourseID() + " " + x.getSectionNumber());
	}
}
public School getNYU() {
return this.NYU;
}
/* (non-Javadoc)
 * @see StudentInterface#viewAvailableCourses()
 */
@Override
public void viewAvailableCourses(){
	System.out.println("Available courses: ");
	for(Course x: NYU.courseList){
		if(x.getMaxStudents() != x.getCurrentStudents()){
			System.out.println(x.getCourseName() + " " + x.getCourseID() + " "  + x.getSectionNumber() );
		}
	}
}
/* (non-Javadoc)
 * @see StudentInterface#viewRegisteredCourses()
 */
@Override
public void viewRegisteredCourses(){
	for(Course x: this.studentCourses){
		System.out.println(x.getCourseName() + " " + x.getCourseID() + " " + x.getSectionNumber());
	}
}
/* (non-Javadoc)
 * @see StudentInterface#withdraw()
 */
public void setNYU(School newSchool){
	super.setNYU(newSchool);
}
@Override
public void withdraw(){
	System.out.println("Enter the ID of the course you would like to drop");
	String tempCourse = input.nextLine();
	for(Course x: studentCourses){
		if(tempCourse.equals(x.getCourseID())){
			studentCourses.remove(x);
			System.out.println("Course removed.");
			x.removeStudent(this);}
		
			for(Course y : NYU.getCourseList()){
				if(tempCourse.equals(y.getCourseID())){
				y.getRegisteredStudents().remove(this);
			}
			return ;
	
	} }
	System.out.println("Course not found. Try again");
}
@Override
public void serializeSchool(){
	super.serializeSchool();
}
}

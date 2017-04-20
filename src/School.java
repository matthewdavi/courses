import java.util.ArrayList;
import java.io.Serializable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Scanner;
import java.io.Serializable;
public class School implements java.io.Serializable{
public ArrayList<Course> courseList = new ArrayList<Course>();
protected ArrayList<Student> studentList = new ArrayList<Student>();
public  ArrayList<Course> getCourseList(){
	return this.courseList;
}
School(){

}
public void loadList() throws FileNotFoundException{ //this reads the CSV file and adds everything to the arrayList.
	
	String inString;
	  Scanner scanner = new Scanner(new File("MyUniversityCourses.csv"));
	  //Course_Name,Course_Id,Maximum_Students,Current_Students,List_Of_Names,Course_Instructor,Course_Section_Number,Course_Location
      scanner.useDelimiter(",");
      while(scanner.hasNext()){
    	  inString = scanner.nextLine();
			String[] nextString = inString.split(",");
			String courseName = nextString[0];
			String courseID = nextString[1];
			int maxStudents = Integer.parseInt(nextString[2]);
			int currentStudents = Integer.parseInt(nextString[3]);
			String professorName = nextString[5];
			String sectionNumber = nextString[6];
			String courseLocation = nextString[7];
			Course newCourse = new Course(courseName, courseID, maxStudents, currentStudents, professorName, sectionNumber, courseLocation);
			courseList.add(newCourse);
         // System.out.print(scanner.next()+"|");
      }
      for(Course x : courseList){
    	// we don't need this anymore //  System.out.println(x.getCourseName() + " " + x.getCourseID());
      }
      scanner.close();
  }
public void setCourseList(ArrayList<Course> courseList) {
	this.courseList = courseList;
}
public void addStudent(Student newStudent){
	studentList.add(newStudent);
}
public ArrayList<Student> getStudentList() {
	return studentList;
}
public void setStudentList(ArrayList<Student> studentList) {
	this.studentList = studentList;
}
public void serialize(){ //this serializes the school
	try {
		//FileOutput Stream writes data to a file
		FileOutputStream fos = new FileOutputStream("School.ser");
		//ObjectOutputStream writes objects to a stream (A sequence of data)
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		
		//Writes the specific object to the OOS
		oos.writeObject(this);
		
		//Close both streams
		oos.close();
		fos.close();
		System.out.println("School serialization complete");
	} 
	catch (IOException ioe) {
		ioe.printStackTrace();
	}
	
	return ;

}
}


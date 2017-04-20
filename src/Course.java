import java.util.ArrayList; 
import java.io.Serializable;
public class Course implements java.io.Serializable{
	
private String professorName;
private String courseName;
private String courseID;
private int maxStudents;
private int currentStudents;
private ArrayList<Student> registeredStudents;
private String courseLocation;
private String sectionNumber;
Course(){
	this.registeredStudents = new ArrayList<Student>(); //this is for when we make a new course later !
}
//Course_Name,Course_Id,Maximum_Students,Current_Students,List_Of_Names,Course_Instructor,Course_Section_Number,Course_Location
public Course(String courseName, String courseID, int maxStudents, int currentStudents, String professorName, String sectionNumber, String courseLocation) {
	this.professorName = professorName;
	this.courseName = courseName;
	this.courseID = courseID;
	this.maxStudents = maxStudents;
	this.currentStudents = currentStudents;
	this.courseLocation = courseLocation;
	this.sectionNumber = sectionNumber;
	this.registeredStudents = new ArrayList<Student>();

}

public String getProfessorName() {
	return professorName;
}
public void setProfessorName(String professorName) {
	this.professorName = professorName;
}
public String getCourseName() {
	return courseName;
}
public void setCourseName(String courseName) {
	this.courseName = courseName;
}
public String getCourseID() {
	return courseID;
}
public void setCourseID(String courseID) {
	this.courseID = courseID;
}
public int getMaxStudents() {
	return maxStudents;
}
public void setMaxStudents(int maxStudents) {
	this.maxStudents = maxStudents;
}
public ArrayList<Student> getRegisteredStudents() {
	return this.registeredStudents;
}
public void setRegisteredStudents(ArrayList<Student> registeredStudents) {
	this.registeredStudents = registeredStudents;
}
public String getCourseLocation() {
	return courseLocation;
}
public void setCourseLocation(String courseLocation) {
	this.courseLocation = courseLocation;
}
public String getSectionNumber() {
	return sectionNumber;
}
public void setSectionNumber(String sectionNumber) {
	this.sectionNumber = sectionNumber;
}
public void addStudent(Student newStudent){ //this adds a student and increments the number of students
	this.registeredStudents.add(newStudent);
	this.currentStudents++;
}
public void removeStudent(StudentInterface newStudent){ //this removes a student and decrements the number of students
	registeredStudents.remove(newStudent);
	this.currentStudents = this.currentStudents - 1;
}
public int getCurrentStudents() {
	return currentStudents;
}
public void setCurrentStudents(int currentStudents) {
	this.currentStudents = currentStudents;
}
}

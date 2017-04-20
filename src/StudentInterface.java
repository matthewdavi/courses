import java.util.ArrayList;

public interface StudentInterface  {

	ArrayList<Course> getStudentCourses();

	void setStudentCourses(ArrayList<Course> studentCourses);

	void register();

	void viewAllCourses();

	void viewAvailableCourses();

	void viewRegisteredCourses();

	void withdraw();

	void serialize();
	void serializeSchool();
	School getNYU();

	void setNYU(School temp); 
} //this interface is a disaster! i get errors every 2 minutes while i'm debugging
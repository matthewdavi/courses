import java.io.IOException;

public interface AdminInterface {

	void viewCourses();

	void viewFullCourses();

	void viewStudentNames();

	void viewCourseInfo();

	void viewStudentCourses();

	void sortCourses();

	void createCourse();

	void editCourse();

	void registerStudent();

	void deleteCourse();

	String getUsername();

	void setUsername(String username);

	String getPassword();

	void setPassword(String password);

	void writeFullCourses() throws IOException;
	void serialize();
	void serializeSchool();
	School getNYU();
	void setNYU(School temp); 


}
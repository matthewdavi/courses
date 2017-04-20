import java.io.FileNotFoundException;
import java.io.Serializable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
public class User implements java.io.Serializable{
protected String username;
protected String password;
protected String firstName;
protected String lastName;
protected School NYU = new School();
User(){
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
User(String username, String password, String firstName, String lastName) throws FileNotFoundException{
	this.username = username;
	this.password = password; //the constructor sets username password 
	this.firstName = firstName;
	this.lastName = lastName;
	
	File f = new File("School.ser"); //if the School.ser file exists, set the NYU member variable to this
	if(f.exists() && !f.isDirectory()) { 
		this.NYU = deserializeSchool();
	}
	else{
		this.NYU = new School();
		NYU.loadList();
	}
}
public String getFirstName() {
	return firstName;
}
public void setFirstName(String firstName) {
	this.firstName = firstName;
}
public String getLastName() {
	return lastName;
}
public void setLastName(String lastName) {
	this.lastName = lastName;
}
public School deserializeSchool() throws FileNotFoundException{ //we don't end up using this
	School NYU = null;
	 try{
		  //FileInputSystem recieves bytes from a file
	      FileInputStream fis = new FileInputStream("School.ser");
	      
	      //ObjectInputStream does the deserialization-- it reconstructs the data into an object
	      ObjectInputStream ois = new ObjectInputStream(fis);
	      
	      //Cast as Employee. readObject will take the object from ObjectInputStream
	      NYU = (School)ois.readObject();
	      ois.close();
	      fis.close();
	    }
	    catch(IOException ioe) {
	       ioe.printStackTrace();
	       return null;
	    }
	 catch(ClassNotFoundException cnfe) {
	       cnfe.printStackTrace();
	       return null;
	     } 
	// NYU.loadList();
	 return NYU;
	// studentMenu(matt);
}
public void serializeSchool(){
	NYU.serialize();
}
public School getNYU(){
	return this.NYU;
}
public void setNYU(School newSchool){ //newSchool not The New School
	this.NYU = newSchool;
}
}

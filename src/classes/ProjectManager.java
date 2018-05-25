package classes;

import java.io.Serializable;

public class ProjectManager extends User{
	
	
	private String speciality;

	public ProjectManager(String username, String password, String firstname, String lastname, double salary,String speciality) {
		super(username, password, firstname, lastname, salary,speciality);
	}

}

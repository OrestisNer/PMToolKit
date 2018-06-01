package classes;

import java.io.Serializable;

public class ProjectManager extends User{
	
	
	private String specialty;

	public ProjectManager(String username, String password, String firstname, String lastname, double salary,String specialty) {
		super(username, password, firstname, lastname, salary,specialty);
	}

}

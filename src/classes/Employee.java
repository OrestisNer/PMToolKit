package classes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class Employee extends User  {
	
	private String speciality;
	private String evaluation;
	private LocalDate employmentDate;
	private double rateOfEmployment;
	private double manmonthsEmployment;
	
	
	public Employee(String username, String password, String firstname, String lastname, String speciality, double salary){
		super(username,password,firstname,lastname, salary,speciality);		
	}
	
	
}

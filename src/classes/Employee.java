package classes;

import java.time.LocalDate;

public class Employee extends User  {
	
	private String speciality;
	private String evaluation;
	private String grade;
	private LocalDate employmentDate;
	private double rateOfEmployment;
	private double manmonthsEmployment;
	
	
	public Employee(String username, String password, String firstname, String lastname, String speciality, double salary){
		super(username,password,firstname,lastname, salary,speciality);		
	}
	
	public void setEvaluation(String evaluation) {
		this.evaluation = evaluation;
	}
	
	public void setGrade(String grade) {
		this.grade = grade;
	}
}
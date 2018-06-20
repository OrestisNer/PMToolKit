package classes;

import java.time.LocalDate;

public class Employee extends User  {
	
	private String evaluation;
	private String grade;
	//private LocalDate employmentDate;
	private double employmentTime;
	private double rateOfEmployment;
	private double manmonths;
	private double costPerEmployee;
	
	public Employee(String username, String password, String firstname, String lastname, double salary,String speciality){
		super(username,password,firstname,lastname, salary,speciality);	
		evaluation = "";
	}
	
	public double getCostPerEmployee() {
		return costPerEmployee;
	}
	
	public double getEmploymentTime() {
		return employmentTime;
	}

	public double getRateOfEmployment() {
		return rateOfEmployment;
	}

	public void setEmploymentTime(double empTime) {
		employmentTime = empTime;
	}
	
	public void setRateOfEmployment(double rate) {
		rateOfEmployment = rate;
	}
	
	public void setManmonths(double manmonths) {
		this.manmonths = manmonths;
	}
	
	public double getManmonths() {
		return manmonths;
	}
	
	public void calcManmonths() {
		setManmonths(this.employmentTime * this.rateOfEmployment);
	}
	
	public void calcCostPerEmployee() {
		setCostPerEmployee(manmonths*salary);
	}
	
	public void setCostPerEmployee(double costperemp) {
		costPerEmployee = costperemp;
	}
	
	public String getEvaluation() {
		return evaluation;
	}
	
	public String getGrade() {
		return grade;
	}
	
	public void setEvaluation(String evaluation) {
		this.evaluation = evaluation;
	}
	
	public void setGrade(String grade) {
		this.grade = grade;
	}
}

package classes;

import java.time.LocalDate;

public class Employee extends User  {
	
	private String evaluation;
	private String grade;
	private LocalDate employmentDate;
	private String employmentTime;
	private String rateOfEmployment;
	private double manmonths;
	private double costPerEmployee;
	
	public Employee(String username, String password, String firstname, String lastname, double salary,String speciality){
		super(username,password,firstname,lastname, salary,speciality);	
		this.employmentDate = LocalDate.now();
		evaluation = "";
	}
	
	public double getCostPerEmployee() {
		return costPerEmployee;
	}
	
	public String getEmploymentTime() {
		return employmentTime;
	}

	public String getRateOfEmployment() {
		return rateOfEmployment;
	}

	public void setEmploymentTime(String empTime) {
		employmentTime = empTime;
	}

	public void setRateOfEmployment(String rate) {
		rateOfEmployment = rate;
	}
	
	public void setManmonths(double manmonths) {
		this.manmonths = manmonths;
	}
	
	public double getManmonths() {
		return manmonths;
	}
	
	public double calcManmonths() {
		return Double.parseDouble(this.employmentTime) * Double.parseDouble(this.rateOfEmployment);
	}
	
	public double calcCostPerEmployee() {
		return manmonths*salary;
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

package classes;

import java.io.Serializable;

public class Cost implements Serializable {
	
	private double directCost;
	private double indirectCost;
	private double softwareCost;
	private double equipmentCost;
	private double totalCost; 
	private double effort;
	private double duration;
	private double amountOfEmployees;
	private final double a;
	private final double b;
	private final double c;
	private final Project project;
	
	public Cost(Project project){
		this.project=project;
		System.out.println(project.getName());
		System.out.println(project.getKloc());
		a=2.4;
		b=1.05;
		c=0.38;
		
		calculateEfort();
		calculateDuration();
		calculateAmountOfEmployee();
	}
	
	private void calculateEfort(){
		double kloc=project.getKloc();
		double temp= Math.pow(kloc, b);
		effort=Math.floor(temp*a);
		
	}
	
	public double getEffort(){
		return effort;
	}
	
	private void calculateDuration(){
		double x= Math.pow(effort, c);
		duration = Math.ceil(2.5*x);
	}
	
	public double getDuration(){
		return duration;
	}
	
	private void calculateAmountOfEmployee(){
		amountOfEmployees= effort/duration;
		amountOfEmployees = Math.ceil(amountOfEmployees);
	}
	
	public double getAmountOfEmployees(){
		return this.amountOfEmployees;
	}
	
}

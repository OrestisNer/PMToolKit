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
		if(this.project.getCategory().equals("Organic")) {
			System.out.println(project.getName());
			System.out.println(project.getKloc());
			a=2.4;
			b=1.05;
			c=0.38;
			
			calculateEffort();
			calculateDuration();
			calculateAmountOfEmployee();
		}else if(this.project.getCategory().equals("Semi-detached")) {
			System.out.println(project.getName());
			System.out.println(project.getKloc());
			a=3.0;
			b=1.12;
			c=0.35;
			
			calculateEffort();
			calculateDuration();
			calculateAmountOfEmployee();
		}else {
			System.out.println(project.getName());
			System.out.println(project.getKloc());
			a=3.6;
			b=1.20;
			c=0.32;
			
			calculateEffort();
			calculateDuration();
			calculateAmountOfEmployee();
		}
	}
	
	private void calculateEffort(){
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

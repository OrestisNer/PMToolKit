package application.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.AlertUtils;
import application.FileUtils;
import application.Window;
import classes.Cost;
import classes.Employee;
import classes.Project;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;

public class CCosting implements Initializable{
	
	private Window window;
	private Project project;
	private String buttonText;
	private ArrayList<Employee> employeesInTable;
	
	private double effort;
	private double duration;
	private double amountOfEmployees;
	private double totalManmonths;
	private double totalEmploymentTime;
	private double totalCostPerEmployee;
	
	//CostingResults.fxml
	@FXML private Label amountOfEmployeesLabel;
	@FXML private Label estimatedDurationLabel;
	@FXML private Label estimatedEffortLabel;
	@FXML private TextField searchField;
	@FXML private TableView<Employee> employeeTable;
	@FXML private TableColumn<Employee, String> employeeCol;
	@FXML private TableColumn<Employee, String> empTimeCol;
	@FXML private TableColumn<Employee, String> rateCol;
	@FXML private TableColumn<Employee, Double> manMonthsCol;
	@FXML private TableColumn<Employee, Double> salaryCol;
	@FXML private TableColumn<Employee, Double> costPerEmpCol;
	@FXML private Button nextButton;
	@FXML private Label totalEmpTimeLabel;
	@FXML private Label totalManMonthsLabel;	
	@FXML private Label totalCostPerEmpLabel;
	@FXML private Button calcValuesButton;

	//CostingTotalProject.fxml
	@FXML private Label employeesCostLabel;
	@FXML private TextField softwareCostField;
	@FXML private TextField equipmentCostField;
	@FXML private TextField expencesField;
	@FXML private TextField totalCostField;
	
	public CCosting(Project project, String textButton){
		this.project=project;
		this.buttonText=textButton;
		employeesInTable = new ArrayList<>();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		if(buttonText.equals("Costing")) {
			project.initializeCost();
			Cost cost = project.getCost();
			effort=cost.getEffort();
			duration=cost.getDuration();
			amountOfEmployees=cost.getAmountOfEmployees();
	
			empTimeCol.setCellFactory(TextFieldTableCell.forTableColumn());
			rateCol.setCellFactory(TextFieldTableCell.forTableColumn());
					
			amountOfEmployeesLabel.setText(Double.toString(amountOfEmployees));
			estimatedDurationLabel.setText(Double.toString(duration));
			estimatedEffortLabel.setText(Double.toString(effort));
		}else 
			employeesCostLabel.setText(Double.toString(calcTotalEmployeesCost()));
			
	}
	
	//CostingResult.fxml

	//Fills the Employee Table
	public void refreshEmployeeTable() {
		employeeCol.setCellValueFactory(new PropertyValueFactory<>("fullname"));
		empTimeCol.setCellValueFactory(new PropertyValueFactory<>("employmentTime"));
		rateCol.setCellValueFactory(new PropertyValueFactory<>("rateOfEmployment"));
		manMonthsCol.setCellValueFactory(new PropertyValueFactory<>("manmonths"));
		salaryCol.setCellValueFactory(new PropertyValueFactory<>("salary"));
		costPerEmpCol.setCellValueFactory(new PropertyValueFactory<>("costPerEmployee"));

		ObservableList<Employee> employeeList = FXCollections.observableArrayList(employeesInTable);
		employeeTable.setItems(employeeList);
	}
	
	private Employee getSelectedEmployee() {
		return employeeTable.getSelectionModel().getSelectedItem();
	}
	
	//Add Button
	public void onAddEmployeeClicked() {
		String username = searchField.getText();
		Employee searchedEmployee = (Employee) FileUtils.getSingleEmployeeFromFile(username);
		searchField.clear();
		employeesInTable.add(searchedEmployee);
		refreshEmployeeTable();
	}
	
	//Delete button
	public void onDeleteEmployeeClicked() {
		ObservableList<Employee> allEmployees ,selectedEmp;
		
		allEmployees = employeeTable.getItems();
		selectedEmp = employeeTable.getSelectionModel().getSelectedItems();
		
		if(allEmployees.isEmpty())
			AlertUtils.createInfoAlert("Information", "Please add an employee to the table before deleting!");
		else if(selectedEmp.isEmpty())
			AlertUtils.createInfoAlert("Information", "Please select an employee before deleting!");
		else
			selectedEmp.forEach(allEmployees::remove);
	}
	
	//Sets the employmentTime in the employee table
	public void setEmptimeCellevent(CellEditEvent<Employee,String> editedCell) {
		Employee selectedEmp = getSelectedEmployee();
		for(Employee emp: employeesInTable) {
			if(selectedEmp.getUsername().equals(emp.getUsername()))
				emp.setEmploymentTime(editedCell.getNewValue().toString());
		}
		//selectedEmp.setEmploymentTime(editedCell.getNewValue().toString());
	}
	
	//Sets the employmentTime in the employee table
	public void setRateCellevent(CellEditEvent<Employee,String> editedCell) {
		Employee selectedEmp = getSelectedEmployee();
		for(Employee emp: employeesInTable) {
			if(selectedEmp.getUsername().equals(emp.getUsername()))
				emp.setRateOfEmployment(editedCell.getNewValue().toString());
		}
		//selectedEmp.setRateOfEmployment(editedCell.getNewValue().toString());
	}
	
	/*
	 * Calculate values button
	 * calculates and sets the correct values to the labels under the table
	 */
	public void onCalcValuesClicked() {
		double totalEmploymentTime = 0;
		double totalManmonths = 0 ;
		double totalCostPerEmp = 0;
		
		for(Employee emp : employeesInTable) {
			totalEmploymentTime = totalEmploymentTime + Double.parseDouble(emp.getEmploymentTime());
			totalManmonths = totalManmonths + emp.getManmonths();
			totalCostPerEmp = totalCostPerEmp + emp.getCostPerEmployee();
		}
		totalCostPerEmp = totalCostPerEmp/employeesInTable.size();
		
		this.totalEmploymentTime = totalEmploymentTime;
		this.totalManmonths = totalManmonths;
		this.totalCostPerEmployee = totalCostPerEmp;
		
		totalEmpTimeLabel.setText(Double.toString(totalEmploymentTime));
		totalManMonthsLabel.setText(Double.toString(totalManmonths));
		totalCostPerEmpLabel.setText(Double.toString(totalCostPerEmp));
	}
	
	/*
	 * Calculate employee values button
	 * Calculates the table column Manmonths, Cost/Employee for each employee
	 */
	public void calcEmployeesManmonthAndCost() {
		for(Employee emp : employeesInTable) {
			double manmonths = emp.calcManmonths();
			emp.setManmonths(manmonths);
			double costPerEmp = emp.calcCostPerEmployee();
			emp.setCostPerEmployee(costPerEmp);
		}
		refreshEmployeeTable();
	}
	
	//Next Button
	public void onNextClicked(ActionEvent actionEvent) throws Exception{
		Stage stage  = AlertUtils.getStageFromEvent(actionEvent);
		this.buttonText = nextButton.getText();
	    window= new Window(stage);
	    window.changeScene("CostingTotalProject.fxml", this);
	}
	
	//CostingTotalProject.fxml
	
	//Save button
	public void onSaveClicked(ActionEvent actionEvent) throws IOException{
		Cost cost = project.getCost();
		cost.setDirectCost(Double.parseDouble(softwareCostField.getText()) +
				Double.parseDouble(equipmentCostField.getText()) +
				Double.parseDouble(employeesCostLabel.getText()));
		cost.setSoftwareCost(Double.parseDouble(softwareCostField.getText()));
		cost.setEquipmentCost(Double.parseDouble(equipmentCostField.getText()));
		cost.setIndirectCost(Double.parseDouble(expencesField.getText()));
		
		cost.setTotalCost(Double.parseDouble(totalCostField.getText()));
		
		ArrayList<String> employeesInProject = project.getEmployees();
		if(employeesInProject.isEmpty()) {
			for(Employee emp: employeesInTable)
				project.addEmployee(emp);
		}else {
			for(Employee emp: employeesInTable) {
				if(!employeesInProject.contains(emp.getUsername())) 
					project.addEmployee(emp);
			}
		}
		
		for(Employee employee: employeesInTable) {
			ArrayList<String> employeeProjects = employee.getProjects();
			if(!employeeProjects.contains(project.getName())) {
				employee.addProject(project.getName());
				FileUtils.saveEmployeeChanges(employee);
			}
		}
		
		project.setIsCosted(true);
		FileUtils.saveProjectChanges(project);
		
		AlertUtils.closeWindow(actionEvent);
	}
	
	//Calculate total cost button
	public void onCalculateTotalCostClicked(ActionEvent actionEvent) {
		double softwareCost = Double.parseDouble(softwareCostField.getText());
		double equipmentCost = Double.parseDouble(equipmentCostField.getText());
		double expenses = Double.parseDouble(expencesField.getText());
		
		double totalCost = softwareCost + equipmentCost + expenses +Double.parseDouble(employeesCostLabel.getText());
		totalCostField.setText(Double.toString(totalCost));
	}
	
	//Calculates the sum of the table column Cost/Employee
	private double calcTotalEmployeesCost(){
		double totalEmployeeCost = 0;
		for(Employee emp: employeesInTable) 
			totalEmployeeCost = totalEmployeeCost + emp.getCostPerEmployee();
		return totalEmployeeCost;
	}
	
	public void onCancelClicked(ActionEvent actionEvent) throws IOException{
		AlertUtils.closeWindow(actionEvent);
	}
}

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
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class CCosting implements Initializable{
	
	private Window window;
	private Project project;
	private String buttonText;
	//private ArrayList<Employee> employeesInTable;
	private Employee searchedEmployee = null;
	
	private double effort;
	private double duration;
	private double amountOfEmployees;
	
	//CostingResults.fxml
	@FXML private Label amountOfEmployeesLabel;
	@FXML private Label estimatedDurationLabel;
	@FXML private Label estimatedEffortLabel;
	@FXML private TextField searchField;
	@FXML private TableView<Employee> employeeTable;
	@FXML private TableColumn<Employee, String> employeeCol;
	@FXML private TableColumn<Employee, Double> empTimeCol;
	@FXML private TableColumn<Employee, Double> rateCol;
	@FXML private TableColumn<Employee, Double> manMonthsCol;
	@FXML private TableColumn<Employee, Double> salaryCol;
	@FXML private TableColumn<Employee, Double> costPerEmpCol;
	@FXML private Button nextButton;
	@FXML private TextField empTimeField;
	@FXML private TextField rateField;
	
	//CostingTotalProject.fxml
	@FXML private Label employeesCostLabel;
	@FXML private TextField softwareCostField;
	@FXML private TextField equipmentCostField;
	@FXML private TextField expensesField;
	@FXML private TextField totalCostField;
	
	public CCosting(Project project, String textButton){
		this.project=project;
		this.buttonText=textButton;
		//employeesInTable = new ArrayList<>();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setProperties();
		project.initializeCost();
		Cost cost = project.getCost();
		effort=cost.getEffort();
		duration=cost.getDuration();
		amountOfEmployees=cost.getAmountOfEmployees();
				
		amountOfEmployeesLabel.setText(Double.toString(amountOfEmployees));
		estimatedDurationLabel.setText(Double.toString(duration));
		estimatedEffortLabel.setText(Double.toString(effort));
	}
	
	//CostingResult.fxml

	//Fills the Employee Table
	private void refreshEmployeeTable() {
		employeeCol.setCellValueFactory(new PropertyValueFactory<>("fullname"));
		empTimeCol.setCellValueFactory(new PropertyValueFactory<>("employmentTime"));
		rateCol.setCellValueFactory(new PropertyValueFactory<>("rateOfEmployment"));
		manMonthsCol.setCellValueFactory(new PropertyValueFactory<>("manmonths"));
		salaryCol.setCellValueFactory(new PropertyValueFactory<>("salary"));
		costPerEmpCol.setCellValueFactory(new PropertyValueFactory<>("costPerEmployee"));
		
		//ObservableList<Employee> employeeList = FXCollections.observableArrayList(employeesInTable);
		//employeeTable.setItems(employeeList);
	}
	
	private Employee getSelectedEmployee() {
		return employeeTable.getSelectionModel().getSelectedItem();
	}
	
	//Add Button
	public void onAddEmployeeClicked() {
		String username = searchField.getText();
		Employee searchedEmployee = (Employee) FileUtils.getSingleEmployeeFromFile(username);
		searchField.clear();
		System.out.println(searchedEmployee.getFullname());
		employeeTable.getItems().add(searchedEmployee);
		//employeesInTable.add(searchedEmployee);
		refreshEmployeeTable();
	}
	
	//Delete button
	public void onDeleteEmployeeClicked() {
		ObservableList<Employee> allEmployees ,selectedEmp;
		
		allEmployees = employeeTable.getItems();
		selectedEmp = employeeTable.getSelectionModel().getSelectedItems();
		
		//employeesInTable.remove(getSelectedEmployee());
		
		if(allEmployees.isEmpty())
			AlertUtils.createInfoAlert("Information", "Please add an employee to the table before deleting!");
		else if(selectedEmp.isEmpty())
			AlertUtils.createInfoAlert("Information", "Please select an employee before deleting!");
		else
			selectedEmp.forEach(allEmployees::remove);
	}
	
	//Set Employment Time Button
	public void onSetEmploymentTimeClicked() {
		Employee selectedEmp = getSelectedEmployee();
		double empTime = Double.parseDouble(empTimeField.getText());

		
		//selectedEmp.setEmploymentTime(empTime);
		/*for(Employee emp: employeesInTable) {
			if(emp.getUsername().equals(selectedEmp.getUsername())) 
				emp.setEmploymentTime(empTime);
		}*/
		refreshEmployeeTable();
	}
	
	//Set Rate of employment button
	public void onSetRateOfEmploymentClicked() {
		Employee selectedEmp = getSelectedEmployee();
		double rate = Double.parseDouble(rateField.getText());
		
		selectedEmp.setRateOfEmployment(rate);
		/*for(Employee emp: employeesInTable) {
			if(emp.getUsername().equals(selectedEmp.getUsername()))
				emp.setRateOfEmployment(rate);
		}*/
		refreshEmployeeTable();
	}
	
	private void setProperties(){
		empTimeField.textProperty().addListener((observable, oldValue, newValue) -> {
			try{
				Double.parseDouble(empTimeField.getText());
			}catch(NumberFormatException e){
				empTimeField.setText("");
			}
		});
		
		rateField.textProperty().addListener((observable, oldValue, newValue) -> {
			try{
				Double.parseDouble(rateField.getText());
			}catch(NumberFormatException e){
				rateField.setText("");
			}
		});
	}
	
	//Next Button
	public void onNextClicked(ActionEvent actionEvent) throws Exception{
		Stage stage  = AlertUtils.getStageFromEvent(actionEvent);
		this.buttonText = nextButton.getText();
	    window= new Window(stage);
	    window.changeScene("CostingTotalProject.fxml", this);
	}
	
	public void onSaveClicked(ActionEvent actionEvent){
		
	}
	
	public void onCalculateTotalCostClicked(ActionEvent actionEvent){
		
	}
	
	public void onCancelClicked(ActionEvent actionEvent) throws IOException{
		AlertUtils.closeWindow(actionEvent);
	}
}

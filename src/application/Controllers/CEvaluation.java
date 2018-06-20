package application.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.AlertUtils;
import application.FileUtils;
import application.Window;
import classes.Employee;
import classes.Project;
import classes.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class CEvaluation implements Initializable{

	private Project project;
	private User employee;
	private Window window;
	private String buttonText;
	
	//Evaluation.fxml
	@FXML private ListView<User> employeeListView;
	@FXML private Button nextButton;
	
	//EvaluateEmployee.fxml
	@FXML private TextArea evaluationTextArea;
	@FXML private RadioButton poorRadioButton;
	@FXML private RadioButton fairRadioButton;
	@FXML private RadioButton averageRadioButton;
	@FXML private RadioButton goodRadioButton;
	@FXML private RadioButton excellentRadioButton;
	private ToggleGroup evaluationToggleGroup;
	@FXML private Button submitButton;
	@FXML private Button cancelButton;
	
	public CEvaluation(Project project, User employee, String buttonText) {
		this.project = project;
		this.employee = employee;
		this.buttonText = buttonText;
	}
	
	public void setButtonText(String btnText) {
		this.buttonText = btnText;
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//if the button clicked is Next from Evaluation window
		if(buttonText.equals(nextButton.getText())) {
			
			evaluationToggleGroup = new ToggleGroup();
			this.poorRadioButton.setToggleGroup(evaluationToggleGroup);
			this.fairRadioButton.setToggleGroup(evaluationToggleGroup);
			this.averageRadioButton.setToggleGroup(evaluationToggleGroup);
			this.goodRadioButton.setToggleGroup(evaluationToggleGroup);
			this.excellentRadioButton.setToggleGroup(evaluationToggleGroup);
		}else {
			fillEmployeesListView();
		}
	}
	
	//Evaluation.fxml methods
	
	//Next button
	public void onNextClicked(ActionEvent actionEvent) throws Exception{
		User selectedEmployee = getSelectedEmployee();
		if(selectedEmployee instanceof Employee) {
			Stage stage = AlertUtils.getStageFromEvent(actionEvent);
			this.setButtonText(nextButton.getText());
			window = new Window(stage);
			window.changeScene("EvaluateEmployee.fxml", this);
		}else
			AlertUtils.createInfoAlert("Information", "The Project Manager can't evaluate himself!");
	}
	
	//Cancel button
	public void onCancelClicked(ActionEvent event) {
		AlertUtils.closeWindow(event);
	}
	
	//Fills employeesListView
	private void fillEmployeesListView() {
		ArrayList<User> employees = FileUtils.getEmployeesFromUsername(project.getEmployees());
		/*Iterator<User> iter = employees.iterator();

		while (iter.hasNext()) {
			User emp = iter.next();

			if (emp.getUsername().equalsIgnoreCase(employee.getUsername()))
				iter.remove();
		}*/

		ObservableList<User> list = FXCollections.observableArrayList(employees);
		employeeListView.setItems(list);
		employeeListView.setCellFactory(param -> new ListCell<User>() {
			@Override
			protected void updateItem(User employee, boolean empty) {
				super.updateItem(employee, empty);

				if (employee == null || employee.getName() == null || employee.getName().equals(" ")) {
					setText(null);
				} else {
					setText(employee.getName() + "-" + employee.getSpecialty());
				}
			}
		});

	}
	
	private User getSelectedEmployee() {
		return employeeListView.getSelectionModel().getSelectedItem();
	}
	
	//EvaluateEmployee.fxml methods
	
	//Submit button
	public void onSubmitClicked(ActionEvent actionEvent) throws Exception {
		evaluateEmployee();
		AlertUtils.createInfoAlert("Proccess", "You successfully evaluated an employee");
		Stage stage = AlertUtils.getStageFromEvent(actionEvent);
		this.setButtonText(submitButton.getText());
		window = new Window(stage);
		window.changeScene("Evaluation.fxml", this);
	}
	
	//Sets the evaluation attributes to selected employee
	private void evaluateEmployee() throws IOException {
		Employee selectedEmployee = (Employee) getSelectedEmployee();
		
		selectedEmployee.setEvaluation(evaluationTextArea.getText());
		
		if(evaluationToggleGroup.getSelectedToggle().equals(this.poorRadioButton))
			selectedEmployee.setGrade("Poor");
		else if(evaluationToggleGroup.getSelectedToggle().equals(this.fairRadioButton))
			selectedEmployee.setGrade("Fair");
		else if(evaluationToggleGroup.getSelectedToggle().equals(this.averageRadioButton))
			selectedEmployee.setGrade("Average");
		else if(evaluationToggleGroup.getSelectedToggle().equals(this.goodRadioButton))
			selectedEmployee.setGrade("Good");
		else
			selectedEmployee.setGrade("Excellent");
		
		FileUtils.saveEmployeeChanges(selectedEmployee);
	}
	
	//Cancel button
	public void onCancelEvaluationClicked(ActionEvent actionEvent) throws Exception{
		Stage stage = AlertUtils.getStageFromEvent(actionEvent);
		this.setButtonText(cancelButton.getText());
		window = new Window(stage);
		window.changeScene("Evaluation.fxml", this);
	}
}

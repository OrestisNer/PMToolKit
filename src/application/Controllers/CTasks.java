package application.Controllers;


import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import application.Utils;
import application.Window;
import classes.Calendar;
import classes.Employee;
import classes.Project;
import classes.Task;
import classes.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CTasks implements Initializable{
	
	private Window window;
	private Project project;
	private String buttonText;
	private User employee;
	private ArrayList<String> employeeTasks;
	private ArrayList<User> employees;
	
	//Tasks.fxml
	@FXML private ListView<Task> tasksListView;
	@FXML private Button createTaskButton;
	@FXML private Button selectTaskButton;
	//CreateTask.fxml
	@FXML private ListView<Task> prerequisitesListView;
	@FXML private ListView<User> employeesListView;
	@FXML private DatePicker startingDatePicker;
	@FXML private TextField averageTimeField;
	@FXML private TextField bestTimeField;
	@FXML private TextField worstTimeField;
	@FXML private TextField nameField;
	@FXML private CheckBox finalCheckbox;
	@FXML private Label calculatedEstimatedTimeLabel;
	@FXML private TextArea descriptionArea;
	@FXML private Button createButton;
	//TaskInfo.fxml
	@FXML private ListView<Task> prerequisitesInfoListView;
	@FXML private ListView<User> employeesInfoListView;
	@FXML private Label taskNameLabel;
	@FXML private Label startingDateLabel;
	@FXML private Label deadLineLabel;
	@FXML private TextArea descriptionInfoArea;
	@FXML private Label estimatedTimeLabel;
	@FXML private Label estimatedManMonthsLabel;
	@FXML private Label actualTimeLabel;
	@FXML private Label actutalManMonthsLabel;
	
	public CTasks(Project project, User employee,  String buttonText) {
		this.project=project;
		this.employee=employee;
		employeeTasks = employee.getUnfinishedTasks(project);
		this.buttonText = buttonText;
	}
	
	private void setButtonText(String btnText) {
		this.buttonText = btnText;
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {	
		//if the button clicked is Create Task from Tasks window
		if(buttonText.equals(createTaskButton.getText())) {
			employees= Utils.getEmployeesFromFile();
			fillEmployeesListView(employees,employeesListView);
			setTextProperties();
			
			prerequisitesListView.setDisable(true);
			employeesListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
			prerequisitesListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		//if the button clicked is Select Task from Tasks Window
		}else if(buttonText.equals(selectTaskButton.getText())){
			Task task = tasksListView.getSelectionModel().getSelectedItem();
			fillTaskInfo(task);
		}else {
			// HashMap<Project,HashMap<Task,Boolean>> tasks = employee.getTasks(project);
			 
			fillTaskListView();
		}
	}
	
	//Tasks.fxml Methods
	
	//Create task button 
	public void onCreateTaskClicked(ActionEvent actionEvent) throws Exception {
		Stage stage = Utils.getStageFromEvent(actionEvent);
		window = new Window(stage);
		this.setButtonText(createTaskButton.getText());
		window.changeScene("CreateTask.fxml", this);
	}
	
	//Select Task button
	public void onSelectTaskClicked(ActionEvent actionEvent) throws Exception {
		Stage stage = Utils.getStageFromEvent(actionEvent);
		this.setButtonText(selectTaskButton.getText());
		window = new Window(stage);
		window.changeScene("TaskInfo.fxml", this);
	}
	
	//Cancel button 
	public void onCancelClicked(ActionEvent actionEvent){
		Utils.closeWindow(actionEvent);
	}
	
	//Fills taskListView
	private void fillTaskListView(){
		ArrayList<String> userTasksID=employee.getUnfinishedTasks(project);
		ObservableList<Task> taskList = FXCollections.observableArrayList(Utils.getTasksFromID(userTasksID));
		tasksListView.setItems(taskList);
		tasksListView.setCellFactory(param -> new ListCell<Task>() {
		    @Override
		    protected void updateItem(Task task, boolean empty) {
		        super.updateItem(task, empty);

		        if (task == null || task.getName() == null) {
		            setText(null);
		        } else {
		            setText(task.getName());
		        }
		    }
		});
	}
		
	//CreateTask.fxml Methods 
	
	/*Cancel or back button clicked
	 * 
	 * Method for the cancel button on CreateTask.fxml and for the back button
	 * on TaskInfo.fxml 
	 */
	public void onCancel_BackClicked(ActionEvent actionEvent) throws Exception{
		Stage stage = Utils.getStageFromEvent(actionEvent);
		window = new Window(stage);
		this.setButtonText("Tasks");
		window.changeScene("Tasks.fxml",this);
	}
	
	public void onPickDate(ActionEvent actionEvent){
		fillPrerequisitesListView(startingDatePicker.getValue());
	}
	
	//Returns which tasks will be completed based on the deadline of the task the user is creating
	private ArrayList<Task> getPrerequisitesTasks(ArrayList<String> tasksID,LocalDate startingDate){
		ArrayList<Task> prerequisitesTasks= new ArrayList<Task>();
		for(String tID: tasksID){
			Task task = Utils.getSingleTaskFromFile(tID);
			if(Calendar.isBefore(task.getDeadLine(), startingDate)){
				prerequisitesTasks.add(task);
			}
		}
		return prerequisitesTasks;
	}
	
	//Fills prerequisitesListView based on the selected date from startingDatePicker
	private void fillPrerequisitesListView(LocalDate startingDate){
		ArrayList<String> unfinishedTasksID=employee.getUnfinishedTasks(project);
		ArrayList<Task> prerequisitesTasks = getPrerequisitesTasks(unfinishedTasksID,startingDate) ;
		this.prerequisitesListView.getItems().clear();
		if(!prerequisitesTasks.isEmpty()){
			this.prerequisitesListView.setDisable(false);
			ObservableList<Task> list = FXCollections.observableArrayList(prerequisitesTasks);
			prerequisitesListView.setItems(list);
			prerequisitesListView.setCellFactory(param -> new ListCell<Task>() {
				@Override
				protected void updateItem(Task task, boolean empty) {
					super.updateItem(task, empty);

					if (task == null || task.getName() == null || task.getName().equals(" ")) {
						setText(null);
					} else {
						setText(task.getName());
					}
				}
			});
		}else{
			this.prerequisitesListView.setDisable(true);
		}
	}
	
	//Fills employeesListView
	private void fillEmployeesListView(ArrayList<User> employees, ListView<User> listview){
		ObservableList<User> list = FXCollections.observableArrayList(employees);
		
		listview.setItems(list);
		listview.setCellFactory(param -> new ListCell<User>() {
		    @Override
		    protected void updateItem(User employee, boolean empty) {
		        super.updateItem(employee, empty);

		        if (employee == null || employee.getName() == null || employee.getName().equals(" ")) {
		            setText(null);
		        } else {
		            setText(employee.getName()+"-"+employee.getSpecialty());
		        }
		    }
		});
	}
	
	//Create button
	public void onCreateClicked(ActionEvent actionEvent) throws IOException{
		if(hasAppropriateArgs()){
			createTask();
			Utils.createInfoAlert("Proccess", "You successfully create a task");
			Stage stage = Utils.getStageFromEvent(actionEvent);
			this.setButtonText(createButton.getText());
			window = new Window(stage);
			window.changeScene("Tasks.fxml", this);
		}else{
			Utils.createInfoAlert("Information", "You have to fill in all the fields");
		}
	}
	
	private void setTextProperties(){
		
		bestTimeField.textProperty().addListener((observable, oldValue, newValue) -> {
			if(!worstTimeField.getText().isEmpty() && !averageTimeField.getText().isEmpty()){
				try{
					calculatedEstimatedTimeLabel.setText(""+Task.calcEstimatedTime(Integer.parseInt(bestTimeField.getText())
							, Integer.parseInt(worstTimeField.getText()),Integer.parseInt(averageTimeField.getText())));
				}catch(NumberFormatException e){
					//do nothing
				}
			}
		});
		
		worstTimeField.textProperty().addListener((observable, oldValue, newValue) -> {
			if(!bestTimeField.getText().isEmpty() && !averageTimeField.getText().isEmpty()){
				try{	
					calculatedEstimatedTimeLabel.setText(""+Task.calcEstimatedTime(Integer.parseInt(bestTimeField.getText())
							, Integer.parseInt(worstTimeField.getText()),Integer.parseInt(averageTimeField.getText())));
				}catch(NumberFormatException e){
					//do nothing
				}
			}
		});
		
		averageTimeField.textProperty().addListener((observable, oldValue, newValue) -> {
			if(!bestTimeField.getText().isEmpty() && !worstTimeField.getText().isEmpty()){
				try{
					calculatedEstimatedTimeLabel.setText(""+Task.calcEstimatedTime(Integer.parseInt(bestTimeField.getText())
							, Integer.parseInt(worstTimeField.getText()),Integer.parseInt(averageTimeField.getText())));
				}catch(NumberFormatException e){
					//do nothing
				}
			}
		});
		
	}
	
	//Checks if the user filled out everything
	private boolean hasAppropriateArgs(){
		ObservableList<User> employees = employeesListView.getSelectionModel().getSelectedItems();
		if(nameField.getText().isEmpty() || startingDatePicker.getValue().equals(null) || employees.isEmpty()
				|| calculatedEstimatedTimeLabel.getText().equals("") || descriptionArea.getText().equals(""))
			return false;
		return true;
	}
	
	//Creates a task object
	private void createTask() throws IOException{
		ObservableList<User> obsEmployees = employeesListView.getSelectionModel().getSelectedItems();
		String taskName=nameField.getText();
		ObservableList<Task> obsPrerequisites = prerequisitesListView.getSelectionModel().getSelectedItems();
		int estimatedTime = Integer.parseInt(calculatedEstimatedTimeLabel.getText());
		String description = descriptionArea.getText();
		LocalDate startingDate = startingDatePicker.getValue();
		
		ArrayList<User> employees = (ArrayList<User>) obsEmployees.stream().collect(Collectors.toList());
		ArrayList<Task> prerequisites;
		
		if(!obsPrerequisites.isEmpty())
			prerequisites = (ArrayList<Task>) obsPrerequisites.stream().collect(Collectors.toList());
		else
			prerequisites= new ArrayList<Task>();
		
		
		Task task = new Task(taskName,prerequisites,employees,estimatedTime,description,startingDate,project);
		Utils.saveTaskChanges(task);
		employeeTasks.add(task.getId());
		project.addTask(task);		
		employee.addTask(project,task);
		Utils.saveEmployeeChanges(employee);
		
		for(User employee : employees){
			if(employee instanceof Employee){
				employee.addTask(project,task);
				
				ArrayList<String> projects = employee.getProjects();
				if(projects.isEmpty())
					employee.addProject(project.getName());
				else {
					for(String project : projects) {
						if(!project.equalsIgnoreCase(this.project.getName()))
							employee.addProject(this.project.getName());
					}
				}
				Utils.saveEmployeeChanges(employee);
			}
		}
		Utils.saveProjectChanges(project);
	}
	
	//TaskInfo.fxml methods
	
	//Fills prerequisitesInfoListView
	private void fillPrerequisitesInfoListView(String taskID){
		Task task= Utils.getSingleTaskFromFile(taskID);
		System.out.println(task.getId());
		ArrayList<Task> prerequisitesTasks= task.getPrerequisites();
		prerequisitesInfoListView.getItems().clear();
		if(!prerequisitesTasks.isEmpty()){
			prerequisitesInfoListView.setDisable(false);
			ObservableList<Task> list = FXCollections.observableArrayList(prerequisitesTasks);
			prerequisitesInfoListView.setItems(list);
			prerequisitesInfoListView.setCellFactory(param -> new ListCell<Task>() {
				@Override
				protected void updateItem(Task task, boolean empty) {
					super.updateItem(task, empty);

					if (task == null || task.getName() == null || task.getName().equals(" ")) {
						setText(null);
					} else {
						setText(task.getName());
					}
				}
			});
		}else
			prerequisitesInfoListView.setDisable(true);
	}
	
	//Fills the info of the Task
	private void fillTaskInfo(Task task){
		this.taskNameLabel.setText(task.getName());
		this.startingDateLabel.setText(task.getStartingDate().toString());
		this.deadLineLabel.setText(task.getDeadLine().toString());
		this.fillEmployeesListView(task.getEmployees(), employeesInfoListView);
		this.fillPrerequisitesInfoListView(task.getId());
		this.descriptionInfoArea.setText(task.getDescription());
		this.estimatedTimeLabel.setText(task.getEstimatedDuration()+"");
		this.estimatedManMonthsLabel.setText(task.getEstimatedManMonths()+"");
		if(task.isCompleted()){
			this.actualTimeLabel.setText(task.getActualDuration()+"");
			this.actutalManMonthsLabel.setText(task.getActualManMonths()+"");
		}else{
			this.actualTimeLabel.setText("N/A");
			this.actutalManMonthsLabel.setText("N/A");
		}
	}	
}


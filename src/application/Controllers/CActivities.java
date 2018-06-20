package application.Controllers;


import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import application.AlertUtils;
import application.FileUtils;
import application.Window;
import classes.Activity;
import classes.Calendar;
import classes.Employee;
import classes.Project;
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

public class CActivities implements Initializable{
	
	private Window window;
	private Project project;
	private String buttonText;
	private User employee;
	private ArrayList<String> employeeTasks;
	private ArrayList<User> employees;
	
	//Tasks.fxml
	@FXML private ListView<Activity> tasksListView;
	@FXML private Button createTaskButton;
	@FXML private Button selectTaskButton;
	//CreateTask.fxml
	@FXML private ListView<Activity> prerequisitesListView;
	@FXML private ListView<User> employeesListView;
	@FXML private DatePicker startingDatePicker;
	@FXML private TextField averageTimeField;
	@FXML private TextField bestTimeField;
	@FXML private TextField worstTimeField;
	@FXML private TextField nameField;
	@FXML private CheckBox finalCheckbox;
	@FXML private Label calculatedEstimatedTimeLabel;
	@FXML private TextField subjectField;
	@FXML private TextArea descriptionArea;
	@FXML private Button createButton;
	//TaskInfo.fxml
	@FXML private ListView<Activity> prerequisitesInfoListView;
	@FXML private ListView<User> employeesInfoListView;
	@FXML private Label taskNameLabel;
	@FXML private Label startingDateLabel;
	@FXML private Label deadLineLabel;
	@FXML private TextArea descriptionInfoArea;
	@FXML private Label subjectLabel;
	@FXML private Label estimatedTimeLabel;
	@FXML private Label estimatedManMonthsLabel;
	@FXML private Label actualTimeLabel;
	@FXML private Label actualManMonthsLabel;
	
	public CActivities(Project project, User employee,  String buttonText) {
		this.project=project;
		this.employee=employee;
		employeeTasks = employee.getUnfinishedActivities(project);
		this.buttonText = buttonText;
	}
	
	private void setButtonText(String btnText) {
		this.buttonText = btnText;
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {	
		//if the button clicked is Create Task from Tasks window
		if(buttonText.equals(createTaskButton.getText())) {
			employees= FileUtils.getEmployeesFromFile();
			fillEmployeesListView(employees,employeesListView);
			setTextProperties();
			
			prerequisitesListView.setDisable(true);
			employeesListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
			prerequisitesListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		//if the button clicked is Select Task from Tasks Window
		}else if(buttonText.equals(selectTaskButton.getText())){
			Activity activity = tasksListView.getSelectionModel().getSelectedItem();
			fillTaskInfo(activity);
		}else {
			// HashMap<Project,HashMap<Task,Boolean>> tasks = employee.getTasks(project);
			if(employee instanceof Employee)
				createTaskButton.setVisible(false);
			fillTaskListView();
		}
	}
	
	//Tasks.fxml Methods
	
	//Create task button 
	public void onCreateTaskClicked(ActionEvent actionEvent) throws Exception {
		Stage stage = AlertUtils.getStageFromEvent(actionEvent);
		window = new Window(stage);
		this.setButtonText(createTaskButton.getText());
		window.changeScene("CreateTask.fxml", this);
	}
	
	//Select Task button
	public void onSelectTaskClicked(ActionEvent actionEvent) throws Exception {
		Stage stage = AlertUtils.getStageFromEvent(actionEvent);
		this.setButtonText(selectTaskButton.getText());
		window = new Window(stage);
		window.changeScene("TaskInfo.fxml", this);
	}
	
	//Cancel button 
	public void onCancelClicked(ActionEvent actionEvent){
		AlertUtils.closeWindow(actionEvent);
	}
	
	//Fills taskListView
	private void fillTaskListView(){
		ArrayList<String> userActivtiesID = employee.getUnfinishedActivities(project);
		ObservableList<Activity> activityList = FXCollections.observableArrayList(FileUtils.getActivitiesFromID(userActivtiesID));
		tasksListView.setItems(activityList);
		tasksListView.setCellFactory(param -> new ListCell<Activity>() {
		    @Override
		    protected void updateItem(Activity act, boolean empty) {
		        super.updateItem(act, empty);

		        if (act == null || act.getName() == null) {
		            setText(null);
		        } else {
		            setText(act.getName());
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
		Stage stage = AlertUtils.getStageFromEvent(actionEvent);
		window = new Window(stage);
		this.setButtonText("Tasks");
		window.changeScene("Tasks.fxml",this);
	}
	
	public void onPickDate(ActionEvent actionEvent){
		fillPrerequisitesListView(startingDatePicker.getValue());
	}
	
	//Returns which tasks will be completed based on the deadline of the task the user is creating
	private ArrayList<Activity> getPrerequisitesTasks(ArrayList<String> activitiesID,LocalDate startingDate){
		ArrayList<Activity> prerequisitesActivities= new ArrayList<Activity>();
		for(String actID: activitiesID){
			Activity act = FileUtils.getSingleActivityFromFile(actID);
			if(Calendar.isBefore(act.getDeadline(), startingDate)){
				prerequisitesActivities.add(act);
			}
		}
		return prerequisitesActivities;
	}
	
	//Fills prerequisitesListView based on the selected date from startingDatePicker
	private void fillPrerequisitesListView(LocalDate startingDate){
		ArrayList<String> unfinishedActsID=employee.getUnfinishedActivities(project);
		ArrayList<Activity> prerequisitesActs = getPrerequisitesTasks(unfinishedActsID,startingDate) ;
		this.prerequisitesListView.getItems().clear();
		if(!prerequisitesActs.isEmpty()){
			this.prerequisitesListView.setDisable(false);
			ObservableList<Activity> list = FXCollections.observableArrayList(prerequisitesActs);
			prerequisitesListView.setItems(list);
			prerequisitesListView.setCellFactory(param -> new ListCell<Activity>() {
				@Override
				protected void updateItem(Activity act, boolean empty) {
					super.updateItem(act, empty);

					if (act == null || act.getName() == null || act.getName().equals(" ")) {
						setText(null);
					} else {
						setText(act.getName());
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
			createActivity();
			AlertUtils.createInfoAlert("Proccess", "You successfully create a task");
			Stage stage = AlertUtils.getStageFromEvent(actionEvent);
			this.setButtonText(createButton.getText());
			window = new Window(stage);
			window.changeScene("Tasks.fxml", this);
		}else{
			AlertUtils.createInfoAlert("Information", "You have to fill in all the fields");
		}
	}
	
	private void setTextProperties(){
		
		bestTimeField.textProperty().addListener((observable, oldValue, newValue) -> {
			if(!worstTimeField.getText().isEmpty() && !averageTimeField.getText().isEmpty()){
				try{
					calculatedEstimatedTimeLabel.setText(""+Activity.calcEstimatedTime(Integer.parseInt(bestTimeField.getText())
							, Integer.parseInt(worstTimeField.getText()),Integer.parseInt(averageTimeField.getText())));
				}catch(NumberFormatException e){
					//do nothing
				}
			}
		});
		
		worstTimeField.textProperty().addListener((observable, oldValue, newValue) -> {
			if(!bestTimeField.getText().isEmpty() && !averageTimeField.getText().isEmpty()){
				try{	
					calculatedEstimatedTimeLabel.setText(""+Activity.calcEstimatedTime(Integer.parseInt(bestTimeField.getText())
							, Integer.parseInt(worstTimeField.getText()),Integer.parseInt(averageTimeField.getText())));
				}catch(NumberFormatException e){
					//do nothing
				}
			}
		});
		
		averageTimeField.textProperty().addListener((observable, oldValue, newValue) -> {
			if(!bestTimeField.getText().isEmpty() && !worstTimeField.getText().isEmpty()){
				try{
					calculatedEstimatedTimeLabel.setText(""+Activity.calcEstimatedTime(Integer.parseInt(bestTimeField.getText())
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
	private void createActivity() throws IOException{
		ObservableList<User> obsEmployees = employeesListView.getSelectionModel().getSelectedItems();
		String activityName=nameField.getText();
		ObservableList<Activity> obsPrerequisites = prerequisitesListView.getSelectionModel().getSelectedItems();
		int estimatedTime = Integer.parseInt(calculatedEstimatedTimeLabel.getText());
		String description = descriptionArea.getText();
		String subject = subjectField.getText();
		LocalDate startingDate = startingDatePicker.getValue();
		
		ArrayList<User> employees = (ArrayList<User>) obsEmployees.stream().collect(Collectors.toList());
		ArrayList<Activity> prerequisites;
		ArrayList<String> prerequisitesIDs = null;
		
		if(!obsPrerequisites.isEmpty()) {
			prerequisites = (ArrayList<Activity>) obsPrerequisites.stream().collect(Collectors.toList());
			for(Activity act: prerequisites)
				prerequisitesIDs.add(act.getId());
		}else
			prerequisitesIDs= new ArrayList<String>();
		
		
		Activity activity = new Activity(activityName,prerequisitesIDs,employees,estimatedTime,subject,description,startingDate,project);
		FileUtils.saveActivityChanges(activity);
		employeeTasks.add(activity.getId());
		project.addActivity(activity);		
		employee.addActivity(project,activity);
		FileUtils.saveEmployeeChanges(employee);
		
		for(User employee : employees){
			if(employee instanceof Employee){
				employee.addActivity(project,activity);
				
				ArrayList<String> projects = employee.getProjects();
				if(projects.isEmpty()) {
					employee.addProject(project.getName());
				} else {
					for(String project : projects) {
						if(!project.equalsIgnoreCase(this.project.getName()))
							employee.addProject(this.project.getName());
					}
				}
				FileUtils.saveEmployeeChanges(employee);
			}
		}
		
		ArrayList<String> empUsernames = project.getEmpUsernames();
		for(User employee : employees) 
			if(!empUsernames.contains(employee.getUsername()))
					this.project.addEmployee(employee);
	
		FileUtils.saveProjectChanges(project);
	}
	
	//TaskInfo.fxml methods
	
	//Fills prerequisitesInfoListView
	private void fillPrerequisitesInfoListView(String activityID){
		Activity activity = FileUtils.getSingleActivityFromFile(activityID);
		ArrayList<Activity> prerequisitesTasks= activity.getPrerequisites();
		prerequisitesInfoListView.getItems().clear();
		if(!prerequisitesTasks.isEmpty()){
			prerequisitesInfoListView.setDisable(false);
			ObservableList<Activity> list = FXCollections.observableArrayList(prerequisitesTasks);
			prerequisitesInfoListView.setItems(list);
			prerequisitesInfoListView.setCellFactory(param -> new ListCell<Activity>() {
				@Override
				protected void updateItem(Activity act, boolean empty) {
					super.updateItem(act, empty);

					if (act == null || act.getName() == null || act.getName().equals(" ")) {
						setText(null);
					} else {
						setText(act.getName());
					}
				}
			});
		}else
			prerequisitesInfoListView.setDisable(true);
	}
	
	//Fills the info of the Task
	private void fillTaskInfo(Activity activity){
		this.taskNameLabel.setText(activity.getName());
		this.startingDateLabel.setText(activity.getStartingDate().toString());
		this.deadLineLabel.setText(activity.getDeadline().toString());
		ArrayList<User> employees = FileUtils.getEmployeesFromUsername(activity.getEmployees());
		this.fillEmployeesListView(employees, employeesInfoListView);
		this.fillPrerequisitesInfoListView(activity.getId());
		this.descriptionInfoArea.setText(activity.getDescription());
		this.estimatedTimeLabel.setText(activity.getEstimatedDuration()+"");
		this.estimatedManMonthsLabel.setText(activity.getEstimatedManMonths()+"");
		this.subjectLabel.setText(activity.getSubject());
		if(activity.isCompleted()){
			this.actualTimeLabel.setText(activity.getActualDuration()+"");
			this.actualManMonthsLabel.setText(activity.getActualManMonths()+"");
		}else{
			this.actualTimeLabel.setText("N/A");
			this.actualManMonthsLabel.setText("N/A");
		}
	}	
}
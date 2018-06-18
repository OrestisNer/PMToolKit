package classes;

import java.io.IOException;
import java.util.ArrayList;

import application.Utils;

public class ProjectManager extends User{

	public ProjectManager(String username, String password, String firstname, String lastname, double salary,String specialty) {
		super(username, password, firstname, lastname, salary,specialty);
	}
	
	public void sendConfirmationMessageToPM(Activity activity) throws IOException {
		ArrayList<User> receivers = new ArrayList<>();
		receivers.add(this);
		String subject = "Confirm Task";
		String message = "All the employees included to the Task: "+ activity.getName()
				+ "have confirmed it. Check if everything is done and confirm it too!";
		
		Message confirmMessage = new Message(this,receivers,subject,message);
		this.addMessage(confirmMessage);
		Utils.saveEmployeeChanges(this);
	}

}

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
		String subject = "Confirm Task "+ activity.getName();
		String message = "All the employees included in the Task: "+ activity.getName()
				+ " \nhave confirmed it. Check if everything is done and confirm it too!";
		
		Message confirmMessage = new Message(this,receivers,subject,message);
		this.addMessage(confirmMessage);
	}
}

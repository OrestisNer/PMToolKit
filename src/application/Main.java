package application;

import application.Controllers.CLogIn;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application{
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void start(Stage primaryStage) throws Exception {
		Window window = new Window("Log In","LogIn.fxml",new CLogIn(),true);
		window.createWindow();
	}

}

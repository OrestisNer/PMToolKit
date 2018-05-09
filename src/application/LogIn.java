package application;
	
import application.Controllers.CLogIn;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class LogIn extends Application {
	
	Stage loginWindow;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) {
		try {
			loginWindow = primaryStage;
			FXMLLoader loader = new FXMLLoader(getClass().getResource("LogIn.fxml"));
			loader.setController(new CLogIn());
			Parent root = loader.load();
			Scene scene = new Scene(root,375,215);
			
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			loginWindow.setOnCloseRequest(e -> {
				e.consume();
				Utils.closeProgram(loginWindow);
			});
			
			loginWindow.setTitle("Log In");
			loginWindow.setScene(scene);
			loginWindow.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
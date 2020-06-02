package kr.or.ddit.css.view.signUp;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class projectSignUpMain extends Application {

	@Override
	public void start(Stage primaryStage) throws IOException {
	    Parent root = FXMLLoader.load(projectSignUpMain.class.getResource("projectSignUp.fxml"));
	    
		Scene scene = new Scene(root);
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("CAR SHARING SYSTEM");
		primaryStage.setMaximized(true);
		primaryStage.show();
		
		Stage stage = new Stage();
		stage.setTitle("Resizing TextArea");
	}

	public static void main(String[] args) {
		launch(args);
	}
}



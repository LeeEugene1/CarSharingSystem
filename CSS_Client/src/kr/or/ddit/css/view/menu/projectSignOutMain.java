package kr.or.ddit.css.view.menu;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class projectSignOutMain extends Application {
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		FXMLLoader loader = new FXMLLoader(projectSignOutMain.class.getResource("projectSignOut.fxml"));
	    Parent root = loader.load();

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

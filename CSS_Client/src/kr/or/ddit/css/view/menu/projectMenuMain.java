package kr.or.ddit.css.view.menu;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class projectMenuMain extends Application {

	@Override
	public void start(Stage primaryStage) throws IOException {
		FXMLLoader loader = new FXMLLoader(projectMenuMain.class.getResource("projectMenu.fxml"));
	    Parent root = loader.load();
	      
		//추가창을 제어하는 Controller 객체에 현재 Controller객체의 인스턴스를 넘겨준다.
//	    studentController fileController = loader.getController();
//		fileController.setMainStage(primaryStage);

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

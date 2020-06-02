package kr.or.ddit.css.view.menu1;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class btn1CommunityInsert extends Application {
	
	//커뮤니티 게시글 추가하기
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		FXMLLoader loader = new FXMLLoader(btn1CommunityInsert.class.getResource("btn1CommunityInsert.fxml"));
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

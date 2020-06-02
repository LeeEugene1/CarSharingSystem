package kr.or.ddit.css.view.menu2;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class btn2CustomerServiceBlackListContentMain extends Application {
	
	//커뮤티니게시글 내용보기
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		FXMLLoader loader = new FXMLLoader(btn2CustomerServiceBlackListContentMain.class.getResource("btn2CustomerServiceBlackListContent.fxml"));
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

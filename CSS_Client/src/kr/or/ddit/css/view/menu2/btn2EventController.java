package kr.or.ddit.css.view.menu2;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class btn2EventController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane root;

    @FXML
    private Label lbTitle;

    @FXML
    private Label lbTitle11;

    @FXML
    private Label lbTitle1;

    @FXML
    void event1Clicked(MouseEvent event) {
    	try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../menu2/EventBanner1.fxml"));
			Parent root1 = (Parent) fxmlLoader.load();
			Stage stage = new Stage();
			stage.initStyle(StageStyle.TRANSPARENT.DECORATED);
			stage.setTitle("Message Window");
			stage.setScene(new Scene(root1));
			stage.show();

		} catch (Exception e) {
			System.out.println("cant load new window");
		}
    }

    @FXML
    void event2Clicked(MouseEvent event) {
    	try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../menu2/EventBanner2.fxml"));
			Parent root1 = (Parent) fxmlLoader.load();
			Stage stage = new Stage();
			stage.initStyle(StageStyle.TRANSPARENT.DECORATED);
			stage.setTitle("Message Window");
			stage.setScene(new Scene(root1));
			stage.show();

		} catch (Exception e) {
			System.out.println("cant load new window");
		}
    }

    @FXML
    void initialize() {
        assert root != null : "fx:id=\"root\" was not injected: check your FXML file 'btn2Event.fxml'.";
        assert lbTitle != null : "fx:id=\"lbTitle\" was not injected: check your FXML file 'btn2Event.fxml'.";
        assert lbTitle11 != null : "fx:id=\"lbTitle11\" was not injected: check your FXML file 'btn2Event.fxml'.";
        assert lbTitle1 != null : "fx:id=\"lbTitle1\" was not injected: check your FXML file 'btn2Event.fxml'.";

    }
}

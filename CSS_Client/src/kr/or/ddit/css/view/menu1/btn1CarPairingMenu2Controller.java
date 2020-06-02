package kr.or.ddit.css.view.menu1;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class btn1CarPairingMenu2Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane communityMainRoot;

    @FXML
    private Label lbTitle;

    @FXML
    private Label lbTitle1;

    @FXML
    private Label lbTitle11;

    @FXML
    private ImageView HumanIcon;

    @FXML
    private Label lbTitle111;

    @FXML
    void CarPairingRegistrationClicked(MouseEvent event) throws IOException {
    	AnchorPane pane = FXMLLoader.load(getClass().getResource("../carpairing/carPairingMenu.fxml"));
    	//Stage primaryStage = 
    	communityMainRoot.getChildren().setAll(pane);
    }
    
    @FXML
    void CarPairingCancleClicked(MouseEvent event) throws IOException {
    	Stage dialog = new Stage(StageStyle.UTILITY);
    	dialog.initModality(Modality.WINDOW_MODAL);
    	
    	Parent child = FXMLLoader.load(btn1CarPairingMenu2Controller.class.getResource("../carpairing/carPairingCancle.fxml"));
    	Scene scene = new Scene(child);
    	
    	dialog.setScene(scene);
    	dialog.setTitle("CSS_CARPAIRING");
    	dialog.setResizable(false);
    	dialog.show();
    }
    

    @FXML
    void initialize() {
        

    }
}

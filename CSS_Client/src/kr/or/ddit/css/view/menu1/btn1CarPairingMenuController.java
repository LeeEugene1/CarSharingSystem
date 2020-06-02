package kr.or.ddit.css.view.menu1;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class btn1CarPairingMenuController {
	
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
    void CarPairingRegisterClicked(MouseEvent event) throws IOException {
    	AnchorPane pane = FXMLLoader.load(getClass().getResource("btn1CarPairingMenu2.fxml"));
    	//Stage primaryStage = 
    	communityMainRoot.getChildren().setAll(pane);
    }

    @FXML
    void CarPairingUserClicked(MouseEvent event) throws IOException {
    	AnchorPane pane = FXMLLoader.load(getClass().getResource("btn1CarPairingMenuInsert1.fxml"));
    	//Stage primaryStage = 
    	communityMainRoot.getChildren().setAll(pane);
    	
    }

    @FXML
    void initialize() {
       

    }
}

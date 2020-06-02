package kr.or.ddit.css.view.menu2;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import kr.or.ddit.css.session.AdminLoginSession;
import kr.or.ddit.css.session.LoginSession;

public class btn2CouponController {

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
    private Label lbTitle111;
    
    @FXML
    private HBox hbox1;
    
    @FXML
    private Label name;
    
    @FXML
    private Label grade;

    @FXML
    private Label lbTitle11112;

    @FXML
    private Label lbTitle11111;

    @FXML
    private Label lbTitle111111;

    @FXML
    void initialize() {
    	if(AdminLoginSession.adminSession==null) {
    	name.setText(LoginSession.session.getMem_name());
    	String memClass = LoginSession.session.getMem_class();
    	grade.setText(memClass);
    	}else {
    		hbox1.setVisible(false);
    	}
    }
}

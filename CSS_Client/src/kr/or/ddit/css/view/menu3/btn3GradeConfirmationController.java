package kr.or.ddit.css.view.menu3;

import java.io.IOException;
import java.net.URL;
import java.rmi.registry.Registry;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.layout.AnchorPane;
import kr.or.ddit.css.service.login.ILoginService;
import kr.or.ddit.css.session.LoginSession;
import kr.or.ddit.css.vo.MemberVO;

public class btn3GradeConfirmationController {

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
    private ImageView gradeImage;

    @FXML
    private Label name;

    @FXML
    private Label lbTitle111;

    @FXML
    private Label grade;

    @FXML
    private Label lbTitle1111;
    
    @FXML
    void initialize() {
    	name.setText(LoginSession.session.getMem_name());
    	String memClass = LoginSession.session.getMem_class();
    	grade.setText(memClass);
    	Image img = null;
    	
    	if(memClass.equals("패밀리")) {
    		img = new Image(btn3GradeConfirmationController.class.getResourceAsStream("./menu3Img/icon_grade.png"));
    	}else if(memClass.equals("VIP")) {
    		img = new Image(btn3GradeConfirmationController.class.getResourceAsStream("./menu3Img/icon_grade2.png"));
    	}else if(memClass.equals("VVIP")) {
    		img = new Image(btn3GradeConfirmationController.class.getResourceAsStream("./menu3Img/icon_grade3.png"));
    	}
    	gradeImage.setImage(img);
    }
    
}

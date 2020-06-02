package kr.or.ddit.css.view.menu2;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import kr.or.ddit.css.session.BlacklistSession;
import kr.or.ddit.css.session.CarDetailSession;

public class BListDetailImageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView detailImg;
    
    String img = BlacklistSession.BlacklistSessionimg1;
    @FXML
    void initialize() {
    	System.out.println("img"+img);
       detailImg.setImage(new Image(BListDetailImageController.class.getResourceAsStream("/img_blacklist/" + img)));

    }
}

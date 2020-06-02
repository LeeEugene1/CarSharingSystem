package kr.or.ddit.css.view.menu1;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import kr.or.ddit.css.session.CarDetailSession;

public class carDetailImageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView detailImg;
    
    String img = CarDetailSession.carDetailSession;
    @FXML
    void initialize() {
    	System.out.println("img"+img);
       detailImg.setImage(new Image(carDetailImageController.class.getResourceAsStream("/img_2/" + img)));

    }
}

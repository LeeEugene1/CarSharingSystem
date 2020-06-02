package kr.or.ddit.css.view.menu1;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import kr.or.ddit.css.session.CarDetailSession;
import kr.or.ddit.css.session.CommunitySession;

public class comDetailImageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView detailImg;
    
    String img = CommunitySession.CommunityImg;
    @FXML
    void initialize() {
    	System.out.println("img"+img);
       detailImg.setImage(new Image(comDetailImageController.class.getResourceAsStream("/img_community/" + img)));

    }
}

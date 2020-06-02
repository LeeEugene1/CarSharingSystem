package kr.or.ddit.css.view.carpairing;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import kr.or.ddit.css.session.CarDetailSession;
import kr.or.ddit.css.session.CarPairingSession;
import kr.or.ddit.css.session.CarPairingUseSession;

public class pairingDetailImageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView detailImg;
    
    String img1 = CarPairingSession.carPairingSessionImg1;
    String img2 = CarPairingSession.carPairingSessionImg2;
    String img3 = CarPairingSession.carPairingSessionImg3;
    int carchoice = CarPairingSession.carChoice;
   
    String img4 = CarPairingUseSession.carPairingUseSessionImg1;
    String img5 = CarPairingUseSession.carPairingUseSessionImg2;
    String img6 = CarPairingUseSession.carPairingUseSessionImg3;
    int carUsechoice = CarPairingUseSession.carUseChoice;
    
    @FXML
    void initialize() {
    	//System.out.println("img"+img1);
    	if(carchoice==1) {
    		System.out.println(img1);
    		detailImg.setImage(new Image(pairingDetailImageController.class.getResourceAsStream("/img_pairing/" + img1)));
    	}else if(carchoice==2) {
    		System.out.println(img2);
    		 detailImg.setImage(new Image(pairingDetailImageController.class.getResourceAsStream("/img_pairing/" + img2)));
    	}else if(carchoice==3) {
    		System.out.println(img3);
    		detailImg.setImage(new Image(pairingDetailImageController.class.getResourceAsStream("/img_pairing/" + img3)));
    	}else if(carUsechoice==1) {
    		detailImg.setImage(new Image(pairingDetailImageController.class.getResourceAsStream("/img_pairing/" + img4)));
    	}else if(carUsechoice==2) {
    		detailImg.setImage(new Image(pairingDetailImageController.class.getResourceAsStream("/img_pairing/" + img5)));
    	}else if(carUsechoice==3) {
    		detailImg.setImage(new Image(pairingDetailImageController.class.getResourceAsStream("/img_pairing/" + img6)));
    	}
       

    }
}

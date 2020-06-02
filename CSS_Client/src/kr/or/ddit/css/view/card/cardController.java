package kr.or.ddit.css.view.card;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.google.cloud.vision.v1.AnnotateImageRequest;
import com.google.cloud.vision.v1.AnnotateImageResponse;
import com.google.cloud.vision.v1.BatchAnnotateImagesResponse;
import com.google.cloud.vision.v1.Feature;
import com.google.cloud.vision.v1.Image;
import com.google.cloud.vision.v1.ImageAnnotatorClient;
import com.google.cloud.vision.v1.Feature.Type;
import com.google.protobuf.ByteString;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import kr.or.ddit.css.service.card.ICardService;
import kr.or.ddit.css.service.license.ILicenseService;
import kr.or.ddit.css.session.LoginSession;
import kr.or.ddit.css.view.signUp.RegEx;
import kr.or.ddit.css.vo.CardVO;
import kr.or.ddit.css.vo.LicenseVO;

public class cardController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane CardRoot;

    @FXML
    private Label lbTitle;

    @FXML
    private Label lbTitle11;

    @FXML
    private Label lbTitle1;

    @FXML
    private TextField tfCard;

    @FXML
    private Button btnFile;

    @FXML
    private Button btnFile2;

    @FXML
    private TextField tfCardNum;

    @FXML
    private TextField tfCardDate;

    @FXML
    private PasswordField tfPass;

    @FXML
    private PasswordField tfPassCheck;

    @FXML
    private Button btnInsert;

    @FXML
    private Button btnCancle;

    @FXML
    void InsertOk(ActionEvent event) throws IOException {
    	String cardNum = tfCardNum.getText();
    	if(cardNum.isEmpty()) {
    		alert("입력오류","카드번호가 입력되지 않았습니다.");
    		return;
    	}
    	
    	String cardDate = tfCardDate.getText();
    	if(cardDate.isEmpty()) {
    		alert("입력오류","카드 유효기간이 입력되지 않았습니다.");
    		return;
    	}
    	
    	String cardPass = tfPass.getText();
    	if(cardPass.isEmpty()) {
    		alert("입력오류","결제 비밀번호가 입력되지 않았습니다.");
    		return;
    	}
    	if(!RegEx.checkCard_pw(cardPass)) {
    		alert("입력오류","4글자 숫자만 입력가능합니다.");
    		return;
    	}
    	
    	String cardPasscheck = tfPassCheck.getText();
    	if(cardPasscheck.isEmpty()) {
    		alert("입력오류","비밀번호 확인이 입력되지 않았습니다.");
    		return;
    	}
    	
    	if(!cardPass.equals(cardPasscheck)) {
    		alert("입력오류","비밀번호를 맞게 입력 해주세요.");
    		return;
    	}
    	String nowMemId = LoginSession.session.getMem_id();
    	CardVO cardVo = new CardVO();
    	
    	String cardMonth = cardDate.substring(0,2);
    	String cardYear = cardDate.substring(3,5);
    	
    	cardVo.setCard_num(cardNum);
    	cardVo.setCard_pw(cardPass);
    	cardVo.setCard_month(cardMonth);
    	cardVo.setCard_year(cardYear);
    	cardVo.setMem_id(nowMemId);
    	
    	int cnt = service.insertCard(cardVo);
    	if(cnt>0) {
    		List<CardVO> cardList = service.getAllCard();
    		data=FXCollections.observableArrayList(cardList);
    		infoMsg("작업성공","결제카드를 등록하였습니다.");
    		AnchorPane pane = FXMLLoader.load(getClass().getResource("../menu/projectMenu.fxml"));
    		CardRoot.getScene().setRoot(pane);
    	}else {
    		alert("작업실패","결제카드 등록을 실패하였습니다.");
    	}
    	
    }

    @FXML
    void cancleOk(ActionEvent event) throws IOException {
    	AnchorPane pane = FXMLLoader.load(getClass().getResource("../menu/projectMenu.fxml"));
    	CardRoot.getScene().setRoot(pane);
    }

    @FXML
    void cardImg(ActionEvent event) {
    	FileChooser fc = new FileChooser();
    	fc.getExtensionFilters().add(new ExtensionFilter("Image File", "*.png", "*.jpg", "*.gif"));
    	File f = fc.showOpenDialog(null);
    	tfCard.setText(f.getAbsolutePath());
    }

    @FXML
    void cardText(ActionEvent event) {
    	try {
    		
			String imageFilePath = tfCard.getText();
			
			List<AnnotateImageRequest> requests = new ArrayList<>();
		
			ByteString imgBytes = ByteString.readFrom(new FileInputStream(imageFilePath));
		
			Image img = Image.newBuilder().setContent(imgBytes).build();
			Feature feat = Feature.newBuilder().setType(Type.TEXT_DETECTION).build();
			AnnotateImageRequest request = AnnotateImageRequest.newBuilder().addFeatures(feat).setImage(img).build();
			requests.add(request);
		
			try (ImageAnnotatorClient client = ImageAnnotatorClient.create()) {
				BatchAnnotateImagesResponse response = client.batchAnnotateImages(requests);
			    List<AnnotateImageResponse> responses = response.getResponsesList();
		
			    for (AnnotateImageResponse res : responses) {
			    	if (res.hasError()) {
			    		System.out.printf("Error: %s\n", res.getError().getMessage());
			    		return;
			    	}
		
			    	System.out.println("Text : ");
			    	System.out.println(res.getTextAnnotationsList().get(0).getDescription());
			    	String test = res.getTextAnnotationsList().get(0).getDescription();
			    	
			    	String[] results = test.split("\n");
			    	
			    	//System.out.println(Arrays.toString(results) );
			    	//System.out.println(results[1]);
			    	results[7]=results[7].substring(5);
			    	
			    	tfCardNum.setText(results[3]);
			    	tfCardNum.setEditable(false);
			    	
			    	tfCardDate.setText(results[7]);
			    	tfCardDate.setEditable(false);
			    	
//			    	String cardDate=results[7];
//			    	String cardMonth = cardDate.substring(0,2);
//			    	String cardYear = cardDate.substring(3,5);
//			    	System.out.println(cardMonth);
//			    	System.out.println(cardYear);
			    	tfPass.requestFocus();
			      
			    	// For full list of available annotations, see http://g.co/cloud/vision/docs
			    	/*for (EntityAnnotation annotation : res.getTextAnnotationsList()) {
				    	  
						//System.out.printf("Text: %s\n", annotation.getDescription());
						//System.out.printf("Position : %s\n", annotation.getBoundingPoly());
					}*/
			    }
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
    }
    
    private ICardService service;
    private ObservableList<CardVO> data;
    private Registry reg;
    
    @FXML
    void initialize() {
    	try {
			reg = LocateRegistry.getRegistry("localhost", 8899);
			service = (ICardService) reg.lookup("card");
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    public void alert(String head, String msg) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("경고");
		alert.setHeaderText(head);
		alert.setContentText(msg);
		
		alert.showAndWait();
		
	}
    
    public void infoMsg(String head,String msg) {
    	Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("정보");
		alert.setHeaderText(head);
		alert.setContentText(msg);
		
		alert.showAndWait();
    }
}

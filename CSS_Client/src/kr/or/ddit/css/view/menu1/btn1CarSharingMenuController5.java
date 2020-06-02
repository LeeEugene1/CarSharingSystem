package kr.or.ddit.css.view.menu1;

import java.awt.CardLayout;
import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import kr.or.ddit.css.service.card.ICardService;
import kr.or.ddit.css.service.payment.IPaymentService;
import kr.or.ddit.css.service.rent.IRentService;
import kr.or.ddit.css.session.CarDetailSession;
import kr.or.ddit.css.session.LoginSession;
import kr.or.ddit.css.view.menu.projectSignOutController;
import kr.or.ddit.css.vo.CardVO;

public class btn1CarSharingMenuController5 {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane paymentMainRoot;

    @FXML
    private Label lbTitle;

    @FXML
    private Label lbTitle1;

    @FXML
    private Label lbTitle11;

    @FXML
    private Label lbTitle12;

    @FXML
    private Label name;			// 고객 이름

    @FXML
    private Label grade;		// 고객 등급
    
    @FXML
    private ImageView gradeImage;	// 이미지 뷰

    @FXML
    private Label rentArea;		// 대여 요금

    @FXML
    private Label useArea;		// 이용 요금

    @FXML
    private Label timeArea;		// 시간당 요금

    @FXML
    private Label insArea;		// 보험료

    @FXML
    private Label sInsArea;		// 선택 보험요금

    @FXML
    private Label reArea;		// 기타

    @FXML
    private Label hiArea;		// 하이패스

    @FXML
    private Label bookArea;		// 예약 수수료

    @FXML
    private Label canArea;		// 취소 위약금

    @FXML
    private Label payArea;		// 결제내역

    @FXML
    private Label cardArea;		// 신용카드(실 결제금액)

    @FXML
    private Label gradeArea;	// 등급 할인

    @FXML
    private Label eventArea;	// 이벤트 할인

    @FXML
    private Label reSaleArea;	// 기타 할인
    
    private ICardService cardService;

    @FXML
    void btnPay(MouseEvent event) throws IOException {
    	String memId = LoginSession.session.getMem_id();
    	List<CardVO> cardList = new ArrayList<CardVO>();
    	cardList = cardService.getMemCard(memId);
    	if(cardList.isEmpty()) {
    		alert("입력오류", "카드 정보가 없습니다. 등록 후 결제해주세요.");
    		AnchorPane pane = FXMLLoader.load(getClass().getResource("../card/cardView.fxml"));
        	paymentMainRoot.getChildren().setAll(pane);
    	}else if(!cardList.isEmpty()){
    		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("btn1CarPayment.fxml"));
    		Parent root2 = (Parent) fxmlLoader.load();
    		
    		btn1CarPaymentController ctl = fxmlLoader.getController();
    		
    		ctl.setPrimaryStage((Stage)paymentMainRoot.getScene().getWindow());
    		
    		Stage stage = new Stage();
    		stage.initStyle(StageStyle.TRANSPARENT.DECORATED);
    		stage.setTitle("Payment Window");
    		stage.setScene(new Scene(root2));
    		stage.show();
    	}
		
    }

    @FXML
    void btnPrevious(MouseEvent event) throws IOException {
    	AnchorPane pane = FXMLLoader.load(getClass().getResource("btn1CarSharingMenuInsert4.fxml"));
    	paymentMainRoot.getChildren().setAll(pane);
    }

    @FXML
    void initialize() {
    	Registry reg;
		try {
			reg = LocateRegistry.getRegistry("localhost",8899);
			cardService = (ICardService) reg.lookup("card");
		} catch (RemoteException e1) {
			e1.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
    	
    	if(LoginSession.session.getMem_class().equals("패밀리")) {
    		gradeImage.setImage(new Image("./img/familyGrade.png"));
    	}else if(LoginSession.session.getMem_class().equals("VIP")) {
    		gradeImage.setImage(new Image("./img/vipGrade.png"));
    	}else if(LoginSession.session.getMem_class().equals("VVIP")) {
    		gradeImage.setImage(new Image("./img/vvipGrade.png"));
    	}else {
    		alert("입력오류", "해당 등급이 없습니다.");
    	}
    	
    	name.setText(LoginSession.session.getMem_name());
    	grade.setText(LoginSession.session.getMem_class());
    	
    	int starRent = Integer.parseInt(CarDetailSession.rentStart.substring(0, 2));
    	int endRent = Integer.parseInt(CarDetailSession.rentEnd.substring(0, 2));
    	int time = endRent-starRent;
    	
    	int usePay = CarDetailSession.carRentCost;
    	int timePay = CarDetailSession.cartimeCost * time;
    	
    	rentArea.setText((usePay + timePay)+"원");
    	useArea.setText("+" + usePay + "원");
    	timeArea.setText("+" + timePay + "원");
    	insArea.setText(CarDetailSession.carInsurance+"원");
    	sInsArea.setText("+" + CarDetailSession.carInsurance+"원");
    	reArea.setText(0+"원");
    	hiArea.setText("+" + 0+"원");
    	bookArea.setText("+" + 0+"원");
    	canArea.setText("+" + 0+"원");
    	int resultCost = usePay + timePay + CarDetailSession.carInsurance + 0;
    	
    	if(LoginSession.session.getMem_class().equals("패밀리")) {
    		gradeArea.setText("-" + (int)(resultCost*0.05)+"원");
    		eventArea.setText("-" + (int)(resultCost*0.1)+"원");
        	payArea.setText((int)((resultCost*0.05)+(resultCost*0.1)) + "원");
        	reSaleArea.setText("-" + 0 +"원");
        	cardArea.setText((int)(resultCost-(resultCost*0.05)-(resultCost*0.1)) +"원");
        	CarDetailSession.carLastCost = (int)(resultCost-(resultCost*0.05)-(resultCost*0.1));
    	}else if(LoginSession.session.getMem_class().equals("VIP")) {
    		gradeArea.setText("-" + (int)(resultCost*0.07)+"원");
    		eventArea.setText("-" + (int)(resultCost*0.1)+"원");
        	payArea.setText((int)((resultCost*0.07)+(resultCost*0.1)) + "원");
        	reSaleArea.setText("-" + 0 +"원");
        	cardArea.setText((int)(resultCost-(resultCost*0.07)-(resultCost*0.1))+"원");
        	CarDetailSession.carLastCost = (int)(resultCost-(resultCost*0.07)-(resultCost*0.1));
    	}else if(LoginSession.session.getMem_class().equals("VVIP")) {
    		gradeArea.setText("-" + (int)(resultCost*0.1)+"원");
    		eventArea.setText("-" + (int)(resultCost*0.1)+"원");
        	payArea.setText((int)((resultCost*0.1)+(resultCost*0.1)) + "원");
        	reSaleArea.setText("-" + 0 +"원");
        	cardArea.setText((int)(resultCost-(resultCost*0.1)-(resultCost*0.1))+"원");
        	CarDetailSession.carLastCost = (int)(resultCost-(resultCost*0.1)-(resultCost*0.1));
    	}else {
    		gradeArea.setText(0+"원");
    	}
    	
    }
    
  //----------------------------------------------------------alert, infoMsg, confirm 메서드 설정 ---------------------------
    public void alert(String head, String msg) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("경고");
		alert.setHeaderText(head);
		alert.setContentText(msg);
		
		alert.showAndWait();
	}
	
	public void infoMsg(String head, String msg) {
		Alert info = new Alert(AlertType.INFORMATION);
		info.setTitle("정보");
		info.setHeaderText(head);
		info.setContentText(msg);
		
		info.showAndWait();
	}
    
    public ButtonType confirm(String head, String msg) {
    	Alert confirm = new Alert(AlertType.CONFIRMATION);
    	confirm.setTitle("확인");
    	confirm.setHeaderText(head);
    	confirm.setContentText(msg);
    	
    	return confirm.showAndWait().get();
    }
    //--------------------------------------------------------------------------------------------------------------------
}

package kr.or.ddit.css.view.menu1;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import kr.or.ddit.css.service.card.ICardService;
import kr.or.ddit.css.service.payment.IPaymentService;
import kr.or.ddit.css.service.rent.IRentService;
import kr.or.ddit.css.session.CarDetailSession;
import kr.or.ddit.css.session.LoginSession;
import kr.or.ddit.css.view.menu.projectMenuController;
import kr.or.ddit.css.vo.CardVO;
import kr.or.ddit.css.vo.PaymentVO;
import kr.or.ddit.css.vo.RentVO;

public class btn1CarPaymentController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane PaymentRoot;

    @FXML
    private Label lbTitle;

    @FXML
    private ImageView cashImage;

    @FXML
    private Label payArea;

    @FXML
    private Label payDate;

    @FXML
    private ComboBox<String> cardTypeBox;

    @FXML
    private TextField cardNum1;

    @FXML
    private TextField cardNum2;

    @FXML
    private PasswordField cardNum3;

    @FXML
    private PasswordField cardNum4;

    @FXML
    private TextField monthArea;

    @FXML
    private TextField yearArea;

    @FXML
    private PasswordField pwArea;

    private Stage primaryStage;
    
    private ICardService cardService;
    
    private IPaymentService paymentService;
    
    private IRentService rentService;
    
    public void setPrimaryStage(Stage primaryStage) {
    	this.primaryStage = primaryStage;
    }
    
    @FXML
    void PaymentBtn(ActionEvent event) throws ParseException, IOException {	// 결제 버튼
    	String memId = LoginSession.session.getMem_id();
    	
    	Stage thisStage = (Stage) payDate.getScene().getWindow();
    	
		cardList = cardService.getMemCard(memId);
		if(cardList == null) {
			alert("입력오류", "card 정보를 가져오는데 실패했습니다.");
		}else if(pwArea.getText().equals("")) {
			alert("입력오류", "카드 비밀번호를 입력해주세요.");
			pwArea.requestFocus();
		}else if(!pwArea.getText().equals(cardList.get(0).getCard_pw().substring(0, 2))) {
			alert("입력오류", "카드 비밀번호가 다릅니다. 다시 입력해주세요.");
			pwArea.requestFocus();
		}else {
			RentVO rentList = new RentVO();
	    	
	    	String rentS = CarDetailSession.rentStart.replaceAll("[^0-9]", "");
	    	String rentE = CarDetailSession.rentEnd.replaceAll("[^0-9]", "");
	    	
	    	String rentSR = CarDetailSession.rentDate + " " + rentS.substring(0,2) + ":" + rentS.substring(2,4);
	    	String rentER = CarDetailSession.rentDate + " " + rentE.substring(0,2) + ":" + rentE.substring(2,4);
	    	
	    	rentList.setRent_start(rentSR);
	    	rentList.setRent_end(rentER);
	    	rentList.setRent_cost(CarDetailSession.carLastCost);
	    	rentList.setRent_status("Y");
	    	rentList.setMem_id(LoginSession.session.getMem_id());
	    	rentList.setCar_num(CarDetailSession.carNum);
	    	rentList.setIns_id(CarDetailSession.carInsId);
	    	rentList.setRent_addr(CarDetailSession.addr1+" "+CarDetailSession.addr2);
	    	
	    	int cnt = 0;
	    	
			cnt = rentService.insertRent(rentList);
			if (cnt > 0) {
				PaymentVO payList = new PaymentVO();

				int paymentId = 0;
				String paymentDate = payDate1.format(d);
				int paymentCost = CarDetailSession.carLastCost;
				int rentId = rentService.selectRentId(memId);

				payList.setPayment_date(paymentDate);
				payList.setPayment_cost(paymentCost);
				payList.setRent_id(rentId);
				payList.setMem_id(memId);
				payList.setCard_num(cardList.get(0).getCard_num());

				int cnt2 = 0;

				cnt2 = paymentService.insertPayment(payList);
				if (cnt2 > 0) {
					infoMsg("확인", "카셰어링 서비스 결제가 완료되었습니다.");

					FXMLLoader loader = new FXMLLoader(getClass().getResource("../menu/projectMenu.fxml"));

					AnchorPane root = loader.load();

					Scene scene = new Scene(root);

					projectMenuController ctl = loader.getController();

					primaryStage.setScene(scene);

					thisStage.close();
				} else {
					alert("입력오류", "결제에 실패했습니다.");
				}
			} else {
				alert("입력오류", "rent 정보를 저장하는데 실패했습니다.");
			}
			
		}
    	
    }

    private ObservableList<String> ComboList = FXCollections.observableArrayList("하나","삼성","현대","우리", "신한");
    private Date d = new Date();
	private SimpleDateFormat payDay = new SimpleDateFormat("yyyy-MM-dd");
	private SimpleDateFormat payDate1 = new SimpleDateFormat("yyyy-MM-dd");
	private SimpleDateFormat payDate2 = new SimpleDateFormat("HH:mm");
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	private List<CardVO> cardList = new ArrayList<CardVO>();
	
    @FXML
    void initialize() {
    	Registry reg;
		try {
			reg = LocateRegistry.getRegistry("localhost",8899);
			cardService = (ICardService) reg.lookup("card");
			paymentService = (IPaymentService) reg.lookup("payment");
			rentService = (IRentService) reg.lookup("rent");
		} catch (RemoteException e1) {
			e1.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
    	
    	payArea.setText(CarDetailSession.carLastCost + "원");
    	
    	payDate.setText(payDay.format(d));
    	cardTypeBox.setItems(ComboList);
    	
    	String memId = LoginSession.session.getMem_id();
    	
    	try {
			cardList = cardService.getMemCard(memId);
			if(cardList.isEmpty()) {
				alert("입력오류", "카드 정보가 없습니다. 등록 후 결제해주세요.");
			}else if(!cardList.isEmpty()){
				cardNum1.setText(cardList.get(0).getCard_num().substring(0, 4));
				cardNum2.setText(cardList.get(0).getCard_num().substring(5, 9));
				cardNum3.setText(cardList.get(0).getCard_num().substring(10, 14));
				cardNum4.setText(cardList.get(0).getCard_num().substring(15, 19));
				monthArea.setText(cardList.get(0).getCard_month());
				yearArea.setText(cardList.get(0).getCard_year());
			}
		} catch (RemoteException e) {
			e.printStackTrace();
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

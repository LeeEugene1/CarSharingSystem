package kr.or.ddit.css.view.menu1;

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
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import kr.or.ddit.css.serivce.insurance.IInsuranceService;
import kr.or.ddit.css.service.login.ILoginService;
import kr.or.ddit.css.session.CarDetailSession;
import kr.or.ddit.css.session.CarTypeSession;
import kr.or.ddit.css.vo.InsuranceVO;
import kr.or.ddit.css.vo.MemberVO;

public class btn1CarSharingMenuController4 {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane InsuranceMainRoot;

    @FXML
    private Label lbTitle;

    @FXML
    private Label lbTitle1;

    @FXML
    private Label lbTitle11;

    @FXML
    private Label lbTitle12;

    @FXML
    private RadioButton stBtn;

    @FXML
    private ToggleGroup rbBtnGroup;

    @FXML
    private Label stLabel;

    @FXML
    private Label stPayLabel;

    @FXML
    private RadioButton spBtn;

    @FXML
    private Label spLabel;

    @FXML
    private Label spPayLabel;

    @FXML
    private RadioButton pmBtn;

    @FXML
    private Label pmLabel;

    @FXML
    private Label pmPayLabel;

    private IInsuranceService insuranceService;
    
    @FXML
    void btnNext(MouseEvent event) throws IOException {
    	
    	if(stBtn.isSelected()) {
    		CarDetailSession.carInsurance = cost1;
    		CarDetailSession.carInsId = carType1;
    	}else if(spBtn.isSelected()) {
    		CarDetailSession.carInsurance = cost2;
    		CarDetailSession.carInsId = carType2;
    	}else if(pmBtn.isSelected()) {
    		CarDetailSession.carInsurance = cost3;
    		CarDetailSession.carInsId = carType3;
    	}
    	
    	AnchorPane pane = FXMLLoader.load(getClass().getResource("btn1CarSharingMenuInsert5.fxml"));
    	//Stage primaryStage = 
    	InsuranceMainRoot.getChildren().setAll(pane);
    }

    @FXML
    void btnPrevious(MouseEvent event) throws IOException {
    	AnchorPane pane = FXMLLoader.load(getClass().getResource("btn1CarSharingMenuInsert3.fxml"));
    	//Stage primaryStage = 
    	InsuranceMainRoot.getChildren().setAll(pane);
    }

    @FXML
    void initialize() throws RemoteException, NotBoundException {
    	Registry reg;
		reg = LocateRegistry.getRegistry("localhost",8899);
		insuranceService = (IInsuranceService) reg.lookup("insurance");
		choice();
    }
    
    private int cost1 = 0;
    private int cost2 = 0;
    private int cost3 = 0;
    private int carType1 = 0;
    private int carType2 = 0;
    private int carType3 = 0;
    
    public void choice() {
    	int carId = 0;
    	
    	carId = CarTypeSession.carTypeSession;
    	
    	switch (carId) {
		case 1:
			try {
				carType1 = 1;
				cost1 = insuranceService.getInsuranceCost(carType1);
				stLabel.setText("+ " +cost1+"원");
		    	stPayLabel.setText("사고시 자기부담금 70만 원");
		    	carType2 = 2;
		    	cost2 = insuranceService.getInsuranceCost(carType2);
		    	spLabel.setText("+ " +cost2+"원");
		    	spPayLabel.setText("사고시 자기부담금 30만 원");
		    	carType3 = 3;
		    	cost3 = insuranceService.getInsuranceCost(carType3);
		    	pmLabel.setText("+ " +cost3+"원");
		    	pmPayLabel.setText("사고시 자기부담금 5만 원");
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
			break;
		case 2:
			try {
				carType1 = 4;
				cost1 = insuranceService.getInsuranceCost(carType1);
				stLabel.setText("+ " +cost1+"원");
		    	stPayLabel.setText("사고시 자기부담금 70만 원");
		    	carType2 = 5;
		    	cost2 = insuranceService.getInsuranceCost(carType2);
		    	spLabel.setText("+ " +cost2+"원");
		    	spPayLabel.setText("사고시 자기부담금 30만 원");
		    	carType3 = 6;
		    	cost3 = insuranceService.getInsuranceCost(carType3);
		    	pmLabel.setText("+ " +cost3+"원");
		    	pmPayLabel.setText("사고시 자기부담금 5만 원");
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
			break;
		case 3:
			try {
				carType1 = 7;
				cost1 = insuranceService.getInsuranceCost(carType1);
				stLabel.setText("+ " +cost1+"원");
		    	stPayLabel.setText("사고시 자기부담금 150만 원");
		    	carType2 = 8;
		    	cost2 = insuranceService.getInsuranceCost(carType2);
		    	spLabel.setText("+ " +cost2+"원");
		    	spPayLabel.setText("사고시 자기부담금 100만 원");
		    	carType3 = 9;
		    	cost3 = insuranceService.getInsuranceCost(carType3);
		    	pmLabel.setText("+ " +cost3+"원");
		    	pmPayLabel.setText("사고시 자기부담금 50만 원");
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
			break;
		case 4:
			try {
				carType1 = 10;
				cost1 = insuranceService.getInsuranceCost(carType1);
				stLabel.setText("+ " +cost1+"원");
		    	stPayLabel.setText("사고시 자기부담금 300만 원");
		    	carType2 = 11;
		    	cost2 = insuranceService.getInsuranceCost(carType2);
		    	spLabel.setText("+ " +cost2+"원");
		    	spPayLabel.setText("사고시 자기부담금 200만 원");
		    	carType3 = 12;
		    	cost3 = insuranceService.getInsuranceCost(carType3);
		    	pmLabel.setText("+ " +cost3+"원");
		    	pmPayLabel.setText("사고시 자기부담금 100만 원");
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
			break;
		case 5:
			try {
				carType1 = 13;
				cost1 = insuranceService.getInsuranceCost(carType1);
				stLabel.setText("+ " +cost1+"원");
		    	stPayLabel.setText("사고시 자기부담금 1000만 원");
		    	carType2 = 14;
		    	cost2 = insuranceService.getInsuranceCost(carType2);
		    	spLabel.setText("+ " +cost2+"원");
		    	spPayLabel.setText("사고시 자기부담금 700만 원");
		    	carType3= 15;
		    	cost3 = insuranceService.getInsuranceCost(carType3);
		    	pmLabel.setText("+ " +cost3+"원");
		    	pmPayLabel.setText("사고시 자기부담금 500만 원");
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
			break;
		default:
			alert("입력오류", "선택하신 차종이 없습니다.");
			try {
				AnchorPane pane = FXMLLoader.load(getClass().getResource("btn1CarSharingMenu.fxml"));
				InsuranceMainRoot.getChildren().setAll(pane);
			} catch (IOException e) {
				e.printStackTrace();
			}
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


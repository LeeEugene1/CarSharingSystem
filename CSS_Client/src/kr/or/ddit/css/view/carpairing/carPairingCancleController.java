package kr.or.ddit.css.view.carpairing;

import java.net.URL;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import kr.or.ddit.css.service.carpairing.ICarPairingUseService;
import kr.or.ddit.css.session.LoginSession;
import kr.or.ddit.css.vo.CarPairingUseBoardVO;

public class carPairingCancleController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private BorderPane pairingCancleRoot;

    @FXML
    private TextField tfCarNum;

    @FXML
    private PasswordField tfPass;

    @FXML
    private Button pairingCancle;

    @FXML
    private Button cancleBtn;

    @FXML
    void cancleOk(ActionEvent event) {
    	Stage stage = (Stage) cancleBtn.getScene().getWindow();
    	stage.close();
    }

    @FXML
    void pairingCancleOk(ActionEvent event) {
    	String memId = LoginSession.session.getMem_id();
    	String carNum = tfCarNum.getText();
    	if(carNum.isEmpty()) {
    		alert("입력오류","등록한 차량 번호를 입력해주세요.");
    		tfCarNum.requestFocus();
    		return;
    	}
    	String memPass = tfPass.getText();
    	System.out.println(memPass);
    	if(carNum.isEmpty()) {
    		alert("입력오류","비밀번호를 입력해주세요.");
    		tfPass.requestFocus();
    		return;
    	}
    	String nowMemPass=LoginSession.session.getMem_pw();
    	if(nowMemPass==memPass) {
    		alert("입력오류","비밀번호를 확인해주세요.");
    		tfPass.requestFocus();
    		return;
    	}
    	Alert alertConfirm = new Alert(AlertType.CONFIRMATION);
		
		alertConfirm.setTitle("CONFIRMATION");
		alertConfirm.setContentText("삭제하시겠습니까?");
		
		// Alert창을 보여주고 사용자가 누른 버튼 값 읽어오기
		ButtonType confirmResult = alertConfirm.showAndWait().get();
		
		if(confirmResult == ButtonType.OK) {
			System.out.println("삭제 되었습니다.");
		HashMap<String, String> param =new HashMap<String, String>();
		System.out.println(carNum);
		System.out.println(memId);
		param.put("carnum", carNum);
		param.put("memid", memId);
		int cnt=0;
		try {
			cnt = service.deleteApply(param);
			System.out.println("cnt="+cnt);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		if(cnt > 0) {
			infoMsg("정보","글 삭제가 완료되었습니다");
			ObservableList<CarPairingUseBoardVO> data_edit = FXCollections.observableArrayList();
			List<CarPairingUseBoardVO> list_edit = new ArrayList<>();
			
			Stage stage = (Stage) cancleBtn.getScene().getWindow();
	    	stage.close();
		}
		
			}else {
				alert("입력오류","등록철회를 실패하였습니다.");
				
			}
		
    }
    
    private Registry reg;
    private ICarPairingUseService service;
    
    @FXML
    void initialize() {
    	try {
			reg = LocateRegistry.getRegistry("localhost", 8899);
			service = (ICarPairingUseService) reg.lookup("carpairingUse");
			//serviceUse=(ICarPairingUseService)reg.lookup("carpairingUse");
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
	public void infoMsg(String headerText, String msg) {
	      Alert alert = new Alert(AlertType.INFORMATION);
	      alert.setTitle("정보 확인");
	      alert.setHeaderText(headerText);
	      alert.setContentText(msg);
	      alert.showAndWait();
  
	}
}

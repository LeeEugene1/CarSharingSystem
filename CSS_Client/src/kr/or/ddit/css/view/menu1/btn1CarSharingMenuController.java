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

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import kr.or.ddit.css.service.license.ILicenseService;
import kr.or.ddit.css.service.login.ILoginService;
import kr.or.ddit.css.session.CarTypeSession;
import kr.or.ddit.css.session.LoginSession;
import kr.or.ddit.css.vo.LicenseVO;

public class btn1CarSharingMenuController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane communityMainRoot;

    @FXML
    private Label lbTitle;

    @FXML
    private Label lbTitle1;

    @FXML
    private Label lbTitle11;

    @FXML
    private ImageView carIcon;

    @FXML
    private Label lbTitle111;

    private ILicenseService service;
    private List<LicenseVO> data = new ArrayList<LicenseVO>();
    private String memId = LoginSession.session.getMem_id();
    
	@FXML
    void CarSharingBigCarClicked(MouseEvent event) throws IOException {	// 차종선택 : 대형차
		data = service.getMemLicense(memId);
		
		if(data.isEmpty()) {
			alert("입력오류", "운전면허 등록 후 이용 가능합니다.");
			AnchorPane pane = FXMLLoader.load(getClass().getResource("../license/license.fxml"));
	    	communityMainRoot.getChildren().setAll(pane);
		}else if(!data.isEmpty()){
			AnchorPane pane = FXMLLoader.load(getClass().getResource("btn1CarSharingMenuInsert1.fxml"));
	    	communityMainRoot.getChildren().setAll(pane);
	    	CarTypeSession.carTypeSession = 3;
		}
		
    }
	
    @FXML
    void CarSharingMiddleCarClicked(MouseEvent event) throws IOException {	// 차종선택 : 중형차
    	data = service.getMemLicense(memId);
		
		if(data.isEmpty()) {
			alert("입력오류", "운전면허 등록 후 이용 가능합니다.");
			AnchorPane pane = FXMLLoader.load(getClass().getResource("../license/license.fxml"));
	    	communityMainRoot.getChildren().setAll(pane);
		}else if(!data.isEmpty()){
	    	AnchorPane pane = FXMLLoader.load(getClass().getResource("btn1CarSharingMenuInsert1_2.fxml"));
	    	communityMainRoot.getChildren().setAll(pane);
	    	CarTypeSession.carTypeSession = 2;
		}
    }

    @FXML
    void CarSharingSUVCarClicked(MouseEvent event) throws IOException {	// 차종선택 : SUV
    	data = service.getMemLicense(memId);
		
		if(data.isEmpty()) {
			alert("입력오류", "운전면허 등록 후 이용 가능합니다.");
			AnchorPane pane = FXMLLoader.load(getClass().getResource("../license/license.fxml"));
	    	communityMainRoot.getChildren().setAll(pane);
		}else if(!data.isEmpty()){
	    	AnchorPane pane = FXMLLoader.load(getClass().getResource("btn1CarSharingMenuInsert1_3.fxml"));
	    	communityMainRoot.getChildren().setAll(pane);
	    	CarTypeSession.carTypeSession = 4;
		}
    }

    @FXML
    void CarSharingSmallCarClicked(MouseEvent event) throws IOException {	// 차종선택 : 소형차
    	data = service.getMemLicense(memId);
		
		if(data.isEmpty()) {
			alert("입력오류", "운전면허 등록 후 이용 가능합니다.");
			AnchorPane pane = FXMLLoader.load(getClass().getResource("../license/license.fxml"));
	    	communityMainRoot.getChildren().setAll(pane);
		}else if(!data.isEmpty()){
	    	AnchorPane pane = FXMLLoader.load(getClass().getResource("btn1CarSharingMenuInsert1_1.fxml"));
	    	communityMainRoot.getChildren().setAll(pane);
	    	CarTypeSession.carTypeSession = 1;
		}
    }
    

    @FXML
    void CarSharingSportsCarClicked(MouseEvent event) throws IOException {	// 차종선택 : 스포츠카
    	data = service.getMemLicense(memId);
		
		if(data.isEmpty()) {
			alert("입력오류", "운전면허 등록 후 이용 가능합니다.");
			AnchorPane pane = FXMLLoader.load(getClass().getResource("../license/license.fxml"));
	    	communityMainRoot.getChildren().setAll(pane);
		}else if(!data.isEmpty()){
	    	AnchorPane pane = FXMLLoader.load(getClass().getResource("btn1CarSharingMenuInsert1_4.fxml"));
	    	communityMainRoot.getChildren().setAll(pane);
	    	CarTypeSession.carTypeSession = 5;
		}
    }
	//게시글 내용으로 이동

    @FXML
    void initialize() {
    	Registry reg;
		try {
			reg = LocateRegistry.getRegistry("localhost",8899);
			service = (ILicenseService) reg.lookup("license");
		} catch (RemoteException e) {
			e.printStackTrace();
		} 
		catch (NotBoundException e) {
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
}


package kr.or.ddit.css.view.carpairing;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.xml.transform.Result;

import org.apache.log4j.DailyRollingFileAppender;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import kr.or.ddit.css.service.carpairing.ICarPairingInsertService;
import kr.or.ddit.css.service.carpairing.ICarPairingUseService;
import kr.or.ddit.css.session.AdminLoginSession;
import kr.or.ddit.css.session.CarPairingSession;
import kr.or.ddit.css.session.LoginSession;
import kr.or.ddit.css.vo.CarPairingInsertBoardVO;
import kr.or.ddit.css.vo.CarPairingUseBoardVO;

public class carPairingContentController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane communityContentRoot;

    @FXML
    private Label lbTitle;

    @FXML
    private Label lbTitle1;

    @FXML
    private Label tfTitle;

    @FXML
    private Label lbWriter;

    @FXML
    private Label lbDate;

    @FXML
    private TextField carNum;

    @FXML
    private TextField rentCost;

    @FXML
    private TextField driveCost;

    @FXML
    private TextArea taContent;

    @FXML
    private ImageView imgFile1;

    @FXML
    private ImageView imgFile2;

    @FXML
    private ImageView imgFile3;

    @FXML
    private Button updateBtn;

    @FXML
    private Button deleteBtn;

    @FXML
    private Button btnApprove;

    @FXML
    private Button listBtn;

    @FXML
    void approveOK(MouseEvent event) throws IOException {
    	CarPairingUseBoardVO CPUBVo = new CarPairingUseBoardVO();
    	String admin = AdminLoginSession.adminSession.getAdmin_id();
    	String tempTitle="";
    	System.out.println("admin="+admin);
    	if(AdminLoginSession.adminSession.getAdmin_id()==null) {
    		alert("입력오류","승인 권한이 없습니다.");
    		imgFile1.requestFocus();
    		return;
    	}
    	TextInputDialog dialog = new TextInputDialog();
    	dialog.setTitle("CSS CARPAIRING INSERT");
    	dialog.setHeaderText("등록할 제목을 입력해주세요.");
    	
    	Optional<String> title = dialog.showAndWait();
    	if(title.isPresent()) {
    		tempTitle=title.get();
    	}
    	
    	CPUBVo.setPairinguseboard_title(tempTitle);
    	CPUBVo.setPairinguseboard_carnum(carNum.getText());
    	CPUBVo.setPairinguseboard_rentcost(Integer.parseInt(rentCost.getText()));
    	CPUBVo.setPairinguseboard_drivecost(Integer.parseInt(driveCost.getText()));
    	CPUBVo.setPairinguseboard_content(taContent.getText());
    	CPUBVo.setPairinguseboard_img1(CarPairingSession.carPairingSessionImg1);
    	CPUBVo.setPairinguseboard_img2(CarPairingSession.carPairingSessionImg2);
    	CPUBVo.setPairinguseboard_img3(CarPairingSession.carPairingSessionImg3);
    	CPUBVo.setMem_id(lbWriter.getText());
    	CPUBVo.setAdmin_id(admin);
    	
    	int cnt = serviceUse.insertBoard(CPUBVo);
    	if(cnt>0) {
    		List<CarPairingUseBoardVO> pairingUseList = serviceUse.getAllBoardList();
    		data=FXCollections.observableArrayList(pairingUseList);
    		
    		infoMsg("작업성공","게시글을 등록하였습니다.");
    		
    		AnchorPane pane = FXMLLoader.load(getClass().getResource("carPairingMenu.fxml"));
    		communityContentRoot.getChildren().setAll(pane);
    	}else {
    		alert("작업실패","게시글 등록을 실패하였습니다.");
    	}
    	
    }

    @FXML
    void deleteOk(ActionEvent event) throws IOException {
    	
//    	System.out.println("memId="+memId);
//		System.out.println("lbWriter="+lbWriter.getText());
    	if(AdminLoginSession.adminSession==null) {
    		String memId = LoginSession.session.getMem_id();
    		if(!memId.equals(lbWriter.getText())) {
        		alert("입력오류","삭제권한이 없습니다.");
        		return;
    	}
    	
    	}
    	Alert alertConfirm = new Alert(AlertType.CONFIRMATION);
		
		alertConfirm.setTitle("CONFIRMATION");
		alertConfirm.setContentText("삭제하시겠습니까?");
		
		// Alert창을 보여주고 사용자가 누른 버튼 값 읽어오기
		ButtonType confirmResult = alertConfirm.showAndWait().get();
		
		if(confirmResult == ButtonType.OK) {
			System.out.println("삭제 되었습니다.");
		
	
		int num = CarPairingSession.carPairingSession;
		//System.out.println(num);
	
		int cnt = 0;
		try {
			cnt = service.deleteBoard(num);
		} catch (RemoteException e1) {
			
			e1.printStackTrace();
		}
		if(cnt > 0) {
			infoMsg("정보","글 삭제가 완료되었습니다");
			ObservableList<CarPairingInsertBoardVO> data_edit = FXCollections.observableArrayList();
			List<CarPairingInsertBoardVO> list_edit = new ArrayList<>();
			
			AnchorPane pane = FXMLLoader.load(getClass().getResource("carPairingMenu.fxml"));
			communityContentRoot.getChildren().setAll(pane);
		}
		
			}else {
				alert("입력오류","게시글 삭제를 실패하였습니다.");
				
			}
    }

    @FXML
    void listViewClicked(MouseEvent event) throws IOException {
    	AnchorPane pane = FXMLLoader.load(getClass().getResource("carPairingMenu.fxml"));
    	communityContentRoot.getChildren().setAll(pane);
    }

    @FXML
    void updateOk(ActionEvent event) {
    	Parent parent = null;
    	String memId = LoginSession.session.getMem_id();
		if(!memId.equals(lbWriter.getText())) {
			alert("입력오류","삭제권한이 없습니다.");
			return;
		}
		try {
			parent = FXMLLoader.load(getClass().getResource("carPairingUpdate.fxml"));
			communityContentRoot.getChildren().setAll(parent);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		TextField txtTitle = (TextField) parent.lookup("#tfTitle");
		TextArea txtContent = (TextArea) parent.lookup("#taContent");
		TextField tfCarNum	= (TextField) parent.lookup("#tfcarNum");
    	TextField tfRentCost = (TextField) parent.lookup("#tfRentCost");
    	TextField tfDriveCost = (TextField) parent.lookup("#tfDriveCost");
    	TextField img1 = (TextField) parent.lookup("#imgFile1");
    	TextField img2 = (TextField) parent.lookup("#imgFile2");
    	TextField img3 = (TextField) parent.lookup("#imgFile3");
		
		txtTitle.setText(tfTitle.getText());
		txtContent.setText(taContent.getText());
		tfCarNum.setText(carNum.getText());
		tfRentCost.setText(rentCost.getText());
		tfDriveCost.setText(driveCost.getText());
		img1.setText(CarPairingSession.carPairingSessionImg1);
		img2.setText(CarPairingSession.carPairingSessionImg2);
		img3.setText(CarPairingSession.carPairingSessionImg3);
    }
    
    @FXML
    void img1choice(MouseEvent event) throws IOException {
    	CarPairingSession.carChoice = 1;
    	Stage dialog = new Stage(StageStyle.UTILITY);
    	dialog.initModality(Modality.WINDOW_MODAL);
    	Parent child = FXMLLoader.load(carPairingContentController.class.getResource("detailPairing.fxml"));
    	Scene scene = new Scene(child);
    	
    	dialog.setScene(scene);
		dialog.setTitle("css_detail_img");
		dialog.setResizable(false); 	//창크기 변경 불가
		dialog.show();
    }

    @FXML
    void img2choice(MouseEvent event) throws IOException {
    	CarPairingSession.carChoice = 2;
    	Stage dialog = new Stage(StageStyle.UTILITY);
    	dialog.initModality(Modality.WINDOW_MODAL);
    	Parent child = FXMLLoader.load(carPairingContentController.class.getResource("detailPairing.fxml"));
    	Scene scene = new Scene(child);
    	
    	dialog.setScene(scene);
		dialog.setTitle("css_detail_img");
		dialog.setResizable(false); 	//창크기 변경 불가
		dialog.show();
    }

    @FXML
    void img3choice(MouseEvent event) throws IOException {
    	CarPairingSession.carChoice = 3;
    	Stage dialog = new Stage(StageStyle.UTILITY);
    	dialog.initModality(Modality.WINDOW_MODAL);
    	Parent child = FXMLLoader.load(carPairingContentController.class.getResource("detailPairing.fxml"));
    	Scene scene = new Scene(child);
    	
    	dialog.setScene(scene);
		dialog.setTitle("css_detail_img");
		dialog.setResizable(false); 	//창크기 변경 불가
		dialog.show();
    }
    private Registry reg;
    private ICarPairingInsertService service;
    private ICarPairingUseService serviceUse;
    private ObservableList<CarPairingUseBoardVO> data;
    
   
   
    
    @FXML
    void initialize() {
    	try {
			reg = LocateRegistry.getRegistry("localhost", 8899);
			service = (ICarPairingInsertService) reg.lookup("carpairingInsert");
			serviceUse=(ICarPairingUseService)reg.lookup("carpairingUse");
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

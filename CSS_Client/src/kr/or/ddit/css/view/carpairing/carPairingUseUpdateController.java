package kr.or.ddit.css.view.carpairing;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import kr.or.ddit.css.service.carpairing.ICarPairingInsertService;
import kr.or.ddit.css.service.carpairing.ICarPairingUseService;
import kr.or.ddit.css.session.AdminLoginSession;
import kr.or.ddit.css.session.CarPairingSession;
import kr.or.ddit.css.session.CarPairingUseSession;
import kr.or.ddit.css.session.LoginSession;
import kr.or.ddit.css.vo.CarPairingInsertBoardVO;
import kr.or.ddit.css.vo.CarPairingUseBoardVO;
import kr.or.ddit.css.vo.CommunityBoardVO;

public class carPairingUseUpdateController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane carPairingUseUpdateRoot;

    @FXML
    private Label lbTitle;

    @FXML
    private Label lbTitle1;

    @FXML
    private TextField tfTitle;

    @FXML
    private TextField tfcarNum;

    @FXML
    private TextField tfRentCost;

    @FXML
    private TextField tfDriveCost;

    @FXML
    private TextArea taContent;

    @FXML
    private TextField imgFile1;

    @FXML
    private Button fileOpenBtn1;

    @FXML
    private TextField imgFile2;

    @FXML
    private Button fileOpenBtn2;

    @FXML
    private TextField imgFile3;

    @FXML
    private Button fileOpenBtn3;

    @FXML
    private Button okBtn;

    @FXML
    private Button cancleBtn;

    @FXML
    void cancleOk(MouseEvent event) throws IOException {
    	AnchorPane pane = FXMLLoader.load(getClass().getResource("../menu1/btn1CarPairingMenuInsert1.fxml"));
    	carPairingUseUpdateRoot.getChildren().setAll(pane);
    }

    @FXML
    void fileOpenBtnClicked1(ActionEvent event) {
    	FileChooser fc = new FileChooser();
    	fc.getExtensionFilters().add(new ExtensionFilter("Image File", "*.png", "*.jpg", "*.gif"));
    	File f = fc.showOpenDialog(null);
    	imgFile1.setText(f.getName());
    }

    @FXML
    void fileOpenBtnClicked2(ActionEvent event) {
    	FileChooser fc = new FileChooser();
    	fc.getExtensionFilters().add(new ExtensionFilter("Image File", "*.png", "*.jpg", "*.gif"));
    	File f = fc.showOpenDialog(null);
    	imgFile2.setText(f.getName());
    }

    @FXML
    void fileOpenBtnClicked3(ActionEvent event) {
    	FileChooser fc = new FileChooser();
    	fc.getExtensionFilters().add(new ExtensionFilter("Image File", "*.png", "*.jpg", "*.gif"));
    	File f = fc.showOpenDialog(null);
    	imgFile3.setText(f.getName());
    }

    @FXML
    void insertOk(ActionEvent event) throws IOException {
    	String boardTitle = tfTitle.getText();
    	if(boardTitle.isEmpty()) {
    		alert("입력오류","제목을 입력해주세요.");
    		tfTitle.requestFocus();
    		return;
    	}
    	
    	String carNum = tfcarNum.getText();
    	if(carNum.isEmpty()) {
    		alert("입력오류","차량 번호를 입력해주세요.");
    		tfcarNum.requestFocus();
    		return;
    	}
    	
    	String rentCost = tfRentCost.getText();
    	if(rentCost.isEmpty()) {
    		tfRentCost.requestFocus();
    		return;
    	}
    	
    	String driveCost = tfDriveCost.getText();
    	if(driveCost.isEmpty()) {
    		tfDriveCost.requestFocus();
    		return;
    	}
    	String boardContent = taContent.getText();
    	if(boardContent.isEmpty()) {
    		alert("입력오류","내용을 입력해주세요.");
    		taContent.requestFocus();
    		return;
    	}
    	
    	String img1 = imgFile1.getText();
    	if(img1.isEmpty()) {
    		alert("입력오류","위치정보를 등록 해주세요.");
    		imgFile1.requestFocus();
    		return;
    	}
    	String img2 = imgFile2.getText();
    	if(img2.isEmpty()) {
    		alert("입력오류","차량사진을 등록 해주세요.");
    		imgFile2.requestFocus();
    		return;
    	}
    	String img3 = imgFile3.getText();
    	if(img3.isEmpty()) {
    		alert("입력오류","내부사진을 등록 해주세요.");
    		imgFile3.requestFocus();
    		return;
    	}
    	CarPairingUseBoardVO CPUBVo = new CarPairingUseBoardVO();
    	int board_id = CarPairingUseSession.carPairingUseSession;
    	String memId = CarPairingUseSession.carPairingUseSession_memId;
    	String admin = AdminLoginSession.adminSession.getAdmin_id();
    	
    	CPUBVo.setPairinguseboard_id(board_id);
    	CPUBVo.setPairinguseboard_title(boardTitle);
    	CPUBVo.setPairinguseboard_carnum(carNum);
    	CPUBVo.setPairinguseboard_rentcost(Integer.parseInt(rentCost));
    	CPUBVo.setPairinguseboard_drivecost(Integer.parseInt(driveCost));
    	CPUBVo.setPairinguseboard_content(boardContent);
    	CPUBVo.setPairinguseboard_img1(img1);
    	CPUBVo.setPairinguseboard_img2(img2);
    	CPUBVo.setPairinguseboard_img3(img3);
    	CPUBVo.setMem_id(memId);
    	CPUBVo.setAdmin_id(admin);
    	
    	int cnt = service.updateBoard(CPUBVo);
    	if(cnt>0) {
    		ObservableList<CarPairingUseBoardVO> data_edit = FXCollections.observableArrayList();
    		List<CarPairingUseBoardVO> list_edit = new ArrayList<>();
    		
    		try {
				list_edit = service.getAllBoardList();
			} catch (RemoteException e2) {
				
				e2.printStackTrace();
			}
			for(CarPairingUseBoardVO bVO : list_edit) {
				data_edit.add(bVO);
			}
//    		List<CommunityBoardVO> comList = service.getAllBoardList();
//    		data=FXCollections.observableArrayList(comList);
//    		
    		infoMsg("작업성공","게시글을 수정하였습니다.");
    		
    		AnchorPane pane = FXMLLoader.load(getClass().getResource("../menu1/btn1CarPairingMenuInsert1.fxml"));
    		carPairingUseUpdateRoot.getChildren().setAll(pane);
    	}else {
    		alert("작업실패","게시글 수정을 실패하였습니다.");
    	}
    }
    
    private ICarPairingUseService service;
    private ObservableList<CarPairingUseBoardVO> data;
    private Registry reg;
    
    @FXML
    void initialize() {
    	try {
			reg = LocateRegistry.getRegistry("localhost", 8899);
			service = (ICarPairingUseService) reg.lookup("carpairingUse");
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

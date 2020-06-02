package kr.or.ddit.css.view.carpairing;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
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
import kr.or.ddit.css.session.LoginSession;
import kr.or.ddit.css.vo.CarPairingInsertBoardVO;

public class carPairingInsertController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane communityInsertRoot;

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
    private Button fileOpenBtn1;

    @FXML
    private TextField imgFile1;

    @FXML
    private Button fileOpenBtn2;

    @FXML
    private TextField imgFile2;

    @FXML
    private Button fileOpenBtn13;

    @FXML
    private TextField imgFile3;

    @FXML
    private Button okBtn;

    @FXML
    private Button cancleBtn;

    @FXML
    void cancleOk(MouseEvent event) throws IOException {
    	AnchorPane pane = FXMLLoader.load(getClass().getResource("carPairingMenu.fxml"));
    	communityInsertRoot.getChildren().setAll(pane);
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
    	
    	CarPairingInsertBoardVO CPIBVo = new CarPairingInsertBoardVO();
    	String memId = LoginSession.session.getMem_id();
    	
    	CPIBVo.setPairinginsertboard_title(boardTitle);
    	CPIBVo.setPairinginsertboard_carnum(carNum);
    	CPIBVo.setPairinginsertboard_rentcost(Integer.parseInt(rentCost));
    	CPIBVo.setPairinginsertboard_drivecost(Integer.parseInt(driveCost));
    	CPIBVo.setPairinginsertboard_content(boardContent);
    	CPIBVo.setPairinginsertboard_img1(img1);
    	CPIBVo.setPairinginsertboard_img2(img2);
    	CPIBVo.setPairinginsertboard_img3(img3);
    	CPIBVo.setMem_id(memId);
    	
    	int cnt = service.insertBoard(CPIBVo);
    	if(cnt>0) {
    		List<CarPairingInsertBoardVO> carPairList = service.getAllBoardList();
    		data=FXCollections.observableArrayList(carPairList);
    		
    		infoMsg("작업성공","게시글을 등록하였습니다.");
    		
    		AnchorPane pane = FXMLLoader.load(getClass().getResource("carPairingMenu.fxml"));
        	communityInsertRoot.getChildren().setAll(pane);
    	}else {
    		alert("작업실패","게시글 등록을 실패하였습니다.");
    	}
    }
    
    private ICarPairingInsertService service;
    private ObservableList<CarPairingInsertBoardVO> data;
    private Registry reg;
    
    @FXML
    void initialize() {
    	try {
			reg = LocateRegistry.getRegistry("localhost", 8899);
			service = (ICarPairingInsertService) reg.lookup("carpairingInsert");
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

package kr.or.ddit.css.view.menu2;

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
import kr.or.ddit.css.service.blacklist.IBlacklistService;
import kr.or.ddit.css.session.BlacklistSession;
import kr.or.ddit.css.session.LoginSession;
import kr.or.ddit.css.vo.BlackListVO;
import kr.or.ddit.css.vo.MemberVO;
import kr.or.ddit.css.vo.NoticeVO;

public class btn2CustomerServiceBlackListUpdateController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane noticeUpdateRoot;

    @FXML
    private Label lbTitle;

    @FXML
    private Label lbTitle1;

    @FXML
    private TextField tfTitle;

    @FXML
    private TextArea taContent;
    
    @FXML
    private TextField IdClaim;

    @FXML
    private Button okBtn;

    @FXML
    private Button cancleBtn;
    
	private IBlacklistService service;
	
	private ObservableList<BlackListVO> data;

	private NoticeVO NTCSession;
	
    @FXML
    private TextField imgFile1;

    @FXML
    private Button fileOpenBtn1;
    
    public void starter(NoticeVO NTCSession) {
    	this.NTCSession = NTCSession;
    }

    @FXML
    void cancleOk(MouseEvent event) throws IOException {
    	AnchorPane pane = FXMLLoader.load(getClass().getResource("btn2CustomerServiceBlackListMenu.fxml"));
    	noticeUpdateRoot.getChildren().setAll(pane);    	
    }
    
    @FXML
    void fileOpenBtnClicked1(ActionEvent event) {
    	FileChooser fc = new FileChooser();
    	fc.getExtensionFilters().add(new ExtensionFilter("Image File", "*.png", "*.jpg", "*.gif"));
    	File f = fc.showOpenDialog(null);
    	imgFile1.setText(f.getName());
    }

    @FXML
    void insertOk(ActionEvent event) throws IOException {
    	String boardTitle = tfTitle.getText();
    	if(boardTitle.isEmpty()) {
    		alert("입력오류","제목을 입력해주세요");
    		tfTitle.requestFocus();
    		return;
    	}
    	
    	String boardContent = taContent.getText();
    	if(boardContent.isEmpty()) {
    		alert("입력오류","내용을 입력해주세요");
    		taContent.requestFocus();
    		return;
    	}
    	
    	String claimId = IdClaim.getText();
    	if(claimId.isEmpty()) {
    		alert("입력오류","신고할 계정을 입력해주세요");
    		IdClaim.requestFocus();
    		return;
    	}

    	
    	String memId = LoginSession.session.getMem_id();
    	int blacklist_id = BlacklistSession.BlacklistSession;
    	//MemberVO memVo = new MemberVO();
    	BlackListVO bListVo = new BlackListVO();
    	String img1 = imgFile1.getText();
    	
    	bListVo.setBlacklist_id(blacklist_id);
    	bListVo.setBlacklist_title(boardTitle);
    	bListVo.setBlacklist_content(boardContent);
    	bListVo.setBlacklist_img(img1);
    	bListVo.setBlacklist_claimid(claimId);
    	System.out.println(img1);
    	bListVo.setMem_id(memId);
    	
    	int cnt = service.updateBoard(bListVo);
    	
    	if(cnt>0) {
//    		List<BlackListVO> bList = service.getAllBoardList();
//    		data = FXCollections.observableArrayList(bList);
    		ObservableList<BlackListVO> data_edit = FXCollections.observableArrayList();
    		List<BlackListVO> list_edit = new ArrayList<>();
    		
    		try {
				list_edit = service.getAllBoardList();
			} catch (RemoteException e2) {
				e2.printStackTrace();
			}
    		for(BlackListVO bVo : list_edit) {
    			data_edit.add(bVo);
    		}
    		
    		infoMsg("작업성공","게시글을 등록하였습니다.");
    		
    		AnchorPane pane = FXMLLoader.load(getClass().getResource("btn2CustomerServiceBlackListMenu.fxml"));
    		noticeUpdateRoot.getChildren().setAll(pane);
    	}else {
    		alert("작업실패","게시글 등록을 실패였습니다.");
    	}
    	
    }
    private Registry reg;

    @FXML
    void initialize() {
    	try {
    		reg = LocateRegistry.getRegistry("localhost", 8899);
    		//service = reg.lookup("blacklist");		
    		service = (IBlacklistService) reg.lookup("blacklist");
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

package kr.or.ddit.css.view.menu2;

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
import javafx.scene.layout.AnchorPane;
import kr.or.ddit.css.service.community.ICommunityService;
import kr.or.ddit.css.service.notice.INoticeService;
import kr.or.ddit.css.session.AdminLoginSession;
import kr.or.ddit.css.session.LoginSession;
import kr.or.ddit.css.vo.CommunityBoardVO;
import kr.or.ddit.css.vo.MemberVO;
import kr.or.ddit.css.vo.NoticeVO;

public class btn2CustomerServiceNoticeInsertController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane NoticeInsertRoot;

    @FXML
    private Label lbTitle;

    @FXML
    private Label lbTitle1;

    @FXML
    private TextField tfTitle;

    @FXML
    private TextArea taContent;

    @FXML
    private Button okBtn;

    @FXML
    private Button cancleBtn;

    @FXML
    void cancleOk(ActionEvent event) throws IOException {
    	//공지사항 메인으로 이동
    	AnchorPane pane = FXMLLoader.load(getClass().getResource("btn2CustomerServiceNoticeMenu.fxml"));
    	NoticeInsertRoot.getChildren().setAll(pane);
    }
    
    private Registry reg;
    private INoticeService service;
	
	private ObservableList<NoticeVO> data;
    
    @FXML
    void insertOk(ActionEvent event) throws IOException {
    	String boardTitle = tfTitle.getText();
    	if(boardTitle.isEmpty()) {
    		alert("입력오류","제목을 입력해주세요.");
    		tfTitle.requestFocus();
    		return;
    	}
    	
    	String boardContent = taContent.getText();
    	if(boardContent.isEmpty()) {
    		alert("입력오류","내용을 입력해주세요.");
    		taContent.requestFocus();
    		return;
    	}
    	String adminId = AdminLoginSession.adminSession.getAdmin_id();
    	
    	NoticeVO noticeVo = new NoticeVO(); 
    	noticeVo.setNotice_title(boardTitle);
    	noticeVo.setNotice_content(boardContent);
    	noticeVo.setAdmin_id(adminId);
    	
    	int cnt = service.insertNBoard(noticeVo);
    	if(cnt>0) {
    		List<NoticeVO> noticeList = service.getAllNBoardList();
    		data=FXCollections.observableArrayList(noticeList);
    		
    		infoMsg("작업성공","게시글을 등록하였습니다.");
    		
    		AnchorPane pane = FXMLLoader.load(getClass().getResource("btn2CustomerServiceNoticeMenu.fxml"));
    		NoticeInsertRoot.getChildren().setAll(pane);
    	}else {
    		alert("작업실패","게시글 등록을 실패하였습니다.");
    	}
    }

    @FXML
    void initialize() {
    	try {
			reg = LocateRegistry.getRegistry("localhost", 8899);
			service = (INoticeService) reg.lookup("notice");
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

package kr.or.ddit.css.view.menu2;

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
import kr.or.ddit.css.service.notice.INoticeService;
import kr.or.ddit.css.session.AdminLoginSession;
import kr.or.ddit.css.session.NoticeSession;
import kr.or.ddit.css.vo.NoticeVO;

public class btn2CustomerServiceNoticeUpdateController {

	private NoticeVO NTCSession;
	
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
    private Button okBtn;

    @FXML
    private Button cancleBtn;
    
    public void starter(NoticeVO NTCSession) {
    	this.NTCSession = NTCSession;
    }
    
    @FXML
    void cancleOk(MouseEvent event) throws IOException {
    	AnchorPane pane = FXMLLoader.load(getClass().getResource("btn2CustomerServiceNoticeContent.fxml"));
    	noticeUpdateRoot.getChildren().setAll(pane);
    }
    

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
    	
    	int notice_id = NoticeSession.NoticeSession;
    	NoticeVO noticeVo = new NoticeVO();
    	
    	noticeVo.setNotice_id(notice_id);
    	noticeVo.setNotice_title(boardTitle);
    	noticeVo.setNotice_content(boardContent);
    	
    	int cnt = service.updateNBorad(noticeVo);
    	if(cnt>0) {
    		ObservableList<NoticeVO> data_edit = FXCollections.observableArrayList();
    		List<NoticeVO> list_edit = new ArrayList<>();
    		
    		try {
				list_edit = service.getAllNBoardList();
			} catch (RemoteException e2) {
				
				e2.printStackTrace();
			}
			for(NoticeVO bVO : list_edit) {
				data_edit.add(bVO);
			}
//    		List<CommunityBoardVO> comList = service.getAllBoardList();
//    		data=FXCollections.observableArrayList(comList);
//    		
    		infoMsg("작업성공","게시글을 수정하였습니다.");
    		
    		AnchorPane pane = FXMLLoader.load(getClass().getResource("btn2CustomerServiceNoticeMenu.fxml"));
    		noticeUpdateRoot.getChildren().setAll(pane);
    	}else {
    		alert("작업실패","게시글 수정을 실패하였습니다.");
    	}
    }
    
    private INoticeService service;
    private ObservableList<NoticeVO> data;
    private Registry reg;
    
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

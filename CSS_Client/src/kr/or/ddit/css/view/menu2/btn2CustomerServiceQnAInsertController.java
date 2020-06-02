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
import kr.or.ddit.css.service.QnA.IQnAService;
import kr.or.ddit.css.service.notice.INoticeService;
import kr.or.ddit.css.session.AdminLoginSession;
import kr.or.ddit.css.vo.NoticeVO;
import kr.or.ddit.css.vo.QnAVO;

public class btn2CustomerServiceQnAInsertController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane QnAInsertRoot;

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
    	//QnA 메인으로 이동
    	AnchorPane pane = FXMLLoader.load(getClass().getResource("btn2CustomerServiceQnAMenu.fxml"));
    	QnAInsertRoot.getChildren().setAll(pane);
    }
    private Registry reg;
    private IQnAService service;
	
	private ObservableList<QnAVO> data;
    
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
    	
    	QnAVO qnaVo = new QnAVO(); 
    	qnaVo.setQna_title(boardTitle);
    	qnaVo.setQna_content(boardContent);
    	qnaVo.setAdmin_id(adminId);
    	
    	int cnt = service.insertQnA(qnaVo);
    	if(cnt>0) {
    		List<QnAVO> QnAList = service.getAllQnAList();
    		data=FXCollections.observableArrayList(QnAList);
    		
    		infoMsg("작업성공","게시글을 등록하였습니다.");
    		
    		AnchorPane pane = FXMLLoader.load(getClass().getResource("btn2CustomerServiceQnAMenu.fxml"));
    		QnAInsertRoot.getChildren().setAll(pane);
    	}else {
    		alert("작업실패","게시글 등록을 실패하였습니다.");
    	}
    }

    @FXML
    void initialize() {
    	try {
			reg = LocateRegistry.getRegistry("localhost", 8899);
			service = (IQnAService) reg.lookup("QnA");
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

package kr.or.ddit.css.view.menu1;

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
import kr.or.ddit.css.service.community.ICommunityService;
import kr.or.ddit.css.session.CommunitySession;
import kr.or.ddit.css.session.LoginSession;
import kr.or.ddit.css.vo.CommunityBoardVO;

public class btn1CommunityUpdateController {
	
	private ICommunityService service;
	
	private ObservableList<CommunityBoardVO> data;
	
    private Registry reg;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane communityUpdateRoot;

    @FXML
    private Label lbTitle;

    @FXML
    private Label lbTitle1;

    @FXML
    private TextField tfTitle;

    @FXML
    private TextArea taContent;

    @FXML
    private Button fileOpenBtn;

    @FXML
    private TextField imgName;

    @FXML
    private Button okBtn;

    @FXML
    private Button cancleBtn;

    @FXML
    void cancleOk(MouseEvent event) throws IOException {
    	AnchorPane pane = FXMLLoader.load(getClass().getResource("btn1CommunityMenu.fxml"));
    	communityUpdateRoot.getChildren().setAll(pane);
    }

    @FXML
    void fileOpenBtnClicked(ActionEvent event) {
    	FileChooser fc = new FileChooser();
    	fc.getExtensionFilters().add(new ExtensionFilter("Image File", "*.png", "*.jpg", "*.gif"));
    	File f = fc.showOpenDialog(null);
    	imgName.setText(f.getName());
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
    	String memId = LoginSession.session.getMem_id();
    	int board_id = CommunitySession.CommunitySession;
//    	int board_id = 2;
    	CommunityBoardVO CBoardVo = new CommunityBoardVO(); 
    	String imgFile = imgName.getText();
    	
//    	System.out.println(board_id);
    	
    	CBoardVo.setBoard_id(board_id);
    	CBoardVo.setBoard_title(boardTitle);
    	CBoardVo.setBoard_content(boardContent);
    	CBoardVo.setBoard_img(imgFile);
//    	CBoardVo.setBoard_title("Hello?");
//    	CBoardVo.setBoard_content("TESTing");
    	
    	int cnt = service.updateBoard(CBoardVo);
    	if(cnt>0) {
    		ObservableList<CommunityBoardVO> data_edit = FXCollections.observableArrayList();
    		List<CommunityBoardVO> list_edit = new ArrayList<>();
    		
    		try {
				list_edit = service.getAllBoardList();
			} catch (RemoteException e2) {
				
				e2.printStackTrace();
			}
			for(CommunityBoardVO bVO : list_edit) {
				data_edit.add(bVO);
			}
//    		List<CommunityBoardVO> comList = service.getAllBoardList();
//    		data=FXCollections.observableArrayList(comList);
//    		
    		infoMsg("작업성공","게시글을 수정하였습니다.");
    		
    		AnchorPane pane = FXMLLoader.load(getClass().getResource("btn1CommunityMenu.fxml"));
    		communityUpdateRoot.getChildren().setAll(pane);
    	}else {
    		alert("작업실패","게시글 수정을 실패하였습니다.");
    	}
    }

    @FXML
    void initialize() {
    	try {
			reg = LocateRegistry.getRegistry("localhost", 8899);
			service = (ICommunityService) reg.lookup("community");
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

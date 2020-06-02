package kr.or.ddit.css.view.menu1;

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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import kr.or.ddit.css.service.community.ICommunityService;
import kr.or.ddit.css.session.AdminLoginSession;
import kr.or.ddit.css.session.CarPairingSession;
import kr.or.ddit.css.session.CommunitySession;
import kr.or.ddit.css.session.LoginSession;
import kr.or.ddit.css.view.carpairing.carPairingContentController;
import kr.or.ddit.css.vo.CommunityBoardVO;

public class btn1CommunityContentController {
	
	//커뮤니티 게시글 내용보기

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
    private Label lbWriter;

    @FXML
    private Label lbDate;

    @FXML
    private Label tfTitle;

    @FXML
    private TextArea taContent;

    @FXML
    private Button updateBtn;
    
    @FXML
    private Button deleteBtn;

    @FXML
    private Button listBtn;
    
    @FXML
    void imgCicked(MouseEvent event) throws IOException {
    	Stage dialog = new Stage(StageStyle.UTILITY);
    	dialog.initModality(Modality.WINDOW_MODAL);
    	Parent child = FXMLLoader.load(btn1CommunityContentController.class.getResource("detailComImg.fxml"));
    	Scene scene = new Scene(child);
    	
    	dialog.setScene(scene);
		dialog.setTitle("css_detail_img");
		dialog.setResizable(false); 	//창크기 변경 불가
		dialog.show();
    }

    @FXML
    void deleteOk(ActionEvent event) throws IOException {
    	
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
		
		int num = CommunitySession.CommunitySession;
		System.out.println(num);
	
		int cnt = 0;
		try {
			cnt = service.deleteBoard(num);
		} catch (RemoteException e1) {
			
			e1.printStackTrace();
		}
		if(cnt > 0) {
			infoMsg("정보","글 삭제가 완료되었습니다");
			ObservableList<CommunityBoardVO> data_edit = FXCollections.observableArrayList();
			List<CommunityBoardVO> list_edit = new ArrayList<>();
			
			AnchorPane pane = FXMLLoader.load(getClass().getResource("btn1CommunityMenu.fxml"));
			communityContentRoot.getChildren().setAll(pane);
		}
		
			}else {
				alert("입력오류","게시글 삭제를 실패하였습니다.");
				
			}
    }

    @FXML
    void listViewClicked(MouseEvent event) throws IOException {
    	AnchorPane pane = FXMLLoader.load(getClass().getResource("btn1CommunityMenu.fxml"));
    	communityContentRoot.getChildren().setAll(pane);
    }

    @FXML
    void updateOk(ActionEvent event) {
    	Parent parent = null;
    	String memId = LoginSession.session.getMem_id();
		if(!memId.equals(lbWriter.getText()))
    		return;
		try {
			parent = FXMLLoader.load(getClass().getResource("btn1CommunityUpdate.fxml"));
			communityContentRoot.getChildren().setAll(parent);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		TextField txtTitle = (TextField) parent.lookup("#tfTitle");
		TextArea txtContent = (TextArea) parent.lookup("#taContent");
		TextField img1 = (TextField) parent.lookup("#imgName");
		
		txtTitle.setText(tfTitle.getText());
		txtContent.setText(taContent.getText());
		img1.setText(CommunitySession.CommunityImg);
    }

    
    private Registry reg;
    private ICommunityService service;
    private ObservableList<CommunityBoardVO> data;
    
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
	public void infoMsg(String headerText, String msg) {
	      Alert alert = new Alert(AlertType.INFORMATION);
	      alert.setTitle("정보 확인");
	      alert.setHeaderText(headerText);
	      alert.setContentText(msg);
	      alert.showAndWait();
  
	}
}

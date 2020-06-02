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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import kr.or.ddit.css.service.blacklist.IBlacklistService;
import kr.or.ddit.css.service.signUp.ISignUpService;
import kr.or.ddit.css.session.AdminLoginSession;
import kr.or.ddit.css.session.BlacklistSession;
import kr.or.ddit.css.session.LoginSession;
import kr.or.ddit.css.view.carpairing.carPairingContentController;
import kr.or.ddit.css.vo.BlackListVO;

public class btn2CustomerServiceBlackListContentController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane BlackListContentRoot;

    @FXML
    private Label lbTitle;

    @FXML
    private Label lbTitle1;

//    @FXML
//    private Label tfTitle;
//    

    @FXML
    private TextField tfTitle;
    
    @FXML
    private Label lbWriter;

    @FXML
    private Label lbDate;

    @FXML
    private TextArea taContent;

//    @FXML
//    private Button fileOpenBtn;

//    @FXML
//    private TextField imgName;

    @FXML
    private Button updateBtn;

    @FXML
    private Button deleteBtn;

    @FXML
    private Button listBtn;
    
    @FXML
    private ImageView imgFile1;


    @FXML
    private TextField IdClaim;
    
    @FXML
    private Button blacklistMemberUpdate;

    private ISignUpService signUpService;
    
	private Stage primaryStage;
    
    public void setPrimaryStage(Stage primaryStage) {
    	
    	
    	this.primaryStage = primaryStage;
    	 
    }
    
    @FXML
    void blacklistMemberUpdateClicked(MouseEvent event) throws IOException {
    	
    	

    	String memId= IdClaim.getText();
//    	if(memId.isEmpty()) {
//    		alert("입력오류","신고 아이디를 입력하세요.");
//    		IdClaim.requestFocus();
//    		return;
//    	}
    	int cnt = 0;
    	try {
    		//System.out.println(memId);
			cnt = signUpService.blacklistMemberUpdate(memId);
			if(cnt>0) {
				//성공
				infoMsg("작업성공", memId + " 회원 정보를 블랙리스트에 추가했습니다.");

//	    		FXMLLoader loader = new FXMLLoader(getClass().getResource("../menu2/btn2CustomerServiceBlackListMenu.fxml"));
//	    		
//	    		AnchorPane root = loader.load();
//	    		
//	    		Scene scene = new Scene(root);
//	    		
//	    		projectLoginController ctl = loader.getController();
//	    		
//	    		primaryStage.setScene(scene);
	    		
	    		//thisStage.close();
				
		           AnchorPane pane = FXMLLoader.load(getClass().getResource("../menu2/btn2CustomerServiceBlackListMenu.fxml"));
		           BlackListContentRoot.getChildren().setAll(pane);
				
			}else {
				//실패
				alert("작업실패", memId + " 회원 정보 블랙리스트에 추가 실패했습니다.");
				IdClaim.requestFocus();
				
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    

    @FXML
    void img1choice(MouseEvent event) throws IOException {
    	Stage dialog = new Stage(StageStyle.UTILITY);
    	dialog.initModality(Modality.WINDOW_MODAL);
    	Parent child = FXMLLoader.load(btn2CustomerServiceBlackListContentController.class.getResource("detailBlist.fxml"));
    	Scene scene = new Scene(child);
    	
    	dialog.setScene(scene);
		dialog.setTitle("css_detail_img");
		dialog.setResizable(false); 	//창크기 변경 불가
		dialog.show();
    }

    @FXML
    void deleteOk(ActionEvent event) throws IOException {
       
       //String adminId = AdminLoginSession.adminSession.getAdmin_id();   //관리자 아이디
       
       if(AdminLoginSession.adminSession==null) {
    	   String memId = LoginSession.session.getMem_id();            //회원 아이디
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
      
        int num = BlacklistSession.BlacklistSession;
        System.out.println(num);
     
        int cnt = 0;
        try {
           cnt = service.deleteBoard(num);
        } catch (RemoteException e1) {
           e1.printStackTrace();
        }
        if(cnt > 0) {
           infoMsg("정보","글 삭제가 완료되었습니다.");
           ObservableList<BlackListVO> data_edit = FXCollections.observableArrayList();
           List<BlackListVO> list_edit = new ArrayList<>();
           
           AnchorPane pane = FXMLLoader.load(getClass().getResource("btn2CustomerServiceBlackListMenu.fxml"));
           BlackListContentRoot.getChildren().setAll(pane);
        }
     
         }else {
            alert("입력오류","게시글 삭제를 실패하였습니다.");
         }
      }
//       }else if(adminId != null) {   // 관리자 로그인
//          
       
   

    @FXML
    void listViewClicked(MouseEvent event) throws IOException {
       //블랙리스트 메인으로 이동
       AnchorPane pane = FXMLLoader.load(getClass().getResource("btn2CustomerServiceBlackListMenu.fxml"));
       BlackListContentRoot.getChildren().setAll(pane);
     
   		
    }

    @FXML
    void updateOk(ActionEvent event) {
    	Parent parent = null;
    	String memId = LoginSession.session.getMem_id();
    	if(!memId.equals(lbWriter.getText())) {
    		alert("경고","수정 권한이 없습니다.");
    		return;    		
    	}
    	try {
			parent = FXMLLoader.load(getClass().getResource("btn2CustomerServiceBlackListUpdate.fxml"));
			BlackListContentRoot.getChildren().setAll(parent);
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	
    	TextField txtTitle = (TextField) parent.lookup("#tfTitle");
    	TextField txtClaim = (TextField) parent.lookup("#IdClaim");
    	TextArea txtContent = (TextArea) parent.lookup("#taContent");
    	TextField img1 = (TextField) parent.lookup("#imgFile1");
    	
    	txtTitle.setText(tfTitle.getText());
    	txtContent.setText(taContent.getText());
    	txtClaim.setText(IdClaim.getText());
    	img1.setText(BlacklistSession.BlacklistSessionimg1);
    }

    private IBlacklistService service;
    
    @FXML
    void initialize() {
    	
       Registry reg;
       try {
         reg = LocateRegistry.getRegistry("localhost", 8899);
         service = (IBlacklistService) reg.lookup("blacklist");
         signUpService = (ISignUpService) reg.lookup("signUp");
         
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
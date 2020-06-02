package kr.or.ddit.css.view.menu;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import kr.or.ddit.css.service.signUp.ISignUpService;
import kr.or.ddit.css.session.LoginSession;
import kr.or.ddit.css.view.login.AES256Util;
import kr.or.ddit.css.view.login.projectLoginController;

public class projectSignOutController {
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane signOutRoot;

    @FXML
    private Label lbTitle;

    @FXML
    private Label lbTitle11;

    @FXML
    private Label lbTitle1;

    @FXML
    private TextField tfPw;

    @FXML
    private Button pwCheckBtn;

    @FXML
    private Label lbTitle11111;

    @FXML
    private CheckBox cb1;

    @FXML
    private Label lbTitle111111;

    @FXML
    private CheckBox cb2;

    @FXML
    private Label lbTitle1111111;

    @FXML
    private CheckBox cb3;

    @FXML
    private Label lbTitle1111112;

    @FXML
    private CheckBox cb4;

    @FXML
    private Label lbTitle1111113;

    @FXML
    private CheckBox cb5;

    @FXML
    private Label lbTitle1111114;

    @FXML
    private CheckBox cb6;

    @FXML
    private Label lbTitle1111115;

    @FXML
    private CheckBox cb7;

    @FXML
    private Label lbTitle1111116;

    @FXML
    private CheckBox cb8;

    @FXML
    private Label lbTitle11111161;

    @FXML
    private CheckBox cb9;

    @FXML
    private Label lbTitle11111163;

    @FXML
    private TextArea textArea;

    @FXML
    private Button signOut;

    private ISignUpService signService;
    
	private Stage primaryStage;
    
    public void setPrimaryStage(Stage primaryStage) {
    	this.primaryStage = primaryStage;
    }
    
    @FXML
    void pwClicked(ActionEvent event) throws Exception{	// pw 확인 메서드
    	// pw
    	
    	HashMap<String, String> map = new HashMap<String, String>();
    	
    	String memId = "";
    	memId = LoginSession.session.getMem_id();
    	
    	AES256Util aes256 = new AES256Util();
    	
    	String encryptedPw = ""; // 암호화된 pw
		encryptedPw = aes256.encrypt(tfPw.getText());
		
		map.put("memId", memId);
		map.put("encryptedPw", encryptedPw);
		
		String currentPw = "";
		
		currentPw = signService.checkPw(map);
		if(!tfPw.getText().equals("")) {
			if(currentPw!=null) {
				infoMsg("확인","비밀번호가 맞습니다.");
	    		return;
			}else if(currentPw==null) {
				alert("입력오류","비밀번호가 맞지 않습니다.");
				tfPw.requestFocus();
	    		return;
			}
		}else {
			alert("입력오류","비밀번호를 입력해주세요.");
			tfPw.requestFocus();
		}
		
	
    }

    @FXML
    void signOutClicked(ActionEvent event) throws IOException {	// 회원 탈퇴 메서드
    	Stage thisStage = (Stage) lbTitle11111163.getScene().getWindow();
    	
    	String reason = "";
    	if(cb1.isSelected()) {
    		reason += "사고 처리 과정에 불만이 있어요.\n";
    	}
    	if(cb2.isSelected()) {
    		reason += "고객 상담에 불만이 있어요.\n";
    	}
    	if(cb3.isSelected()) {
    		reason += "가격이 비싸요.\n";
    	}
    	if(cb4.isSelected()) {
    		reason += "원하는 차종이 없어요.\n";
    	}
    	if(cb5.isSelected()) {
    		reason += "사용 방법이 어려워요.\n";
    	}
    	if(cb6.isSelected()) {
    		reason += "면허 1년 미만이라 사용할 수 없어요.\n";
    	}
    	if(cb7.isSelected()) {
    		reason += "중복가입으로 해당 게정을 탈퇴할게요.\n";
    	}
    	if(cb8.isSelected()) {
    		reason += "면허정보 등 개인 정보를 변경할 거예요.\n";
    	}
    	if(cb9.isSelected()) {
    		reason += textArea.getText()+"\n";
    	}
    	
//		회원ID값 가져오기
    	String memId = LoginSession.session.getMem_id();
//		String memActive = LoginSession.session.getMem_active();
		System.out.println("mem_active : "+LoginSession.session.getMem_active());
		int cnt = signService.checkActive(memId);	// DB에서 비활성화하기
		System.out.println("cnt : " + cnt);
		
		if(cnt>0) {	// DB에서 비활성화 성공
			infoMsg("삭제성공",memId + " 회원 정보를 삭제했습니다.");
    		
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("../login/projectLogin.fxml"));
    		
    		AnchorPane root = loader.load();
    		
    		Scene scene = new Scene(root);
    		
    		projectLoginController ctl = loader.getController();
    		
    		primaryStage.setScene(scene);
    		
    		thisStage.close();
		}else {
			alert("작업실패",memId + " 회원 정보 삭제를 실패했습니다.");
		}
    }
    
    @FXML
    void initialize() {
    	Registry reg;
		try {
			reg = LocateRegistry.getRegistry("localhost",8899);
			signService = (ISignUpService) reg.lookup("signUp");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		
    }
    
  //----------------------------------------------------------alert, infoMsg, confirm 메서드 설정 ---------------------------
    public void alert(String head, String msg) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("경고");
		alert.setHeaderText(head);
		alert.setContentText(msg);
		
		alert.showAndWait();
	}
	
	public void infoMsg(String head, String msg) {
		Alert info = new Alert(AlertType.INFORMATION);
		info.setTitle("정보");
		info.setHeaderText(head);
		info.setContentText(msg);
		
		info.showAndWait();
	}
    
    public ButtonType confirm(String head, String msg) {
    	Alert confirm = new Alert(AlertType.CONFIRMATION);
    	confirm.setTitle("확인");
    	confirm.setHeaderText(head);
    	confirm.setContentText(msg);
    	
    	return confirm.showAndWait().get();
    }
    //--------------------------------------------------------------------------------------------------------------------
}

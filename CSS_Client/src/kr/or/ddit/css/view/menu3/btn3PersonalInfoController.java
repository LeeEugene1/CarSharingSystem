package kr.or.ddit.css.view.menu3;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import kr.or.ddit.css.service.community.ICommunityService;
import kr.or.ddit.css.service.login.ILoginService;
import kr.or.ddit.css.service.signUp.ISignUpService;
import kr.or.ddit.css.session.LoginSession;
import kr.or.ddit.css.view.signUp.AES256Util;
import kr.or.ddit.css.view.signUp.PostCodeModule2;
import kr.or.ddit.css.vo.MemberVO;
import oracle.sql.LobPlsqlUtil;
import javafx.fxml.Initializable;


public class btn3PersonalInfoController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane root;

    @FXML
    private Label lbTitle;

    @FXML
    private Label lbTitle11;

    @FXML
    private Label lbTitle1;

    @FXML
    private TextField tfId;

    @FXML
    private TextField tfName;

    @FXML
    private PasswordField tfPw;

    @FXML
    private PasswordField tfPw2;

    @FXML
    private TextField tfBirth;

    @FXML
    private TextField tfEmail;

    @FXML
    private TextField tfEmail2;

    @FXML
    private ComboBox<String> emailCombo;
    private ObservableList<String> ComboList = FXCollections.observableArrayList("직접입력","naver.com","daum.net","gmail.com","yahoo.com");

    @FXML
    private RadioButton rd1;

    @FXML
    private ToggleGroup gender;

    @FXML
    private RadioButton rd2;

    @FXML
    private TextField tfTel1;

    @FXML
    private TextField tfTel2;

    @FXML
    private TextField tfTel3;

    @FXML
    private TextField tfAdd1;

    @FXML
    private Button add;

    @FXML
    private TextField tfAdd2;

    @FXML
    private Button signUp;
    
    public TextField getTfAdd1() {
		return tfAdd1;
	}

	public void setTfAdd1(TextField tfAdd1) {
		this.tfAdd1 = tfAdd1;
	}

	public Stage primaryStage3 = new Stage();
    
    @FXML
    void addrClicked(ActionEvent event) throws Exception {
    	LoginSession.bpic = this;
    	PostCodeModule2 post = new PostCodeModule2();
    	post.start(primaryStage3);
    }
	
    @FXML
    void emailComboChecked(ActionEvent event) {	// 이메일 콤보박스 메서드
    	if(emailCombo.getValue().equals("직접입력")) {
    		tfEmail2.setText(null);
    		tfEmail2.requestFocus();
    	}else {
    		tfEmail2.setText(emailCombo.getValue());
    	}
    }

    @FXML
    void updateClicked(ActionEvent event){
    	
    	try {
    		AES256Util aes = new AES256Util();
    		
    		String memPw = tfPw.getText();
			String aesPw = aes.encrypt(memPw);
			
			if(memPw.isEmpty()) {
	    		alert("입력오류","회원 비밀번호를 입력하세요.");
	    		tfPw.requestFocus();
	    		return;
	    	}
	    	
	    	String memPwCheck = tfPw2.getText();
	    	if(memPwCheck.isEmpty()) {
	    		alert("입력오류","회원 비밀번호를 입력하세요.");
	    		tfPw2.requestFocus();
	    		return;
	    	}else if(!memPw.equals(memPwCheck)) {
	    		alert("입력오류","회원 비밀번호가 동일하지 않습니다.");
	    		tfPw2.requestFocus();
	    		return;
	    	}
	    	
	    	String memEmail = tfEmail.getText();
	    	if(memEmail.isEmpty()) {
	    		alert("입력오류","회원 이메일 아이디를 입력하세요.");
	    		tfEmail.requestFocus();
	    		return;
	    	}
	    	
	    	
	    	String memEmail2 = tfEmail2.getText();
	    	if(memEmail2.isEmpty()) {
	    		alert("입력오류","회원 이메일을 입력하세요.");
	    		tfEmail2.requestFocus();
	    		return;
	    	}
	    	
	    	String memTel1 = tfTel1.getText();
	    	if(memTel1.isEmpty()) {
	    		alert("입력오류","회원 전화번호를 입력하세요.");
	    		tfTel1.requestFocus();
	    		return;
	    	}
	    	
	    	String memTel2 = tfTel2.getText();
	    	if(memTel2.isEmpty()) {
	    		alert("입력오류","회원 전화번호를 입력하세요.");
	    		tfTel2.requestFocus();
	    		return;
	    	}
	    	
	    	String memTel3 = tfTel3.getText();
	    	if(memTel3.isEmpty()) {
	    		alert("입력오류","회원 전화번호를 입력하세요.");
	    		tfTel3.requestFocus();
	    		return;
	    	}
	    	
	    	String memAddr1 = tfAdd1.getText();
	    	if(memAddr1.isEmpty()) {
	    		alert("입력오류","회원 기본 주소를 입력하세요.");
	    		tfAdd1.requestFocus();
	    		return;
	    	}
	    	
	    	String memAddr2 = tfAdd2.getText();
	    	if(memAddr2.isEmpty()) {
	    		alert("입력오류","회원 상세 주소를 입력하세요.");
	    		tfAdd2.requestFocus();
	    		return;
	    	}
			
			MemberVO memVo = new MemberVO();
	    	memVo.setMem_pw(aesPw);
	    	memVo.setMem_email(memEmail +"@"+ memEmail2);
	    	memVo.setMem_tel(memTel1 +"-"+memTel2+"-"+memTel3);
	    	memVo.setMem_addr(memAddr1 +"@"+ memAddr2);
	    	
	    	String memId = LoginSession.session.getMem_id();
	    	memVo.setMem_id(memId);
	    	
			// DB에 수정 작업
			int cnt = signUpService.updateMember(memVo);
			
			if(cnt>0) {
				infoMsg("작업성공", tfName.getText() + " 회원 정보를 수정했습니다.");
				//로그인페이지 열리게하는 소스
		    	AnchorPane pane = FXMLLoader.load(getClass().getResource("../menu/projectMenu.fxml"));
		    	//Stage primaryStage = 
		    	root.getScene().setRoot(pane);
			}else {
				alert("작업실패", tfName.getText()+" 회원의 수정 작업 실패!!");
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (GeneralSecurityException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    }
    
    private ISignUpService signUpService;
    private ILoginService loginService;
    
    @FXML
    void initialize() throws RemoteException {
    	Registry reg;
		try {
			reg = LocateRegistry.getRegistry("localhost",8899);
			signUpService = (ISignUpService) reg.lookup("signUp");
			loginService = (ILoginService) reg.lookup("login");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
    	
		String memId = LoginSession.session.getMem_id();
		
		List<MemberVO> memList = new ArrayList<MemberVO>();
		
		memList = loginService.login(memId);
		if(memList==null) {
			alert("입력오류", "회원 정보를 읽어오는데 실패했습니다.");
		}else if(memList!=null){
			tfId.setText(memId);
	    	tfName.setText(memList.get(0).getMem_name());
	    	tfPw.setText(memList.get(0).getMem_pw());
	        tfPw2.setText(memList.get(0).getMem_pw());
	        tfBirth.setText(memList.get(0).getMem_birth());
	        
	        String mail = memList.get(0).getMem_email();
	        int idx = mail.indexOf("@"); 
	        System.out.println("idx : " + idx);
	        String mail1 = mail.substring(0, idx);
	        String mail2 = mail.substring(idx+1);
	        tfEmail.setText(mail1);
	        tfEmail2.setText(mail2);
	        emailCombo.setItems(ComboList);

	        if(memList.get(0).getMem_gender().equals("남자")) {
	        	gender.selectToggle(rd1);
	        }else if(memList.get(0).getMem_gender().equals("여자")) {
	        	gender.selectToggle(rd2);
	        }
	        
	        tfTel1.setText(memList.get(0).getMem_tel().substring(0, 3));
	        tfTel2.setText(memList.get(0).getMem_tel().substring(4, 8));
	        tfTel3.setText(memList.get(0).getMem_tel().substring(9, 13));
	        
	        String addr = memList.get(0).getMem_addr();
	        int idx2 = addr.indexOf("@");
	        String addr1 = addr.substring(0, idx2);
	        String addr2 = addr.substring(idx2+1);
	        tfAdd1.setText(addr1);
	        tfAdd2.setText(addr2);
	        
	        tfId.setEditable(false);
	        tfName.setEditable(false);
	        tfPw.setEditable(true);
	        tfPw2.setEditable(true);
	        tfBirth.setEditable(false);
	        tfEmail.setEditable(true);
	        tfEmail2.setEditable(true);
	        tfTel1.setEditable(true);
	        tfTel2.setEditable(true);
	        tfTel3.setEditable(true);
	        tfAdd1.setEditable(false);
	        tfAdd2.setEditable(true);
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

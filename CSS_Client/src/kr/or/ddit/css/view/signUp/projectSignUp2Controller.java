package kr.or.ddit.css.view.signUp;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import kr.or.ddit.css.service.login.ILoginService;
import kr.or.ddit.css.service.signUp.ISignUpService;
import kr.or.ddit.css.session.LoginSession;
import kr.or.ddit.css.view.menu1.MapModule;
import kr.or.ddit.css.vo.MemberVO;

public class projectSignUp2Controller {

	@FXML
    private AnchorPane signUpRoot;
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField tfId;

    @FXML
    private Button idCheckBtn;

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

    @FXML
    private Button cancle;

    public TextField getTfAdd1() {
		return tfAdd1;
	}

	public void setTfAdd1(TextField tfAdd1) {
		this.tfAdd1 = tfAdd1;
	}

    public Stage primaryStage3 = new Stage();
    @FXML
    void addrClicked(ActionEvent event) throws Exception {	// 우편번호 버튼 메서드
    	LoginSession.psuc = this;
    	PostCodeModule post = new PostCodeModule();
    	post.start(primaryStage3);
    }
    
    @FXML
    void cancleClicked(ActionEvent event) throws IOException {	// 회원가입 취소하기 버튼 메서드
    	//로그인페이지 열리게하는 소스
    	AnchorPane pane = FXMLLoader.load(getClass().getResource("../login/projectLogin.fxml"));
    	//Stage primaryStage = 
    	signUpRoot.getScene().setRoot(pane);
    }
    
    @FXML
    void emailComboChecked(ActionEvent event) {	// 이메일 콤보박스 메서드
    	System.out.println("emailCombo.getValue() : " + emailCombo.getValue());
    	if(emailCombo.getValue().equals("직접입력")) {
    		tfEmail2.setText(null);
    		tfEmail2.requestFocus();
    	}else {
    		System.out.println("emailCombo.getValue() : " + emailCombo.getValue());
    		tfEmail2.setText(emailCombo.getValue());
    	}
    }
    
    @FXML
    void idClicked(ActionEvent event) throws RemoteException {	// 아이디 중복확인 메서드
		String currentId = signUpService.checkId(tfId.getText());
		if(!tfId.getText().equals("")) {
			if(currentId != null) {
				alert("입력오류","중복된 아이디입니다.");
	    		tfId.requestFocus();
	    		return;
			}
//			else {
//				infoMsg("확인","사용 가능한 아이디입니다.");
//	    		tfName.requestFocus();
//	    		return;
//			}
			else if(!RegEx.checkMem_id(tfId.getText())) {
				alert("입력오류","영문대소문자,숫자 4-15글자를 입력하세요");
				tfId.requestFocus();
			}else {
				infoMsg("확인","사용 가능한 아이디입니다.");
	    		tfName.requestFocus();
	    		return;
			}
		}else {
			alert("입력오류","아이디를 입력해주세요.");
    		tfId.requestFocus();
		}
		
    }

    @FXML
    void signUpClicked(ActionEvent event) throws Exception {	// 회원가입 가입하기 버튼 메서드
    	// 현재 TextField에 입력한 내용을 가져온다
    	String memId = tfId.getText();
    	if(memId.isEmpty()) {
    		alert("입력오류","회원 ID를 입력하세요.");
    		tfId.requestFocus();
    		return;
    	}
    	else if(!RegEx.checkMem_id(tfId.getText())) {
			alert("입력오류","영문대소문자,숫자 4-15글자를 입력하세요");
			tfId.requestFocus();
			return;
		}
    	
    	String memName = tfName.getText();
    	if(memName.isEmpty()) {
    		alert("입력오류","회원 이름을 입력하세요.");
    		tfName.requestFocus();
    		return;
    	}
    	
    	AES256Util aes = new AES256Util();
//    	aes.encrypt("암호화");
//    	aes.decrypt("복호화");
    	
    	String memPw = tfPw.getText();
    	String encPass = aes.encrypt(memPw);
    	if(memPw.isEmpty()) {
    		alert("입력오류","회원 비밀번호를 입력하세요.");
    		tfPw.requestFocus();
    		return;
    	}if(!RegEx.checkMem_pw(memPw)) {
    		alert("입력오류","비밀번호 4~8글자를 입력하세요.");
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
    	
    	String memBirth = tfBirth.getText();
    	if(memBirth.isEmpty()) {
    		alert("입력오류","회원 생년월일을 입력하세요.");
    		tfBirth.requestFocus();
    		return;
    	}
    	
    	String memEmail = tfEmail.getText();
    	if(memEmail.isEmpty()) {
    		alert("입력오류","회원 이메일 아이디를 입력하세요.");
    		tfEmail.requestFocus();
    		return;
    	}
    	if(!RegEx.checkMem_id(memEmail)) {
    		alert("입력오류","이메일 아이디 영문대소문자,숫자 4-15글자를 입력하세요");
    		tfEmail.requestFocus();
    		return;
    	}
    	
    	String memEmail2 = tfEmail2.getText();
    	if(memEmail2.isEmpty()) {
    		alert("입력오류","회원 이메일을 입력하세요.");
    		tfEmail2.requestFocus();
    		return;
    	}
    	
    	String memGender = "";
    	if(rd1.isSelected()) {
    		memGender = "남자";
    	}else if(rd2.isSelected()) {
    		memGender = "여자";
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
    	
    	MemberVO memVo = new MemberVO();
    	memVo.setMem_id(memId);
    	memVo.setMem_name(memName);
    	memVo.setMem_pw(encPass);
    	memVo.setMem_birth(memBirth);
    	memVo.setMem_email(memEmail +"@"+ memEmail2);
    	memVo.setMem_gender(memGender);
    	memVo.setMem_tel(memTel1 +"-"+memTel2+"-"+memTel3);
    	memVo.setMem_addr(memAddr1 +"@"+ memAddr2);
    	memVo.setMem_class("패밀리");
    	memVo.setMem_blacklist("N");
    	memVo.setBlacklist_point(0);
    	memVo.setMem_active("Y");
    	
		// DB에 추가 작업
		int cnt = signUpService.insertMember(memVo);
		
		if(cnt>0) {
			infoMsg("작업성공", memName + " 회원 정보를 추가했습니다.");
			//로그인페이지 열리게하는 소스
	    	AnchorPane pane = FXMLLoader.load(getClass().getResource("../login/projectLogin.fxml"));
	    	//Stage primaryStage = 
	    	signUpRoot.getScene().setRoot(pane);
		}else {
			alert("작업실패", memName+" 회원의 추가 작업 실패!!");
		}
    }
    
    private ObservableList<String> ComboList = FXCollections.observableArrayList("직접입력","naver.com","daum.net","gmail.com","yahoo.com");
    private ObservableList<MemberVO> data;
    private ISignUpService signUpService;
    
    @FXML
    void initialize() {
    	Registry reg;
		try {
			reg = LocateRegistry.getRegistry("localhost",8899);
			signUpService = (ISignUpService) reg.lookup("signUp");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
    	
    	emailCombo.setItems(ComboList);
    	
    	tfName.setEditable(false);
    	tfBirth.setEditable(false);
    	tfTel1.setEditable(false);
    	tfTel2.setEditable(false);
    	tfTel3.setEditable(false);
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


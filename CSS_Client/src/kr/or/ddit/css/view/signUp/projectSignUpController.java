package kr.or.ddit.css.view.signUp;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.HashMap;
import java.util.ResourceBundle;

import org.json.simple.JSONObject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import kr.or.ddit.css.service.signUp.ISignUpService;
import kr.or.ddit.css.session.LoginSession;
import kr.or.ddit.css.smsApi.Coolsms;

public class projectSignUpController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane signUpRoot;

    @FXML
    private TextField tfName;

    @FXML
    private TextField tfBirth;

    @FXML
    private TextField tfTel1;

    @FXML
    private TextField tfTel2;

    @FXML
    private TextField tfTel3;

    @FXML
    private TextField tfConfirm;

    @FXML
    void cancleClicked(ActionEvent event) throws IOException {
    	// 회원가입 취소하기 버튼 메서드
    	//로그인페이지 열리게하는 소스
    	AnchorPane pane = FXMLLoader.load(getClass().getResource("../login/projectLogin.fxml"));
    	//Stage primaryStage = 
    	signUpRoot.getScene().setRoot(pane);
    }

    @FXML
    void confirmBtnClicked(ActionEvent event) {	// 인증번호 받는 버튼
    	
//    	@Override
// 	   public String scanID() {
// 	      Scanner in = new Scanner(System.in);
// 	      System.out.println("ID를 입력해 주세요.");
// 	      while(true){
// 	         try{
//// 	            System.out.println("(영문 대소문자 또는 숫자로 시작하는 아이디, 길이는 4~15자)");
// 	            String input = in.nextLine();
// 	            if(RegEx.checkMem_id(input)){
// 	               return input;
// 	            } else {
// 	               System.out.println("ID의 형식이 잘못되었습니다.\n영문 대소문자 또는 숫자로 시작하는 아이디, 길이는 4~15자만 가능합니다.");
// 	               continue;
// 	            }
// 	         } catch(Exception e4){
// 	            System.out.println("ID의 형식이 잘못되었습니다.");
// 	            continue;
// 	         }
// 	      }
// 	   }
    	
    	String memName = tfName.getText();
    	if(memName.isEmpty()) {
    		alert("입력오류","회원 이름을 입력하세요.");
    		tfName.requestFocus();
    		return;
    	}
    	
    	String memBirth = tfBirth.getText();
    	if(memBirth.isEmpty()) {
    		alert("입력오류","회원 생년월일을 입력하세요.");
    		tfBirth.requestFocus();
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
    	
    	SendExample();
    	infoMsg("확인", "인증번호 전송이 완료되었습니다.");
    }

    @FXML
    void signUp2Clicked(ActionEvent event) throws IOException {
    	String memName = tfName.getText();
    	if(memName.isEmpty()) {
    		alert("입력오류","회원 이름을 입력하세요.");
    		tfName.requestFocus();
    		return;
    	}
    	
    	if(!RegEx.checkMem_name(memName)) {
    		alert("입력오류","이름은 한글 2~4글자만 가능합니다.");
    		tfName.requestFocus();
    		return;
    	}
    	
    	String memBirth = tfBirth.getText();
    	if(memBirth.isEmpty()) {
    		alert("입력오류","회원 생년월일을 입력하세요.");
    		tfBirth.requestFocus();
    		return;
    	}
    	if(!RegEx.checkMem_bir(memBirth)) {
    		alert("입력오류","생년월일을 다시입력해주세요 '-' 포함.");
    		tfBirth.requestFocus();
    		return;
    	}
    	
    	String memTel1 = tfTel1.getText();
    	if(memTel1.isEmpty()) {
    		alert("입력오류","회원 전화번호를 입력하세요.");
    		tfTel1.requestFocus();
    		return;
    	}
    	if(!RegEx.checkMem_num1(memTel1)) {
    		alert("입력오류","회원 전화번호를 다시 입력해주세요1");
    		tfTel1.requestFocus();
    		return;
    	}
    	
    	String memTel2 = tfTel2.getText();
    	if(memTel2.isEmpty()) {
    		alert("입력오류","회원 전화번호를 입력하세요.");
    		tfTel2.requestFocus();
    		return;
    	}
    	if(!RegEx.checkMem_num2(memTel2)) {
    		alert("입력오류","회원 전화번호를 다시 입력해주세요2");
    		tfTel2.requestFocus();
    		return;
    	}
    	
    	String memTel3 = tfTel3.getText();
    	if(memTel3.isEmpty()) {
    		alert("입력오류","회원 전화번호를 입력하세요.");
    		tfTel3.requestFocus();
    		return;
    	}
    	if(!RegEx.checkMem_num3(memTel3)) {
    		alert("입력오류","회원 전화번호를 다시 입력해주세요3");
    		tfTel3.requestFocus();
    		return;
    	}
    	
    	if(tfConfirm.getText().equals(confirmPw2)) {
    		infoMsg("확인","본인 인증이 완료되었습니다.");
    		
    		Parent parent = null;
    		
    		try {
    			parent = FXMLLoader.load(getClass().getResource("projectSignUp2.fxml"));
    			signUpRoot.getScene().setRoot(parent);
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
    		
    		TextField txtName = (TextField) parent.lookup("#tfName");
    		TextField txtBirth = (TextField) parent.lookup("#tfBirth");
    		TextField txtTel1 = (TextField) parent.lookup("#tfTel1");
    		TextField txtTel2 = (TextField) parent.lookup("#tfTel2");
    		TextField txtTel3 = (TextField) parent.lookup("#tfTel3");
    		
    		txtName.setText(tfName.getText());
    		txtBirth.setText(tfBirth.getText());
    		txtTel1.setText(tfTel1.getText());
    		txtTel2.setText(tfTel2.getText());
    		txtTel3.setText(tfTel3.getText());
    		
    	}else {
    		alert("입력오류","인증번호를 다시 확인해주세요.");
    		tfConfirm.requestFocus();
    		return;
    	}
    }

    private ISignUpService signUpService;
 // 인증번호 랜덤 숫자 발생
    private int confirmPw1 = (int)(Math.random()*900000+100000);
	private String confirmPw2 = Integer.toString(confirmPw1);
    
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

    public void SendExample() {
    	
		/*
		 * 서버에서 받은 API_KEY, API_SECRET를 입력해주세요.
		 */
		String api_key = "NCSHRPFYXCKYU8K3";
		String api_secret = "NKXUGE3YHCWTLSBVKCDZLANZISBIGHPS";
		Coolsms coolsms = new Coolsms(api_key, api_secret);
	
		/*
		 * Parameters
		 * 관련정보 : http://www.coolsms.co.kr/SDK_Java_API_Reference_ko#toc-0
		 */
		HashMap<String, String> set = new HashMap<String, String>();
		set.put("to", tfTel1.getText()+tfTel2.getText()+tfTel3.getText()); // 수신번호
		
		// 10월 16일 이후로 발신번호 사전등록제로 인해 등록된 발신번호로만 문자를 보내실 수 있습니다.
		set.put("from", "01048010162"); // 발신번호
		set.put("text", confirmPw2); // 문자내용
		set.put("type", "sms"); // 문자 타입

		JSONObject result = coolsms.send(set); // 보내기&전송결과받기
		if ((Boolean) result.get("status") == true) {
			// 메시지 보내기 성공 및 전송결과 출력
			System.out.println("성공");			
			System.out.println(result.get("group_id")); // 그룹아이디
			System.out.println(result.get("result_code")); // 결과코드
			System.out.println(result.get("result_message"));  // 결과메시지
			System.out.println(result.get("success_count")); // 성공갯수
			System.out.println(result.get("error_count"));  // 발송실패 메시지 수
		} else {
			// 메시지 보내기 실패
			System.out.println("실패");
			System.out.println(result.get("code")); // REST API 에러코드
			System.out.println(result.get("message")); // 에러메시지
		}		
	}
}


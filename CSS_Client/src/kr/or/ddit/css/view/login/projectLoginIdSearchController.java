package kr.or.ddit.css.view.login;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import kr.or.ddit.css.service.login.ILoginService;
import kr.or.ddit.css.session.LoginSession;
import kr.or.ddit.css.view.signUp.RegEx;

public class projectLoginIdSearchController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane idCheckPane;

    @FXML
    private TextField nameArea;

    @FXML
    private TextField emailArea;

    @FXML
    private TextField emailCheckArea;
    
    private ILoginService loginService;
    
    private static String mem_id = "";
    
    @FXML
    void emailCheckClicked(ActionEvent event) throws IOException{
    	
    	String mem_name = nameArea.getText();
    	if(mem_name.isEmpty()) {
    		alert("입력오류","회원 이름을 입력하세요.");
    		nameArea.requestFocus();
    		return;
    	}
    	if(!RegEx.checkMem_name(mem_name)) {
    		alert("입력오류","이름은 한글 2~4글자만 가능합니다.");
    		nameArea.requestFocus();
    		return;
    	}
    	
    	String mem_email = emailArea.getText();
    	if(mem_email.isEmpty()) {
    		alert("입력오류","회원 이메일을 입력하세요.");
    		emailArea.requestFocus();
    		return;
    	}
    	if(!RegEx.checkMem_email(mem_email)) {
    		alert("입력오류","이메일을 다시 입력하세요. '@'포함");
    		emailArea.requestFocus();
    		return;
    	}
    	HashMap<String, String> param = new HashMap<String, String>();
    	
    	param.put("memName", mem_name);
    	param.put("memEmail", mem_email);
    	
    	try {
			mem_id = loginService.idSearch(param);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	if(mem_id==null) {
    		alert("입력오류","회원 정보에 맞는 아이디가 없습니다.");
    		nameArea.clear();
    		emailArea.clear();
    		nameArea.requestFocus();
    	}else{
    		infoMsg("확인", "회원 아이디를 해당 메일로 전송하였습니다.");
    		selectMail(); // 일반메일 전송 실행
    		AnchorPane pane = FXMLLoader.load(getClass().getResource("projectLogin.fxml"));
        	idCheckPane.getScene().setRoot(pane);
    	}
    	
    }
    
    @FXML
    void loginClicked(ActionEvent event) throws IOException {	// 로그인 화면으로 이동 메서드
    	AnchorPane pane = FXMLLoader.load(getClass().getResource("projectLogin.fxml"));
    	idCheckPane.getScene().setRoot(pane);
    }
    
    @FXML
    void initialize() {
    	Registry reg;
		try {
			reg = LocateRegistry.getRegistry("localhost",8899);
			loginService = (ILoginService) reg.lookup("login");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
    }
    
    public static void selectMail(){
		String title = ""; //제목
		String content = ""; //내용
		
		title = "[대덕인재개발원] CSS팀 요청하신 ID입니다.";
		content = "회원님의 아이디는 : "+mem_id+" 입니다.\n감사합니다.";
		try {
			sendMail(title,content); //제목,내용을 매개변수로 sendMail메서드 호출 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
    private static void sendMail(String title, String content) throws Exception {
		// 발신자의 계정,비밀번호 설정
		final String FROM = "whrqkf581@naver.com"; // SMTP설정한 계정 이메일
		final String PASSWORD = "Dudqk581!"; // SMTP설정한 계정 패스워드
		final String FROM_NAME = "css 고객지원팀"; // 송신자 이름
		String userName = "css 고객님"; //수신자 이름
		
		// SMTP서버 설정
		String host = "smtp.naver.com"; //Gmail은 smtp.gmail.com
		int port = 587; //네이버 SMTP port번호 587 , Gmail SMTP port번호 465
		Properties props = new Properties();
		props.put("mail.smtp.host", host); 
		props.put("mail.smtp.port", port); 
		props.put("mail.smtp.auth", "true");
		
		// 사용자 정보 인증 구간
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() { 
			protected PasswordAuthentication getPasswordAuthentication() { 
					return new PasswordAuthentication(FROM, PASSWORD); //SMTP설정한 계정 정보를 인자값으로 넘김
			}
		}); 
		
		/*
		 * 위 부분은 고정.. 계정 정보, SMTP서버 설정만 변경하면됨 
		 */
		
		MimeMessage message = new MimeMessage(session); //위에서 인증된 session으로 message객체 생성
		
		try {
			message.setFrom(new InternetAddress(FROM,FROM_NAME)); //송신측 정보 
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(FROM,userName)); //수신측 정보  
			message.setSentDate(new Date());
			message.setSubject(title); //제목 설정
			message.setText(content); //본문 설정
			Transport.send(message); // 전송
			System.out.println("메일 전송이 완료되었습니다."); 
		} catch (MessagingException e) {
			e.printStackTrace();
			System.out.println("메일 전송이 실패했습니다.");
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

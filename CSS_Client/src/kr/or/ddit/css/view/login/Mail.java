package kr.or.ddit.css.view.login;

import java.util.*;

import javax.mail.*;
import javax.mail.internet.*;

public class Mail {
	/**
	 * 메일 전송 테스트를 위한 메서드
	 * 실제 서비스에서는 사용하지 않음
	 */
	public static void main(String[] args){
		selectMail(); // 일반메일 전송 실행
	}
	/**
	 * 메일 유형을 선택하는 메서드
	 * @param String type 
	 * 매개변수로 넘어오는 값에 따라 다른 타입을 실행
	 */
	public static void selectMail(){
		String title = ""; //제목
		String content = ""; //내용
		//일반타입 실행
//		if(type.equals("u")){
			title = "[대덕인재개발원] CSS팀 ID 임시비밀번호입니다.";
			content = "비밀번호 인증 번호";
			try {
				sendMail(title,content); //구분자 u와 제목,내용을 매개변수로 sendMail메서드 호출 
			} catch (Exception e) {
				e.printStackTrace();
			}
//		 }
		
		//첨부파일 타입 실행
//		if(type.equals("f")){
//			System.out.println("첨부파일 전송을 선택하셨습니다.");
//			System.out.println("제목을 입력해주세요.");
//			title = sc.nextLine();
//			System.out.println("내용을 입력해주세요.");
//			content = sc.nextLine();
//			try {
//				sendMail("f",title,content); //구분자 p와 제목,내용을 매개변수로 sendMail메서드 호출
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
	}
	/**
	 * 메일 전송 메서드
	 * @param String type, String title, String content
	 * 타입,제목,내용을 받음
	 */
	
	private static void sendMail(String title, String content) throws Exception {
		// 발신자의 계정,비밀번호 설정
		final String FROM = "whrqkf581@naver.com"; // SMTP설정한 계정 이메일
		final String PASSWORD = "dudqk1!!"; // SMTP설정한 계정 패스워드
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
		
		//일반 메일 타입 전송부
//		if(type.equals("u")){
			try {
//				System.out.println("전송중...");
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
//		}
//		//첨부파일 메일 타입 전송부
//		else{ 
//			Scanner sc = new Scanner(System.in);
//		    System.out.println("파일1 이름을 입력해주세요.");
//			String file1Name = sc.nextLine();
//			System.out.println("파일2 이름을 입력해주세요.");
//			String file2Name = sc.nextLine();
//			String classPath = MailModule.class.getResource("").getPath(); //현재 클래스의 경로를 읽어옴
//			// 현재클래스의 경로의 패키지 + 상위의 files라는 폴더경로 + 입력한 파일이름으로 file 객체 생성			
//			File file1 = new File(classPath+"../files/"+file1Name);  
//			File file2 = new File(classPath+"../files/"+file2Name);
//			if(!file1.exists()&&!file2.exists()){ //존재하지 않는 파일이면 메서드 종료
//				System.out.println("존재하지 않는 파일입니다.");
//				return;
//			}else{ //존재하는 파일이면 전송 실행
//				try {
//					FileDataSource fds1 = new FileDataSource(file1);
//					FileDataSource fds2 = new FileDataSource(file2);
//					Multipart multipart = new MimeMultipart();
//					BodyPart contentsBodyPart = new MimeBodyPart();
//					System.out.println("전송중...");
//					message.setFrom(new InternetAddress(FROM,FROM_NAME)); 
//					message.addRecipient(Message.RecipientType.TO, new InternetAddress(FROM,userName));  
//					message.setSentDate(new Date());
//					message.setSubject(title);
//					contentsBodyPart.setText(content); // 일반메일 전송과는 달리 첨부파일 전송은 메일 내용을 이런식으로 세팅
//					//메일 내용을 bodyPart에 설정
//					multipart.addBodyPart(contentsBodyPart);
//					//첫번째 파일을 bodyPart에 설정
//					contentsBodyPart = new MimeBodyPart();
//					contentsBodyPart.setDataHandler(new DataHandler(fds1));
//					contentsBodyPart.setFileName(file1Name); // 파일 이름 세팅
//					multipart.addBodyPart(contentsBodyPart); // 첨부파일1 세팅
//					//두번째 파일(한글이름 파일)을 bodyPart에 설정
//					contentsBodyPart = new MimeBodyPart();
//					contentsBodyPart.setDataHandler(new DataHandler(fds2));
//					String fileName2 = fds2.getName(); //한글파일명은 영문으로 인코딩해야 첨부가 된다.
//					fileName2 = new String(fileName2.getBytes("KSC5601"), "8859_1"); //인코딩 과정
//					contentsBodyPart.setFileName(fileName2); // 파일 이름 세팅
//					multipart.addBodyPart(contentsBodyPart); // 첨부파일2 세팅
//				    message.setContent(multipart); // 모든 첨부파일 최종 세팅
//					Transport.send(message);  // 전송
//					System.out.println("메일 전송이 완료되었습니다."); 
//				} catch (MessagingException e) {
//					e.printStackTrace();
//					System.out.println("메일 전송이 실패했습니다.");
//				}
//			}
//		}
	}
	
}
package kr.or.ddit.css.main;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.application.Platform;
import kr.or.ddit.css.service.carsharing.CarSharingServiceImpl;
import kr.or.ddit.css.service.carsharing.ICarSharingService;
import kr.or.ddit.css.serivce.insurance.IInsuranceService;
import kr.or.ddit.css.serivce.insurance.InsuranceServiceImpl;
import kr.or.ddit.css.service.QnA.IQnAService;
import kr.or.ddit.css.service.QnA.QnAServiceImpl;
import kr.or.ddit.css.service.blacklist.BlacklistServiceImpl;
import kr.or.ddit.css.service.blacklist.IBlacklistService;
import kr.or.ddit.css.service.card.CardServiceImpl;
import kr.or.ddit.css.service.card.ICardService;
import kr.or.ddit.css.service.carpairing.CarPairingInsertServiceImpl;
import kr.or.ddit.css.service.carpairing.CarPairingUseServiceImpl;
import kr.or.ddit.css.service.carpairing.ICarPairingInsertService;
import kr.or.ddit.css.service.carpairing.ICarPairingUseService;
import kr.or.ddit.css.service.community.CommunityServiceImpl;
import kr.or.ddit.css.service.community.ICommunityService;
import kr.or.ddit.css.service.license.ILicenseService;
import kr.or.ddit.css.service.license.LicenseServiceImpl;
import kr.or.ddit.css.service.login.ILoginService;
import kr.or.ddit.css.service.login.LoginServiceImpl;
import kr.or.ddit.css.service.notice.INoticeService;
import kr.or.ddit.css.service.notice.NoticeServiceImpl;
import kr.or.ddit.css.service.payment.IPaymentService;
import kr.or.ddit.css.service.payment.PaymentServiceImpl;
import kr.or.ddit.css.service.rent.IRentService;
import kr.or.ddit.css.service.rent.RentServiceImpl;
import kr.or.ddit.css.service.signUp.ISignUpService;
import kr.or.ddit.css.service.signUp.SignUpServiceImpl;

public class CSSMain {
	public static void main(String[] args) {
		try {
			ISignUpService signUp = SignUpServiceImpl.getInstance();
			ILoginService login = LoginServiceImpl.getInstance();
			ICommunityService community = CommunityServiceImpl.getInstance();
			INoticeService notice = NoticeServiceImpl.getInstance();
			ICarSharingService carSharing = CarSharingServiceImpl.getInstance();
			IBlacklistService blacklist = BlacklistServiceImpl.getInstance();
			ICarPairingInsertService carpairingInsert = CarPairingInsertServiceImpl.getInstance();
			ICarPairingUseService carpairingUse = CarPairingUseServiceImpl.getInstance();
			ILicenseService license = LicenseServiceImpl.getInstance();
			IInsuranceService insurance = InsuranceServiceImpl.getInstance();
			ICardService card = CardServiceImpl.getInstance();
			IQnAService QnA = QnAServiceImpl.getInstance();
			IPaymentService payment = PaymentServiceImpl.getInstance();
			IRentService rent = RentServiceImpl.getInstance();
			Registry reg = LocateRegistry.createRegistry(8899);
			
			new Chat().serverStart();
			
			reg.rebind("signUp" , signUp);
			reg.rebind("login" , login);
			reg.rebind("community",community);
			reg.rebind("notice", notice);
			reg.rebind("carSharing", carSharing);
			reg.rebind("blacklist", blacklist);
			reg.rebind("carpairingInsert", carpairingInsert);
			reg.rebind("carpairingUse", carpairingUse);
			reg.rebind("license", license);
			reg.rebind("insurance", insurance);
			reg.rebind("card", card);
			reg.rebind("QnA", QnA);
			reg.rebind("payment", payment);
			reg.rebind("rent", rent);
			
			System.out.println("Server Start!!");
		} catch (Exception e) {
			System.out.println("서버연결 실패!");
			e.printStackTrace();
		}
	}
}
//
//class ChatVO{
//	private String id;
//	private Socket socket;
//	
//	public ChatVO(String id, Socket socket) {
//		super();
//		this.id = id;
//		this.socket = socket;
//	}
//
//	public String getId() {
//		return id;
//	}
//
//	public void setId(String id) {
//		this.id = id;
//	}
//
//	public Socket getSocket() {
//		return socket;
//	}
//
//	public void setSocket(Socket socket) {
//		this.socket = socket;
//	}
//	
//}

class Chat{
	private List<Socket> socketList = Collections.synchronizedList(new ArrayList<Socket>()) ;
	
	//private Map<String, Socket> userMap = Collections.synchronizedMap(new HashMap<String, Socket>()) ;
    
    ServerSocket mainServerSocket= null;
    
    public void serverStart() {
    	try {
    		mainServerSocket = new ServerSocket();
			mainServerSocket.bind(new InetSocketAddress(InetAddress.getLocalHost(), 8989));
			
			ConnectThread connectThread = new ConnectThread(mainServerSocket);
			connectThread.setDaemon(true);
			connectThread.start();
		} catch (Exception e) {
			// TODO: handle exception
		}
    }
    
    class ConnectThread extends Thread {
    	ServerSocket mainServerSocket = null;
 
        ConnectThread(ServerSocket mainServerSocket) {
            this.mainServerSocket = mainServerSocket;
        }
 
        @Override
        public void run() {
            try {
                while (true) {
                    Socket socket = mainServerSocket.accept();
                    
                    //outputField.setText("사용자 접속!");
 
                    socketList.add(socket);
//                    Platform.runLater(() -> {
//                        //lblCount.setText(socketList.size() + " 명");
//                    });
 					System.out.println(socketList.size() + " 명");
                    // 접속한 사용자가 처음 보낸 메시지 받기 ( 사용자 이름 받기)
                    InputStream inputStream = socket.getInputStream();
                    byte[] byteArray = new byte[256];
                    int size = inputStream.read(byteArray);
 
                    String readMessage = new String(byteArray, 0, size, "UTF-8");
//                    Platform.runLater(() -> {
//                        //taOutputMsg.appendText(readMessage + "님 접속\n");
//                    });
                    
//                    userMap.put(readMessage, socket);
//                    System.out.println(userMap.size() + " 명");
                    
                    System.out.println(readMessage + "님 접속\n");
                    
                    //--
                    
                    String sendMessage = readMessage +"님이 접속하셨습니다.";
                    byteArray = sendMessage.getBytes("UTF-8");
                    
                    for (int i = 0; i < socketList.size(); i++) {
                    	if (socket != socketList.get(i)){
	                        OutputStream outputStream = socketList.get(i).getOutputStream();
	                        outputStream.write(byteArray);
	                        outputStream.flush();
                    	}
                    }
                    
                    
//                    // 관리자에게만 클라이언트의 접속 여부 전송
//                    for(String user : userMap.keySet()) {
//                    	if(user.equals("admin")) {
//                    		OutputStream outputStream = userMap.get(user).getOutputStream();
// 	                        outputStream.write(byteArray);
// 	                        outputStream.flush();
//                    	}
//                    }
//                    
                    //--
                    
                    ServerReader serverReader = new ServerReader(socket);
                    serverReader.start();
                }
            } catch (Exception e) {
            	//System.out.println("==> " + mainServerSocket.isClosed());
            	if(!mainServerSocket.isClosed())
            		e.printStackTrace();
            }
        }
    }
    class ServerReader extends Thread {
        Socket socket = null;
 
        ServerReader(Socket socket) {
            this.socket = socket;
        }
 
        @Override
        public void run() {
            try {
                while (true) {
                    InputStream inputStream = socket.getInputStream();
                    byte[] byteArray = new byte[256];
                    int size = inputStream.read(byteArray);
                    //System.out.println("<<<<<============>>>> size = " + size);
                    if (size == -1) { // 클라이언트이 '접속종료'버튼으로 접속을 끊었을 때.
                    	
                        for (int i = 0; i < socketList.size(); i++) {
                            if (socket == socketList.get(i)) {
                            	socketList.remove(i);
                            	break;
                            } 
                        }
//                    	for(String user : userMap.keySet()) {
//                    		if(socket == userMap.get(user)) {
//                    			userMap.remove(user);
//                    			break;
//                    		}
//                    	}
                    	
//                        Platform.runLater(() -> {
//                        	//lblCount.setText(socketList.size() + " 명- while");
//                        });
                        break;
                    }
 
                    String readMessage = new String(byteArray, 0, size, "UTF-8");
                    //System.out.println("li.size() = " + socketList.size());
                    byteArray = readMessage.getBytes("UTF-8");
                    //outputField.setText(readMessage);
                    
                    //--------------------------
                    for (int i = 0; i < socketList.size(); i++) {
							OutputStream outputStream = socketList.get(i).getOutputStream();
							outputStream.write(byteArray);
							//System.out.println("byteArray(Server) => " + byteArray);
							outputStream.flush();
                    }
                    //---------------------------
                    
                }
            } catch (Exception e) {
            	// 클라이언트 프로그램이 종료되었을 때.
                for (int i = 0; i < socketList.size(); i++) {
                    if (socket == socketList.get(i)) {
                    	socketList.remove(i);
                    	break;
                    } 
                }
//                Platform.runLater(() -> {
//                	//lblCount.setText(socketList.size() + " 명");
//                });
                System.out.println(socketList.size() + " 명");
            }
        }
    }
    
}